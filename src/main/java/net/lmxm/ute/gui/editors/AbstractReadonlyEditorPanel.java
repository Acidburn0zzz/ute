/**
 * Copyright (C) 2011 Shaun Johnson, LMXM LLC
 * 
 * This file is part of Universal Task Executor.
 * 
 * Universal Task Executor is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * Universal Task Executor is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * Universal Task Executor. If not, see <http://www.gnu.org/licenses/>.
 */
package net.lmxm.ute.gui.editors;

import net.lmxm.ute.configuration.ConfigurationHolder;
import net.lmxm.ute.resources.types.LabelResourceType;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * The Class AbstractReadonlyEditorPanel.
 */
public abstract class AbstractReadonlyEditorPanel extends AbstractEditorPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4872391172555140940L;

	/**
	 * Instantiates a new abstract readonly editor panel.
	 * 
	 * @param guiComponentLabel the gui component label
	 * @param toolBar the tool bar
	 * @param configurationHolder the configuration holder
	 * @param actionListener the action listener
	 */
	public AbstractReadonlyEditorPanel(final LabelResourceType guiComponentLabel, final JToolBar toolBar,
			final ConfigurationHolder configurationHolder, final ActionListener actionListener) {
		super(guiComponentLabel, toolBar, configurationHolder, actionListener);
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.AbstractEditorPanel#addFields()
	 */
	@Override
	protected final void addFields() {

	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.AbstractEditorPanel#getEditedObjectClass()
	 */
	@Override
	protected Object getEditedObjectClass() {
		return new Object();
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.AbstractEditorPanel#loadData()
	 */
	@Override
	public final void loadData() {

	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.AbstractEditorPanel#setFocusToFirstInput()
	 */
	@Override
	public final void setFocusToFirstInput() {

	}
}
