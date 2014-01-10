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
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import org.eclipse.riena.ui.swt.CompletionCombo;

/**
 * A CompletionCombo with a text field, with an optional image on the left, and
 * a table widget (which can show an image next to each item).
 * <p>
 * <b>Important:</b> this is the RAP-specific implementation of this widget. Use
 * {@code UIControlsFactory.createCompletionComboWithImage(...)} to
 * automatically get the correct platform-specific (RCP or RAP) instance.
 */
public class CompletionComboWithImageRAP extends CompletionCombo {

	public CompletionComboWithImageRAP(final Composite parent, final int style) {
		super(parent, style);
	}

	@Override
	protected Label createLabel(final Composite parent) {
		final Label result = new Label(parent, SWT.NONE);
		result.setBackground(getBackground());
		return result;
	}

	@Override
	protected Control createList(final Composite parent) {
		final int style = SWT.SINGLE | SWT.V_SCROLL | SWT.FULL_SELECTION;
		return new Table(parent, style);
	}

	@Override
	protected void deselectAll(final Control list) {
		((Table) list).deselectAll();
	}

	@Override
	protected Image getImage(final Control list, final int index) {
		final TableItem item = ((Table) list).getItem(index);
		return item.getImage();
	}

	@Override
	protected Image[] getImages(final Control list) {
		final TableItem[] items = ((Table) list).getItems();
		final Image[] result = new Image[items.length];
		for (int i = 0; i < items.length; i++) {
			result[i] = items[i].getImage();
		}
		return result;
	}

	@Override
	protected String getItem(final Control list, final int index) {
		final TableItem item = ((Table) list).getItem(index);
		return item.getText();
	}

	@Override
	protected int getItemHeight(final Control list) {
		return ((Table) list).getItemHeight();
	}

	@Override
	protected String[] getItems(final Control list) {
		final TableItem[] items = ((Table) list).getItems();
		final String[] result = new String[items.length];
		for (int i = 0; i < items.length; i++) {
			result[i] = items[i].getText();
		}
		return result;
	}

	@Override
	protected int getItemCount(final Control list) {
		return ((Table) list).getItemCount();
	}

	@Override
	protected int getSelectionIndex(final Control list) {
		return ((Table) list).getSelectionIndex();
	}

	@Override
	protected int getTopIndex(final Control list) {
		return ((Table) list).getTopIndex();
	}

	@Override
	protected int indexOf(final Control list, final String string, final int start) {
		final Table table = (Table) list;
		final int max = table.getItemCount();
		for (int i = start; i < max; i++) {
			final TableItem item = table.getItem(i);
			if (string.equals(item.getText())) {
				return i;
			}
		}
		return -1;
	}

	@Override
	protected void removeAll(final Control list) {
		((Table) list).removeAll();
	}

	@Override
	protected void setItems(final Control list, final String[] items, final Image[] images) {
		final Table table = (Table) list;
		table.removeAll();
		for (int i = 0; i < items.length; i++) {
			final TableItem item = new TableItem(table, SWT.NONE, i);
			item.setText(items[i]);
			if (images != null) {
				item.setImage(images[i]);
			}
		}
	}

	@Override
	protected void setSelection(final Control list, final int index) {
		((Table) list).setSelection(index);
	}

	@Override
	protected void setTopIndex(final Control list, final int index) {
		((Table) list).setTopIndex(index);
	}
}