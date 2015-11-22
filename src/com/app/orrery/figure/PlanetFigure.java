package com.app.orrery.figure;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Vector;

import com.app.orrery.handle.planet.NorthEastHandle;
import com.app.orrery.handle.planet.NorthWestHandle;
import com.app.orrery.handle.planet.SouthEastHandle;
import com.app.orrery.handle.planet.SouthWestHandle;

import CH.ifa.draw.figure.EllipseFigure;
import CH.ifa.draw.framework.Handle;

/**
 * @author Edward McNealy <edwardmcn64@gmail.com> - Oct 31, 2015
 *
 */
public class PlanetFigure extends EllipseFigure implements IConnectable {
	private static final long serialVersionUID = -3146574478769258549L;

	public static final int PLANET_SIZE = 120;

	private String planetName;
	private Font fFont;
	// The FontMetrics will be set when the planet name is ready to be drawn
	private FontMetrics metrics;
	private Color fColor;
	private boolean connected = false;
	private IConnectable connectedObject;

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
		return getPlanetBounds();
	}

	@Override
	public void basicDisplayBox(Point origin, Point corner) {
		int dragX = corner.x, dragY = corner.y;
		if (dragX < origin.x && dragX <= origin.x - PLANET_SIZE) {
			corner.x = origin.x - PLANET_SIZE;
		}
		if (dragY < origin.y && dragY <= origin.y - PLANET_SIZE) {
			corner.y = origin.y - PLANET_SIZE;
		}
		super.basicDisplayBox(origin, corner);
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

	@Override
	public Vector<Handle> handles() {
		Vector<Handle> handles = new Vector<Handle>();
		handles.add(new NorthEastHandle(this));
		handles.add(new NorthWestHandle(this));
		handles.add(new SouthEastHandle(this));
		handles.add(new SouthWestHandle(this));
		return handles;
	}

	@Override
	public boolean canConnect() {
		return true;
	}
	
	public void repaint() {
		invalidate();
	}

	public void setColor(Color color) {
		fColor = color;
	}

	public String getPlanetName() {
		return planetName;
	}

	public void setPlanetName(String planetName) {
		this.planetName = planetName;
	}

	@Override
	public boolean isConnected() {
		return connected;
	}

	@Override
	public void setConnected(boolean connected, IConnectable connectedObject) {
		this.connected = connected;
		this.connectedObject = connected ? connectedObject : null;
	}
	
	@Override
	public IConnectable getConnectedObject() {
		return connectedObject;
	}

	@Override
	public String toString() {
		return String.format("Planet: %s", planetName);
	}

}
