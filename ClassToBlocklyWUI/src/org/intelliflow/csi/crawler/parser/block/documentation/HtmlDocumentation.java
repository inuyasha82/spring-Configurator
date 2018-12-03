package org.intelliflow.csi.crawler.parser.block.documentation;

import java.lang.reflect.Constructor;

public class HtmlDocumentation {
	
	public static String getConstructorLabel() {
		return "<p>Constructor:</p>\n";
	}

	public static String getFieldsLabel() {
		return "<p>Fields:</p>\n";
	}
	
	public static String getLabel(String labelName) {
		return "<p>" + labelName  + "</p>"; 
	}
	
	public static String createPackageLine(String packageName) {
		return "<h3>Package: <i>" + packageName + "</i></h3>";
	}
	
	public static String getListOpen() {
		return "<ul>\n";
	}
	
	public static String createFieldDocumentationListElement(String type, String name) {
		return "<li>" + type + " : " + name +"</li>\n";
	}
	
	public static String createConstructorLine(Constructor constructor) {
		System.out.println(constructor.toString());
		return "<li>" + constructor.toString() + "</li>\n";
	}

	public static String getListClose() {
		return "</ul>\n";
	}
	
	public static String generateHtmlHeader(String className) {
		return SharedStrings.HTML_OPEN_TAG 
				+ SharedStrings.HTML_HEADER.replace("@CLASSNAME", className)
				+ SharedStrings.BODY_OPEN_TAG;
	}

	public static String createSingleConstructorDocumentationListElement(String type, String name, int position) {
		return  "<li> Position: " + position + " - " + type + " : " + name +"</li>\n";
	}

	public static String generateHtmlFooter() {
		return SharedStrings.BODY_CLOSE_TAG 
				+ SharedStrings.HTML_CLOSE_TAG;
	}

	public static String createDocumentationPageHeader(String  header) {
		return SharedStrings.CLASSNAME_HEADER.replace("@CLASSNAME", header);
		
	}
}
