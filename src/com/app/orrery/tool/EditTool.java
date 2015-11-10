package com.app.orrery.tool;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.app.orrery.figure.PlanetFigure;

import CH.ifa.draw.framework.DrawingView;
import CH.ifa.draw.framework.Figure;
import CH.ifa.draw.samples.net.NodeFigure;
import CH.ifa.draw.tool.CreationTool;
import CH.ifa.draw.util.FloatingTextField;
import CH.ifa.draw.util.TextHolder;

/**
 * @author Edward McNealy <edwardmcn64@gmail.com> - Nov 10, 2015
 *
 */
public abstract class EditTool extends CreationTool {
	
	private FloatingTextField fTextField;
	private TextHolder fTypingTarget;
	
	protected PlanetFigure planetFigure;
	
	protected EditTool(DrawingView view) {
		super(view);
		new NodeFigure();
	}
	
	protected EditTool(DrawingView view, Figure prototype) {
		super(view, prototype);
	}
	
	protected void beginEdit(TextHolder figure, PlanetFigure planetFigure) {
		this.planetFigure = planetFigure;
		if (fTextField == null) {
			fTextField = new FloatingTextField();
			fTextField.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					endEdit();
				}
			});
		}

		if (figure != fTypingTarget && fTypingTarget != null)
			endEdit();
		fTextField.createOverlay((JPanel) view(), new Font("Segoe UI", Font.PLAIN, 8));
		fTextField.setBounds(planetFigure.getTextBounds(), "PLANET");
		fTypingTarget = figure;
	}

	protected void endEdit() {
		if (fTypingTarget != null) {
			fTypingTarget = null;
			fTextField.endOverlay();
			// Set the planet's name and tell the figure to redraw with the planet name
			planetFigure.setPlanetName(fTextField.getText());
			planetFigure.repaint();
			view().checkDamage();
		}
	}
}
