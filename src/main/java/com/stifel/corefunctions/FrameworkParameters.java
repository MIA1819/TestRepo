package com.stifel.corefunctions;

public class FrameworkParameters {
	private String relativePath;
	private String resultPath;
	private static int passCount;
	private static int failCount;
	private static int noRunCount;
	private static FrameworkParameters frameworkParameters;

	public String getRelativePath() {
		return this.relativePath;
	}

	public void setRelativePath(String relPAth) {
		this.relativePath = relPAth;
	}

	public String getResultPath() {
		return this.resultPath;
	}

	public void setResultPath(String resPAth) {
		this.resultPath = resPAth;
	}

	public int getOverallFail() {
		return failCount;
	}

	public void setOverallFail(int ovrlFail) {
		failCount = ovrlFail;
	}

	public int getOverallPass() {
		return passCount;
	}

	public void setOverallPass(int ovrlPass) {
		passCount = ovrlPass;
	}

	public int getOverallNoRun() {
		return noRunCount;
	}

	public void setOverallNoRun(int ovrlNoRun) {
		noRunCount = ovrlNoRun;
	}

	public static synchronized FrameworkParameters getInstance() {
		if (frameworkParameters == null) {
			frameworkParameters = new FrameworkParameters();
		}
		return frameworkParameters;
	}
}