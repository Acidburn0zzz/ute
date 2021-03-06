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

import net.lmxm.ute.beans.IdentifiableBean;
import net.lmxm.ute.beans.configuration.Configuration;
import net.lmxm.ute.configuration.ConfigurationHolder;
import net.lmxm.ute.gui.components.GuiComponentFactory;
import net.lmxm.ute.gui.validation.InputValidator;
import net.lmxm.ute.resources.types.LabelResourceType;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The Class AbstractEditor.
 */
public abstract class AbstractEditorPanel extends JPanel {

	/** The Constant BOLD_FONT. */
	private static final Font BOLD_FONT;

	/** The Constant SEPARATOR_LABEL_COLOR. */
	private static final Color SEPARATOR_LABEL_COLOR = new Color(0, 70, 213);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2931881275263682418L;

	static {
		BOLD_FONT = new Font(Font.DIALOG, Font.BOLD, 12);
	}

	/** The action listener. */
	private final ActionListener actionListener;

	/** The configuration holder. */
	private final ConfigurationHolder configurationHolder;

	/** The content panel. */
	private JPanel contentPanel;

	/** The input validators. */
	private final List<InputValidator> inputValidators = new ArrayList<InputValidator>();

	/** The user object. */
	private Object userObject;

	/**
	 * Instantiates a new abstract editor panel.
	 * 
	 * @param guiComponentLabel the gui component label
	 * @param toolBar the tool bar
	 * @param configurationHolder the configuration holder
	 * @param actionListener the action listener
	 */
	public AbstractEditorPanel(final LabelResourceType guiComponentLabel, final JToolBar toolBar,
			final ConfigurationHolder configurationHolder, final ActionListener actionListener) {
		super();

		checkNotNull(actionListener, "Action listener may not be null");
		checkNotNull(configurationHolder, "Configuration holder may not be null");

		this.actionListener = actionListener;
		this.configurationHolder = configurationHolder;

		// Setup this panel
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder());

		// Setup the toolbar
		if (toolBar != null) {
			add(toolBar, BorderLayout.NORTH);
		}

		// Setup the content main
		add(getContentPanel(), BorderLayout.CENTER);

		// Add initial separator label
		addSeparator(guiComponentLabel);
	}

	/**
	 * Adds the checkbox.
	 * 
	 * @param checkBox the check box
	 * @param guiComponentLabel the gui component label
	 */
	protected final void addCheckbox(final JCheckBox checkBox, final LabelResourceType guiComponentLabel) {
		final JPanel subPanel = new JPanel(new MigLayout("ins 0", "[left]"));
		subPanel.add(checkBox);
		subPanel.add(createLabel(guiComponentLabel));

		getContentPanel().add(subPanel, "skip 1");
	}

	/**
	 * Adds the fields.
	 */
	protected abstract void addFields();

	/**
	 * Adds the input validator.
	 * 
	 * @param inputValidator the input validator
	 */
	protected final void addInputValidator(final InputValidator inputValidator) {
		inputValidators.add(inputValidator);
	}

	/**
	 * Adds the label.
	 * 
	 * @param guiComponentLabel the gui component label
	 */
	protected final void addLabel(final LabelResourceType guiComponentLabel) {
		getContentPanel().add(createLabel(guiComponentLabel), "gapleft 20, top");
	}

	/**
	 * Adds the required label.
	 * 
	 * @param guiComponentLabel the gui component label
	 */
	protected final void addRequiredLabel(final LabelResourceType guiComponentLabel) {
		getContentPanel().add(createRequiredLabel(guiComponentLabel), "gapleft 20, top");
	}

	/**
	 * Adds the separator.
	 * 
	 * @param guiComponentLabel the gui component label
	 */
	protected final void addSeparator(final LabelResourceType guiComponentLabel) {
		final JLabel label = createLabel(guiComponentLabel);
		label.setFont(BOLD_FONT);
		label.setForeground(SEPARATOR_LABEL_COLOR);

		getContentPanel().add(label, "gapy 10, span, split 2, aligny center");
		getContentPanel().add(new JSeparator(), "gapleft rel, gapy 14, growx");
	}

	/**
	 * Clear input validators.
	 */
	public final void clearInputValidators() {
		disposeInputValidators();
	}

	/**
	 * Creates the default combo box model.
	 * 
	 * @param list the list
	 * @return the combo box model
	 */
	protected final ComboBoxModel createDefaultComboBoxModel(final List<? extends IdentifiableBean> list) {
		if (list == null) {
			return new DefaultComboBoxModel();
		}
		else {
			return new DefaultComboBoxModel(list.toArray());
		}
	}

	/**
	 * Creates the label.
	 * 
	 * @param guiComponentLabel the gui component label
	 * @return the j label
	 */
	protected final JLabel createLabel(final LabelResourceType guiComponentLabel) {
		return GuiComponentFactory.createLabel(guiComponentLabel, SwingConstants.LEADING);
	}

	/**
	 * Creates the required label.
	 * 
	 * @param guiComponentLabel the gui component label
	 * @return the j label
	 */
	protected final JLabel createRequiredLabel(final LabelResourceType guiComponentLabel) {
		return GuiComponentFactory.createRequiredLabel(guiComponentLabel, SwingConstants.LEADING);
	}

	/**
	 * Dispose input validators.
	 */
	private void disposeInputValidators() {
		for (final InputValidator inputValidator : inputValidators) {
			inputValidator.clear();
		}
	}

	/**
	 * Gets the action listener.
	 * 
	 * @return the action listener
	 */
	protected final ActionListener getActionListener() {
		return actionListener;
	}

	/**
	 * Gets the configuration holder.
	 * 
	 * @return the configuration holder
	 */
	protected final ConfigurationHolder getConfigurationHolder() {
		return configurationHolder;
	}

	/**
	 * Gets the content panel.
	 * 
	 * @return the content panel
	 */
	protected final JPanel getContentPanel() {
		if (contentPanel == null) {
			contentPanel = new JPanel(new MigLayout("wrap 2", "[right][fill]"));
		}
		return contentPanel;
	}

	/**
	 * Gets the edited object class.
	 * 
	 * @return the edited object class
	 */
	protected abstract Object getEditedObjectClass();

	/**
	 * Gets the user object.
	 * 
	 * @return the user object
	 */
	public final Object getUserObject() {
		return userObject;
	}

	/**
	 * Initialize.
	 * 
	 * @param configuration the configuration
	 */
	public void initialize(final Configuration configuration) {
		clearInputValidators();
	}

	/**
	 * Load data.
	 */
	public abstract void loadData();

	/**
	 * Removes the input validator.
	 * 
	 * @param component the component
	 */
	protected final void removeInputValidator(final JComponent component) {
		checkNotNull(component, "Component is null");

		final InputVerifier inputVerifier = component.getInputVerifier();

		if (inputVerifier instanceof InputValidator) {
			final InputValidator inputValidator = (InputValidator) inputVerifier;

			inputValidator.clear();
			inputValidators.remove(inputValidator);
		}

		component.setInputVerifier(null);
	}

	/**
	 * Sets the focus to first input.
	 */
	public abstract void setFocusToFirstInput();

	/**
	 * Sets the user object.
	 * 
	 * @param userObject the new user object
	 */
	public final void setUserObject(final Object userObject) {
		this.userObject = userObject;
	}
}
