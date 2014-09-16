package org.bill.bridges.file.converter;

public enum OutputFormat {
	MP3("MP3"), FLAC("FLAC"), WAV("WAV");
	
	private String value;
	
	private OutputFormat(String value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
}
