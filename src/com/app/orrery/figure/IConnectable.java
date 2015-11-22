package com.app.orrery.figure;

/**
 * An interface for objects that should be able to connect to other objects.
 * 
 * @author Edward
 *
 */
public interface IConnectable {

	/**
	 * Tells if this object is already connected, or orbiting, another object.
	 * 
	 * @return True if connected, false otherwise.
	 */
	public boolean isConnected();

	/**
	 * Sets this object to be connected or disconnected. This also sets the
	 * object that the parent is connected to. This should be null when
	 * disconnecting an object.
	 * 
	 * @param connected
	 *            True if this object should be connected, false otherwise.
	 * @param connectedObject
	 *            The object that this parent object is connected to.
	 */
	public void setConnected(boolean connected, IConnectable connectedObject);

	/**
	 * Gets the object that this parent object is connected to.
	 * 
	 * @return The connected object.
	 */
	public IConnectable getConnectedObject();

}
