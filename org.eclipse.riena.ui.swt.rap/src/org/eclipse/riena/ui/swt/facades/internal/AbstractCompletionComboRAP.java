/*******************************************************************************
 * Copyright (c) 2000, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation (CCombo)
 *     compeople AG    - adjustments for autocompletion
 *******************************************************************************/
package org.eclipse.riena.ui.swt.facades.internal;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Composite;

import org.eclipse.riena.ui.swt.CompletionCombo;

/**
 * TODO [ev] docs
 */
abstract class AbstractCompletionComboRAP extends CompletionCombo {

	protected AbstractCompletionComboRAP(final Composite parent, final int style) {
		super(parent, style);
	}

	@Override
	public void addModifyListener(final ModifyListener listener) {
		checkWidget();
		ModifyEvent.addListener(this, listener);
	}

	@Override
	public void addSelectionListener(final SelectionListener listener) {
		checkWidget();
		SelectionEvent.addListener(this, listener);
	}

	@Override
	public void addVerifyListener(final VerifyListener listener) {
		checkWidget();
		VerifyEvent.addListener(this, listener);
	}

	@Override
	public void removeModifyListener(final ModifyListener listener) {
		checkWidget();
		ModifyEvent.removeListener(this, listener);
	}

	@Override
	public void removeSelectionListener(final SelectionListener listener) {
		checkWidget();
		SelectionEvent.removeListener(this, listener);
	}

	@Override
	public void removeVerifyListener(final VerifyListener listener) {
		checkWidget();
		VerifyEvent.addListener(this, listener);
	}

}