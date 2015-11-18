package com.app.orrery.handle.satellite;

import java.awt.Color;
import java.awt.Rectangle;

import com.app.orrery.figure.SatelliteFigure;

import CH.ifa.draw.framework.Locator;
import CH.ifa.draw.handle.LocatorHandle;

/**
 * @author Edward McNealy <edwardmcn64@gmail.com> - Nov 18, 2015
 *
 */
public class SatelliteHandle extends LocatorHandle implements ISatelliteHandle {
	
	protected Rectangle bounds;
	protected Color color;

	public SatelliteHandle(SatelliteFigure owner, Locator locator) {
		super(owner, locator);
		updateBounds(owner.displayBox());
		color = Color.BLUE;
	}
	
	@Override
	public void updateBounds(Rectangle bounds) {
		this.bounds = new Rectangle(bounds.x, bounds.y, bounds.width, bounds.height);
	}

	@Override
	public Rectangle displayBox() {
		return bounds;
	}

	@Override
	public boolean containsPoint(int x, int y) {
		return false;
	}
}
