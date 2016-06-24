package com.ourpalm.editor.util;

public class ShellUtils {

	private ShellUtils() {
		super();
	}

	public static String getSearchCmd(String uid, String date, String category) {
		String command = "grep %s /opt/ourpalm/data/logs/*/*.racing.hgame.%s.%s*|awk -F ':%s-' '{ print $2}'";
		command = String.format(command, new Object[]{uid, category, date, date.split("-")[0]});
		System.out.println(command);
		return command;
	}
}
