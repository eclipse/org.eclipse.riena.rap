/*******************************************************************************
 * Copyright (c) 2007, 2014 compeople AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    compeople AG - initial API and implementation
 *******************************************************************************/
package org.eclipse.riena.demo.client.lnf;

import org.eclipse.swt.SWT;

import org.eclipse.riena.ui.swt.lnf.ColorLnfResource;
import org.eclipse.riena.ui.swt.lnf.FontLnfResource;
import org.eclipse.riena.ui.swt.lnf.ILnfCustomizer;
import org.eclipse.riena.ui.swt.lnf.LnfKeyConstants;

/**
 * Webtheme for the demo client
 */
public class WebTheme extends EclipseTheme {
	private static final ColorLnfResource COLOR_BLACK = new ColorLnfResource(0, 0, 0);
	private static final ColorLnfResource COLOR_WHITE = new ColorLnfResource(255, 255, 255);
	private static final ColorLnfResource COLOR_LIGHT_GREY = new ColorLnfResource(243, 243, 244);

	@Override
	public void customizeLnf(final ILnfCustomizer lnf) {
		super.customizeLnf(lnf);
		customizeSettings(lnf);
		lnf.putLnfResource(LnfKeyConstants.NAVIGATION_BACKGROUND, COLOR_WHITE);
		lnf.putLnfResource(LnfKeyConstants.STATUSLINE_BACKGROUND, COLOR_WHITE);
		lnf.putLnfResource(LnfKeyConstants.COOLBAR_BACKGROUND, COLOR_WHITE);

		lnf.putLnfResource(LnfKeyConstants.TITLELESS_SHELL_BACKGROUND, COLOR_WHITE);
		lnf.putLnfResource(LnfKeyConstants.STATUSLINE_UI_PROCESS_LIST_BACKGROUND, COLOR_WHITE);
		lnf.putLnfResource(LnfKeyConstants.GRAB_CORNER_BACKGROUND, COLOR_WHITE);

		lnf.putLnfResource(LnfKeyConstants.MODULE_GROUP_WIDGET_BACKGROUND, COLOR_LIGHT_GREY);
		lnf.putLnfResource(LnfKeyConstants.SUB_MODULE_TREE_BACKGROUND, COLOR_LIGHT_GREY);
	}

	private void customizeSettings(final ILnfCustomizer lnf) {
		lnf.putLnfSetting(LnfKeyConstants.MARKER_SUPPORT_ID, "defaultMarkerSupport"); //$NON-NLS-1$
		lnf.putLnfSetting(LnfKeyConstants.TITLELESS_SHELL_RESIZEABLE, false);

		lnf.putLnfSetting(LnfKeyConstants.TITLELESS_SHELL_NAVIGATION_HORIZONTAL_GAP, 0);
		lnf.putLnfSetting(LnfKeyConstants.TITLELESS_SHELL_PADDING, 0);
		lnf.putLnfSetting(LnfKeyConstants.TITLELESS_SHELL_SUB_MODULE_HORIZONTAL_GAP, 0);
		lnf.putLnfSetting(LnfKeyConstants.NAVIGATION_SUB_MODULE_GAP, 0);
		lnf.putLnfSetting(LnfKeyConstants.TOOLBAR_WORK_AREA_VERTICAL_GAP, 0);
		lnf.putLnfSetting(LnfKeyConstants.MENUBAR_TOP_MARGIN, 0);
		lnf.putLnfSetting(LnfKeyConstants.TOOLBAR_TOP_MARGIN, 0);

		lnf.putLnfSetting(LnfKeyConstants.SHELL_HIDE_OS_BORDER, true);

		lnf.putLnfSetting(LnfKeyConstants.SUB_APPLICATION_SWITCHER_HEIGHT, 95);
		lnf.putLnfSetting(LnfKeyConstants.SUB_APPLICATION_SWITCHER_TOP_MARGIN, 0);
	}

	@Override
	protected ColorLnfResource getPrimaryForeground() {
		return COLOR_BLACK;
	}

	@Override
	protected ColorLnfResource getPrimaryBackground() {
		return COLOR_WHITE;
	}

	@Override
	protected FontLnfResource getPrimaryFont() {
		return new FontLnfResource("Arial", 12, SWT.NORMAL); //$NON-NLS-1$
	}

	@Override
	protected boolean hideOsBorder() {
		return true;
	}
}
