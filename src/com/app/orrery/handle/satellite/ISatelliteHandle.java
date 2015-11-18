package com.app.orrery.handle.satellite;

import java.awt.Rectangle;

import CH.ifa.draw.framework.Handle;

/**
 * @author Edward McNealy <edwardmcn64@gmail.com> - Nov 18, 2015
 *
 */
public interface ISatelliteHandle extends Handle {

	public void updateBounds(Rectangle bounds);
	
}
