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

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.equinox.log.Logger;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISourceProvider;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.internal.WorkbenchPage;
import org.eclipse.ui.services.ISourceProviderService;

import org.eclipse.riena.core.Log4r;
import org.eclipse.riena.navigation.ApplicationNodeManager;
import org.eclipse.riena.navigation.IApplicationNode;
import org.eclipse.riena.navigation.INavigationNode;
import org.eclipse.riena.navigation.ui.swt.component.MenuCoolBarComposite;
import org.eclipse.riena.navigation.ui.swt.presentation.SwtViewId;
import org.eclipse.riena.navigation.ui.swt.presentation.SwtViewProvider;
import org.eclipse.riena.navigation.ui.swt.views.NavigationViewPart;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.riena.internal.ui.swt.facades.WorkbenchFacade#switchToNavigation(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public boolean switchToNavigation(final ExecutionEvent event) {
		final IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		final IWorkbenchPage page = window.getActivePage();
		IViewPart navigationView = null;
		if (page != null) {
			final IViewReference[] viewRefs = page.getViewReferences();
			for (final IViewReference ref : viewRefs) {
				if (NavigationViewPart.ID.equals(ref.getId())) {
					navigationView = ref.getView(false);
					break;
				}
			}
		}
		if (navigationView != null) {
			navigationView.setFocus();
			return true;
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.riena.internal.ui.swt.facades.WorkbenchFacade#switchToWorkarea(java.lang.String)
	 */
	@Override
	public boolean switchToWorkarea(final ExecutionEvent event) {
		final String viewId = getViewId(getActiveNode());
		if (viewId == null) {
			return false;
		}
		final IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		final IWorkbenchPage page = window.getActivePage();
		for (final IViewReference viewRef : page.getViewReferences()) {
			if (viewId.equals(getFullId(viewRef))) {
				final IViewPart view = viewRef.getView(false);
				if (view != null) {
					view.setFocus();
					return true;
				}
				break;
			}
		}
		return false;
	}

	private INavigationNode<?> getActiveNode() {
		final IApplicationNode appNode = ApplicationNodeManager.getApplicationNode();
		return appNode.getNavigationProcessor().getSelectedNode();
	}

	private String getViewId(final INavigationNode<?> node) {
		String result = null;
		if (node != null) {
			final SwtViewId viewId = SwtViewProvider.getInstance().getSwtViewId(node);
			if (viewId != null) {
				result = viewId.getCompoundId();
			}
		}
		return result;
	}

	private String getFullId(final IViewReference viewRef) {
		String result = viewRef.getId();
		if (viewRef.getSecondaryId() != null) {
			result = result + ":" + viewRef.getSecondaryId(); //$NON-NLS-1$
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.riena.internal.ui.swt.facades.WorkbenchFacade#switchToWindowMenu(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public boolean switchToWindowMenu(final ExecutionEvent event) {
		final IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		final Shell shell = window.getShell();
		for (final Control child : shell.getChildren()) {
			if (child instanceof MenuCoolBarComposite) {
				child.setFocus();
				return true;
			}
		}
		return false;
	}

}
