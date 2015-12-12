package com.app.orrery.util;

import java.awt.Point;
import java.awt.Rectangle;

import com.app.orrery.figure.IConnectable;
import com.app.orrery.figure.PlanetFigure;

import CH.ifa.draw.figure.ArrowTip;
import CH.ifa.draw.figure.connection.LineConnection;
import CH.ifa.draw.framework.Figure;
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

	private static double THETA = 0.1;

	@Override
	public void animationStep() {
		try {
			Figure orbitingFigure = startFigure();
			Figure planet = endFigure();

			Rectangle planetBounds = null;
			Rectangle orbitingFigureBounds = null;

			if (planet instanceof PlanetFigure && ((PlanetFigure) planet).isConnected()) {
				PlanetFigure connectedPlanet = (PlanetFigure) ((PlanetFigure) planet).getConnectedObject();
				Point predictedPlanetPoint = getRotationPoint(planet.displayBox(), connectedPlanet.displayBox());
				planetBounds = new Rectangle(predictedPlanetPoint.x, predictedPlanetPoint.y, predictedPlanetPoint.x + planet.displayBox().width, predictedPlanetPoint.y + planet.displayBox().height);
				
				int newOrbitingFigureX = predictedPlanetPoint.x - orbitingFigure.displayBox().x;
				int newOrbitingFigureY = predictedPlanetPoint.y - orbitingFigure.displayBox().y;
//				int newOrbitingFigureX = Math.abs(orbitingFigure.displayBox().x - predictedPlanetPoint.x);
//				int newOrbitingFigureY = Math.abs(orbitingFigure.displayBox().y - predictedPlanetPoint.y);
				orbitingFigureBounds = new Rectangle(newOrbitingFigureX, newOrbitingFigureY, newOrbitingFigureX + orbitingFigure.displayBox().width, newOrbitingFigureY + orbitingFigure.displayBox().height);
			} else {
				planetBounds = planet.displayBox();
				orbitingFigureBounds = orbitingFigure.displayBox();
			}

			Point newPoint = getRotationPoint(orbitingFigureBounds, planetBounds);
			orbitingFigure.moveBy(newPoint.x, newPoint.y);
		} catch (NullPointerException e) {
			// System.out.println("Could not find animatable figure...");
		}
	}

	private Point getRotationPoint(Rectangle orbitingFigure, Rectangle planet) {
		double orbitX = orbitingFigure.getCenterX();
		double orbitY = orbitingFigure.getCenterY();
		double planetX = planet.getCenterX();
		double planetY = planet.getCenterY();

		int rotateX = (int) (planetX + (orbitX - planetX) * Math.cos(THETA) - (orbitY - planetY) * Math.sin(THETA));
		int rotateY = (int) (planetY + (orbitX - planetX) * Math.sin(THETA) + (orbitY - planetY) * Math.cos(THETA));

		int newOrbitX = (int) (rotateX - orbitX);
		int newOrbitY = (int) (rotateY - orbitY);

		return new Point(newOrbitX, newOrbitY);
	}

}
