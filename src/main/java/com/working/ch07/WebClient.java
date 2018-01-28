package com.working.ch07;

import java.io.IOException;
import java.io.InputStream;

public class WebClient {
	public String getContent(ConnectionFactory connectionFactory) {
		InputStream is = null;
		StringBuffer content = new StringBuffer();
		try {
			is = connectionFactory.getData();
			int count;
			while (-1 != (count = is.read())) {
				content.append(new String(Character.toChars(count)));
			}
		} catch (Exception e) {
			return null;
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					return null;
				}
			}
		}
		return content.toString();
	}
}
