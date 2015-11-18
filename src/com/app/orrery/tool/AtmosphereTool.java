package com.app.orrery.tool;

import java.awt.event.MouseEvent;

import com.app.orrery.figure.PlanetDecorator;
import com.app.orrery.figure.PlanetFigure;

import CH.ifa.draw.framework.DrawingView;
import CH.ifa.draw.framework.Figure;
import CH.ifa.draw.tool.ActionTool;

/**
 * @author Edward McNealy <edwardmcn64@gmail.com> - Nov 17, 2015
 *
 */
public class AtmosphereTool extends ActionTool {

	public AtmosphereTool(DrawingView view) {
		super(view);
	}
	
	@Override
	public void mouseDown(MouseEvent e, int x, int y) {
        Figure target = drawing().findFigure(x, y);
        if (target != null) {
            action(target);
        }
    }

    @Override
	public void mouseUp(MouseEvent e, int x, int y) {
    }

	@Override
	public void action(Figure figure) {
		if (figure instanceof PlanetFigure)
			drawing().replace(figure, new PlanetDecorator((PlanetFigure) figure));
		if (figure instanceof PlanetDecorator) {
			PlanetFigure planetFigure = ((PlanetDecorator)figure).removeFromAtmosphere();
			drawing().replace(figure, planetFigure);
		}
	}

}
