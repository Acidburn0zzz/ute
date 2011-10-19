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
package net.lmxm.ute.gui.utils;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import net.lmxm.ute.beans.Configuration;
import net.lmxm.ute.beans.Preference;
import net.lmxm.ute.beans.Property;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.beans.locations.FileSystemLocation;
import net.lmxm.ute.beans.locations.HttpLocation;
import net.lmxm.ute.beans.locations.SubversionRepositoryLocation;
import net.lmxm.ute.beans.tasks.Task;
import net.lmxm.ute.gui.matchers.TreeNodeMatcher;
import net.lmxm.ute.gui.matchers.UserObjectClassEqualsMatcher;
import net.lmxm.ute.gui.matchers.UserObjectEqualsMatcher;
import net.lmxm.ute.gui.nodes.FileSystemLocationsRootTreeNode;
import net.lmxm.ute.gui.nodes.HttpLocationsRootTreeNode;
import net.lmxm.ute.gui.nodes.JobsRootTreeNode;
import net.lmxm.ute.gui.nodes.PreferencesRootTreeNode;
import net.lmxm.ute.gui.nodes.PropertiesRootTreeNode;
import net.lmxm.ute.gui.nodes.SubversionRepositoryLocationsRootTreeNode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

/**
 * The Class GuiUtils.
 */
public final class GuiUtils {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(GuiUtils.class);

	/**
	 * Adds the file system locations to tree model.
	 * 
	 * @param rootNode the root node
	 * @param configuration the configuration
	 */
	private static void addFileSystemLocationsToTreeModel(final DefaultMutableTreeNode rootNode,
			final Configuration configuration) {
		final String prefix = "addFileSystemLocationsToTreeModel() :";

		LOGGER.debug("{} entered, configuration={}", prefix, configuration);

		final List<FileSystemLocation> fileSystemLocations = configuration.getFileSystemLocations();
		final FileSystemLocationsRootTreeNode fileSystemLocationsRootTreeNode = new FileSystemLocationsRootTreeNode(
				"File System Locations (" + fileSystemLocations.size() + ")");
		final DefaultMutableTreeNode fileSystemLocationsNode = new DefaultMutableTreeNode(
				fileSystemLocationsRootTreeNode);
		rootNode.add(fileSystemLocationsNode);

		LOGGER.debug("{} loading {} locations", prefix, fileSystemLocations.size());

		for (final FileSystemLocation fileSystemLocation : fileSystemLocations) {
			fileSystemLocationsNode.add(new DefaultMutableTreeNode(fileSystemLocation));
		}

		LOGGER.debug("{} leaving");
	}

	/**
	 * Adds the http locations to tree model.
	 * 
	 * @param rootNode the root node
	 * @param configuration the configuration
	 */
	private static void addHttpLocationsToTreeModel(final DefaultMutableTreeNode rootNode,
			final Configuration configuration) {
		final String prefix = "addHttpLocationsToTreeModel() :";

		LOGGER.debug("{} entered, configuration={}", prefix, configuration);

		final List<HttpLocation> httpLocations = configuration.getHttpLocations();
		final HttpLocationsRootTreeNode httpLocationsRootTreeNode = new HttpLocationsRootTreeNode("HTTP Locations ("
				+ httpLocations.size() + ")");
		final DefaultMutableTreeNode httpLocationsNode = new DefaultMutableTreeNode(httpLocationsRootTreeNode);
		rootNode.add(httpLocationsNode);

		LOGGER.debug("{} loading {} locations", prefix, httpLocations.size());

		for (final HttpLocation httpLocation : httpLocations) {
			httpLocationsNode.add(new DefaultMutableTreeNode(httpLocation));
		}

		LOGGER.debug("{} leaving");
	}

