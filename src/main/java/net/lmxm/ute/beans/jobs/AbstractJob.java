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
package net.lmxm.ute.beans.jobs;

import net.lmxm.ute.beans.BeanType;
import net.lmxm.ute.beans.IdentifiableDomainBean;
import org.apache.commons.lang3.StringUtils;

/**
 * The Class AbstractJob.
 */
public abstract class AbstractJob extends IdentifiableDomainBean implements Job {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7974535917838830967L;

	/** The description. */
	private String description;

	/**
	 * Instantiates a new job.
	 */
	public AbstractJob() {
		super();
	}

	/**
	 * Instantiates a new abstract job.
	 * 
	 * @param id the id
	 */
	public AbstractJob(final String id) {
		super(id);
	}

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	@Override
	public final String getDescription() {
		return description;
	}

    /**
     * Gets the bean type.
     *
     * @return Bean type
     */
    @Override
    public final BeanType getType() {
        return BeanType.Job;
    }

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.IdentifiableDomainBean#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return super.isEmpty() && StringUtils.isBlank(description);
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.DomainBean#removeEmptyObjects()
	 */
	@Override
	public void removeEmptyObjects() {
		// Do nothing
	}

	/**
	 * Sets the description.
	 * 
	 * @param description the new description
	 */
	@Override
	public final void setDescription(final String description) {
		this.description = description;
	}
}
