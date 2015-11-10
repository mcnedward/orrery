package com.app.orrery.figure;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import CH.ifa.draw.figure.EllipseFigure;

/**
 * @author Edward McNealy <edwardmcn64@gmail.com> - Oct 31, 2015
 *
 */
public class PlanetFigure extends EllipseFigure {
	private static final long serialVersionUID = -3146574478769258549L;

	private String planetName;
	private Font fFont;
	// The FontMetrics will be set when the planet name is ready to be drawn
	private FontMetrics metrics;
	private Color fColor;

	public PlanetFigure() {
		super(new Point(0, 0), new Point(0, 0));
		fFont = new Font("Segoe UI", Font.PLAIN, 10);
		planetName = "PLANET";
	}

	@Override
	public void drawBackground(Graphics g) {
		Rectangle r = getPlanetBounds();

		if (r.width <= 40)
			fColor = Color.WHITE;
		else if (r.width > 40 && r.width <= 80)
			fColor = Color.GREEN;
		else
			fColor = Color.RED;

		drawPlanetText(g, r);

		g.setColor(fColor);
		g.fillOval(r.x, r.y, r.width, r.height);
	}

	private void drawPlanetText(Graphics g, Rectangle planetBounds) {
		g.setColor(Color.BLACK);
		g.setFont(fFont);
		metrics = g.getFontMetrics(fFont);
		int textWidth = metrics.stringWidth(planetName);
		int planetWidthHalf = planetBounds.width / 2;
		int textWidthHalf = textWidth / 2;
		int textX = (planetBounds.x + planetWidthHalf) - textWidthHalf;
		int textY = planetBounds.y + planetBounds.height + 5;
		g.drawString(planetName, textX, textY + metrics.getAscent());
	}

	@Override
	public void drawFrame(Graphics g) {
		Rectangle r = getPlanetBounds();
		g.drawOval(r.x, r.y, r.width - 1, r.height - 1);
	}

	@Override
	public Rectangle displayBox() {
		Rectangle bounds = getPlanetBounds();
		if (metrics != null) {
			int textHeight = 15 + metrics.getAscent();
			bounds.setBounds(bounds.x, bounds.y, bounds.width, bounds.height + textHeight);
		}
		return bounds;
	}

	public void repaint() {
		invalidate();
	}

	public Rectangle getPlanetBounds() {
		Rectangle r = super.displayBox();

		if (r.width > r.height)
			r.width = r.height;
		if (r.height > r.width)
			r.height = r.width;

		if (r.width > 120)
			r.width = 120;
		if (r.height > 120)
			r.height = 120;

		return r;
	}

	public Rectangle getTextBounds() {
		Rectangle planet = getPlanetBounds();
		int width = planet.width - 20;
		if (width < 50) {
			width = 50;
		}
		int height = 15;
		int fx = (planet.x + (planet.width / 2)) - (width / 2);
		int fy = planet.y + planet.height + 5;
		return new Rectangle(fx, fy, width, height);
	}

	public String getPlanetName() {
		return planetName;
	}
	
	public void setPlanetName(String planetName) {
		this.planetName = planetName;
	}

	@Override
	public String toString() {
		return String.format("Planet: %s", planetName);
	}

}