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

package org.eclipse.riena.ui.swt.facades;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

/**
 * Implements {@link ClipboardFacade} for RAP.
 */
public class ClipboardFacadeRAP extends ClipboardFacade {

	@Override
	public void cut(final Text control) {
		// do nothing
	}

	@Override
	public String getTextFromClipboard(final Display display) {
		return null;
	}

}
