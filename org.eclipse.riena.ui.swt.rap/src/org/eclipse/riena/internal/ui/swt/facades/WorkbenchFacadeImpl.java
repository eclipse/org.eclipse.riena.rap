/*******************************************************************************
 * Copyright (c) 2007, 2012 compeople AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    compeople AG - initial API and implementation
 *******************************************************************************/
package org.eclipse.riena.internal.ui.swt.facades;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchPage;

/**
 * RAP specific implementation.
 */
public class WorkbenchFacadeImpl extends WorkbenchFacade {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.riena.internal.ui.swt.facades.RcpFacade#showView(org.eclipse
	 * .ui.IWorkbenchPage, org.eclipse.ui.IViewReference)
	 */
	@Override
	public void showView(final IWorkbenchPage page, final IViewReference viewRef) {
		((WorkbenchPage) page).getActivePerspective().bringToTop(viewRef);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.riena.internal.ui.swt.facades.WorkbenchFacade#getActiveShell
	 * ()
	 */
	@Override
	public Shell getActiveShell() {
		return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	}

}
