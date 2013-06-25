/*******************************************************************************
 * Copyright (c) 2007, 2013 compeople AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    compeople AG - initial API and implementation
 *******************************************************************************/
package org.eclipse.riena.ui.swt.facades.internal;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.riena.ui.swt.InfoFlyout;
import org.eclipse.riena.ui.swt.RienaMessageDialog;

/**
 * Platform specific {@link InfoFlyout} for RAP.
 * <p>
 * Implementation note: current this is a mock using a MessageDialog.
 */
public class InfoFlyoutRAP extends InfoFlyout {

	private final Composite parent;
	private String message;
	private String icon;

	public InfoFlyoutRAP(final Composite parent) {
		this.parent = parent;
	}

	@Override
	public void openFlyout() {
		final String title = "MOCK InfoFlyout"; //$NON-NLS-1$
		final Shell shell = parent.getShell();
		RienaMessageDialog.openInformation(shell, title, String.valueOf(message));
	}

	@Override
	public void setMessage(final String message) {
		this.message = message;
	}

	@Override
	public void setIcon(final String icon) {
		this.icon = icon;
	}

	@Override
	public void waitForClosing() {
		// TODO Auto-generated method stub
	}

}
