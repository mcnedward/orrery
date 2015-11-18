package com.app.orrery.handle.satellite;

import java.awt.Graphics;

import com.app.orrery.figure.SatelliteFigure;

import CH.ifa.draw.locator.RelativeLocator;

/**
 * @author Edward McNealy <edwardmcn64@gmail.com> - Nov 16, 2015
 *
 */
public class SatelliteWestHandle extends SatelliteHandle {

	public SatelliteWestHandle(SatelliteFigure owner) {
		super(owner, RelativeLocator.west());
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawLine(bounds.x, bounds.y, bounds.x, bounds.y + bounds.height);
	}
	
}
