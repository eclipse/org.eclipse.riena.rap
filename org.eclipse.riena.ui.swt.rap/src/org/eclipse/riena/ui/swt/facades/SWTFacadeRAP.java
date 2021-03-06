/*******************************************************************************
 * Copyright (c) 2007, 2014 compeople AG and others.
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
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.rap.rwt.service.ServerPushSession;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Tree;

import org.eclipse.riena.ui.swt.CompletionCombo;
import org.eclipse.riena.ui.swt.EmbeddedTitleBar;
import org.eclipse.riena.ui.swt.InfoFlyout;
import org.eclipse.riena.ui.swt.facades.internal.CompletionComboRAP;
import org.eclipse.riena.ui.swt.facades.internal.CompletionComboWithImageRAP;
import org.eclipse.riena.ui.swt.facades.internal.InfoFlyoutRAP;

/**
 * Implements {@link SWTFacade} for RAP.
 */
public final class SWTFacadeRAP extends SWTFacade {
	private final Map<String, ServerPushSession> pushSessions = new HashMap<String, ServerPushSession>();

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
	public void createSubApplicationToolTip(final Control parent) {
		// do nothing
	}

	@Override
	public Listener createTreeItemEraserAndPainter() {
		return null;
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

	/**
	 * {@inheritDoc}
	 * <p>
	 * The RAP Implementation of {@link ScrollBar} doesn't support the property {@code increment}, because of that this implementation do nothing.
	 */
	@Override
	public void setIncrement(final ScrollBar scrollBar, final int value) {
		// do nothing
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The RAP Implementation of {@link ScrollBar} doesn't support the property {@code increment}, because of that this implementation do nothing.
	 */
	@Override
	public void setPageIncrement(final ScrollBar scrollBar, final int value) {
		// do nothing
	}

	@Override
	public void beforeInfoFlyoutShow(final InfoFlyout flyout) {
		final ServerPushSession serverPushSession = new ServerPushSession();
		serverPushSession.start();
		pushSessions.put(flyout.getPropertyName(), serverPushSession);
	}

	@Override
	public void afterInfoFlyoutShow(final InfoFlyout flyout) {
		final ServerPushSession serverPushSession = pushSessions.remove(flyout.getPropertyName());
		if (serverPushSession != null) {
			serverPushSession.stop();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.riena.ui.swt.facades.SWTFacade#textExtent(org.eclipse.swt .graphics.GC, java.lang.String, int)
	 */
	@Override
	public Point textExtent(final GC gc, final String string, final int flags) {
		// RAP does not support flags
		return gc.textExtent(string);
	}

	// protected methods
	////////////////////

	@Override
	protected void addModifyListeners(final Control control, final Object[] listeners) {
		for (final Object listener : listeners) {
			//			if (listener instanceof ModifyListener) {
			//				ModifyEvent.addListener(control, (ModifyListener) listener);
			//			} else 
			if (listener instanceof Listener || listener instanceof ModifyListener) {
				control.addListener(SWT.Modify, (Listener) listener);
			} else {
				throw new IllegalArgumentException("unsupported listener type: " + listener); //$NON-NLS-1$
			}
		}
	}

	@Override
	protected void addVerifyListeners(final Control control, final Object[] listeners) {
		for (final Object listener : listeners) {
			//			if (listener instanceof VerifyListener) {
			//				VerifyEvent.addListener(control, (VerifyListener) listener);
			//			} else 
			if (listener instanceof Listener || listener instanceof VerifyListener) {
				control.addListener(SWT.Verify, (Listener) listener);
			} else {
				throw new IllegalArgumentException("unsupported listener type: " + listener); //$NON-NLS-1$
			}
		}
	}

	@Override
	protected Object[] removeModifyListeners(final Control control) {
		final Listener[] typedListeners = control.getListeners(SWT.Modify);
		//		final Object[] typedListeners = ModifyEvent.getListeners(control);
		for (final Listener listener : typedListeners) {
			//			ModifyEvent.removeListener(control, (ModifyListener) listener);
			control.removeListener(SWT.Modify, listener);
		}
		final Object[] untypedListeners = control.getListeners(SWT.Modify);
		for (final Object listener : untypedListeners) {
			control.removeListener(SWT.Modify, (Listener) listener);
		}
		final Object[] result = new Object[typedListeners.length + untypedListeners.length];
		System.arraycopy(typedListeners, 0, result, 0, typedListeners.length);
		System.arraycopy(untypedListeners, 0, result, typedListeners.length, untypedListeners.length);
		return result;
	}

	@Override
	protected Object[] removeVerifyListeners(final Control control) {
		final Listener[] typedListeners = control.getListeners(SWT.Verify);
		//		final Object[] typedListeners = VerifyEvent.getListeners(control);
		for (final Listener listener : typedListeners) {
			//			VerifyEvent.removeListener(control, (VerifyListener) listener);
			control.removeListener(SWT.Verify, listener);
		}
		final Object[] untypedListeners = control.getListeners(SWT.Verify);
		for (final Object listener : untypedListeners) {
			control.removeListener(SWT.Verify, (Listener) listener);
		}
		final Object[] result = new Object[typedListeners.length + untypedListeners.length];
		System.arraycopy(typedListeners, 0, result, 0, typedListeners.length);
		System.arraycopy(untypedListeners, 0, result, typedListeners.length, untypedListeners.length);
		return result;
	}

}
