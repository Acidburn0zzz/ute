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

import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.executers.AbstractExecuter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The Class AbstractTaskExecuter.
 */
public abstract class AbstractTaskExecuter extends AbstractExecuter {

    /**
     * Associated job object.
     */
    private final Job job;

    public AbstractTaskExecuter(Job job) {
        this.job = checkNotNull(job, "Job may not be null");
    }

    /**
     * Gets the associated job.
     *
     * @return Associated job object
     */
    protected final Job getJob() {
        return job;
    }
}
