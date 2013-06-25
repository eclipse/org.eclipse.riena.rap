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
package org.eclipse.riena.navigation.ui.swt.facades;

import org.eclipse.riena.navigation.ui.swt.facades.internal.ModuleNavigationListenerRAP;
import org.eclipse.swt.widgets.Tree;

public class NavigationFacadeRAP extends NavigationFacade {

	@Override
	public void attachModuleNavigationListener(Tree tree) {
		new ModuleNavigationListenerRAP(tree);
	}

}
