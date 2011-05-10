/*******************************************************************************
 * Copyright (c) 2007, 2011 compeople AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    compeople AG - initial API and implementation
 *******************************************************************************/

package org.eclipse.riena.demo.client.lnf;

import org.eclipse.riena.navigation.ui.swt.lnf.renderer.ShellBorderRenderer;

public class WebShellBorderRenderer extends ShellBorderRenderer {
	@Override
	public int getBorderWidth() {
		return 0;
	}

	@Override
	public int getCompleteBorderWidth() {
		return 0;
	}
}