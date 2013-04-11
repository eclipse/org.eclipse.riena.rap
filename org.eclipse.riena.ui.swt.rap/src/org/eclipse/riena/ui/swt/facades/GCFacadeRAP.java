/*******************************************************************************
 * Copyright (c) 2007, 2011 compeople AG and others.
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
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.widgets.Display;

/**
 * Implements {@link GCFacade} for RAP.
 */
public class GCFacadeRAP extends GCFacade {

	@Override
	public int getAdvanceWidth(final GC gc, final char ch) {
		return gc.textExtent(String.valueOf(ch)).x;
	}

	@Override
	public void setAdvanced(final GC gc, final boolean isEnabled) {
		// do nothing
	}

	@Override
	public void setAntialias(final GC gc, final int option) {
		// do nothing
	}

	@Override
	public void setLineDash(final GC gc, final int[] dashes) {
		// do nothing
	}

	@Override
	public GC createGCFromImage(final Image img) {
		return new GC(Display.getCurrent());
	}

	@Override
	public Image createImage(final Display display, final int width, final int height) {
		final ImageData data = new ImageData(width, height, 24, new PaletteData(0xFF, 0xFF00, 0xFF0000));
		final Image img = new Image(display, data);
		return img;
	}

}
