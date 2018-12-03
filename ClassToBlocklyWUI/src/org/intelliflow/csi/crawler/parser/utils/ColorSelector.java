package org.intelliflow.csi.crawler.parser.utils;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.core.env.SystemEnvironmentPropertySource;

public class ColorSelector {

	private static HashMap<String, Integer> colorsContainer = new HashMap<String, Integer>();
	private static int currentColor = 0;
	private static boolean[] usedColors = new boolean[360];
	public static String prefix;

	public static int getColor(String packageName) {
		/*if(!colorsContainer.containsKey(packageName)) {
			colorsContainer.put(packageName, currentColor);
		}
		currentColor+=15;*/
		//System.out.println(ColorSelector.class.getName() + " - Package: " + packageName + " Color: " + selectColor(packageName) + " Prefix: " + prefix );
		return selectColor(packageName);
		/*Class[] interfaces = currentClass.getInterfaces();
		boolean parentFound;
		System.out.println(currentClass.getPackage().toString());
		String packageName = currentClass.getPackage().toString();
		for(String key: colorsContainer.keySet()) {
			if(!packageName.contains(key)) {
				//int color =  ThreadLocalRandom.current().nextInt(currentColor%360+15, 361); 
			}
		}*/
		/*if(interfaces != null) {
			System.out.println("interfaces: " + interfaces.length);
			for(int i = 0; i<interfaces.length; i++) {
				System.out.println("val: " + interfaces[i].getSimpleName());
			}
		}*/

	}

	private static int selectColor(String packageName) {
		String subPackage = packageName.replace(prefix + ".", "");
		System.out.println(ColorSelector.class.getName() + " SubPackage: " + subPackage);
		String[] packagesArray = subPackage.split("\\.");
		String colorKey;
		//System.out.println(packagesArray.length);
		//System.out.println(packagesArray[0]);
		if(packagesArray != null && packagesArray.length > 1) {
			colorKey = packagesArray[0];
			if(!colorsContainer.containsKey(packagesArray[0])) {
				colorsContainer.put(packagesArray[0], currentColor);
				currentColor = updateCurrentColor(currentColor);
			}
		} else { 
			colorKey = "base_color_package";
			if(!colorsContainer.containsKey("base_color_package")) {			
				colorsContainer.put("base_color_package", currentColor);
				currentColor = updateCurrentColor(currentColor);
			}
		}
		return colorsContainer.get(colorKey);
	}
	
	private static int updateCurrentColor(int currentColor) {
		if(currentColor+15 > 360) {
			return 0;
		}
		return currentColor+15;
	}

	public static void main(String[] args) {
		prefix = "it.uniroma1.csi.crawler.parser";
		int a =getColor("it.uniroma1.csi.crawler.parser.utils.ColorSelector");
		int b =getColor("it.uniroma1.csi.crawler.parser.objects.ColorSelector");
		int c =getColor("it.uniroma1.csi.crawler.parser.utils.subpackage.ColorSelector");
		int d =getColor("it.uniroma1.csi.crawler.parser.ColorSelector");
		int e =getColor("it.uniroma1.csi.crawler.parser.ColossrSelector");
		System.out.println("a = 0 is " + a);
		System.out.println("b = 15 is " + b);
		System.out.println("c = 0 is " + c);
		System.out.println("d = 30 is " + d);
		System.out.println("e = 30 is " + e);
		System.out.println("Key: utils" + colorsContainer.get("utils"));
		System.out.println("Key: objects" + colorsContainer.get("objects"));
		System.out.println(colorsContainer.size());
	}
}
