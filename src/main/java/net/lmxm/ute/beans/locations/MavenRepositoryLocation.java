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
package net.lmxm.ute.beans.locations;

import net.lmxm.ute.beans.BeanType;

/**
 * The Class MavenRepositoryLocation.
 */
public class MavenRepositoryLocation extends AbstractHttpLocation  {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -1934593380172193695L;

	/**
	 * Instantiates a new Maven repository location.
	 */
	public MavenRepositoryLocation() {
		super();
	}

	/**
	 * Instantiates a new Maven repository location.
	 *
	 * @param id the id
	 */
	public MavenRepositoryLocation(final String id) {
		super(id);
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.IdentifiableBean#getDisplayText()
	 */
	@Override
	public String getDisplayText() {
		return getId();
	}

    /**
     * Gets the bean type.
     *
     * @return Bean type
     */
    @Override
    public final BeanType getType() {
        return BeanType.MavenRepositoryLocation;
    }
}
