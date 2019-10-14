/*******************************************************************************
 * Copyright (c) 2019- UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Initial API and implementation and/or initial documentation - 
 *   Jay Jay Billings, Joe Osborn
 *******************************************************************************/
package org.eclipse.ice.commands;

/**
 * This class provides the complete configuration for a remote
 * {@link org.eclipse.ice.commands.Connection}.
 * 
 * @author Jay Jay Billings, Joe Osborn
 *
 */
public class ConnectionConfiguration {

	/**
	 * Username to configure a particular connection
	 */
	private String username = "";

	/**
	 * The hostname on which to host the particular session, or where the
	 * RemoteCommand will eventually be run
	 */
	private String hostname = "";

	/**
	 * A name given to this particular connection configuration, which can be used
	 * to identify the forthcoming connection and, for example, get it from the
	 * ConnectionManager class
	 */
	private String name = "";

	/**
	 * The password used to make the connection. Used for unit tests with the dummy
	 * ssh account only
	 */
	private String password = "";

	/**
	 * An optional path that the user can provide to a text file with their ssh
	 * credentials available. The file must be completely empty except 
	 * for the ssh password to be used.
	 */
	private String credentialPath = "";

	/**
	 * A boolean indicating whether or not the files/directories created on the
	 * remote host should be deleted or not. Default set to false, for obvious
	 * reasons. This will delete the working directory of a remote command on the
	 * remote host, if it is set to true.
	 */
	private boolean deleteWorkingDirectory = false;

	/**
	 * Default constructor
	 */
	public ConnectionConfiguration() {
	}

	/**
	 * Setter function for
	 * {@link org.eclipse.ice.commands.ConnectionConfiguration#username}
	 * 
	 * @param uname
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Getter for the username for a connection
	 * 
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Setter function for
	 * {@link org.eclipse.ice.commands.ConnectionConfiguration#hostname}
	 * 
	 * @param hname
	 */
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	/**
	 * Create getter and setter functions to access member variables
	 */

	public String getHostname() {
		return hostname;
	}

	/**
	 * A getter to access
	 * {@link org.eclipse.ice.commands.ConnectionConfiguration#name}
	 * 
	 * @return - name
	 */
	public String getName() {
		return name;
	}

	/**
	 * A setter to access
	 * {@link org.eclipse.ice.commands.ConnectionConfiguration#name}
	 * 
	 * @return - name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for whether or not to delete the remote working directory upon
	 * completion
	 * {@link org.eclipse.ice.commands.ConnectionConfiguration#deleteWorkingDirectory}
	 * 
	 * @return
	 */
	public boolean getDeleteWorkingDirectory() {
		return deleteWorkingDirectory;
	}

	/**
	 * Setter for whether or not to delete the remote working directory upon
	 * completion
	 * {@link org.eclipse.ice.commands.ConnectionConfiguration#deleteWorkingDirectory}
	 * 
	 * @param
	 */
	public void setDeleteWorkingDirectory(boolean _delete) {
		deleteWorkingDirectory = _delete;
	}

	/**
	 * Setter for {@link org.eclipse.ice.commands.ConnectionConfiguration#password}
	 * Use with caution, only if you are comfortable putting your password into a
	 * String variable (which you rarely should be!!). This function is primarily
	 * for unit testing with a dummy ssh account in CI.
	 * 
	 * @param _pass
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Getter for obtaining password string. Protected so that it cannot be publicly
	 * accessed, only by classes within this package.
	 * 
	 * @return
	 */
	protected String getPassword() {
		return password;
	}

	/**
	 * Setter for the credential path, if desired. See
	 * {@link org.eclipse.ice.commands.ConnectionConfiguration#credentialPath}
	 * 
	 * @param credentialPath
	 */
	public void setCredentialPath(String credentialPath) {
		this.credentialPath = credentialPath;
	}

	/**
	 * Getter for the credential path, if desired. See
	 * {@link org.eclipse.ice.commands.ConnectionConfiguration#credentialPath}
	 * 
	 * @return
	 */
	public String getCredentialPath() {
		return credentialPath;
	}

}
