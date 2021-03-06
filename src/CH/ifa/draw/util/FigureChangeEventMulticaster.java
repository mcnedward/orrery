/*
 * @(#)FigureChangeEventMulticaster.java 5.1
 *
 */

package CH.ifa.draw.util;

import java.awt.AWTEventMulticaster;
import java.util.EventListener;

import CH.ifa.draw.framework.FigureChangeEvent;
import CH.ifa.draw.framework.FigureChangeListener;

/**
 * Manages a list of FigureChangeListeners to be notified of
 * specific FigureChangeEvents.
 */


public class FigureChangeEventMulticaster extends
    AWTEventMulticaster implements FigureChangeListener {

    public static FigureChangeListener add(FigureChangeListener a, FigureChangeListener b) {
        return (FigureChangeListener)addInternal(a, b);
    }

    protected static EventListener addInternal(FigureChangeListener a, FigureChangeListener b) {
	    if (a == null)  return b;
	    if (b == null)  return a;
	    return new FigureChangeEventMulticaster(a, b);
    }

    public static FigureChangeListener remove(FigureChangeListener l, FigureChangeListener oldl) {
	    return (FigureChangeListener) removeInternal(l, oldl);
    }

    protected static EventListener removeInternal(EventListener l, EventListener oldl) {
    	if (l == oldl || l == null) {
    	    return null;
    	} else if (l instanceof FigureChangeEventMulticaster) {
    	    return ((FigureChangeEventMulticaster)l).remove(oldl);
    	} else {
    	    return l;		// it's not here
    	}
    }

    public FigureChangeEventMulticaster(EventListener a, EventListener b) {
    	super(a, b);
    }

    @Override
	public void figureChanged(FigureChangeEvent e) {
        ((FigureChangeListener)a).figureChanged(e);
        ((FigureChangeListener)b).figureChanged(e);
    }

    @Override
	public void figureInvalidated(FigureChangeEvent e) {
        ((FigureChangeListener)a).figureInvalidated(e);
        ((FigureChangeListener)b).figureInvalidated(e);
    }


    @Override
	public void figureRemoved(FigureChangeEvent e) {
        ((FigureChangeListener)a).figureRemoved(e);
        ((FigureChangeListener)b).figureRemoved(e);
    }

    @Override
	public void figureRequestRemove(FigureChangeEvent e) {
        ((FigureChangeListener)a).figureRequestRemove(e);
        ((FigureChangeListener)b).figureRequestRemove(e);
    }

    @Override
	public void figureRequestUpdate(FigureChangeEvent e) {
        ((FigureChangeListener)a).figureRequestUpdate(e);
        ((FigureChangeListener)b).figureRequestUpdate(e);
    }

    @Override
	protected EventListener remove(EventListener oldl)
    {
        if (oldl == a)
            return b;
        if (oldl == b)
            return a;
        EventListener a2 = removeInternal(a, oldl);
        EventListener b2 = removeInternal(b, oldl);
        if (a2 == a && b2 == b)
            return this;
        else
            return addInternal((FigureChangeListener)a2, (FigureChangeListener)b2);
    }

}
