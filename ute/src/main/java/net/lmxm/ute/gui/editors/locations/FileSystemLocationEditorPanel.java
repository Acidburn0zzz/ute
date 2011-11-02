/**
 * Copyright (C) 2011 Shaun Johnson, LMXM LLC
 * 
 * This file is part of Universal Task Executer.
 * 
 * Universal Task Executer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * Universal Task Executer is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * Universal Task Executer. If not, see <http://www.gnu.org/licenses/>.
 */
package net.lmxm.ute.gui.editors.locations;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

import net.lmxm.ute.beans.locations.FileSystemLocation;
import net.lmxm.ute.gui.components.GuiComponentLabel;
import net.lmxm.ute.gui.components.GuiComponentToolbarButton;
import net.lmxm.ute.gui.toolbars.AbstractToolBar;
import net.lmxm.ute.listeners.ChangeAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class FileSystemLocationEditorPanel.
 */
public final class FileSystemLocationEditorPanel extends AbstractLocationEditorPanel {

	/**
	 * The Class FileSystemLocationEditorToolBar.
	 */
	private static class FileSystemLocationEditorToolBar extends AbstractToolBar {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 5647133443099985373L;

		/**
		 * Instantiates a new file system location editor tool bar.
		 * 
		 * @param actionListener the action listener
		 */
		public FileSystemLocationEditorToolBar(final ActionListener actionListener) {
			super(actionListener);

			addToolbarButton(GuiComponentToolbarButton.DELETE_FILE_SYSTEM_LOCATION);
		}
	}

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(FileSystemLocationEditorPanel.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7086761608623968446L;

	/** The path text field. */
	private JTextField pathTextField = null;

	/**
	 * Instantiates a new file system location editor panel.
	 * 
	 * @param actionListener the action listener
	 */
	public FileSystemLocationEditorPanel(final ActionListener actionListener) {
		super(GuiComponentLabel.FILE_SYSTEM_LOCATION, new FileSystemLocationEditorToolBar(actionListener),
				actionListener);

		addFields();
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.AbstractCommonEditorPanel#addFields()
	 */
	@Override
	protected void addFields() {
		super.addFields();

		final JPanel contentPanel = getContentPanel();

		addLabel(GuiComponentLabel.PATH);
		contentPanel.add(getPathTextField());
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.AbstractEditorPanel#getEditedObjectClass()
	 */
	@Override
	protected Object getEditedObjectClass() {
		return new FileSystemLocation();
	}

	/**
	 * Gets the path text field.
	 * 
	 * @return the path text field
	 */
	private JTextField getPathTextField() {
		if (pathTextField == null) {
			pathTextField = new JTextField();
			pathTextField.setMinimumSize(new Dimension(400, (int) pathTextField.getSize().getHeight()));
			pathTextField.getDocument().addDocumentListener(new ChangeAdapter() {
				@Override
				public void valueChanged(final String newValue) {
					if (getUserObject() instanceof FileSystemLocation) {
						((FileSystemLocation) getUserObject()).setPath(newValue);
					}
				}
			});
		}
		return pathTextField;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.AbstractIdEditorPanel#loadData()
	 */
	@Override
	public void loadData() {
		final String prefix = "loadData(): ";

		LOGGER.debug("{} entered", prefix);

		super.loadData();

		if (getUserObject() instanceof FileSystemLocation) {
			final FileSystemLocation fileSystemLocation = (FileSystemLocation) getUserObject();

			getPathTextField().setText(fileSystemLocation.getPath());
		}

		LOGGER.debug("{} leaving", prefix);
	}
}
