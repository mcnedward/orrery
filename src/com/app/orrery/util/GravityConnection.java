package com.app.orrery.util;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;

import com.app.orrery.OrreryApp;
import com.app.orrery.figure.IConnectable;
import com.app.orrery.figure.PlanetFigure;

import CH.ifa.draw.figure.ArrowTip;
import CH.ifa.draw.figure.connection.LineConnection;
import CH.ifa.draw.framework.Figure;
import CH.ifa.draw.storable.StorableInput;
import CH.ifa.draw.storable.StorableOutput;
import CH.ifa.draw.util.Animatable;

/**
 * Sets the gravity representation between Planets and Satellites.
 * 
 * @author Edward
 *
 */
public class GravityConnection extends LineConnection implements Animatable {
	private static final long serialVersionUID = 1L;

	public GravityConnection() {
		setEndDecoration(new ArrowTip());
		setStartDecoration(null);
	}

	@Override
	public boolean canConnect(Figure start, Figure end) {
		// Only allow objects to orbit planets
		if (end instanceof PlanetFigure == false)
			return false;
		if (start instanceof IConnectable && end instanceof IConnectable) {
			IConnectable startObject = (IConnectable) start;
			IConnectable endObject = (IConnectable) end;
			// The starting object should not be connected already, and the end
			// object cannot already be connected to the start
			if (!startObject.isConnected()) {
				if (endObject.getConnectedObject() == null || !endObject.getConnectedObject().equals(startObject))
					return true;
			}
		}
		return false;
	}

	@Override
	public void handleConnect(Figure start, Figure end) {
		if (start instanceof IConnectable)
			((IConnectable) start).setConnected(true, (IConnectable) end);
	}

	@Override
	public void handleDisconnect(Figure start, Figure end) {
		if (start instanceof IConnectable)
			((IConnectable) start).setConnected(false, (IConnectable) end);
	}

	private static int X_VELOCITY = 1;
	private static int Y_VELOCITY = 1;
	private static int THETA = 90;

	@Override
	public void animationStep() {
		Figure start = startFigure();
		int xSpeed = X_VELOCITY;
		int ySpeed = Y_VELOCITY;
		Rectangle bounds = start.displayBox();

		if ((bounds.x + bounds.width > OrreryApp.WIDTH) && (xSpeed > 0))
			xSpeed = -xSpeed;
		if ((bounds.y + bounds.height > OrreryApp.HEIGHT) && (ySpeed > 0))
			ySpeed = -ySpeed;
		if ((bounds.x < 0) && (xSpeed < 0))
			xSpeed = -xSpeed;
		if ((bounds.y < 0) && (ySpeed < 0))
			ySpeed = -ySpeed;

		double x = Math.cos(THETA) * bounds.x - Math.sin(THETA) * bounds.y;
		double y = Math.sin(THETA) * bounds.x + Math.cos(THETA) * bounds.y;

		xSpeed += x;
		ySpeed += y;
		// x += xSpeed;
		// y += ySpeed;

//		velocity(xSpeed, ySpeed);
		start.moveBy((int) x, (int) y); 
//		start.moveBy(xSpeed, ySpeed);
	}

	@Override
	public synchronized void basicDisplayBox(Point origin, Point corner) {
		super.basicDisplayBox(origin, corner);
	}

	@Override
	public synchronized void basicMoveBy(int x, int y) {
		super.basicMoveBy(x, y);
	}

	@Override
	public synchronized Rectangle displayBox() {
		return super.displayBox();
	}

	@Override
	public void read(StorableInput dr) throws IOException {
		super.read(dr);
		X_VELOCITY = dr.readInt();
		Y_VELOCITY = dr.readInt();
	}

	public Point velocity() {
		return new Point(X_VELOCITY, Y_VELOCITY);
	}

	// -- store / load ----------------------------------------------
	public void velocity(int xVelocity, int yVelocity) {
		X_VELOCITY = xVelocity;
		Y_VELOCITY = yVelocity;
	}

	@Override
	public void write(StorableOutput dw) {
		super.write(dw);
		dw.writeInt(X_VELOCITY);
		dw.writeInt(Y_VELOCITY);
	}

}
