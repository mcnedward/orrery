package com.app.orrery.tool;

import java.awt.event.MouseEvent;

import CH.ifa.draw.framework.DrawingView;
import CH.ifa.draw.framework.Figure;
import CH.ifa.draw.tool.CreationTool;

/**
 * @author Edward McNealy <edwardmcn64@gmail.com> - Nov 17, 2015
 *
 */
public class SatelliteTool extends CreationTool {

	public SatelliteTool(DrawingView view, Figure prototype) {
		super(view, prototype);
	}

	@Override
	public void mouseDown(MouseEvent e, int x, int y) {
		super.mouseDown(e, x, y);
	}

	@Override
	public void mouseUp(MouseEvent e, int x, int y) {
		super.mouseUp(e, x, y);
	}
	
	@Override
	public void mouseDrag(MouseEvent e, int x, int y) {
		// Leave blank to prevent resizing
	}
	
}
