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

import org.eclipse.jface.dialogs.IDialogConstants;

/**
 * Implements {@link DialogConstantFacade} for RAP. Provides access to common
 * Dialog-Button labels.
 */
public final class DialogConstantsFacadeRAP extends DialogConstantsFacade {

	@Override
	public String getOkLabel() {
		return IDialogConstants.get().OK_LABEL;
	}

	@Override
	public String getCancelLabel() {
		return IDialogConstants.get().CANCEL_LABEL;
	}

	@Override
	public String getYesLabel() {
		return IDialogConstants.get().YES_LABEL;
	}

	@Override
	public String getNoLabel() {
		return IDialogConstants.get().NO_LABEL;
	}

}
