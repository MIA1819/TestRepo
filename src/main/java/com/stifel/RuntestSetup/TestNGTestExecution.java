package com.stifel.RuntestSetup;

import java.io.IOException;
import java.net.URISyntaxException;

import org.testng.annotations.Test;

import com.stifel.corefunctions.*;


public class TestNGTestExecution extends TestNGSuiteCreator {
	@Test
	public static void SFDCRegressionSuite() throws URISyntaxException, IOException {
		new TestNGSuiteCreator().runTestNGTest();
	}
}
