package jeep.data.beans;

/**
 * MailConfig is the java bean that holds the configuration values for the Email
 * program.
 * 
 * @author Natacha Gabbamonte 0932340
 * 
 */
public class MailConfig {

	private String username;
	private String emailAddress;
	private String urlPOP3Server;
	private String urlSMTPServer;
	private String urlMySQLServer;

	private String portPOP3Server;
	private String portSMTPServer;
	private String portMySQLServer;

	private String databaseMySQL;

	private Boolean isGmailAccount;
	private Boolean isSMTPAuth;

	private String userNamePOP3;
	private String passwordPOP3;
	private String userNameMySQL;
	private String passwordMySQL;

	private String language;

	/**
	 * Default Constructor for MailConfig.
	 */
	public MailConfig() {
		super();
		this.username = "";
		this.emailAddress = "";
		this.urlPOP3Server = "";
		this.urlSMTPServer = "";
		this.urlMySQLServer = "";
		this.portPOP3Server = "";
		this.portSMTPServer = "";
		this.portMySQLServer = "";
		this.databaseMySQL = "";
		this.userNamePOP3 = "";
		this.passwordPOP3 = "";
		this.userNameMySQL = "";
		this.passwordMySQL = "";
		this.language = "en-CA";

		setIsGmailAccount(false);
		setIsSMTPAuth(false);
	}

	/**
	 * Returns a String representation of the MailConfig object.
	 */
	@Override
	public String toString() {
		return "MailConfig [username=" + username + ", emailAddress="
				+ emailAddress + ", urlPOP3Server=" + urlPOP3Server
				+ ", urlSMTPServer=" + urlSMTPServer + ", urlMySQLServer="
				+ urlMySQLServer + ", portPOP3Server=" + portPOP3Server
				+ ", portSMTPServer=" + portSMTPServer + ", portMySQLServer="
				+ portMySQLServer + ", databaseMySQL=" + databaseMySQL
				+ ", isGmailAccount=" + isGmailAccount + ", isSMTPAuth="
				+ isSMTPAuth + ", userNamePOP3=" + userNamePOP3
				+ ", passwordPOP3=" + passwordPOP3 + ", userNameMySQL="
				+ userNameMySQL + ", passwordMySQL=" + passwordMySQL
				+ ", language=" + language + "]";
	}

	/**
	 * Returns the user's name.
	 * 
	 * @return The user's name.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the user's name.
	 * 
	 * @param username
	 *            The new user's name.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Returns the user's email address.
	 * 
	 * @return The user's email address.
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * Sets the user's email address.
	 * 
	 * @param emailAddress
	 *            The user's new email address.
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * Returns the URL for the POP3 Server.
	 * 
	 * @return The URL for the POP3 Server.
	 */
	public String getUrlPOP3Server() {
		return urlPOP3Server;
	}

	/**
	 * Sets the URL for the POP3 Server.
	 * 
	 * @param urlPOP3Server
	 *            The new URL for the POP3 Server.
	 */
	public void setUrlPOP3Server(String urlPOP3Server) {
		this.urlPOP3Server = urlPOP3Server;
	}

	/**
	 * Returns the URL of the SMTP Server.
	 * 
	 * @return The URL of the SMTP Server.
	 */
	public String getUrlSMTPServer() {
		return urlSMTPServer;
	}

	/**
	 * Sets the URL of the SMTP Server.
	 * 
	 * @param urlSMTPServer
	 *            The new URL for the SMTP Server.
	 */
	public void setUrlSMTPServer(String urlSMTPServer) {
		this.urlSMTPServer = urlSMTPServer;
	}

	/**
	 * Returns the URL of the MYSQL Database.
	 * 
	 * @return The URL of the MYSQL Database.
	 */
	public String getUrlMySQLServer() {
		return urlMySQLServer;
	}

	/**
	 * Sets the Url of the MYSQL Database.
	 * 
	 * @param urlMySQLServer
	 *            The new URL of the MYSQL Database.
	 */
	public void setUrlMySQLServer(String urlMySQLServer) {
		this.urlMySQLServer = urlMySQLServer;
	}

	/**
	 * Returns the Port of the POP3 Server.
	 * 
	 * @return The Port of the POP3 Server.
	 */
	public String getPortPOP3Server() {
		return portPOP3Server;
	}

	/**
	 * Sets the Port of the POP3 Server.
	 * 
	 * @param portPOP3Server
	 *            The new port of the POP3 Server.
	 */
	public void setPortPOP3Server(String portPOP3Server) {
		this.portPOP3Server = portPOP3Server;
	}

