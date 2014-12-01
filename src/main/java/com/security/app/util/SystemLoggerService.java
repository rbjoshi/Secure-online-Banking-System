package com.security.app.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class SystemLoggerService {
	public static Logger logger = Logger.getLogger(SystemLoggerService.class);

	public static Map<String, List<String>> getFileContentMap()
			throws IOException {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("C:/User/user/Desktop/log/log.log"));
			String line = null;
			while ((line = br.readLine()) != null) {
				if (line.contains("SystemLoggerService")) {
					String date = line.split(" ")[0].trim();
					List<String> list = map.get(date);
					if (list == null) {
						list = new ArrayList<String>();
					}
					list.add(line);
					map.put(date, list);
				}
			}
		} catch (IOException e) {
			logger.info(e);
		} finally {
			if(br != null)
				br.close();
		}
		return map;
	}
}
