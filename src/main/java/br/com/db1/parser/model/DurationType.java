package br.com.db1.parser.model;

public enum DurationType {
	HOURLY, DAILY;

	public static boolean isDaily(DurationType duration){
		return DAILY.equals(duration);
	}
}