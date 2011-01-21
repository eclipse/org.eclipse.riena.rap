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
package org.eclipse.riena.ui.swt.lnf.rap;

import org.eclipse.rwt.internal.lifecycle.UICallBackServiceHandler;
import org.eclipse.rwt.lifecycle.WidgetUtil;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

import org.eclipse.riena.core.util.Nop;
import org.eclipse.riena.ui.ridgets.IControlDecoration;
import org.eclipse.riena.ui.ridgets.swt.MarkerSupport;

/**
 * Helper class for SWT Ridgets under RAP to delegate their marker issues to.
 * The markers are visiualised throu CSS.
 */
public class CopyOfRAPMarkerSupport extends MarkerSupport {
	private static final long FLASH_DURATION_MS = 300;
	private boolean isFlashInProgress = false;
	private IControlDecoration errorDecoration;

	@Override
	protected void addError(final Control control) {
		control.setData(WidgetUtil.CUSTOM_VARIANT, "Emo"); //$NON-NLS-1$
	}

	@Override
	protected void clearError(final Control control) {
		control.setData(WidgetUtil.CUSTOM_VARIANT, null);
	}

	@Override
	public synchronized void flash() {
		final Control control = getUIControl();
		final String ridgetID = getRidget().getID();
		if (!isFlashInProgress && control != null) {
			isFlashInProgress = true;

			UICallBackServiceHandler.activateUICallBacksFor(ridgetID);

			if (errorDecoration == null) {
				errorDecoration = createErrorDecoration(control);
			}

			errorDecoration.show();

			final Display display = control.getDisplay();
			final Runnable op = new Runnable() {
				public void run() {
					try {
						Thread.sleep(FLASH_DURATION_MS);
					} catch (final InterruptedException e) {
						Nop.reason("ignore"); //$NON-NLS-1$
					} finally {
						display.syncExec(new Runnable() {
							public void run() {
								if (!control.isDisposed()) {
									errorDecoration.hide();
								}
								UICallBackServiceHandler.deactivateUICallBacksFor(ridgetID);
							}
						});
						isFlashInProgress = false;
					}
				}
			};

			new Thread(op).start();
		}
	}
}
