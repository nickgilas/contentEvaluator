package com.nick.monitor.output;

/**
 * This interface allows for the easy addition of new
 * types of output destinations. For example, SMTP, FTP,
 * WebSocket, etc...)
 *  
 *  Future thought: if the input param data type
 * @author Nick Gilas
 *
 */
public interface OutputDestination<T> {

	void sendContents(T contents);
	
}
