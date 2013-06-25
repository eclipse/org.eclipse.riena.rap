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

import org.eclipse.riena.ui.swt.lnf.ILnfCustomizer;
import org.eclipse.riena.ui.swt.lnf.LnfKeyConstants;
import org.eclipse.riena.ui.swt.lnf.rienadefault.RienaDefaultTheme;

/**
 * Webtheme for Riena on RAP
 */
public class RAPTheme extends RienaDefaultTheme {

	@Override
	public void customizeLnf(final ILnfCustomizer lnf) {
		super.customizeLnf(lnf);
		customizeSettings(lnf);
	}

	private void customizeSettings(final ILnfCustomizer lnf) {
		//lnf.putLnfSetting(LnfKeyConstants.MARKER_SUPPORT_ID, "rapMarkerSupport"); //$NON-NLS-1$
		lnf.putLnfSetting(LnfKeyConstants.MARKER_SUPPORT_ID, "borderMarkerSupport"); //$NON-NLS-1$
		lnf.putLnfSetting(LnfKeyConstants.INFO_FLYOUT_PAUSE_ANIMATION_TIME, 0);
		lnf.putLnfSetting(LnfKeyConstants.INFO_FLYOUT_SHOW_AND_HIDE_ANIMATION_TIME, 10);
		lnf.putLnfSetting(LnfKeyConstants.INFO_FLYOUT_WAIT_ANIMATION_TIME, 10);

	}

	@Override
	protected boolean hideOsBorder() {
		return true;
	}
}
