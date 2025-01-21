/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package com.stifel.corefunctions;

import java.util.HashMap;
import java.util.Map;

public class RowRecord {
	Map<String, String> data;

	public RowRecord() {
		this.data = new HashMap();
	}

	public Map<String, String> getData() {
		return this.data;
	}

	public String getMyMap(String key) {
		return ((String) this.data.get(key));
	}

	public void addToMap(String key, String value) {
		this.data.put(key, value);
	}
}
