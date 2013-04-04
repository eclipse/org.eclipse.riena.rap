package org.eclipse.riena.navigation.ui.swt.facades;

import org.eclipse.riena.navigation.ui.swt.facades.internal.ModuleNavigationListenerRAP;
import org.eclipse.swt.widgets.Tree;

public class NavigationFacadeRAP extends NavigationFacade {

	@Override
	public void attachModuleNavigationListener(Tree tree) {
		new ModuleNavigationListenerRAP(tree);
	}

}
