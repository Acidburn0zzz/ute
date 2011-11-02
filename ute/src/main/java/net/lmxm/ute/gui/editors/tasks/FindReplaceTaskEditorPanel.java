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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.lmxm.ute.beans.FindReplacePattern;
import net.lmxm.ute.beans.jobs.SequentialJob;
import net.lmxm.ute.beans.tasks.FindReplaceTask;
import net.lmxm.ute.enums.Scope;
import net.lmxm.ute.gui.components.GuiComponentLabel;
import net.lmxm.ute.gui.toolbars.AbstractTaskEditorToolBar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class FindReplaceTaskEditorPanel.
 */
public final class FindReplaceTaskEditorPanel extends AbstractTaskEditorPanel {

	/**
	 * The Class FindReplaceTaskEditorToolBar.
	 */
	private static class FindReplaceTaskEditorToolBar extends AbstractTaskEditorToolBar {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = -4652716534538899767L;

		/**
		 * Instantiates a new find replace task editor tool bar.
		 * 
		 * @param actionListener the action listener
		 */
		public FindReplaceTaskEditorToolBar(final ActionListener actionListener) {
			super(actionListener);
		}
	}

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(FindReplaceTaskEditorPanel.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5004821032399609379L;

	/** The file scope radio button. */
	private JRadioButton fileScopeRadioButton;

	/** The line scope radio button. */
	private JRadioButton lineScopeRadioButton;

	/** The patterns pane. */
	private JPanel patternsPane = null;

	/** The patterns scroll pane. */
	private JScrollPane patternsScrollPane = null;

	/** The patterns table. */
	private JTable patternsTable = null;

	/** The scope pane. */
	private JPanel scopePane = null;

	/**
	 * Instantiates a new find replace task editor panel.
	 * 
	 * @param actionListener the action listener
	 */
	public FindReplaceTaskEditorPanel(final ActionListener actionListener) {
		super(GuiComponentLabel.FIND_AND_REPLACE_TASK, new FindReplaceTaskEditorToolBar(actionListener), actionListener);

		addFields();
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.tasks.AbstractTaskEditorPanel#addFields()
	 */
	@Override
	protected void addFields() {
		super.addFields();

		final JPanel contentPanel = getContentPanel();

		addSeparator(GuiComponentLabel.TARGET);
		addFileSystemTargetFields();
		addFilesFields();

		addSeparator(GuiComponentLabel.FIND_AND_REPLACE);
		addLabel(GuiComponentLabel.SCOPE);
		contentPanel.add(getScopePane());

		addPatternsFields();
	}

	/**
	 * Adds the patterns fields.
	 */
	protected final void addPatternsFields() {
		final JPanel contentPanel = getContentPanel();

		addLabel(GuiComponentLabel.PATTERNS);
		contentPanel.add(getPatternsPane());
	}

	/**
	 * Creates the empty patterns table model.
	 * 
	 * @return the default table model
	 */
	protected final DefaultTableModel createEmptyPatternsTableModel() {
		final DefaultTableModel tableModel = new DefaultTableModel();

		tableModel.addColumn("Pattern");
		tableModel.addColumn("Replacement");

		return tableModel;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.AbstractEditorPanel#getEditedObjectClass()
	 */
	@Override
	protected Object getEditedObjectClass() {
		return new FindReplaceTask(new SequentialJob());
	}

	/**
	 * Gets the file scope radio button.
	 * 
	 * @return the file scope radio button
	 */
	private JRadioButton getFileScopeRadioButton() {
		if (fileScopeRadioButton == null) {
			fileScopeRadioButton = new JRadioButton(Scope.FILE.toString());
		}

		return fileScopeRadioButton;
	}

	/**
	 * Gets the line scope radio button.
	 * 
	 * @return the line scope radio button
	 */
	private JRadioButton getLineScopeRadioButton() {
		if (lineScopeRadioButton == null) {
			lineScopeRadioButton = new JRadioButton(Scope.LINE.toString());
		}

		return lineScopeRadioButton;
	}

	/**
	 * Gets the patterns pane.
	 * 
	 * @return the patterns pane
	 */
	protected final JPanel getPatternsPane() {
		if (patternsPane == null) {
			patternsPane = new JPanel();
			patternsPane.setLayout(new BorderLayout());
			patternsPane.add(getPatternsScrollPane(), BorderLayout.CENTER);
			patternsPane.setMaximumSize(new Dimension(400, 100));
		}

		return patternsPane;
	}

	/**
	 * Gets the patterns scroll pane.
	 * 
	 * @return the patterns scroll pane
	 */
	protected final JScrollPane getPatternsScrollPane() {
		if (patternsScrollPane == null) {
			patternsScrollPane = new JScrollPane(getPatternsTable());
			patternsScrollPane.setMaximumSize(new Dimension(400, 100));
		}

		return patternsScrollPane;
	}

	/**
	 * Gets the patterns table.
	 * 
	 * @return the patterns table
	 */
	protected final JTable getPatternsTable() {
		if (patternsTable == null) {
			patternsTable = new JTable(createEmptyPatternsTableModel());
			patternsTable.setFillsViewportHeight(true);
		}

		return patternsTable;
	}

	/**
	 * Gets the scope pane.
	 * 
	 * @return the scope pane
	 */
	private final JPanel getScopePane() {
		if (scopePane == null) {
			scopePane = new JPanel();
			scopePane.setLayout(new FlowLayout(FlowLayout.LEFT));
			scopePane.add(getLineScopeRadioButton());
			scopePane.add(getFileScopeRadioButton());

			final ButtonGroup group = new ButtonGroup();
			group.add(getLineScopeRadioButton());
			group.add(getFileScopeRadioButton());
		}

		return scopePane;
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

		if (getUserObject() instanceof FindReplaceTask) {
			final FindReplaceTask findReplaceTask = (FindReplaceTask) getUserObject();

			loadPatternsFieldData(findReplaceTask);

			if (findReplaceTask.getScope() == Scope.LINE) {
				getLineScopeRadioButton().setSelected(true);
			}
			else {
				getFileScopeRadioButton().setSelected(true);
			}
		}

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Load patterns field data.
	 * 
	 * @param findReplaceTask the find replace task
	 */
	protected final void loadPatternsFieldData(final FindReplaceTask findReplaceTask) {
		final DefaultTableModel tableModel = createEmptyPatternsTableModel();

		if (findReplaceTask != null) {
			for (final FindReplacePattern findReplacePattern : findReplaceTask.getPatterns()) {
				tableModel.addRow(new Object[] { findReplacePattern.getFind(), findReplacePattern.getReplace() });
			}
		}

		getPatternsTable().setModel(tableModel);
	}
}