	/**
	 * Returns the Port of the SMTP Server.
	 * 
	 * @return The Port of the SMTP Server.
	 */
	public String getPortSMTPServer() {
		return portSMTPServer;
	}

	/**
	 * Sets the Port of the SMTP Server.
	 * 
	 * @param portSMTPServer
	 *            The new Port of the SMTP Server.
	 */
	public void setPortSMTPServer(String portSMTPServer) {
		this.portSMTPServer = portSMTPServer;
	}

	/**
	 * Returns the Port of the MYSQL Database.
	 * 
	 * @return The Port of the MYSQL Database.
	 */
	public String getPortMySQLServer() {
		return portMySQLServer;
	}

	/**
	 * Sets the Port of the MYSQL Database.
	 * 
	 * @param portMySQLServer
	 *            The new Port of the MYSQL Database.
	 */
	public void setPortMySQLServer(String portMySQLServer) {
		this.portMySQLServer = portMySQLServer;
	}

	/**
	 * Returns the database name of the SQL server.
	 * 
	 * @return The database name.
	 */

	public String getDatabaseMySQL() {
		return databaseMySQL;
	}

	/**
	 * Sets the database name for the MySQL server.
	 * 
	 * @param databaseMySQL
	 *            The new database name.
	 */
	public void setDatabaseMySQL(String databaseMySQL) {
		this.databaseMySQL = databaseMySQL;
	}

	/**
	 * Returns whether or not the email is a Gmail account.
	 * 
	 * @return Whether or not the email is a Gmail account.
	 */
	public Boolean getIsGmailAccount() {
		return isGmailAccount;
	}

	/**
	 * Sets whether or not the email is a Gmail account.
	 * 
	 * @param isGmailAccount
	 *            The new value.
	 */
	public void setIsGmailAccount(Boolean isGmailAccount) {

		this.isGmailAccount = isGmailAccount;
	}

	/**
	 * Returns whether this email is SMTP authorized.
	 * 
	 * @return Whether it is SMTP authorized.
	 */
	public Boolean getIsSMTPAuth() {
		return isSMTPAuth;
	}

	/**
	 * Sets whether this email is SMTP authorized.
	 * 
	 * @param isSMTPAuth
	 *            The new value for SMTP authroized.
	 */
	public void setIsSMTPAuth(Boolean isSMTPAuth) {
		this.isSMTPAuth = isSMTPAuth;
	}

	/**
	 * Returns the user name on the POP3 Server.
	 * 
	 * @return The user name on the POP3 Server.
	 */
	public String getUserNamePOP3() {
		return userNamePOP3;
	}

	/**
	 * Sets the user name on the POP3 Server.
	 * 
	 * @param userNamePOP3
	 *            The new user name on the POP3 Server.
	 */
	public void setUserNamePOP3(String userNamePOP3) {
		this.userNamePOP3 = userNamePOP3;
	}

	/**
	 * Returns the password on the POP3 Server.
	 * 
	 * @return The password on the POP3 Server.
	 */
	public String getPasswordPOP3() {
		return passwordPOP3;
	}

	/**
	 * Sets the password on the POP3 Server.
	 * 
	 * @param passwordPOP3
	 *            The new password on the POP3 Server.
	 */
	public void setPasswordPOP3(String passwordPOP3) {
		this.passwordPOP3 = passwordPOP3;
	}

	/**
	 * Returns the user name on the MYSQL database.
	 * 
	 * @return The user name on the MYSQL database.
	 */
	public String getUserNameMySQL() {
		return userNameMySQL;
	}

	/**
	 * Sets the user name on the MYSQL database.
	 * 
	 * @param userNameMySQL
	 *            The new user name on the MYSQL database.
	 */
	public void setUserNameMySQL(String userNameMySQL) {
		this.userNameMySQL = userNameMySQL;
	}

	/**
	 * Returns the password on the MYSQL database.
	 * 
	 * @return The password for the MYSQL database.
	 */
	public String getPasswordMySQL() {
		return passwordMySQL;
	}

	/**
	 * Sets the password for the MYSQL database.
	 * 
	 * @param passwordMySQL
	 *            The new password for the MYSQL database.
	 */
	public void setPasswordMySQL(String passwordMySQL) {
		this.passwordMySQL = passwordMySQL;
	}

	/**
	 * Returns the language.
	 * 
	 * @return The language.
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * Sets the language
	 * 
	 * @param language
	 *            The language.
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
}
