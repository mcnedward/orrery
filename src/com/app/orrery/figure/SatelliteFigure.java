package com.app.orrery.figure;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.app.orrery.handle.satellite.ISatelliteHandle;
import com.app.orrery.handle.satellite.SatelliteEastHandle;
import com.app.orrery.handle.satellite.SatelliteNorthHandle;
import com.app.orrery.handle.satellite.SatelliteSouthHandle;
import com.app.orrery.handle.satellite.SatelliteWestHandle;
import com.app.orrery.util.GravityConnection;

import CH.ifa.draw.connector.ChopEllipseConnector;
import CH.ifa.draw.figure.AttributeFigure;
import CH.ifa.draw.figure.EllipseFigure;
import CH.ifa.draw.figure.RectangleFigure;
import CH.ifa.draw.framework.Connector;
import CH.ifa.draw.framework.Handle;
import CH.ifa.draw.handle.ConnectionHandle;
import CH.ifa.draw.locator.RelativeLocator;

/**
 * @author Edward McNealy <edwardmcn64@gmail.com> - Nov 17, 2015
 *
 */
public class SatelliteFigure extends AttributeFigure implements IConnectable {
	private static final long serialVersionUID = 1L;

	private static final int BODY_SIZE = 20;
	private static final int PANEL_WIDTH = 10;
	private static final int PANEL_HEIGHT = 30;

	private EllipseFigure body;
	private RectangleFigure topPanel;
	private RectangleFigure bottomPanel;

	private boolean selected;
	private boolean connected = false;
	private IConnectable connectedObject;

	public SatelliteFigure() {
		this(new Point(0, 0), new Point(0, 0));
	}

	public SatelliteFigure(Point origin, Point corner) {
		basicDisplayBox(origin, corner);
	}

	@Override
	public void drawBackground(Graphics g) {
		// Draw Panels first
		g.setColor(Color.GRAY);
		Rectangle topPanelBounds = topPanel.displayBox();
		g.fillRect(topPanelBounds.x, topPanelBounds.y, topPanelBounds.width, topPanelBounds.height);

		Rectangle bottomPanelBounds = bottomPanel.displayBox();
		g.fillRect(bottomPanelBounds.x, bottomPanelBounds.y, bottomPanelBounds.width, bottomPanelBounds.height);

		Rectangle bodyBounds = body.displayBox();
		g.setColor(Color.WHITE);
		g.fillOval(bodyBounds.x, bodyBounds.y, bodyBounds.width, bodyBounds.height);
	}

	@Override
	public void drawFrame(Graphics g) {
		Rectangle r = body.displayBox();
		g.setColor(Color.BLACK);
		g.drawOval(r.x, r.y, r.width - 1, r.height - 1);

		if (selected) {
			for (ISatelliteHandle handle : handles) {
				handle.updateBounds(displayBox());
			}
		}
	}

	@Override
	public void basicDisplayBox(Point origin, Point corner) {
		int bodyX = origin.x - (BODY_SIZE / 2);
		int bodyY = origin.y - (BODY_SIZE / 2);
		body = new EllipseFigure();
		body.displayBox(new Rectangle(bodyX, bodyY, BODY_SIZE, BODY_SIZE));

		int panelX = origin.x - (PANEL_WIDTH / 2);
		int topPanelY = origin.y - PANEL_HEIGHT;
		int bottomPanelY = origin.y;
		topPanel = new RectangleFigure();
		bottomPanel = new RectangleFigure();
		topPanel.displayBox(new Rectangle(panelX, topPanelY, PANEL_WIDTH, PANEL_HEIGHT));
		bottomPanel.displayBox(new Rectangle(panelX, bottomPanelY, PANEL_WIDTH, PANEL_HEIGHT));
	}

	@Override
	public Rectangle displayBox() {
		Rectangle topPanelBounds = topPanel.displayBox();
		Rectangle bodyBounds = body.displayBox();
		Rectangle bounds = new Rectangle(bodyBounds.x, topPanelBounds.y, BODY_SIZE, (PANEL_HEIGHT * 2));
		return bounds;
	}

	public void repaint() {
		invalidate();
	}

	@Override
	protected void basicMoveBy(int dx, int dy) {
		Rectangle r1 = body.displayBox();
		r1.translate(dx, dy);
		body.displayBox(r1);
		Rectangle r2 = topPanel.displayBox();
		r2.translate(dx, dy);
		topPanel.displayBox(r2);
		Rectangle r3 = bottomPanel.displayBox();
		r3.translate(dx, dy);
		bottomPanel.displayBox(r3);

		selected = true;
		repaint();
	}

	List<ISatelliteHandle> handles = new ArrayList<>();

	@Override
	public Vector<Handle> handles() {
		Vector<Handle> handles = createBasicHandles();
		Vector<Handle> connectionHandles = createConnectionHandles();
		for (Handle handle : connectionHandles)
			handles.add(handle);
		return handles;
	}
	
	private Vector<Handle> createBasicHandles() {
		Vector<Handle> handles = new Vector<>();
		ISatelliteHandle north = new SatelliteNorthHandle(this);
		ISatelliteHandle east = new SatelliteEastHandle(this);
		ISatelliteHandle west = new SatelliteWestHandle(this);
		ISatelliteHandle south = new SatelliteSouthHandle(this);
		handles.add(north);
		handles.add(east);
		handles.add(west);
		handles.add(south);
		// These are the borders
		this.handles.add(north);
		this.handles.add(east);
		this.handles.add(west);
		this.handles.add(south);
		return handles;
	}
	
	private Vector<Handle> createConnectionHandles() {
		GravityConnection prototype = new GravityConnection();
		Vector<Handle> handles = new Vector<Handle>();
        handles.addElement(new ConnectionHandle(this, RelativeLocator.east(), prototype));
        handles.addElement(new ConnectionHandle(this, RelativeLocator.west(), prototype));
        handles.addElement(new ConnectionHandle(this, RelativeLocator.south(), prototype));
        handles.addElement(new ConnectionHandle(this, RelativeLocator.north(), prototype));
        return handles;
	}

	@Override
	public Insets connectionInsets() {
		Rectangle r = displayBox();
		int cx = r.width / 2;
		int cy = r.height / 2;
		return new Insets(cy, cx, cy, cx);
	}

	@Override
	public Connector connectorAt(int x, int y) {
		return new ChopEllipseConnector(this);
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

}
