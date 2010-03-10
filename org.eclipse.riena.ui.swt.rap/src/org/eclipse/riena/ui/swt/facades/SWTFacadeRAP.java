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

import java.util.EventListener;

import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Tree;

/**
 * Implements {@link SWTFacade} for RAP.
 */
public final class SWTFacadeRAP extends SWTFacade {

	@Override
	public void addEraseItemListener(Table table, Listener listener) {
		// do nothing
	}

	@Override
	public void addEraseItemListener(Tree tree, Listener listener) {
		// do nothing
	}

	@Override
	public void addMouseTrackListener(Control control, MouseTrackListener listener) {
		// do nothing
	}

	@Override
	public void addPaintItemListener(Tree tree, Listener listener) {
		// do nothing
	}

	@Override
	public void addPaintListener(Control control, EventListener listener) {
		// do nothing
	}

	@Override
	public EventListener createDisabledPainter() {
		return null;
	}

	@Override
	public Listener createTreeItemEraserAndPainter() {
		return null;
	}

	@Override
	public void removeEraseItemListener(Table table, Listener listener) {
		// do nothing
	}

	@Override
	public void removeEraseItemListener(Tree tree, Listener listener) {
		// do nothing
	}

	@Override
	public void removeMouseTrackListener(Control control, MouseTrackListener listener) {
		// do nothing
	}

	@Override
	public void removePaintItemListener(Tree tree, Listener listener) {
		// do nothing
	}

	@Override
	public void removePaintListener(Control control, EventListener listener) {
		// do nothing
	}

	@Override
	public void setDigits(Spinner spinner, int digits) {
		throw new UnsupportedOperationException("Spinner.setDigits(...) is not available");
	}

}