	/**
	 * Adds the jobs to tree model.
	 * 
	 * @param rootNode the root node
	 * @param configuration the configuration
	 */
	private static void addJobsToTreeModel(final DefaultMutableTreeNode rootNode, final Configuration configuration) {
		final String prefix = "addJobsToTreeModel() :";

		LOGGER.debug("{} entered, configuration={}", prefix, configuration);

		final List<Job> jobs = configuration.getJobs();
		final JobsRootTreeNode jobsRootTreeNode = new JobsRootTreeNode("Jobs (" + jobs.size() + ")");
		final DefaultMutableTreeNode jobsNode = new DefaultMutableTreeNode(jobsRootTreeNode);
		rootNode.add(jobsNode);

		LOGGER.debug("{} loading {} jobs", prefix, jobs.size());

		for (final Job job : jobs) {
			final DefaultMutableTreeNode jobNode = new DefaultMutableTreeNode(job);
			final List<Task> tasks = job.getTasks();

			LOGGER.debug("{} loading {} tasks", prefix, tasks.size());

			for (final Task task : tasks) {
				final DefaultMutableTreeNode taskNode = new DefaultMutableTreeNode(task);

				jobNode.add(taskNode);
			}

			jobsNode.add(jobNode);
		}

		LOGGER.debug("{} leaving");
	}

	/**
	 * Adds the preferences to tree model.
	 * 
	 * @param rootNode the root node
	 * @param configuration the configuration
	 */
	private static void addPreferencesToTreeModel(final DefaultMutableTreeNode rootNode,
			final Configuration configuration) {
		final String prefix = "addPreferencesToTreeModel() :";

		LOGGER.debug("{} entered, configuration={}", prefix, configuration);

		final List<Preference> preferences = configuration.getPreferences();
		final PreferencesRootTreeNode preferencesRootTreeNode = new PreferencesRootTreeNode("Preferences ("
				+ preferences.size() + ")");
		final DefaultMutableTreeNode preferencesNode = new DefaultMutableTreeNode(preferencesRootTreeNode);
		rootNode.add(preferencesNode);

		LOGGER.debug("{} loading {} preferences", prefix, preferences.size());

		for (final Preference preference : preferences) {
			preferencesNode.add(new DefaultMutableTreeNode(preference));
		}

		LOGGER.debug("{} leaving");
	}

	/**
	 * Adds the properties to tree model.
	 * 
	 * @param rootNode the root node
	 * @param configuration the configuration
	 */
	private static void addPropertiesToTreeModel(final DefaultMutableTreeNode rootNode,
			final Configuration configuration) {
		final String prefix = "addPropertiesToTreeModel() :";

		LOGGER.debug("{} entered, configuration={}", prefix, configuration);

		final List<Property> properties = configuration.getProperties();
		final PropertiesRootTreeNode propertiesRootTreeNode = new PropertiesRootTreeNode("Properties ("
				+ properties.size() + ")");
		final DefaultMutableTreeNode propertiesNode = new DefaultMutableTreeNode(propertiesRootTreeNode);
		rootNode.add(propertiesNode);

		LOGGER.debug("{} loading {} properties", prefix, properties.size());

		for (final Property property : properties) {
			propertiesNode.add(new DefaultMutableTreeNode(property));
		}

		LOGGER.debug("{} leaving");
	}

	/**
	 * Adds the property to tree model.
	 * 
	 * @param tree the tree
	 * @param treeModel the tree model
	 * @param property the property
	 */
	public static TreePath addPropertyToTreeModel(final JTree tree, final Property property) {
		final DefaultTreeModel treeModel = (DefaultTreeModel) tree.getModel();
		final TreePath treePath = findPathToNode(tree, new UserObjectClassEqualsMatcher(PropertiesRootTreeNode.class));

		if (treePath == null) {
			throw new RuntimeException("Unable to find Properties node"); // TODO use appropriate exception
		}

		final MutableTreeNode propertiesNode = (MutableTreeNode) treePath.getLastPathComponent();
		final MutableTreeNode propertyNode = new DefaultMutableTreeNode(property);
		treeModel.insertNodeInto(propertyNode, propertiesNode, 0);

		return findPathToNode(tree, new UserObjectEqualsMatcher(property));
	}

