module moduleInfo {
	exports persistentie;
	exports cui;
	exports utils;
	exports gui;
	exports main;
	exports domein;
	exports testen;
	exports dto;
	exports exceptions;

	requires java.sql;
	requires junit;
	requires org.junit.jupiter.api;
	requires org.junit.jupiter.params;
	
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	
	opens main to javafx.graphics;
	opens gui to javafx.fxml;
}