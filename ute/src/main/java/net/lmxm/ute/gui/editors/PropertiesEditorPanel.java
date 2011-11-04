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
package net.lmxm.ute.gui.editors;

import java.awt.event.ActionListener;

import net.lmxm.ute.gui.components.LabelResourceType;
import net.lmxm.ute.gui.components.ToolbarButtonResourceType;
import net.lmxm.ute.gui.toolbars.AbstractToolBar;

/**
 * The Class PropertiesEditorPanel.
 */
public class PropertiesEditorPanel extends AbstractReadonlyEditorPanel {

	/**
	 * The Class PropertiesEditorToolBar.
	 */
	private static class PropertiesEditorToolBar extends AbstractToolBar {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = -1153768266558335635L;

		/**
		 * Instantiates a new properties editor tool bar.
		 * 
		 * @param actionListener the action listener
		 */
		public PropertiesEditorToolBar(final ActionListener actionListener) {
			super(actionListener);

			addToolbarButton(ToolbarButtonResourceType.ADD_PROPERTY);
		}
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2351152862222662562L;

	/**
	 * Instantiates a new properties editor panel.
	 * 
	 * @param actionListener the action listener
	 */
	public PropertiesEditorPanel(final ActionListener actionListener) {
		super(LabelResourceType.PROPERTIES, new PropertiesEditorToolBar(actionListener), actionListener);

		addFields();
	}
}