	/**
	 * Adds the subversion repository locations to tree model.
	 * 
	 * @param rootNode the root node
	 * @param configuration the configuration
	 */
	private static void addSubversionRepositoryLocationsToTreeModel(final DefaultMutableTreeNode rootNode,
			final Configuration configuration) {
		final String prefix = "addSubversionRepositoryLocationsToTreeModel() :";

		LOGGER.debug("{} entered, configuration={}", prefix, configuration);

		final List<SubversionRepositoryLocation> subversionRepositoryLocations = configuration
				.getSubversionRepositoryLocations();
		final SubversionRepositoryLocationsRootTreeNode subversionRepositoryLocationsRootTreeNode = new SubversionRepositoryLocationsRootTreeNode(
				"Subversion Locations (" + subversionRepositoryLocations.size() + ")");
		final DefaultMutableTreeNode subversionLocationsNode = new DefaultMutableTreeNode(
				subversionRepositoryLocationsRootTreeNode);
		rootNode.add(subversionLocationsNode);

		LOGGER.debug("{} loading {} locations", prefix, subversionRepositoryLocations.size());

		for (final SubversionRepositoryLocation subversionLocation : subversionRepositoryLocations) {
			subversionLocationsNode.add(new DefaultMutableTreeNode(subversionLocation));
		}

		LOGGER.debug("{} leaving");
	}

	/**
	 * Centers the frame on the user's screen.
	 * 
	 * @param component the component
	 */
	public static void center(final Component component) {
		final String prefix = "center() :";

		LOGGER.debug("{} entered", prefix);

		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		final Dimension screenSize = toolkit.getScreenSize();

		final int x = (screenSize.width - component.getWidth()) / 2;
		final int y = (screenSize.height - component.getHeight()) / 2;

		component.setLocation(x, y);

		LOGGER.debug("{} leaving", prefix);
	}

	public static TreePath findPathToNode(final JTree tree, final TreeNodeMatcher matcher) {
		final TreeNode root = (TreeNode) tree.getModel().getRoot();

		return findPathToNode(root, matcher);
	}

	public static TreePath findPathToNode(final TreeNode treeNode, final TreeNodeMatcher matcher) {
		return findPathToNode(new TreePath(treeNode), matcher);
	}

	public static TreePath findPathToNode(final TreePath treePath, final TreeNodeMatcher matcher) {
		final TreeNode treeNode = (TreeNode) treePath.getLastPathComponent();

		if (matcher.matches(treeNode)) {
			return treePath;
		}

		if (treeNode.getChildCount() >= 0) {
			for (final Enumeration child = treeNode.children(); child.hasMoreElements();) {
				final TreeNode childTreeNode = (TreeNode) child.nextElement();
				final TreePath childPath = treePath.pathByAddingChild(childTreeNode);
				final TreePath result = findPathToNode(childPath, matcher);

				if (result != null) {
					return result;
				}
			}
		}

		// No match at this branch
		return null;
	}

	/**
	 * Load job details tree model.
	 * 
	 * @param configuration the configuration
	 * @return the tree model
	 */
	public static TreeModel loadJobDetailsTreeModel(final Configuration configuration) {
		final String prefix = "loadJobDetailsTreeModel() :";

		LOGGER.debug("{} entered, configuration={}", prefix, configuration);

		Preconditions.checkNotNull(configuration, "Configuration may not be null");

		final DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Root");
		final DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);

		addJobsToTreeModel(rootNode, configuration);
		addFileSystemLocationsToTreeModel(rootNode, configuration);
		addHttpLocationsToTreeModel(rootNode, configuration);
		addSubversionRepositoryLocationsToTreeModel(rootNode, configuration);
		addPropertiesToTreeModel(rootNode, configuration);
		addPreferencesToTreeModel(rootNode, configuration);

		LOGGER.debug("{} returning treeModel={}", prefix, treeModel);

		return treeModel;
	}

	/**
	 * Instantiates a new gui utils.
	 */
	private GuiUtils() {
		throw new AssertionError();
	}
};
