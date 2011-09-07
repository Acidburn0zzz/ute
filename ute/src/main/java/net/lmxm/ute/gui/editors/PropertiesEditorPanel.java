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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.lmxm.ute.beans.Configuration;
import net.lmxm.ute.gui.utils.ImageUtil;

/**
 * The Class PropertiesEditorPanel.
 */
public class PropertiesEditorPanel extends AbstractEditorPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2351152862222662562L;

	/** The add property button. */
	private JButton addPropertyButton;

	/**
	 * Instantiates a new properties editor panel.
	 * 
	 * @param configuration the configuration
	 */
	public PropertiesEditorPanel(final Configuration configuration) {
		super(configuration, "Properties");

		final JPanel contentPanel = getContentPanel();

		contentPanel.add(new JLabel("Add New Property:"));
		contentPanel.add(getAddPropertyButton());
	}

	/**
	 * Gets the adds the property button.
	 * 
	 * @return the adds the property button
	 */
	private JButton getAddPropertyButton() {
		if (addPropertyButton == null) {
			addPropertyButton = new JButton();
			addPropertyButton.setIcon(ImageUtil.ADD_PROPERTY_ICON);
			addPropertyButton.setToolTipText("Add new property");
			addPropertyButton.setText("Add Property");
			addPropertyButton.setEnabled(false); // TODO disabled since it is not implemented
		}
		return addPropertyButton;
	}
}