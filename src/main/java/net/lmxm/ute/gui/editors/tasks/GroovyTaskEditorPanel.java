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

import net.lmxm.ute.beans.jobs.SequentialJob;
import net.lmxm.ute.beans.tasks.GroovyTask;
import net.lmxm.ute.configuration.ConfigurationHolder;
import net.lmxm.ute.event.DocumentAdapter;
import net.lmxm.ute.gui.UteActionListener;
import net.lmxm.ute.gui.toolbars.AbstractTaskEditorToolBar;
import net.lmxm.ute.resources.types.LabelResourceType;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

/**
 * The Class GroovyTaskEditorPanel.
 */
public final class GroovyTaskEditorPanel extends AbstractTaskEditorPanel {

	/**
	 * The Class GroovyTaskEditorToolBar.
	 */
	private static class GroovyTaskEditorToolBar extends AbstractTaskEditorToolBar {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 5352183821352721127L;

		/**
		 * Instantiates a new groovy task editor tool bar.
		 * 
		 * @param actionListener the action listener
		 */
		public GroovyTaskEditorToolBar(final UteActionListener actionListener) {
			super(actionListener);
		}
	}

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
	 * 
	 * @param configurationHolder the configuration holder
	 * @param actionListener the action listener
	 */
	public GroovyTaskEditorPanel(final ConfigurationHolder configurationHolder, final UteActionListener actionListener) {
		super(LabelResourceType.GROOVY_TASK, new GroovyTaskEditorToolBar(actionListener), configurationHolder,
				actionListener);

		addFields();
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.tasks.AbstractTaskEditorPanel#addFields()
	 */
	@Override
	protected void addFields() {
		super.addFields();

		addSeparator(LabelResourceType.GROOVY_SCRIPT);
		addRequiredLabel(LabelResourceType.SCRIPT);
		getContentPanel().add(getScriptPane());
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.AbstractEditorPanel#getEditedObjectClass()
	 */
	@Override
	protected Object getEditedObjectClass() {
		return new GroovyTask(new SequentialJob());
	}

	/**
	 * Gets the script pane.
	 * 
	 * @return the script pane
	 */
	private JScrollPane getScriptPane() {
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
	private RSyntaxTextArea getScriptTextArea() {
		if (scriptTextArea == null) {
			scriptTextArea = new RSyntaxTextArea();
			scriptTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_GROOVY);
			scriptTextArea.setColumns(80);
			scriptTextArea.setRows(30);
			scriptTextArea.setTabSize(4);
			scriptTextArea.setDragEnabled(true);
			scriptTextArea.getDocument().addDocumentListener(new DocumentAdapter() {
				@Override
				public void valueChanged(final String newValue) {
					if (getUserObject() instanceof GroovyTask) {
						final GroovyTask groovyTask = (GroovyTask) getUserObject();
						groovyTask.setScript(newValue);
					}
				}
			});
		}
		return scriptTextArea;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.tasks.AbstractTaskEditorPanel#loadData()
	 */
	@Override
	public void loadData() {
		final String prefix = "loadData(): ";

		LOGGER.debug("{} entered", prefix);

		super.loadData();

		if (getUserObject() instanceof GroovyTask) {
			final GroovyTask groovyTask = (GroovyTask) getUserObject();

			getScriptTextArea().setText(groovyTask.getScript());
		}

		LOGGER.debug("{} leaving", prefix);
	}
}
