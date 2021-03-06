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
package net.lmxm.ute.console;

import com.beust.jcommander.JCommander;
import net.lmxm.ute.GenericApplication;
import net.lmxm.ute.beans.configuration.Configuration;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.configuration.ConfigurationReader;
import net.lmxm.ute.event.StatusChangeEventBus;
import net.lmxm.ute.resources.ResourcesUtils;
import net.lmxm.ute.resources.types.ApplicationResourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * The Class ConsoleApplication.
 */
public final class ConsoleApplication extends GenericApplication {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleApplication.class);

    private final ConsoleArguments consoleArguments;

	/**
	 * Instantiates a new console application.
	 *
	 * @param args the args
	 */
	public ConsoleApplication(final String[] args) {
		super();

        consoleArguments = getConsoleArguments(args);
	}

	/**
	 * Execute.
	 */
	public void execute() {
		final String prefix = "execute() :";

		LOGGER.debug("{} entered", prefix);

		final File inputFile = consoleArguments.getInputFile();
		final Configuration configuration = new ConfigurationReader(inputFile).read();

		loadAndValidatePreferencesAreSet(configuration, null);
		final List<Job> jobs = loadJobs(configuration, consoleArguments.getJobId(), consoleArguments.getTaskId());
        StatusChangeEventBus.register(new ConsoleStatusChangeListener());
		executeJobs(jobs, configuration);

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Gets the console arguments.
	 *
	 * @return the console arguments
	 */
	private static ConsoleArguments getConsoleArguments(final String[] args) {
		final String prefix = "getConsoleArguments() :";

		LOGGER.debug("{} entered", prefix);

		final ConsoleArguments consoleArguments = new ConsoleArguments();
		final JCommander jCommander = new JCommander(consoleArguments);

		jCommander.setProgramName(ResourcesUtils.getResourceText(ApplicationResourceType.NAME));

		try {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("{} parsing args={}", prefix, Arrays.toString(args));
			}

			jCommander.parse(args);
		}
		catch (final RuntimeException e) {
			LOGGER.error(prefix + " error occurred parsing arguments");

            // Legitimate use of System.err.println() to respond to user on command line
			System.err.println(e.getMessage() + "\n");
			jCommander.usage();
			System.exit(-1);
		}

		LOGGER.debug("{} returning", prefix);

		return consoleArguments;
	}
}
