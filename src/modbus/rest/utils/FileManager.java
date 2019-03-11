package modbus.rest.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {
	public static void writeFile(String filePath, String content) throws IOException {
		FileWriter writer = new FileWriter(filePath);

		writer.write(content);

		writer.close();
	}

	public static ArrayList<String> readFile(String file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String sCurrentLine;
		ArrayList<String> lines = new ArrayList<String>();

		while ((sCurrentLine = br.readLine()) != null) {
			lines.add(sCurrentLine);
		}

		br.close();

		return lines;
	}
	
	
	

//	public static ArrayList<String> readFileAsList(String file) throws IOException {
//		BufferedReader br = new BufferedReader(new FileReader(file));
//		String sCurrentLine;
//		StringBuilder sb = new StringBuilder();
//		Stack<String> linesStack = new Stack<String>();
//		System.out.println("LEGGO");
//
//		ArrayList<String> l = new ArrayList<String>();
//
//		while ((sCurrentLine = br.readLine()) != null) {
//			System.out.println("Linea:" + sCurrentLine);
//			linesStack.push(sCurrentLine);
//			l.add(sCurrentLine);
//		}
//
//		System.out.println("Stack size:" + linesStack.size());
//
//		br.close();
//
//		lines = lines > l.size() ? l.size() : lines;
//
//		for (int i = l.size() - lines; i < l.size(); i++) {
//			sb.append(l.get(i));
//		}
//		System.out.println("Return value:" + sb.toString());
//		return sb.toString();
//	}
}
