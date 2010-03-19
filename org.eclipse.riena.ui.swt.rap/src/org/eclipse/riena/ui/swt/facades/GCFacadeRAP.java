/*******************************************************************************
 * Copyright (c) 2007, 2009 compeople AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    compeople AG - initial API and implementation
 *******************************************************************************/

package org.eclipse.riena.ui.swt.facades;

import org.eclipse.swt.graphics.GC;

/**
 * Implements {@link GCFacade} for RAP.
 */
public class GCFacadeRAP extends GCFacade {

	@Override
	public void drawLine(GC gc, int x1, int y1, int x2, int y2) {
		// do nothing
	}

	@Override
	public void drawRoundRectangle(GC gc, int x, int y, int width, int height, int arcWidth, int arcHeight) {
		// do nothing
	}

	@Override
	public void setAdvanced(GC gc, boolean isEnabled) {
		// do nothing
	}

	@Override
	public void setAntialias(GC gc, int option) {
		// do nothing
	}

}
