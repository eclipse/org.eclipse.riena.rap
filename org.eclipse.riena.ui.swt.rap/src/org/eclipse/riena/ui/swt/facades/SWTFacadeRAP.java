/*******************************************************************************
 * Copyright (c) 2007, 2010 compeople AG and others.
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

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Tree;

import org.eclipse.riena.ui.swt.CompletionCombo;
import org.eclipse.riena.ui.swt.EmbeddedTitleBar;
import org.eclipse.riena.ui.swt.InfoFlyout;
import org.eclipse.riena.ui.swt.facades.internal.CompletionComboRAP;
import org.eclipse.riena.ui.swt.facades.internal.CompletionComboWithImageRAP;
import org.eclipse.riena.ui.swt.facades.internal.InfoFlyoutRAP;
import org.eclipse.riena.ui.swt.facades.internal.ModuleNavigationListenerRAP;

/**
 * Implements {@link SWTFacade} for RAP.
 */
public final class SWTFacadeRAP extends SWTFacade {

	@Override
	public void addEraseItemListener(final Table table, final Listener listener) {
		// do nothing
	}

	@Override
	public void addEraseItemListener(final Tree tree, final Listener listener) {
		// do nothing
	}

	@Override
	public void addFilterMouseExit(final Display display, final Listener listener) {
		// do nothing
	}

	@Override
	public void addFilterMouseMove(final Display display, final Listener listener) {
		// do nothing
	}

	@Override
	public void addFilterMouseWheel(final Display display, final Listener listener) {
		// do nothing
	}

	@Override
	public void addMouseMoveListener(final Control control, final MouseMoveListener listener) {
		// do nothing
	}

	@Override
	public void addMouseTrackListener(final Control control, final MouseTrackListener listener) {
		// do nothing
	}

	@Override
	public void addPaintItemListener(final Tree tree, final Listener listener) {
		// do nothing
	}

	@Override
	public void addPaintListener(final Control control, final EventListener listener) {
		// do nothing
	}

	@Override
	public void attachModuleNavigationListener(final Tree tree) {
		new ModuleNavigationListenerRAP(tree);
	}

	@Override
	public void copyEventKeyLocation(final Event source, final Event target) {
	}

	@Override
	public CompletionCombo createCompletionCombo(final Composite parent, final int style) {
		return new CompletionComboRAP(parent, style);
	}

	@Override
	public CompletionCombo createCompletionComboWithImage(final Composite parent, final int style) {
		return new CompletionComboWithImageRAP(parent, style);
	}

	@Override
	public Cursor createCursor(final Display display, final Image cursorImage, final int alternateStyle) {
		return new Cursor(display, alternateStyle);
	}

	@Override
	public EventListener createDisabledPainter() {
		return null;
	}

	@Override
	public void createGrabCornerListenerWithTracker(final Control control) {
		// do nothing
	}

	@Override
	public InfoFlyout createInfoFlyout(final Composite parent) {
		return new InfoFlyoutRAP(parent);
	}

	@Override
	public void createEmbeddedTitleBarToolTip(final EmbeddedTitleBar parent) {
		// do nothing
	}

	@Override
	public void createSubModuleToolTip(final Tree parent, final ILabelProvider labelProvider) {
		// do nothing
	}

	@Override
	public Listener createTreeItemEraserAndPainter() {
		return null;
	}

	@Override
	public Control getCursorControl(final Display display) {
		return display.getCursorControl();
	}

	@Override
	public boolean postEvent(final Display display, final Event event) {
		return false;
	}

	@Override
	public void removeEraseItemListener(final Table table, final Listener listener) {
		// do nothing
	}

	@Override
	public void removeEraseItemListener(final Tree tree, final Listener listener) {
		// do nothing
	}

	@Override
	public void removeFilterMouseWheel(final Display display, final Listener listener) {
		// do nothing
	}

	@Override
	public void removeMouseMoveListener(final Control control, final Object listener) {
		// do nothing
	}

	@Override
	public void removeMouseTrackListener(final Control control, final MouseTrackListener listener) {
		// do nothing
	}

	@Override
	public void removePaintItemListener(final Tree tree, final Listener listener) {
		// do nothing
	}

	@Override
	public void removePaintListener(final Control control, final EventListener listener) {
		// do nothing
	}

	@Override
	public boolean traverse(final Control control, final int traversal) {
		return false;
	}

}
