package org.bill.bridges.file.converter;

import java.io.File;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;

public class FileFormatConverter {
	public static String convertFile(String originalFileLocation, OutputFormat outputFormat, BitRate bitRate) {
		switch(outputFormat) {
			case MP3:
				return convertToMp3(originalFileLocation, bitRate);
			case FLAC:
				return convertToFlac(originalFileLocation, bitRate);
			case WAV:
				return originalFileLocation;
		}
		
		throw new RuntimeException("Invalid output format specified"); //this shouldn't happen
	}
	
	protected static String convertToMp3(String originalFileLocation, BitRate bitRate) {
		String outputFileName = getFilenameFromLocation(originalFileLocation) + ".mp3";
		String outputLocation = ConverterProperties.getInstance().getOutputDir() + "/" + outputFileName;
		executeConversion(originalFileLocation, outputLocation, "libmp3lame", bitRate);
		return outputLocation;
	}
	
	protected static String convertToFlac(String originalFileLocation, BitRate bitRate) {
		String outputFileName = getFilenameFromLocation(originalFileLocation) + ".flac";
		String outputLocation = ConverterProperties.getInstance().getOutputDir() + "/" + outputFileName;
		executeConversion(originalFileLocation, outputLocation, "flac", bitRate);
		return outputLocation;
	}
	
	private static void executeConversion(String originalFileLocation, String outputFileLocation, String codec, BitRate bitRate) {
		String command = "\"" + ConverterProperties.getInstance().getFfmpegPath() + "/ffmpeg\""; // + parameters;
		CommandLine cmd = CommandLine.parse(command);
		//input file argument
		cmd = cmd.addArgument("-i", false).addArgument(originalFileLocation, false);
		
		//codec argument
		cmd = cmd.addArgument("-c:a", false).addArgument(codec, false);
		
		//bit rate argument
		if(!bitRate.getValue().isEmpty()) {
			if(bitRate.equals(BitRate.VARIABLE_HIGH) || bitRate.equals(BitRate.VARIABLE_LOW) ||
					bitRate.equals(BitRate.VARIABLE_MEDIUM) || bitRate.equals(BitRate.VARIABLE_SUPER_HIGH)) {
				cmd = cmd.addArgument("-q:a", false).addArgument(bitRate.getValue(), false);
			}
			else {
				cmd = cmd.addArgument("-b:a", false).addArgument(bitRate.getValue(), false);
			}
		}
		
		//output file
		cmd = cmd.addArgument(outputFileLocation, false);
		DefaultExecutor executor = new DefaultExecutor();
		try {
			int exitValue = executor.execute(cmd);
			System.out.println(exitValue);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private static String getFilenameFromLocation(String originalFileLocation) {
		File originalFile = new File(originalFileLocation);
		String fileName = originalFile.getName();
		String fileNameWithoutExtension = fileName.split("\\.")[0];
		return fileNameWithoutExtension;
	}
}
