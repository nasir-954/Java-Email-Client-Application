package jeep.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.text.ParseException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.text.MaskFormatter;

import jeep.data.beans.Contact;
import jeep.data.bundles.MessageController;
import jeep.data.mysql.DatabaseController;
import jeep.gui.focus.MyFocusTravelPolicy;
import jeep.gui.regex.RegexFormatter;

/**
 * This panel is used for displaying contact information, updating contact
 * information and creating new contacts.
 * 
 * @author Natacha Gabbamonte 0932340
 * 
 */
public class ContactPanel extends JPanel {

	private static final long serialVersionUID = 98431495187963243L;

	private Logger logger = null;

	private DatabaseController dbController = null;
	private ContactTableModel contactTableModel = null;

	private JTable table = null;
	private JScrollPane scrollPan = null;

	private MessageController messages = null;

	JLabel headingLabel = null;

	// SOUTH PANELS
	private JPanel editContactPanel = null;
	JEditorPane commentArea = null;
	JTextField firstNameField = null;
	JTextField lastNameField = null;
	JFormattedTextField emailField = null;
	JFormattedTextField phoneNumberField = null;
	JTextField addressField = null;

	JButton saveButton = null;
	JButton deleteButton = null;
	JButton newButton = null;

	JLabel errorLabel = null;

	private boolean isNewContact = true;

	private MyFocusTravelPolicy focusPolicy = null;

	/**
	 * Constructor
	 * 
	 * @param mailApp
	 *            The MailApp
	 */
	public ContactPanel(MailApp mailApp) {
		dbController = mailApp.getDbController();
		contactTableModel = mailApp.getContactTableModel();
		messages = mailApp.getMessageController();
		logger = mailApp.getLogger();

		this.setLayout(new BorderLayout());
		getTable();
		getDisplayPanel();
		Font font = new Font("Times New Roman", Font.BOLD, 20);
		headingLabel = new JLabel(" ", JLabel.CENTER);
		headingLabel.setFont(font);
		headingLabel.setText(messages.getString("contact_heading"));
		headingLabel.setIcon(MailApp.createImageIcon("images/contacts.png"));
		add(headingLabel, BorderLayout.NORTH);
		add(scrollPan, BorderLayout.CENTER);
		add(editContactPanel, BorderLayout.SOUTH);

		// Setting the Focus Travel Policy.
		Vector<Component> order = new Vector<Component>(10);
		order.add(table);
		order.add(firstNameField);
		order.add(lastNameField);
		order.add(emailField);
		order.add(phoneNumberField);
		order.add(addressField);
		order.add(commentArea);
		order.add(saveButton);
		order.add(deleteButton);
		order.add(newButton);
		focusPolicy = new MyFocusTravelPolicy(order);
		this.setFocusTraversalPolicy(focusPolicy);
	}

	/**
	 * Prints the contact information for the currently selected contact.
	 * 
	 * @return Whether the printing was successful.
	 */
	public boolean printPage() {
		JEditorPane printingPane = new JEditorPane();
		try {
			String text = "";
			text = messages.getString("contact_contact") + "\n\n"
					+ messages.getString("contact_first") + " "
					+ firstNameField.getText() + "\n"
					+ messages.getString("contact_last") + " "
					+ lastNameField.getText() + "\n"
					+ messages.getString("contact_email") + " "
					+ emailField.getText() + "\n"
					+ messages.getString("contact_phoneNumber") + " "
					+ phoneNumberField.getText() + "\n"
					+ messages.getString("contact_address") + " "
					+ addressField.getText() + "\n"
					+ messages.getString("contact_comments") + "\n"
					+ commentArea.getText();

			printingPane.setText(text);
			printingPane.print(null, null);
			return true;
		} catch (PrinterException e) {
			logger.log(Level.WARNING,
					"Was unable to print: " + printingPane.getText(), e);
			return false;
		}
	}

