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

import net.lmxm.ute.gui.ActionConstants;

/**
 * The Enum MenuItemResourceType.
 */
public enum MenuItemResourceType implements ResourceType {

	/** The ABOUT. */
	ABOUT(ImageUtil.ABOUT_ICON, ActionConstants.ABOUT),

	/** The ADD_FILE_SYSTEM_DELETE_TASK. */
	ADD_FILE_SYSTEM_DELETE_TASK(ImageUtil.ADD_FILE_SYSTEM_DELETE_TASK_ICON, ActionConstants.ADD_FILE_SYSTEM_DELETE_TASK),

	/** The ADD_FILE_SYSTEM_LOCATION. */
	ADD_FILE_SYSTEM_LOCATION(ImageUtil.ADD_FILE_SYSTEM_LOCATION_ICON, ActionConstants.ADD_FILE_SYSTEM_LOCATION),

	/** The ADD_FIND_REPLACE_TASK. */
	ADD_FIND_REPLACE_TASK(ImageUtil.ADD_FIND_REPLACE_TASK_ICON, ActionConstants.ADD_FIND_REPLACE_TASK),

	/** The ADD_GROOVY_TASK. */
	ADD_GROOVY_TASK(ImageUtil.ADD_GROOVY_TASK_ICON, ActionConstants.ADD_GROOVY_TASK),

	/** The ADD_HTTP_DOWNLOAD_TASK. */
	ADD_HTTP_DOWNLOAD_TASK(ImageUtil.ADD_HTTP_DOWNLOAD_TASK_ICON, ActionConstants.ADD_HTTP_DOWNLOAD_TASK),

	/** The ADD_HTTP_LOCATION. */
	ADD_HTTP_LOCATION(ImageUtil.ADD_HTTP_LOCATION_ICON, ActionConstants.ADD_HTTP_LOCATION),

	/** The ADD_JOB. */
	ADD_JOB(ImageUtil.ADD_JOB_ICON, ActionConstants.ADD_JOB),

	/** The ADD_PREFERENCE. */
	ADD_PREFERENCE(ImageUtil.ADD_PREFERENCE_ICON, ActionConstants.ADD_PREFERENCE),

	/** The ADD_PROPERTY. */
	ADD_PROPERTY(ImageUtil.ADD_PROPERTY_ICON, ActionConstants.ADD_PROPERTY),

	/** The ADD_SUBVERSION_EXPORT_TASK. */
	ADD_SUBVERSION_EXPORT_TASK(ImageUtil.ADD_SUBVERSION_EXPORT_TASK_ICON, ActionConstants.ADD_SUBVERSION_EXPORT_TASK),

	/** The ADD_SUBVERSION_REPOSITORY_LOCATION. */
	ADD_SUBVERSION_REPOSITORY_LOCATION(ImageUtil.ADD_SUBVERSION_REPOSITORY_LOCATION_ICON,
			ActionConstants.ADD_SUBVERSION_REPOSITORY_LOCATION),

	/** The ADD_SUBVERSION_UPDATE_TASK. */
	ADD_SUBVERSION_UPDATE_TASK(ImageUtil.ADD_SUBVERSION_UPDATE_TASK_ICON, ActionConstants.ADD_SUBVERSION_UPDATE_TASK),

	/** The ADD_TASK. */
	ADD_TASK(null, null),

	/** The CLOSE_ALL_TABS. */
	CLOSE_ALL_TABS(null, ActionConstants.CLOSE_ALL_TABS),

	/** The DELETE_FILE_SYSTEM_LOCATION. */
	DELETE_FILE_SYSTEM_LOCATION(ImageUtil.DELETE_FILE_SYSTEM_LOCATION_ICON, ActionConstants.DELETE_FILE_SYSTEM_LOCATION),

	/** The DELETE_HTTP_LOCATION. */
	DELETE_HTTP_LOCATION(ImageUtil.DELETE_HTTP_LOCATION_ICON, ActionConstants.DELETE_HTTP_LOCATION),

	/** The DELETE_JOB. */
	DELETE_JOB(ImageUtil.DELETE_JOB_ICON, ActionConstants.DELETE_JOB),

	/** The DELETE_PREFERENCE. */
	DELETE_PREFERENCE(ImageUtil.DELETE_PREFERENCE_ICON, ActionConstants.DELETE_PREFERENCE),

	/** The DELETE_PROPERTY. */
	DELETE_PROPERTY(ImageUtil.DELETE_PROPERTY_ICON, ActionConstants.DELETE_PROPERTY),

	/** The DELETE_SUBVERSION_REPOSITORY_LOCATION. */
	DELETE_SUBVERSION_REPOSITORY_LOCATION(ImageUtil.DELETE_SUBVERSION_REPOSITORY_LOCATION_ICON,
			ActionConstants.DELETE_SUBVERSION_REPOSITORY_LOCATION),

	/** The DELETE_TASK. */
	DELETE_TASK(null, ActionConstants.DELETE_TASK),

	/** The EDIT_PREFERENCES. */
	EDIT_PREFERENCES(ImageUtil.EDIT_PREFERENCES_ICON, ActionConstants.EDIT_PREFERENCES),

	/** The EXECUTE_JOB. */
	EXECUTE_JOB(ImageUtil.EXECUTE_ICON, ActionConstants.EXECUTE),

	/** The EXECUTE_TASK. */
	EXECUTE_TASK(ImageUtil.EXECUTE_ICON, ActionConstants.EXECUTE),

	/** The EXIT. */
	EXIT(ImageUtil.EXIT_ICON, ActionConstants.EXIT),

	/** The NEW_FILE. */
	NEW_FILE(ImageUtil.NEW_FILE_ICON, ActionConstants.NEW_FILE),

	/** The OPEN_FILE. */
	OPEN_FILE(ImageUtil.OPEN_FILE_ICON, ActionConstants.OPEN_FILE),

	/** The SAVE_FILE. */
	SAVE_FILE(ImageUtil.SAVE_FILE_ICON, ActionConstants.SAVE_FILE),

	/** The SAVE_FILE_AS. */
	SAVE_FILE_AS(ImageUtil.SAVE_FILE_AS_ICON, ActionConstants.SAVE_FILE_AS);

	/** The action command. */
	private final String actionCommand;

	/** The icon. */
	private final Icon icon;

	/**
	 * Instantiates a new gui component menu item.
	 * 
	 * @param icon the icon
	 * @param actionCommand the action command
	 */
	MenuItemResourceType(final Icon icon, final String actionCommand) {
		this.icon = icon;
		this.actionCommand = actionCommand;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.components.GuiComponentType#getActionCommand()
	 */
	@Override
	public String getActionCommand() {
		return actionCommand;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.components.GuiComponentType#getGuiComponentCategory()
	 */
	@Override
	public ResourceCategory getResourceCategory() {
		return ResourceCategory.MENU_ITEM;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.components.GuiComponentType#getIcon()
	 */
	@Override
	public Icon getIcon() {
		return icon;
	}
}
