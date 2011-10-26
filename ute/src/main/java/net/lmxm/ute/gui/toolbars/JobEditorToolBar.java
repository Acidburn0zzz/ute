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
package net.lmxm.ute.gui.toolbars;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import net.lmxm.ute.gui.components.GuiComponentButton;
import net.lmxm.ute.gui.components.GuiComponentFactory;

/**
 * The Class JobEditorToolBar.
 */
public class JobEditorToolBar extends AbstractToolBar {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2056761468717025839L;

	/** The delete job button. */
	private JButton deleteJobButton = null;

	/** The execute job button. */
	private JButton executeJobButton = null;

	/**
	 * Instantiates a new properties tool bar.
	 * 
	 * @param actionListener the action listener
	 */
	public JobEditorToolBar(final ActionListener actionListener) {
		super(actionListener);

		add(getExecuteJobButton());
		add(getJobButton());
	}

	/**
	 * Gets the execute job button.
	 * 
	 * @return the execute job button
	 */
	private JButton getExecuteJobButton() {
		if (executeJobButton == null) {
			executeJobButton = GuiComponentFactory.createButton(GuiComponentButton.EXECUTE_JOB, getActionListener());
		}
		return executeJobButton;
	}

	/**
	 * Gets the job button.
	 * 
	 * @return the job button
	 */
	private JButton getJobButton() {
		if (deleteJobButton == null) {
			deleteJobButton = GuiComponentFactory.createButton(GuiComponentButton.DELETE_JOB, getActionListener());
		}
		return deleteJobButton;
	}
}
