package com.app.orrery.handle.satellite;

import java.awt.Graphics;

import com.app.orrery.figure.SatelliteFigure;

import CH.ifa.draw.locator.RelativeLocator;

/**
 * @author Edward McNealy <edwardmcn64@gmail.com> - Nov 16, 2015
 *
 */
public class SatelliteEastHandle extends SatelliteHandle {

	public SatelliteEastHandle(SatelliteFigure owner) {
		super(owner, RelativeLocator.east());
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawLine(bounds.x + bounds.width, bounds.y, bounds.x + bounds.width, bounds.y + bounds.height);
	}
}
