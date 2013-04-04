package org.eclipse.riena.navigation.ui.swt.facades.internal;

import org.eclipse.rap.rwt.service.ServerPushSession;
import org.eclipse.riena.navigation.ui.swt.views.ModuleNavigationListener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class ModuleNavigationListenerRAP extends ModuleNavigationListener {

	public ModuleNavigationListenerRAP(final Tree moduleTree) {
		super(moduleTree);
	}

	@Override
	protected NodeSwitcher createNodeSwitcher(final TreeItem item) {
		return new NodeSwitcherRAP(item);
	}

	private final static class NodeSwitcherRAP extends ModuleNavigationListener.NodeSwitcher {

		private final ServerPushSession pushSession;

		NodeSwitcherRAP(final TreeItem item) {
			super(item);
			pushSession = new ServerPushSession();
		}

		@Override
		public synchronized void start() {
			// the callback must be activated from the UIThread not from this thread
			pushSession.start();
			super.start();
		}

		@Override
		public void run() {
			try {
				super.run();
			} finally {
				display.syncExec(new Runnable() {
					public void run() {
						pushSession.stop();
					}
				});
			}
		}
	}

}
