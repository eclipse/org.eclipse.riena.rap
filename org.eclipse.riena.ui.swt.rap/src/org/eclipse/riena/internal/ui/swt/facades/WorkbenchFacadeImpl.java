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
package org.eclipse.riena.internal.ui.swt.facades;

import org.osgi.service.log.LogService;

import org.eclipse.equinox.log.Logger;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISourceProvider;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchPage;
import org.eclipse.ui.services.ISourceProviderService;

import org.eclipse.riena.core.Log4r;

/**
 * RAP specific implementation.
 */
public class WorkbenchFacadeImpl extends WorkbenchFacade {

	private static final Logger LOGGER = Log4r.getLogger(WorkbenchFacadeImpl.class);

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.riena.internal.ui.swt.facades.WorkbenchFacade#closeWorkbench
	 * ()
	 */
	@Override
	public boolean closeWorkbench() {
		return PlatformUI.getWorkbench().close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.riena.internal.ui.swt.facades.WorkbenchFacade#
	 * getActiveWindowShell()
	 */
	@Override
	public Shell getActiveWindowShell() {
		if (PlatformUI.isWorkbenchRunning() && PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null) {
			return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.riena.internal.ui.swt.facades.WorkbenchFacade#getWorkbenchDisplay
	 * ()
	 */
	@Override
	public Display getWorkbenchDisplay() {
		if (PlatformUI.isWorkbenchRunning() && PlatformUI.getWorkbench().getDisplay() != null) {
			return PlatformUI.getWorkbench().getDisplay();
		} else {
			return null;
		}
	}

	private ISourceProviderService getSourceProviderService() {
		return (ISourceProviderService) PlatformUI.getWorkbench().getService(ISourceProviderService.class);
	}

	@Override
	public ISourceProvider[] getSourceProviders() {
		try {
			final ISourceProviderService sourceProviderService = getSourceProviderService();
			if (sourceProviderService == null) {
				return new ISourceProvider[0];
			} else {
				return sourceProviderService.getSourceProviders();
			}
		} catch (final IllegalStateException ex) {
			LOGGER.log(LogService.LOG_ERROR, "No service for ISourceProviderService!", ex); //$NON-NLS-1$
			return new ISourceProvider[0];
		}
	}

}
