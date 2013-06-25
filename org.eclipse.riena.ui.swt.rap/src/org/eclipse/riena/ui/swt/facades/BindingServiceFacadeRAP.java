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

import java.io.IOException;

import org.eclipse.jface.bindings.Binding;
import org.eclipse.jface.bindings.Scheme;
import org.eclipse.ui.keys.IBindingService;

/**
 * Implements {@link SWTFacade} for RAP.
 */
public final class BindingServiceFacadeRAP extends BindingServiceFacade {

	@Override
	public Binding[] getBindings(final IBindingService service) {
		return null;
	}

	@Override
	public Scheme getScheme(final IBindingService service, final String schemeId) {
		return null;
	}

	@Override
	public void savePreferences(final IBindingService service, final Scheme scheme, final Binding[] bindings)
			throws IOException {
		// do nothing
	}

}
