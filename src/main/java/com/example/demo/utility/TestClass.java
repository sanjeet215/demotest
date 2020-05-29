package com.example.demo.utility;

public class TestClass {

	public static void main(String[] args) {

		ReadExcelSheetService readExcel = new ReadExcelSheetService();
		readExcel.readLeaveTypeExcel("TestFile.xlsx").forEach(item ->{
			System.out.println(item);
		});

	}

}
