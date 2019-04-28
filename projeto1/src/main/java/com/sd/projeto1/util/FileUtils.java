package com.sd.projeto1.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {

	public static void writeFile(String operationType, BigInteger id, String message) {
		String separator = "#";
		try (FileWriter writer = new FileWriter("app.log"); BufferedWriter bw = new BufferedWriter(writer)) {
			String line = operationType.concat(separator).concat(String.valueOf(id)).concat(separator).concat(message);
			bw.write(line);

		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
	}

	public static void read() {

		StringBuilder sb = new StringBuilder();

		try (BufferedReader br = Files.newBufferedReader(Paths.get("app.log"))) {

			
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line).append("\n");
			}

		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}

		System.out.println(sb);

	}
}
