package com.app.orrery;

import java.awt.Dimension;

import javax.swing.JPanel;

import com.app.orrery.figure.PlanetFigure;
import com.app.orrery.tool.ClearTool;
import com.app.orrery.tool.EditableTool;
import com.app.orrery.tool.PlanetEditTool;
import com.app.orrery.tool.PlanetTool;

import CH.ifa.draw.application.DrawApplication;
import CH.ifa.draw.framework.Tool;
import CH.ifa.draw.palette.PaletteButton;

/**
 * @author Edward McNealy <edwardmcn64@gmail.com> - Oct 31, 2015
 *
 */
public class OrreryApp extends DrawApplication {
	private static final long serialVersionUID = -930533118785490372L;

	private PlanetTool planetTool;
	
	public static void main(String[] args) {
		OrreryApp window = new OrreryApp();
		window.open();
	}

	public OrreryApp() {
		super("Orrery App");
	}

	@Override
	protected void createTools(JPanel palette) {
		super.createTools(palette);

		planetTool = new PlanetTool(view(), new PlanetFigure());
		palette.add(createToolButton(IMAGES + "ELLIPSE", "Planet Tool", planetTool));

		Tool tool = new PlanetEditTool(view());
		palette.add(createToolButton(IMAGES + "TEXT", "Planet Name Tool", tool));

		tool = new ClearTool(view());
		palette.add(createToolButton(IMAGES + "ERASER", "Clear Tool", tool));
	}

	@Override
	public void paletteUserSelected(PaletteButton button) {
		Tool selectedTool = tool();
		// Finish editing if switching tools straight from planet creation
		if (selectedTool instanceof EditableTool) {
			((EditableTool) selectedTool).endEdit();
		}
		super.paletteUserSelected(button);
	}

	@Override
	public void open() {
		super.open();
	}

	@Override
	protected Dimension getDrawingViewSize() {
		return new Dimension(700, 700);
	}
}
