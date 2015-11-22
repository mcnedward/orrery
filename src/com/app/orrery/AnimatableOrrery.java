/**
 * 
 */
package com.app.orrery;

import java.util.Enumeration;

import CH.ifa.draw.framework.Figure;
import CH.ifa.draw.standard.StandardDrawing;
import CH.ifa.draw.util.Animatable;

/**
 * @author Edward - Nov 21, 2015
 *
 */
public class AnimatableOrrery extends StandardDrawing implements Animatable {
	private static final long serialVersionUID = 1L;

	public AnimatableOrrery() {
		super();
	}
	
	@Override
	public void animationStep() {
		Enumeration<Figure> figures = figures();
		while (figures.hasMoreElements()) {
			Figure figure = figures.nextElement();
			if (figure instanceof Animatable) {
				Animatable anim = (Animatable) figure;
				anim.animationStep();
			}
		}
	}

}
