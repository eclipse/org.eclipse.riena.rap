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

package org.eclipse.riena.ui.swt.facades;

import java.lang.reflect.Field;

import org.eclipse.swt.browser.Browser;

/**
 * Implements {@link BrowserFacade} for RAP.
 */
public class BrowserFacadeRAP extends BrowserFacade {

	@Override
	public String getText(final Browser browser) {
		String result = null;
		try {
			final Field htmlField = browser.getClass().getDeclaredField("html"); //$NON-NLS-1$
			if (!htmlField.isAccessible()) {
				htmlField.setAccessible(true);
			}
			result = (String) htmlField.get(browser);
		} catch (final IllegalAccessException iae) {
			iae.printStackTrace(); // TODO [ev] log?
		} catch (final NoSuchFieldException nsfe) {
			nsfe.printStackTrace(); // TODO [ev] log?
		}
		return result == null ? "" : result; //$NON-NLS-1$
	}

}
