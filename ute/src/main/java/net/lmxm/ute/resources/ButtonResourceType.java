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
package net.lmxm.ute.resources;

import javax.swing.Icon;

/**
 * The Enum ButtonResourceType.
 */
public enum ButtonResourceType implements ResourceType {

	/** The CLOSE_DIALOG button. */
	CLOSE_DIALOG(null, null),

	/** The DIRECTORY_BROWSE button. */
	DIRECTORY_BROWSE(null, null);

	/** The action command. */
	private final String actionCommand;

	/** The icon. */
	private final Icon icon;

	/**
	 * Instantiates a new gui component button.
	 * 
	 * @param icon the icon
	 * @param actionCommand the action command
	 */
	ButtonResourceType(final Icon icon, final String actionCommand) {
		this.icon = icon;
		this.actionCommand = actionCommand;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.components.GuiComponent#getActionCommand()
	 */
	@Override
	public String getActionCommand() {
		return actionCommand;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.components.GuiComponent#getGuiComponentCategory()
	 */
	@Override
	public ResourceCategory getResourceCategory() {
		return ResourceCategory.BUTTON;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.components.GuiComponent#getIcon()
	 */
	@Override
	public Icon getIcon() {
		return icon;
	}
}