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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.osgi.framework.Bundle;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.rwt.RWT;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;

import org.eclipse.riena.internal.demo.client.Activator;
import org.eclipse.riena.navigation.ApplicationNodeManager;
import org.eclipse.riena.navigation.ISubApplicationNode;
import org.eclipse.riena.navigation.NavigationNodeId;
import org.eclipse.riena.navigation.ui.swt.component.SubApplicationItem;
import org.eclipse.riena.navigation.ui.swt.lnf.renderer.SubApplicationSwitcherRenderer;
import org.eclipse.riena.ui.core.marker.HiddenMarker;

@SuppressWarnings("restriction")
public class WebThemeHeaderRenderer extends SubApplicationSwitcherRenderer {

	private Browser browser;
	private List<SubApplicationItem> items;
	private String template;

	public WebThemeHeaderRenderer() {
		template = loadTemplate("/theme/header_template.html"); //$NON-NLS-1$
		final String baseURL = getBaseURL();
		template = template.replace("%URL%", baseURL); //$NON-NLS-1$
	}

	@Override
	public void paint(final GC gc, final Object value) {
		if (null == browser) {
			final Canvas parent = (Canvas) value;
			parent.setLayout(new FillLayout());
			browser = new Browser(parent, SWT.None);
			new SubApplicationSwitchFunction(browser, "activateSubApplication"); //$NON-NLS-1$
			new MenuFunction(browser, "menuAction"); //$NON-NLS-1$
			browser.setText(template);
		}

		if (browser.isDisposed()) {
			return;
		}
	}

	private String getBaseURL() {
		// Protocol, ip & port
		final ServletContext sc = RWT.getRequest().getSession().getServletContext();
		final String realPath = sc.getRealPath("/"); //$NON-NLS-1$

		final HttpServletRequest request = RWT.getRequest();
		final String protocol = "http://"; //$NON-NLS-1$
		final String ip = request.getLocalAddr();
		final int port = request.getLocalPort();
		final String URL = protocol + ip + ":" + port; //$NON-NLS-1$
		String webAppName = ""; //$NON-NLS-1$

		if (realPath == null) { // Start application locally within Eclipse
								// (Windows)
			return URL;
		} // Start application on a server (Apache Tomcat or so)
		webAppName = sc.getContextPath();
		return URL + webAppName;
	}

	@Override
	public Rectangle getBounds() {
		final Rectangle bounds = super.getBounds();
		return new Rectangle(0, 0, bounds.width, bounds.height);
	}

	private class SubApplicationSwitchFunction extends BrowserFunction {
		public SubApplicationSwitchFunction(final Browser browser, final String name) {
			super(browser, name);
		}

		@Override
		public Object function(final Object[] arguments) {
			final Object param = arguments[0];
			final int index = Integer.parseInt((String) param);
			final SubApplicationItem subApplicationItem = getVisibleItems().get(index);
			final ISubApplicationNode subApplicationNode = subApplicationItem.getSubApplicationNode();

			subApplicationNode.activate();
			return null;
		}
	}

	private class MenuFunction extends BrowserFunction {
		public MenuFunction(final Browser browser, final String name) {
			super(browser, name);
		}

		@Override
		public Object function(final Object[] arguments) {
			final Object param = arguments[0];
			final String value = (String) param;

			if ("new".equals(value)) { //$NON-NLS-1$
				ApplicationNodeManager.getApplicationNode().navigate(
						new NavigationNodeId("riena.demo.client.CustomerRecord")); //$NON-NLS-1$
			} else if ("history.back".equals(value)) { //$NON-NLS-1$
				ApplicationNodeManager.getApplicationNode().historyBack();
			} else if ("history.forward".equals(value)) { //$NON-NLS-1$
				ApplicationNodeManager.getApplicationNode().historyForward();
			}
			return null;
		}
	}

	private List<SubApplicationItem> getVisibleItems() {
		final List<SubApplicationItem> visibleItems = new ArrayList<SubApplicationItem>();

		for (final SubApplicationItem item : getItems()) {
			if (item.getMarkersOfType(HiddenMarker.class).isEmpty()) {
				visibleItems.add(item);
			}
		}
		return visibleItems;
	}

	/**
	 * @param items
	 *            the items to set
	 */
	@Override
	public void setItems(final List<SubApplicationItem> items) {
		this.items = items;
	}

	/**
	 * @return the items
	 */
	private List<SubApplicationItem> getItems() {
		if (items == null) {
			items = new ArrayList<SubApplicationItem>();
		}
		return items;
	}

	private File getTemplate(final String templateName) {
		Bundle bundle = null;

		// FIXME
		for (final Bundle frag : Platform.getFragments(Activator.getDefault().getBundle())) {
			if ("org.eclipse.riena.demo.client.webtheme".equals(frag //$NON-NLS-1$
					.getSymbolicName())) {
				bundle = frag;
				break;
			}
		}
		final URL fileURL = bundle.getEntry(templateName);
		URL realUrl;
		try {
			realUrl = FileLocator.resolve(fileURL);
			final File file = new File(realUrl.toURI());
			return file;
		} catch (final IOException e) {
			e.printStackTrace();
		} catch (final URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String loadTemplate(final String templateName) {
		FileReader fileReader = null;
		try {
			final File template = getTemplate(templateName);
			fileReader = new FileReader(template);
		} catch (final FileNotFoundException e1) {
			e1.printStackTrace();
		}

		final BufferedReader br = new BufferedReader(fileReader);
		final StringBuilder bob = new StringBuilder();
		String line = null;
		try {
			while ((line = br.readLine()) != null) {
				bob.append(line).append("\n"); //$NON-NLS-1$
			}
		} catch (final IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
		return bob.toString();
	}
}
