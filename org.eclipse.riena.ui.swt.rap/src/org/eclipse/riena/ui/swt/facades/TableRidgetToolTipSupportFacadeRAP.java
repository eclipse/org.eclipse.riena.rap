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

import org.eclipse.jface.viewers.ColumnViewer;

/**
 * <i>Implements</i> {@link TableRidgetToolTipSupportFacade} for RAP.
 */
public class TableRidgetToolTipSupportFacadeRAP extends TableRidgetToolTipSupportFacade {

	@Override
	public void disable() {
		// not supported
	}

	@Override
	public void enableFor(final ColumnViewer viewer) {
		// not supported
	}

	@Override
	public boolean isSupported() {
		return false;
	}

}
