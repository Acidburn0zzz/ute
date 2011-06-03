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
package net.lmxm.ute.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import net.lmxm.ute.beans.FileReference;
import net.lmxm.ute.beans.locations.HttpLocation;
import net.lmxm.ute.beans.sources.HttpSource;
import net.lmxm.ute.listeners.StatusChangeEvent;
import net.lmxm.ute.listeners.StatusChangeListener;

import org.junit.Test;

/**
 * The Class HttpUtilsTest.
 */
public class HttpUtilsTest {

	/**
	 * The listener interface for receiving myStatusChange events. The class that is interested in processing a
	 * myStatusChange event implements this interface, and the object created with that class is registered with a
	 * component using the component's <code>addMyStatusChangeListener<code> method. When
	 * the myStatusChange event occurs, that object's appropriate
	 * method is invoked.
	 * 
	 * @see MyStatusChangeEvent
	 */
	private static class MyStatusChangeListener implements StatusChangeListener {

		/*
		 * (non-Javadoc)
		 * @see net.lmxm.ute.listeners.StatusChangeListener#statusChange(net.lmxm.ute.listeners.StatusChangeEvent)
		 */
		@Override
		public void statusChange(final StatusChangeEvent changeEvent) {
			System.out.println(changeEvent);
		}
	}

	/** The Constant EMPTY_FILE_LIST. */
	private static final List<FileReference> EMPTY_FILE_LIST = new ArrayList<FileReference>();

	/** The Constant EXISTING_DIRECTORY. */
	private static final String EXISTING_DIRECTORY = System.getProperty("java.io.tmpdir");

	/** The Constant FILE_LIST. */
	private static final List<FileReference> FILE_LIST = new ArrayList<FileReference>(1);

	/** The Constant FULL_URL. */
	private static final String FULL_URL = "http://google.com/mail";

	/** The Constant RELATIVE_PATH. */
	private static final String RELATIVE_PATH = "mail";

	/** The Constant STATUS_CHANGE_LISTENER. */
	private static final MyStatusChangeListener STATUS_CHANGE_LISTENER = new MyStatusChangeListener();

	/** The Constant URL. */
	private static final String URL = "http://google.com";

	static {
		FILE_LIST.add(new FileReference());
	}

	/**
	 * Test download files empty files.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDownloadFilesEmptyFiles() {
		HttpUtils.getInstance().downloadFiles(URL, EXISTING_DIRECTORY, EMPTY_FILE_LIST, STATUS_CHANGE_LISTENER);
	}

	/**
	 * Test download files null destination path.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDownloadFilesNullDestinationPath() {
		HttpUtils.getInstance().downloadFiles(URL, null, FILE_LIST, STATUS_CHANGE_LISTENER);
	}

	/**
	 * Test download files null files.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDownloadFilesNullFiles() {
		HttpUtils.getInstance().downloadFiles(URL, EXISTING_DIRECTORY, null, STATUS_CHANGE_LISTENER);
	}

	/**
	 * Test download files null status change listener.
	 */
	@Test(expected = NullPointerException.class)
	public void testDownloadFilesNullStatusChangeListener() {
		HttpUtils.getInstance().downloadFiles(URL, EXISTING_DIRECTORY, FILE_LIST, null);
	}

	/**
	 * Test download files null url.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDownloadFilesNullUrl() {
		HttpUtils.getInstance().downloadFiles(null, EXISTING_DIRECTORY, FILE_LIST, STATUS_CHANGE_LISTENER);
	}

	/**
	 * Test get full url.
	 */
	@Test
	public void testGetFullUrl() {
		// Test null source
		try {
			HttpUtils.getFullUrl(null);
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		// Test null location
		final HttpSource httpSource = new HttpSource();
		try {
			HttpUtils.getFullUrl(httpSource);
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		// Test null location URL
		final HttpLocation location = new HttpLocation();
		httpSource.setLocation(location);
		try {
			HttpUtils.getFullUrl(httpSource);
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		// Test blank location URL
		location.setUrl("");
		try {
			HttpUtils.getFullUrl(httpSource);
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		// Test URL only
		location.setUrl(URL);
		assertEquals(URL, HttpUtils.getFullUrl(httpSource));

		// Test URL with relative path
		httpSource.setRelativePath(RELATIVE_PATH);
		assertEquals(FULL_URL, HttpUtils.getFullUrl(httpSource));
	}
}
