package com.angelcraftonomy.disabler.interfaces;

public interface CommandInterface {
	public void initialize();

	public void run();

	public void cleanup();

	public void sendNoPermMessage();

	public String getPermission();
}
