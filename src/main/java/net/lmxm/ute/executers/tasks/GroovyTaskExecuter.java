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
package net.lmxm.ute.executers.tasks;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import net.lmxm.ute.beans.FileReference;
import net.lmxm.ute.beans.Preference;
import net.lmxm.ute.beans.PropertiesHolder;
import net.lmxm.ute.beans.Property;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.beans.tasks.GroovyTask;
import net.lmxm.ute.event.StatusChangeEventBus;
import net.lmxm.ute.exceptions.TaskExecuterException;
import net.lmxm.ute.resources.types.ExceptionResourceType;
import net.lmxm.ute.utils.FileSystemTargetUtils;
import net.lmxm.ute.utils.FileSystemUtils;
import org.codehaus.groovy.control.CompilationFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;
import static net.lmxm.ute.resources.types.StatusChangeMessageResourceType.*;

/**
 * The Class GroovyTaskExecuter.
 */
public final class GroovyTaskExecuter extends AbstractTaskExecuter {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GroovyTaskExecuter.class);

    /**
     * The properties holder.
     */
    private final PropertiesHolder propertiesHolder;

    /**
     * The task.
     */
    private final GroovyTask task;

    /**
     * Instantiates a new groovy task executer.
     *
     * @param job                the job
     * @param task               the task
     * @param propertiesHolder   the properties holder
     */
    public GroovyTaskExecuter(final Job job, final GroovyTask task, final PropertiesHolder propertiesHolder) {
        super(job);

        this.task = checkNotNull(task, "Task may not be null");
        this.propertiesHolder = checkNotNull(propertiesHolder, "PropertiesHolder may not be null");
    }

    /**
     * Convert preferences to map.
     *
     * @param propertiesHolder the properties holder
     * @return the object
     */
    private Map<String, String> convertPreferencesToMap(final PropertiesHolder propertiesHolder) {
        final Map<String, String> preferences = new HashMap<String, String>();

        for (final Preference preference : propertiesHolder.getPreferences()) {
            preferences.put(preference.getId(), preference.getValue());
        }

        return preferences;
    }

    /**
     * Convert properties to map.
     *
     * @param propertiesHolder the properties holder
     * @return the object
     */
    private Object convertPropertiesToMap(final PropertiesHolder propertiesHolder) {
        final Map<String, String> properties = new HashMap<String, String>();

        for (final Property property : propertiesHolder.getProperties()) {
            properties.put(property.getId(), property.getValue());
        }

        return properties;
    }

    /*
     * (non-Javadoc)
     * @see net.lmxm.ute.executers.ExecuterIF#execute()
     */
    @Override
    public void execute() {
        final String prefix = "execute() :";

        LOGGER.debug("{} entered", prefix);

        final String path = FileSystemTargetUtils.getFullPath(task.getTarget());
        final List<FileReference> files = task.getFiles();

        executeScript(task.getScript(), path, files, propertiesHolder);

        LOGGER.debug("{} returning", prefix);
    }

    /**
     * Execute script.
     *
     * @param script           the script
     * @param path             the path
     * @param files            the files
     * @param propertiesHolder the properties holder
     */
    protected void executeScript(final String script, final String path, final List<FileReference> files,
                                 final PropertiesHolder propertiesHolder) {
        final String prefix = "executeScript() :";

        LOGGER.debug("{} entered", prefix);

        final Binding binding = new Binding();
        binding.setVariable("path", path);
        binding.setVariable("files", FileSystemUtils.convertToFileObjects(path, files));
        binding.setVariable("properties", convertPropertiesToMap(propertiesHolder));
        binding.setVariable("preferences", convertPreferencesToMap(propertiesHolder));

        StatusChangeEventBus.info(GROOVY_EXECUTION_STARTED, getJob());

        try {
            final Object returnValue = new GroovyShell(binding).evaluate(script);

            StatusChangeEventBus.info(GROOVY_EXECUTION_FINISHED, getJob(), returnValue);
        }
        catch (final CompilationFailedException e) {
            LOGGER.error(prefix + " Script compilation failed", e);
            StatusChangeEventBus.error(GROOVY_COMPILATION_ERROR, getJob());
            throw new TaskExecuterException(ExceptionResourceType.GROOVY_SCRIPT_COMPILATION_FAILED, e);
        }
        catch (final Exception e) {
            LOGGER.error(prefix + " Script execution failed", e);
            StatusChangeEventBus.error(GROOVY_EXECUTION_ERROR, getJob());
            throw new TaskExecuterException(ExceptionResourceType.GROOVY_SCRIPT_EXECUTION_FAILED, e);
        }

        LOGGER.debug("{} leaving", prefix);
    }
}
