/*******************************************************************************
 * Copyright (c) 2020- UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Daniel Bluhm - Initial implementation
 *******************************************************************************/

package org.eclipse.ice.tests.data;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

/**
 * Test TestConfig class loading and usage.
 * @author Daniel Bluhm
 */
class TestConfigTest {

	private static final String PROP_CONTENTS = "test=abcd1234";
	private static final String PROP_FILENAME = "test.properties";

	private static Path setupPropFile(TestDataPath dataPath) throws IOException {
		dataPath.create();
		try(PrintWriter writer = new PrintWriter(dataPath.writer(PROP_FILENAME))) {
			writer.println(PROP_CONTENTS);
		}
		return dataPath.resolve(PROP_FILENAME);
	}

	@Test
	void testLoadAndAccess() throws IOException {
		TestDataPath dataPath = TestDataPathTest.inMemTestDataPath(null);
		TestConfig config = new TestConfig(dataPath);
		setupPropFile(dataPath);
		config.load(PROP_FILENAME);
		String test = config.getProperty("test");
		assertEquals("abcd1234", test);
	}
}