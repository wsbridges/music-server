package org.bill.bridges.file.converter;

public class ConverterDriver {

	public static void main(String[] args) {
		String wavFilePath = "/Users/Bill/billows intro stereo.wav";
		FileFormatConverter.convertFile(wavFilePath, OutputFormat.FLAC, BitRate.FLAC);
	}

}
