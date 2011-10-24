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
package net.lmxm.ute.gui.editors.tasks;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.lmxm.ute.beans.targets.FileSystemTarget;
import net.lmxm.ute.beans.tasks.GroovyTask;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class GroovyTaskEditorPanel.
 */
public final class GroovyTaskEditorPanel extends AbstractTaskEditorPanel {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(GroovyTaskEditorPanel.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5799320021366647084L;

	/** The script pane. */
	private JScrollPane scriptPane = null;

	/** The script text area. */
	private RSyntaxTextArea scriptTextArea = null;

	/**
	 * Instantiates a new groovy task editor panel.
	 */
	public GroovyTaskEditorPanel() {
		super("Groovy Task");

		final JPanel contentPanel = getContentPanel();

		addTaskCommonFields();

		addSeparator(contentPanel, "Target");
		addFileSystemTargetFields();
		addFilesFields();

		addSeparator(contentPanel, "Groovy Script");
		addLabel(contentPanel, "Script");
		contentPanel.add(getScriptPane());
	}

	/**
	 * Gets the script pane.
	 * 
	 * @return the script pane
	 */
	protected final JScrollPane getScriptPane() {
		if (scriptPane == null) {
			scriptPane = new RTextScrollPane(getScriptTextArea());
		}

		return scriptPane;
	}

	/**
	 * Gets the script text area.
	 * 
	 * @return the script text area
	 */
	protected final RSyntaxTextArea getScriptTextArea() {
		if (scriptTextArea == null) {
			scriptTextArea = new RSyntaxTextArea();
			scriptTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_GROOVY);
			scriptTextArea.setColumns(80);
			scriptTextArea.setRows(30);
			scriptTextArea.setTabSize(4);
		}
		return scriptTextArea;
	}

	/**
	 * Load data.
	 * 
	 * @param groovyTask the groovy task
	 */
	public void loadData(final GroovyTask groovyTask) {
		final String prefix = "loadData(): ";

		LOGGER.debug("{} entered, groovyTask={}", prefix, groovyTask);

		final FileSystemTarget target = groovyTask == null ? null : groovyTask.getTarget();

		loadTaskCommonFieldData(groovyTask);
		loadFileSystemTargetFieldData(target);
		loadFilesFieldData(groovyTask);

		if (groovyTask == null) {
			getScriptTextArea().setText("");
		}
		else {
			getScriptTextArea().setText(groovyTask.getScript());
		}

		LOGGER.debug("{} leaving", prefix);
	}
}
