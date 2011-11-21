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
package net.lmxm.ute.gui.validation;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;

import net.lmxm.ute.gui.validation.rules.ValidationRule;
import net.lmxm.ute.resources.ImageUtil;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

/**
 * The Class AbstractInputValidator.
 */
public abstract class AbstractInputValidator extends InputVerifier implements KeyListener {

	/** The input component. */
	final JComponent inputComponent;

	/** The message icon. */
	private JLabel messageIcon = null;

	/** The message label. */
	private JLabel messageLabel = null;

	/** The messages dialog. */
	private JDialog messagesDialog = null;

	/** The validators. */
	private final List<ValidationRule> validators = new ArrayList<ValidationRule>();

	/**
	 * Instantiates a new abstract input validator.
	 * 
	 * @param inputComponent the input component
	 */
	public AbstractInputValidator(final JComponent inputComponent) {
		super();

		this.inputComponent = inputComponent;

		inputComponent.addKeyListener(this);

		// Preload GUI components
		getMessagesDialog();
	}

	/**
	 * Clear.
	 */
	public final void clear() {
		getMessagesDialog().dispose();

		inputComponent.setBackground(getDefaultBackgroundColor());
		inputComponent.removeKeyListener(this);
	}

	/**
	 * Display messages dialog.
	 * 
	 * @param component the component
	 * @param messages the messages
	 */
	private void displayMessagesDialog(final JComponent component, final List<String> messages) {
		final JDialog dialog = getMessagesDialog();

		// Load dialog with messages.
		getMessagesLabel().setText(StringUtils.join(messages, "\n"));

		// Relocate dialog relative to the input component
		dialog.setSize(0, 0);
		dialog.setLocationRelativeTo(component);
		final Point location = dialog.getLocation();
		final Dimension componentSize = component.getSize();
		dialog.setLocation(location.x - (int) componentSize.getWidth() / 2,
				location.y + (int) componentSize.getHeight() / 2);
		dialog.pack();
		dialog.setVisible(true);
	}

	/**
	 * Gets the default background color.
	 * 
	 * @return the default background color
	 */
	protected abstract Color getDefaultBackgroundColor();

	/**
	 * Gets the messages dialog.
	 * 
	 * @return the messages dialog
	 */
	private JDialog getMessagesDialog() {
		if (messagesDialog == null) {
			messagesDialog = new JDialog();

			messagesDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			messagesDialog.setFocusableWindowState(false);
			messagesDialog.setUndecorated(true);

			final Container contentPane = messagesDialog.getContentPane();
			contentPane.setLayout(new FlowLayout());
			contentPane.setBackground(new Color(255, 250, 1));
			contentPane.add(getMessagesIcon());
			contentPane.add(getMessagesLabel());
		}

		return messagesDialog;
	}

	/**
	 * Gets the messages icon.
	 * 
	 * @return the messages icon
	 */
	private JLabel getMessagesIcon() {
		if (messageIcon == null) {
			messageIcon = new JLabel(ImageUtil.ERROR_ICON);
		}

		return messageIcon;
	}

	/**
	 * Gets the messages label.
	 * 
	 * @return the messages label
	 */
	private JLabel getMessagesLabel() {
		if (messageLabel == null) {
			messageLabel = new JLabel();
		}

		return messageLabel;
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public final void keyPressed(final KeyEvent keyEvent) {
		getMessagesDialog().dispose();
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public final void keyReleased(final KeyEvent keyEvent) {

	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public final void keyTyped(final KeyEvent keyEvent) {

	}

	/**
	 * Validate.
	 * 
	 * @param component the component
	 * @return the list
	 */
	private List<String> validate(final JComponent component) {
		final List<String> messages = new ArrayList<String>();

		try {
			for (final ValidationRule validator : validators) {
				messages.addAll(validator.validate(component));
			}
		}
		catch (final Exception e) {
			messages.add("Unexpected occurred during validation"); // TODO
		}

		return messages;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.InputVerifier#verify(javax.swing.JComponent)
	 */
	@Override
	public final boolean verify(final JComponent component) {
		final List<String> messages = validate(component);

		if (CollectionUtils.isEmpty(messages)) {
			component.setBackground(getDefaultBackgroundColor());

			return true;
		}
		else {
			component.setBackground(Color.PINK);
			displayMessagesDialog(component, messages);

			return false;
		}
	}
}
