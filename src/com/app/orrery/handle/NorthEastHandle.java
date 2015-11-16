package com.app.orrery.handle;

import java.awt.Point;
import java.awt.Rectangle;

import com.app.orrery.figure.PlanetFigure;

import CH.ifa.draw.framework.DrawingView;
import CH.ifa.draw.handle.LocatorHandle;
import CH.ifa.draw.locator.RelativeLocator;

/**
 * @author Edward McNealy <edwardmcn64@gmail.com> - Nov 16, 2015
 *
 */
public class NorthEastHandle extends LocatorHandle {

	private PlanetFigure planetFigure;
	
	public NorthEastHandle(PlanetFigure owner) {
		super(owner, RelativeLocator.northEast());
		planetFigure = owner;
	}

	@Override
	public void invokeStep(int x, int y, int anchorX, int anchorY, DrawingView view) {
		Rectangle r = planetFigure.getPlanetBounds();

		int lockX = r.x;
		int lockY = r.y + r.height;
		
		int width = x - lockX;
		int height = lockY - y;
		int size = Math.min(PlanetFigure.PLANET_SIZE, Math.max(width, height));
		
		int p1x = lockX;
		int p1y = Math.min(lockY, lockY - size);
		Point p1 = new Point(p1x, p1y);
		
		int p2x = Math.max(lockX, lockX + size);
		int p2y = lockY;
		Point p2 = new Point(p2x, p2y);
		
		owner().displayBox(p1, p2);
	}

}
