package org.intelliflow.csi.crawler.parser.utils;

public class CategorySelector {
	
	public static String prefix;
	
	public static String getCategory(String packageName) {
		String packageNameWithoutPrefix = getPackageNameWithoutPrefix(packageName);
		if(packageNameWithoutPrefix.equals(packageName)) {
			return packageName;
		}
		String[] packageNameArray =packageNameWithoutPrefix.split("\\.");
		if(packageNameArray != null) {
			return packageNameArray[0];
		} else {
			return packageName;
		}
	}
	
	public static String getSubCategory(String packageName) {
		String packageNameWithoutPrefix = getPackageNameWithoutPrefix(packageName);
		String[] packageNameArray = packageNameWithoutPrefix.split("\\.");
		if(packageNameArray.length > 1) {
			return packageNameArray[1];
		}
		return null;
	}
	
	private static String getPackageNameWithoutPrefix(String packageName) {
		if(prefix != null) {
			return packageName.replace(prefix + ".", "");
		} 
		return packageName;
	}
	
	public static void main(String[] args) {
		prefix = "it.uniroma1.csi.crawler";
		System.out.println(getCategory("it.uniroma1.csi.crawler.parser.utils"));
		System.out.println(getSubCategory("it.uniroma1.csi.crawler.parser.utils"));
		System.out.println(getCategory("it.uniroma1.csi.crawler"));
	}
}