	/**
	 * Creates the contact table and populates it from the model.
	 */
	private void getTable() {

		if (scrollPan != null)
			remove(scrollPan);

		table = new JTable(contactTableModel);
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setPreferredScrollableViewportSize(new Dimension(600, 200));
		table.setFillsViewportHeight(true);
		table.getTableHeader().setReorderingAllowed(false);

		// Create the scroll pane and add the table to it.
		scrollPan = new JScrollPane(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Contact contact = contactTableModel.getContact(table
						.getSelectedRow());
				setDisplayPanel(contact);
			}
		});
	}

	/**
	 * Creates the display panel that will be used to display a contact's
	 * information.
	 */
	private void getDisplayPanel() {
		editContactPanel = new JPanel();
		editContactPanel.setLayout(new GridBagLayout());

		JLabel label;

		// First name label & field
		label = new JLabel(messages.getString("contact_first"));
		firstNameField = new JTextField(30);
		firstNameField.setDocument(new DocumentLimit(50));
		editContactPanel.add(label, MailApp.makeConstraints(0, 0, 1, 1,
				new int[] { 1, 1, 1, 1 }, 1, 1, GridBagConstraints.HORIZONTAL));
		editContactPanel.add(firstNameField, MailApp.makeConstraints(1, 0, 3,
				1, new int[] { 1, 1, 1, 1 }, 1, 1,
				GridBagConstraints.HORIZONTAL));

		// Last name label & field
		label = new JLabel(messages.getString("contact_last"));
		lastNameField = new JTextField(30);
		lastNameField.setDocument(new DocumentLimit(50));
		editContactPanel.add(label, MailApp.makeConstraints(0, 1, 1, 1,
				new int[] { 1, 1, 1, 1 }, 1, 1, GridBagConstraints.HORIZONTAL));

		editContactPanel.add(lastNameField, MailApp.makeConstraints(1, 1, 3, 1,
				new int[] { 1, 1, 1, 1 }, 1, 1, GridBagConstraints.HORIZONTAL));

		// Email & field
		label = new JLabel(messages.getString("contact_email"));
		String emailRegEx = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
		emailField = new JFormattedTextField(new RegexFormatter(emailRegEx));
		emailField.setColumns(30);
		emailField.setDocument(new DocumentLimit(50));
		editContactPanel.add(label, MailApp.makeConstraints(0, 2, 1, 1,
				new int[] { 1, 1, 1, 1 }, 1, 1, GridBagConstraints.HORIZONTAL));

		editContactPanel.add(emailField, MailApp.makeConstraints(1, 2, 3, 1,
				new int[] { 1, 1, 1, 1 }, 1, 1, GridBagConstraints.HORIZONTAL));

		// Phone number label & field
		label = new JLabel(messages.getString("contact_phoneNumber"));
		String phoneMask = "###-###-####";
		phoneNumberField = new JFormattedTextField(createFormatter(phoneMask));
		phoneNumberField.setColumns(30);
		editContactPanel.add(label, MailApp.makeConstraints(0, 3, 1, 1,
				new int[] { 1, 1, 1, 1 }, 1, 1, GridBagConstraints.HORIZONTAL));

		editContactPanel.add(phoneNumberField, MailApp.makeConstraints(1, 3, 3,
				1, new int[] { 1, 1, 1, 1 }, 1, 1,
				GridBagConstraints.HORIZONTAL));

		// Address label & field
		label = new JLabel(messages.getString("contact_address"));
		addressField = new JTextField(30);
		addressField.setDocument(new DocumentLimit(50));
		editContactPanel.add(label, MailApp.makeConstraints(0, 4, 1, 1,
				new int[] { 1, 1, 1, 1 }, 1, 1, GridBagConstraints.HORIZONTAL));

		editContactPanel.add(addressField, MailApp.makeConstraints(1, 4, 3, 1,
				new int[] { 1, 1, 1, 1 }, 1, 1, GridBagConstraints.HORIZONTAL));

		// Comments field
		commentArea = new JEditorPane();
		commentArea.setPreferredSize(new Dimension(500, 100));
		commentArea.setDocument(new DocumentLimit(500));

		// Put it inside a ScrollPane
		JScrollPane scroll = new JScrollPane(commentArea);

		editContactPanel.add(scroll, MailApp.makeConstraints(0, 5, 4, 1,
				new int[] { 1, 1, 1, 1 }, 1, 1, GridBagConstraints.BOTH));

		// The Buttons panel
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridBagLayout());

		// Save button
		saveButton = new JButton(messages.getString("contact_save"));
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row != -1) {
					Contact contact = contactTableModel.getContact(row);
					if (contact != null) {
						if (validFields()) {
							contact.setAddress(addressField.getText());
							contact.setComments(commentArea.getText());
							contact.setEmail(emailField.getText());
							contact.setFirstName(firstNameField.getText());
							contact.setLastName(lastNameField.getText());
							contact.setPhoneNumber(phoneNumberField.getText());
							dbController.updateContact(contact);
							contactTableModel.loadData(dbController
									.getContacts());
							setDisplayPanel(new Contact());
							isNewContact = true;
						}
					}
				} else if (isNewContact) {
					if (validFields()) {
						Contact contact = new Contact();
						contact.setAddress(addressField.getText());
						contact.setComments(commentArea.getText());
						contact.setEmail(emailField.getText());
						contact.setFirstName(firstNameField.getText());
						contact.setLastName(lastNameField.getText());
						contact.setPhoneNumber(phoneNumberField.getText());
						dbController.insertContact(contact);
						contactTableModel.addNewContact(contact);
						setDisplayPanel(new Contact());
					}
				}
			}
		});

		buttons.add(saveButton, MailApp.makeConstraints(0, 0, 1, 1, new int[] {
				1, 1, 1, 1 }, 1, 1, GridBagConstraints.HORIZONTAL));

		// Delete button
		deleteButton = new JButton(messages.getString("contact_delete"));
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row != -1) {
					Contact contact = contactTableModel.getContact(row);
					if (contact != null) {
						dbController.deleteContact(contact.getContactId());
						contactTableModel.loadData(dbController.getContacts());
						setDisplayPanel(new Contact());
						isNewContact = true;
					}
				} else if (isNewContact)
					setDisplayPanel(new Contact());
			}

		});
		buttons.add(deleteButton, MailApp.makeConstraints(1, 0, 1, 1,
				new int[] { 1, 1, 1, 1 }, 1, 1, GridBagConstraints.HORIZONTAL));

		// New button
		newButton = new JButton(messages.getString("contact_new"));
		newButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				table.clearSelection();
				setDisplayPanel(new Contact());
				isNewContact = true;
			}

		});
		buttons.add(newButton, MailApp.makeConstraints(2, 0, 1, 1, new int[] {
				1, 1, 1, 1 }, 1, 1, GridBagConstraints.HORIZONTAL));

		editContactPanel.add(buttons, MailApp.makeConstraints(0, 6, 4, 1,
				new int[] { 1, 1, 1, 1 }, 1, 1, GridBagConstraints.BOTH));

		// The error message label.
		errorLabel = new JLabel(" ", JLabel.CENTER);
		errorLabel.setForeground(Color.RED);

		editContactPanel.add(errorLabel, MailApp.makeConstraints(0, 7, 4, 1,
				new int[] { 1, 1, 1, 1 }, 1, 1, GridBagConstraints.HORIZONTAL));
	}

	/*
	 * Checks to make sure that the fields have valid arguments.
	 */
	private boolean validFields() {
		String errorMessage = null;

		String firstName = firstNameField.getText();
		String lastName = lastNameField.getText();
		String email = emailField.getText();
		if (firstName.isEmpty())
			errorMessage = messages.getString("contact_error_first");
		else if (lastName.isEmpty())
			errorMessage = messages.getString("contact_error_last");
		else if (email.isEmpty())
			errorMessage = messages.getString("contact_error_email");
		if (errorMessage == null) {
			errorLabel.setText(" ");
			return true;
		} else {
			errorLabel.setText(errorMessage);
			return false;
		}
	}

	/*
	 * Sets the display panel to the given values.
	 */
	private void setDisplayPanel(Contact contact) {
		if (contact != null) {
			firstNameField.setText(contact.getFirstName());
			lastNameField.setText(contact.getLastName());
			emailField.setText(contact.getEmail());
			phoneNumberField.setText(contact.getPhoneNumber());
			addressField.setText(contact.getAddress());
			commentArea.setText(contact.getComments());
		}
	}

	/**
	 * This method returns a MaskFormatter for the JFormattedTextField
	 * 
	 * @return javax.swing.MaskFormatter
	 */
	private MaskFormatter createFormatter(String s) {
		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter(s);
		} catch (ParseException exc) {
			logger.log(Level.SEVERE, "Formatter is bad: " + s, exc);
			System.exit(-1);
		}
		return formatter;
	}

}
