package com.app.orrery.tool;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import com.app.orrery.figure.EditableFigure;
import com.app.orrery.figure.PlanetFigure;

import CH.ifa.draw.framework.DrawingView;
import CH.ifa.draw.framework.Figure;
import CH.ifa.draw.tool.AbstractTool;

/**
 * @author Edward McNealy <edwardmcn64@gmail.com> - Nov 10, 2015
 *
 */
public class PlanetEditTool extends AbstractTool implements EditableTool {

	private EditableFigure fEditableFigure;
	
	public PlanetEditTool(DrawingView view) {
		super(view);
	}

	/**
	 * Handles mouse down events and starts the corresponding tracker.
	 */
	@Override
	public void mouseDown(MouseEvent e, int x, int y) {
		super.mouseDown(e, x, y);

		if (fEditableFigure != null) {
			fEditableFigure.endEdit();
			fEditableFigure = null;
		}
		
		Figure figure = drawing().findFigure(e.getX(), e.getY());
		if (figure != null) {
			if (figure instanceof PlanetFigure) {
				PlanetFigure planet = (PlanetFigure) figure;
				Rectangle bounds = planet.getPlanetBounds();
				if ((x > bounds.x) && (x < bounds.x + bounds.width) && (y > bounds.y) && (y < bounds.y + bounds.height)) {
					fEditableFigure = new EditableFigure(planet, view());
					fEditableFigure.beginEdit();
				}
			}
		}
	}
	
	@Override
	public void endEdit() {
		if (fEditableFigure != null)
			fEditableFigure.endEdit();
	}

}
