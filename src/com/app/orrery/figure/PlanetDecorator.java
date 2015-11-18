package com.app.orrery.figure;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import CH.ifa.draw.figure.DecoratorFigure;

/**
 * @author Edward McNealy <edwardmcn64@gmail.com> - Nov 17, 2015
 *
 */
public class PlanetDecorator extends DecoratorFigure {
	private static final long serialVersionUID = 1L;
	
	private PlanetFigure planetFigure;
	
	public PlanetDecorator(PlanetFigure planetFigure) {
		super(planetFigure);
		this.planetFigure = planetFigure;
	}
	
	public PlanetFigure removeFromAtmosphere() {
		return planetFigure;
	}
	
	@Override
	public void draw(Graphics g) {
		Rectangle bounds = displayBox();
		g.setColor(Color.MAGENTA);
		g.fillOval(bounds.x, bounds.y, bounds.width, bounds.height);
		super.draw(g);
	}
	
	@Override
	public Rectangle displayBox() {
		Rectangle r = fComponent.displayBox();
		r.grow(border().x, border().y);
		return r;
	}
	
	private Point border() {
		return new Point(4, 4);
	}

}
