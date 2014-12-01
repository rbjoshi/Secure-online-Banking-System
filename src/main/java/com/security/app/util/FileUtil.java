package com.security.app.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class FileUtil {

	public static void createLogFiles(Map<String, List<String>> map) throws IOException {
		for(Entry<String, List<String>> enrty : map.entrySet()) {
			BufferedWriter writer = new BufferedWriter(new FileWriter("logs/"+enrty.getKey()+".log"));
			for(String line : enrty.getValue()) {
				writer.write(line);
				writer.newLine();
			}
			writer.close();
		}
	}
}
