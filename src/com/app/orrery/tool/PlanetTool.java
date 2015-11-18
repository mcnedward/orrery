package com.app.orrery.tool;

import java.awt.event.MouseEvent;

import com.app.orrery.OrreryApp;
import com.app.orrery.figure.EditableFigure;
import com.app.orrery.figure.PlanetFigure;

import CH.ifa.draw.framework.DrawingView;
import CH.ifa.draw.framework.Figure;
import CH.ifa.draw.tool.CreationTool;

/**
 * @author Edward McNealy <edwardmcn64@gmail.com> - Oct 31, 2015
 *
 */
public class PlanetTool extends CreationTool implements EditableTool {

	private PlanetFigure fPlanetFigure;
	private EditableFigure fEditableFigure;

	public PlanetTool(DrawingView view, Figure prototype) {
		super(view, prototype);
	}

	@Override
	public void mouseDown(MouseEvent e, int x, int y) {
		super.mouseDown(e, x, y);
		Figure createdFigure = createdFigure();
		if (createdFigure != null && createdFigure instanceof PlanetFigure) {
			OrreryApp.PLANETS.add((PlanetFigure) createdFigure);
		}
		if (fEditableFigure != null)
			fEditableFigure.endEdit();
	}

	@Override
	public void mouseUp(MouseEvent e, int x, int y) {
		fPlanetFigure = getPlanetFigure();
		if (fPlanetFigure != null) {
			if (fPlanetFigure.getPlanetBounds().width > 0 && fPlanetFigure.getPlanetBounds().height > 0) {
				fEditableFigure = new EditableFigure(fPlanetFigure, view());
				fEditableFigure.beginEdit();
			}
		}
		super.mouseUp(e, x, y);
	}

	private PlanetFigure getPlanetFigure() {
		Figure createdFigure = createdFigure();
		if (createdFigure instanceof PlanetFigure)
			return (PlanetFigure) createdFigure;
		return null;
	}

	@Override
	public void endEdit() {
		if (fEditableFigure != null)
			fEditableFigure.endEdit();
	}
}
