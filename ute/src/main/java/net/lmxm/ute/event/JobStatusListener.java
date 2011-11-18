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
package net.lmxm.ute.event;

/**
 * The listener interface for receiving jobStatus events. The class that is interested in processing a jobStatus event
 * implements this interface, and the object created with that class is registered with a component using the
 * component's <code>addJobStatusListener<code> method. When
 * the jobStatus event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see JobStatusEvent
 */
public interface JobStatusListener {

	/**
	 * Job aborted.
	 */
	void jobAborted();

	/**
	 * Job completed.
	 */
	void jobCompleted();

	/**
	 * Job started.
	 */
	void jobStarted();

	/**
	 * Job stopped.
	 */
	void jobStopped();

	/**
	 * Job task completed.
	 */
	void jobTaskCompleted();

	/**
	 * Job task skipped.
	 */
	void jobTaskSkipped();

	/**
	 * Job task started.
	 */
	void jobTaskStarted();
}