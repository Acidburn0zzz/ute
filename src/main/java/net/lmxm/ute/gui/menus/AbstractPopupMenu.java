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
package net.lmxm.ute.gui.menus;

import net.lmxm.ute.gui.UteActionListener;
import net.lmxm.ute.gui.components.GuiComponentFactory;
import net.lmxm.ute.resources.types.MenuItemResourceType;
import net.lmxm.ute.resources.types.MenuResourceType;

import javax.swing.*;
import java.awt.*;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The Class AbstractPopupMenu.
 */
public abstract class AbstractPopupMenu extends JPopupMenu {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7518698215552137785L;

	/** The action listener. */
	private final UteActionListener actionListener;

	/**
	 * Instantiates a new abstract popup menu.
	 * 
	 * @param actionListener the action listener
	 */
	public AbstractPopupMenu(final UteActionListener actionListener) {
		super();

		checkNotNull(actionListener, "Action listener may not be null");

		this.actionListener = actionListener;
	}

	/**
	 * Adds the menu item.
	 * 
	 * @param guiComponentMenuItem the gui component menu item
	 */
	protected final void addMenuItem(final MenuItemResourceType guiComponentMenuItem) {
		add(GuiComponentFactory.createMenuItem(guiComponentMenuItem, actionListener));
	}

	/**
	 * Adds the task add menu.
	 */
	protected final void addTaskAddMenu() {
		final JMenu menu = GuiComponentFactory.createMenu(MenuResourceType.ADD_TASK);

		menu.add(GuiComponentFactory.createMenuItem(MenuItemResourceType.ADD_FILE_SYSTEM_DELETE_TASK, actionListener));
		menu.add(GuiComponentFactory.createMenuItem(MenuItemResourceType.ADD_FIND_REPLACE_TASK, actionListener));
		menu.add(GuiComponentFactory.createMenuItem(MenuItemResourceType.ADD_GROOVY_TASK, actionListener));
		menu.add(GuiComponentFactory.createMenuItem(MenuItemResourceType.ADD_HTTP_DOWNLOAD_TASK, actionListener));
		menu.add(GuiComponentFactory.createMenuItem(MenuItemResourceType.ADD_MAVEN_REPOSITORY_DOWNLOAD_TASK, actionListener));
		menu.add(GuiComponentFactory.createMenuItem(MenuItemResourceType.ADD_SUBVERSION_EXPORT_TASK, actionListener));
		menu.add(GuiComponentFactory.createMenuItem(MenuItemResourceType.ADD_SUBVERSION_UPDATE_TASK, actionListener));

		add(menu);
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.JPopupMenu#show(java.awt.Component, int, int)
	 */
	@Override
	public final void show(final Component invoker, final int x, final int y) {
		super.show(invoker, x, y);
	}
}
