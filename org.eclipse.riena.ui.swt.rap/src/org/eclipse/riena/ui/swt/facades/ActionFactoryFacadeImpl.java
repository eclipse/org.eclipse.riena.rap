/*******************************************************************************
 * Copyright (c) 2007, 2009 compeople AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    compeople AG - initial API and implementation
 *******************************************************************************/
package org.eclipse.riena.ui.swt.facades;

import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

/**
 * Implements {@link ActionFactoryFacade} for RAP.
 */
public final class ActionFactoryFacadeImpl extends ActionFactoryFacade {

	/**
	 * Always returns null.
	 */
	@Override
	public IWorkbenchAction createAboutAction(IWorkbenchWindow window) {
		return null;
	}

}
