package com.app.orrery.figure;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import CH.ifa.draw.figure.DecoratorFigure;
import CH.ifa.draw.framework.DrawingView;
import CH.ifa.draw.framework.Figure;
import CH.ifa.draw.samples.net.NodeFigure;
import CH.ifa.draw.util.FloatingTextField;
import CH.ifa.draw.util.TextHolder;

/**
 * @author Edward McNealy <edwardmcn64@gmail.com> - Nov 10, 2015
 *
 */
public class EditableFigure extends DecoratorFigure {
	private static final long serialVersionUID = 1L;

	private FloatingTextField fTextField;
	private TextHolder fTypingTarget;

	private PlanetFigure fPlanetFigure;
	private DrawingView fView;
	
	public EditableFigure(Figure figure, DrawingView view) {
		super(figure);
		if (figure instanceof PlanetFigure)
			fPlanetFigure = (PlanetFigure) figure;
		fView = view;
	}

	public void beginEdit() {
		if (fTextField == null) {
			fTextField = new FloatingTextField();
			fTextField.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					endEdit();
				}
			});
		}
		TextHolder figure = new NodeFigure();
		if (figure != fTypingTarget && fTypingTarget != null)
			endEdit();
		fTextField.createOverlay((JPanel) fView, new Font("Segoe UI", Font.PLAIN, 8));
		fTextField.setBounds(fPlanetFigure.getTextBounds(), fPlanetFigure.getPlanetName());
		fTypingTarget = figure;
	}

	public void endEdit() {
		if (fTypingTarget != null) {
			fTypingTarget = null;
			fTextField.endOverlay();
			// Set the planet's name and tell the figure to redraw with the planet name
			fPlanetFigure.setPlanetName(fTextField.getText());
			fPlanetFigure.repaint();
			fView.checkDamage();
		}
	}

}
