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
package org.eclipse.riena.ui.swt.facades.internal;

import org.eclipse.rwt.internal.lifecycle.UICallBackServiceHandler;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import org.eclipse.riena.navigation.INavigationNode;
import org.eclipse.riena.navigation.ui.swt.views.ModuleNavigationListener;

/**
 * Adds RAP specific behavior to {@link ModuleNavigationListener}.
 */
public class ModuleNavigationListenerRAP extends ModuleNavigationListener {

	public ModuleNavigationListenerRAP(final Tree moduleTree) {
		super(moduleTree);
	}

	@Override
	protected NodeSwitcher createNodeSwitcher(final TreeItem item) {
		return new NodeSwitcherRAP(item.getDisplay(), (INavigationNode<?>) item.getData());
	}

	private final static class NodeSwitcherRAP extends ModuleNavigationListener.NodeSwitcher {
		private final String id;

		NodeSwitcherRAP(final Display display, final INavigationNode<?> node) {
			super(display, node);
			id = node.getNodeId().getInstanceId();
		}

		@Override
		public synchronized void start() {
			// the callback must be activated from the UIThread not from this thread
			UICallBackServiceHandler.activateUICallBacksFor(id);
			super.start();
		}

		@Override
		public void run() {
			try {
				super.run();
			} finally {
				display.syncExec(new Runnable() {
					public void run() {
						UICallBackServiceHandler.deactivateUICallBacksFor(id);
					}
				});
			}
		}
	}

}
