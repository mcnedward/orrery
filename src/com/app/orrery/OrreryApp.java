package com.app.orrery;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.app.orrery.figure.PlanetFigure;
import com.app.orrery.figure.SatelliteFigure;
import com.app.orrery.tool.AtmosphereTool;
import com.app.orrery.tool.ClearTool;
import com.app.orrery.tool.EditableTool;
import com.app.orrery.tool.PlanetEditTool;
import com.app.orrery.tool.PlanetTool;
import com.app.orrery.tool.SatelliteTool;
import com.app.orrery.util.GravityConnection;

import CH.ifa.draw.application.DrawApplication;
import CH.ifa.draw.framework.Drawing;
import CH.ifa.draw.framework.Tool;
import CH.ifa.draw.palette.PaletteButton;
import CH.ifa.draw.samples.javadraw.Animator;
import CH.ifa.draw.tool.ConnectionTool;
import CH.ifa.draw.util.Animatable;

/**
 * @author Edward McNealy <edwardmcn64@gmail.com> - Oct 31, 2015
 *
 */
public class OrreryApp extends DrawApplication {
	private static final long serialVersionUID = -930533118785490372L;

	public static int WIDTH = 700;
	public static int HEIGHT = 700;
	
	public static List<PlanetFigure> PLANETS;

	private PlanetTool planetTool;
	private Animator animator;

	public static void main(String[] args) {
		OrreryApp window = new OrreryApp();
		window.open();
	}

	public OrreryApp() {
		super("Orrery App");
		PLANETS = new ArrayList<>();
	}

	@Override
	protected void createTools(JPanel palette) {
		super.createTools(palette);

		planetTool = new PlanetTool(view(), new PlanetFigure());
		palette.add(createToolButton(IMAGES + "ELLIPSE", "Planet Tool", planetTool));

		Tool tool = new AtmosphereTool(view());
		palette.add(createToolButton(IMAGES + "BORDDEC", "Planet Atmosphere Tool", tool));

		tool = new PlanetEditTool(view());
		palette.add(createToolButton(IMAGES + "TEXT", "Planet Name Tool - Click inside a Planet to rename", tool));

		tool = new SatelliteTool(view(), new SatelliteFigure());
		palette.add(createToolButton(IMAGES + "SAT", "Satellite Tool", tool));

		tool = new ConnectionTool(view(), new GravityConnection());
		palette.add(createToolButton(IMAGES + "CONN", "Gravity Tool", tool));

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
	public Drawing createDrawing() {
		return new AnimatableOrrery();
	}

	@Override
	protected void createMenus(JMenuBar mb) {
		super.createMenus(mb);
		mb.add(createAnimationMenu());
	}

	private JMenu createAnimationMenu() {
		JMenu menu = new JMenu("Animation");
		JMenuItem item = new JMenuItem("Start");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				startAnimation();
			}
		});
		menu.add(item);

		item = new JMenuItem("Stop");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				endAnimation();
			}
		});
		menu.add(item);
		return menu;
	}

	public void startAnimation() {
		if (drawing() instanceof Animatable && animator == null) {
			animator = new Animator((Animatable) drawing(), view());
			animator.start();
		}
	}

	public void endAnimation() {
		if (animator != null) {
			animator.end();
			animator = null;
		}
	}

	public void destroy() {
		super.destroy();
		endAnimation();
	}

	@Override
	public void open() {
		super.open();
	}

	@Override
	protected Dimension getDrawingViewSize() {
		return new Dimension(WIDTH, HEIGHT);
	}
}
