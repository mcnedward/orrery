package com.app.orrery.tool;

import CH.ifa.draw.framework.Drawing;
import CH.ifa.draw.framework.DrawingView;
import CH.ifa.draw.tool.AbstractTool;

/**
 * @author Edward McNealy <edwardmcn64@gmail.com> - Nov 6, 2015
 *
 */
public class ClearTool extends AbstractTool {

	/**
	 * @param view
	 */
	public ClearTool(DrawingView view) {
		super(view);
	}
	
	@Override
	public void activate() {
		clear();
	}

	public void clear() {
		Drawing drawing = view().drawing();
		while (drawing.figures().hasMoreElements()) {
			drawing.remove(drawing.figures().nextElement());
		}
		view().paintComponent(view().getGraphics());
	}

}
