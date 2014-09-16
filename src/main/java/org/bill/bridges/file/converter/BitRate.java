package org.bill.bridges.file.converter;

public enum BitRate {
	_128K("128k"), _160K("160k"), _192K("192k"), VARIABLE_LOW("6"), VARIABLE_MEDIUM("4"), 
	VARIABLE_HIGH("2"), VARIABLE_SUPER_HIGH("0"), FLAC(""), WAV("");
	
	private String value;
	
	private BitRate(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
