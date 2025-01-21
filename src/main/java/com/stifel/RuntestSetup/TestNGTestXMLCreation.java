package com.stifel.RuntestSetup;

import java.io.IOException;
import java.net.URISyntaxException;

import com.stifel.corefunctions.TestNGSuiteCreator;

public class TestNGTestXMLCreation {
	public static void main(String args[]) throws URISyntaxException, IOException {
		new TestNGSuiteCreator().createSuiteXMLFile();
	}
}
