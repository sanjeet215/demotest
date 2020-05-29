package com.example.demo.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/*This class should be able to read all types of excel file and return records as rows */

@Service
public class ReadExcelSheetService {

	private static final Logger logger = Logger.getLogger(ReadExcelSheetService.class);

	@Value("${file.upload-dir}")
	private String uploadDir;

	public List<String> readLeaveTypeExcel(String fileName) {

		logger.debug("File path :" + fileName);

		List<String> fileData = new ArrayList<>();
		String readRow="";

		FileInputStream fis = null;
		XSSFWorkbook book = null;

		try {
			File excel = new File(uploadDir + fileName);
			fis = new FileInputStream(excel);
			book = new XSSFWorkbook(fis);
			XSSFSheet sheet = book.getSheetAt(0);
			Iterator<Row> itr = sheet.iterator();
			while (itr.hasNext()) {
				Row row = itr.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						readRow = readRow + cell.getStringCellValue() + ";";
						break;
					case Cell.CELL_TYPE_NUMERIC:
						readRow = readRow + cell.getNumericCellValue() + ";";
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						readRow = readRow + cell.getBooleanCellValue() + ";";
						break;
					default:
					}
				}

				fileData.add(readRow);

				readRow = null;
			}

			book.close();
			fis.close();

		} catch (FileNotFoundException fe) {
			logger.error("There is aan error while uploading the file. File Not Found Exception");
			fe.printStackTrace();
		} catch (IOException ie) {
			logger.error("There is an error while uploading the file IO exception");
		} catch (Exception ep) {
			logger.error("There is an error while uploading the file exception");
			ep.printStackTrace();
		} finally {
			try {
				book.close();
				fis.close();
			} catch (IOException e) {
			}

		}

		return fileData;
	}
}
