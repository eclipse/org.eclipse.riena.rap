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
package org.eclipse.riena.ui.swt.facades.internal;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;

import org.eclipse.riena.ui.swt.CompletionCombo;

/**
 * A {@link CompletionCombo} with a text field and a list widget.
 * <p>
 * <b>Important:</b> this is the RAP-specific implementation of this widget. Use
 * {@code UIControlsFactory.createCompletionCombo(...)} to automatically get the
 * correct platform-specific (RCP or RAP) instance.
 */
public class CompletionComboRAP extends CompletionCombo {

	public CompletionComboRAP(final Composite parent, final int style) {
		super(parent, style);
	}

	@Override
	protected Label createLabel(final Composite parent) {
		return null;
	}

	@Override
	protected Control createList(final Composite parent) {
		final int style = getStyle();
		int listStyle = SWT.SINGLE | SWT.V_SCROLL;
		if ((style & SWT.FLAT) != 0) {
			listStyle |= SWT.FLAT;
		}
		//		if ((style & SWT.RIGHT_TO_LEFT) != 0) {
		//			listStyle |= SWT.RIGHT_TO_LEFT;
		//		}
		if ((style & SWT.LEFT_TO_RIGHT) != 0) {
			listStyle |= SWT.LEFT_TO_RIGHT;
		}
		return new List(parent, listStyle);
	}

	@Override
	protected void deselectAll(final Control list) {
		((List) list).deselectAll();
	}

	@Override
	protected Image getImage(final Control list, final int index) {
		return null;
	}

	@Override
	protected Image[] getImages(final Control list) {
		return null;
	}

	@Override
	protected String getItem(final Control list, final int index) {
		return ((List) list).getItem(index);
	}

	@Override
	protected int getItemHeight(final Control list) {
		return ((List) list).getItemHeight();
	}

	@Override
	protected String[] getItems(final Control list) {
		return ((List) list).getItems();
	}

	@Override
	protected int getItemCount(final Control list) {
		return ((List) list).getItemCount();
	}

	@Override
	protected int getSelectionIndex(final Control list) {
		return ((List) list).getSelectionIndex();
	}

	@Override
	protected int getTopIndex(final Control list) {
		return ((List) list).getTopIndex();
	}

	@Override
	protected int indexOf(final Control list, final String string, final int start) {
		return ((List) list).indexOf(string, start);
	}

	@Override
	protected void removeAll(final Control list) {
		((List) list).removeAll();
	}

	@Override
	protected void setItems(final Control list, final String[] items, final Image[] images) {
		((List) list).setItems(items);
	}

	@Override
	protected void setSelection(final Control list, final int index) {
		((List) list).setSelection(index);
	}

	@Override
	protected void setTopIndex(final Control list, final int index) {
		((List) list).setTopIndex(index);
	}
}
