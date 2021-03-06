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
package net.lmxm.ute.executers.jobs;

import net.lmxm.ute.beans.PropertiesHolder;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.beans.tasks.Task;
import net.lmxm.ute.event.StatusChangeEventBus;
import net.lmxm.ute.executers.AbstractExecuter;

import static com.google.common.base.Preconditions.checkNotNull;
import static net.lmxm.ute.resources.types.StatusChangeMessageResourceType.*;

/**
 * The Class AbstractJobExecuter.
 */
public abstract class AbstractJobExecuter extends AbstractExecuter implements JobExecuter {

    /**
     * The job.
     */
    private final Job job;

    /**
     * The properties holder.
     */
    private final PropertiesHolder propertiesHolder;

    /**
     * Instantiates a new abstract job executer.
     *
     * @param job              the job
     * @param propertiesHolder the properties holder
     */
    public AbstractJobExecuter(final Job job, final PropertiesHolder propertiesHolder) {
        checkNotNull(job, "Job may not be null");
        checkNotNull(propertiesHolder, "PropertiesHolder may not be null");

        this.job = job;
        this.propertiesHolder = propertiesHolder;
    }

    /**
     * Gets the job.
     *
     * @return the job
     */
    protected Job getJob() {
        return job;
    }

    /**
     * Gets the properties holder.
     *
     * @return the properties holder
     */
    protected final PropertiesHolder getPropertiesHolder() {
        return propertiesHolder;
    }

    /**
     * Job aborted.
     */
    protected final void jobAborted() {
        StatusChangeEventBus.heading(JOB_ABORTED, job, job.getId());
        JobStatusEventBus.post(new JobStatusEvent(JobStatusEvent.JobStatusEventType.JobAborted, job));
    }

    /**
     * Job completed.
     */
    protected final void jobCompleted() {
        StatusChangeEventBus.heading(JOB_FINISHED, job, job.getId());
        JobStatusEventBus.post(new JobStatusEvent(JobStatusEvent.JobStatusEventType.JobCompleted, job));
    }

    /**
     * Job started.
     */
    protected final void jobStarted() {
        StatusChangeEventBus.heading(JOB_STARTED, job, job.getId());
        JobStatusEventBus.post(new JobStatusEvent(JobStatusEvent.JobStatusEventType.JobStarted, job));
    }

    /**
     * Task completed.
     *
     * @param task the task
     */
    protected final void taskCompleted(final Task task) {
        JobStatusEventBus.post(new JobStatusEvent(JobStatusEvent.JobStatusEventType.TaskCompleted, task));
    }

    /**
     * Task skipped.
     *
     * @param task the task
     */
    protected final void taskSkipped(final Task task) {
        StatusChangeEventBus.info(DISABLED_TASK_SKIPPED, job, task.getId());
        JobStatusEventBus.post(new JobStatusEvent(JobStatusEvent.JobStatusEventType.TaskSkipped, task));
    }

    /**
     * Task started.
     *
     * @param task the task
     */
    protected final void taskStarted(final Task task) {
        JobStatusEventBus.post(new JobStatusEvent(JobStatusEvent.JobStatusEventType.TaskStarted, task));
    }
}
