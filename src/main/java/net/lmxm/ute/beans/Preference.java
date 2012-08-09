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
package net.lmxm.ute.beans;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * The Class Preference.
 */
public final class Preference extends IdentifiableDomainBean {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4325574409193215376L;

	/** The value. */
	private String value;

	/**
	 * Instantiates a new preference.
	 */
	public Preference() {
		super();
	}

	/**
	 * Instantiates a new preference.
	 * 
	 * @param id the preference id
	 */
	public Preference(final String id) {
		super(id);
	}

    /**
     * Instantiates a new preference.
     *
     * @param id the id of the preference
     * @param value the value of the preference
     */
    public Preference(final String id, final String value) {
        super(id);
        this.value = value;
    }

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}

		if (obj == this) {
			return true;
		}

		if (obj.getClass() != getClass()) {
			return false;
		}

		final Preference rhs = (Preference) obj;

		return new EqualsBuilder().appendSuper(super.equals(obj)).append(getId(), rhs.getId())
				.append(value, rhs.getValue()).isEquals();
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
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(getId()).append(value).toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.DomainBean#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return super.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.DomainBean#removeEmptyObjects()
	 */
	@Override
	public void removeEmptyObjects() {
		// No children objects
	}

	/**
	 * Sets the value.
	 * 
	 * @param value the new value
	 */
	public void setValue(final String value) {
		this.value = value;
	}
}
