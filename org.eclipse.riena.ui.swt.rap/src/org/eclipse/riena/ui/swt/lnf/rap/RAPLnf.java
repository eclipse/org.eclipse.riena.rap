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
package org.eclipse.riena.ui.swt.lnf.rap;

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

import org.eclipse.riena.ui.swt.lnf.rienadefault.RienaDefaultLnf;

/**
 * Look and Feel (Lnf) Riena on RAP applications.<br>
 * The Lnf uses its own theme.
 */
public class RAPLnf extends RienaDefaultLnf {

	/**
	 * ID of this Look and Feel
	 */
	public final static String LNF_ID = "RAPLnf"; //$NON-NLS-1$

	/**
	 * Creates a new instance of {@code ExampleLnf}
	 */
	public RAPLnf() {
		super(new RAPTheme());
	}

	/**
	 * @see org.eclipse.riena.ui.swt.lnf.rienadefault.RienaDefaultLnf#getLnfId()
	 */
	@Override
	protected String getLnfId() {
		return LNF_ID;
	}

}
