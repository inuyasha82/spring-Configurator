package org.intelliflow.csi.crawler.parser.utils;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class UtilityFunctions {
	
	public static String generateProjectFolderName() {
		DateFormat dateFormat = new SimpleDateFormat("HHmm");
		Date date = new Date();
		LocalDate localdate = LocalDate.now();
		StringBuffer partialName = new StringBuffer();
		partialName.append( Integer.toString(localdate.getYear()) + 
				Integer.toString(localdate.getMonthValue()) + 
				Integer.toString(localdate.getDayOfMonth()) + 
				dateFormat.format(date));
		return "project" + partialName;
	}
	
	public static String toCamelCase(String source) {
		return source.substring(0, 1).toLowerCase() + source.substring(1);
	}
	
	public static void createFolders(String fullProjectPath) {
		
	}
	
	public static String packageNameShortener(String fieldName) {
		String[] fields = fieldName.split("\\.");
		int length = fields.length;
		if(length > 2) { 
			return fields[length - 3] + "." + fields[length-2] + "." +  fields[length-1];
		}
		return fieldName;
	}

	public static String extractType(String key) {
		return key.substring(key.lastIndexOf("#") + 1, key.length() );
	}
	
	public static String packageNameExtractAndShorten(String fieldTypeExtended) {
		return packageNameShortener(extractType(fieldTypeExtended));
	}
	public static boolean isDeclaredInCoreClasses(Method method) {
		String declaredClass = method.getDeclaringClass().getName();
		if(declaredClass.contains("java.lang") || declaredClass.contains("java.io")) {
			return true;
		}
		return false;
	}

}
