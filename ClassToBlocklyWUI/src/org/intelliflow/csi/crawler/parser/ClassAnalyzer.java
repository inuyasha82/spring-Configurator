package org.intelliflow.csi.crawler.parser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.cli.CommandLine;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.intelliflow.csi.crawler.parser.block.BlockDefinition;
import org.intelliflow.csi.crawler.parser.block.BlockGenerator;
import org.intelliflow.csi.crawler.parser.block.BlockStrings;
import org.intelliflow.csi.crawler.parser.utils.CategorySelector;
import org.intelliflow.csi.crawler.parser.utils.ColorSelector;
import org.intelliflow.csi.crawler.parser.utils.UtilityFunctions;
import org.springframework.beans.factory.annotation.Required;

public class ClassAnalyzer {
	ArrayList<String> parametersList;
	String projectFolderName;
	ArrayList<String> blocksNames = new ArrayList<String>();
	HashMap<String, ArrayList<String>> categorizedItems;
	static final Logger logger = Logger.getLogger(ClassAnalyzer.class);

	public ClassAnalyzer() {
		BasicConfigurator.configure();
		this.projectFolderName = UtilityFunctions.generateProjectFolderName();
		this.categorizedItems = new HashMap<>();
	}

	public ClassAnalyzer(String projectFolderName) {
		this.projectFolderName = projectFolderName;
		this.categorizedItems = new HashMap<>();
	}

	public void run(String packageName, String className) {
		try {
			logger.info("Analyzing: " + className);
			BlockDefinition block = new BlockDefinition(packageName, className, false, projectFolderName);
			BlockGenerator generator = new BlockGenerator(packageName, className, projectFolderName);
			Class sourceClass = Class.forName(packageName + "." + className);
			int classColor = ColorSelector.getColor(packageName);
			logger.debug("Class color is:" + classColor);
			if (!Modifier.isAbstract(sourceClass.getModifiers()) && !Modifier.isInterface(sourceClass.getModifiers())) {
				parametersList = new ArrayList<>();
				Field fieldList[] = sourceClass.getFields();
				System.out.println("Fields: " + fieldList.length);
				Method[] methods = sourceClass.getMethods();
				Constructor[] ctors = sourceClass.getConstructors();
				System.out.println("Ctors length: " + ctors.length);
				parseConstructors(ctors, block, generator);
				for (Method method : methods) {
					parseMethod(method, block, generator);
				}
				block.writeToJsFile();
				generator.writeToJsFile();
				block.generateDocumentation(ctors, projectFolderName);
				blocksNames.add(className.toLowerCase());
				saveClassItem(packageName, className);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(2);
		} catch (SecurityException e) {
			e.printStackTrace();
			System.exit(2);
		}
	}

	private void saveClassItem(String packageName, String className) {
		String mainCategory = CategorySelector.getCategory(packageName);
		logger.info("Package name: " + packageName + " Class name: " + className);
		ArrayList<String> items;
		if(!categorizedItems.containsKey(mainCategory)) {
			items = new ArrayList<>();
			items.add(className.toLowerCase());
			categorizedItems.put(mainCategory, items);
		} else {
			items = categorizedItems.get(mainCategory);
			items.add(className.toLowerCase());
		}
	}

	public String getHtmlProjectPath() {
		StringBuffer partialPath = new StringBuffer();
		if (this.projectFolderName != null && !this.projectFolderName.equals("")) {
			partialPath.append(this.projectFolderName + "/");
		}
		return partialPath.append("html/").toString();
	}

	public void writeToHtmlObjectList() {
		String fullPath = getHtmlProjectPath();
		File projectHtmlDirectory = new File(fullPath);
		projectHtmlDirectory.mkdirs();
		StringBuffer htmlCode = new StringBuffer();
		try {
			BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(fullPath + "toolboxcontent.html"), true));
			for (String blockname : blocksNames) {
				htmlCode.append((BlockStrings.HTML_FIELD_TAG).replace("@BLOCKNAME", blockname.toLowerCase()));
			}
			bwr.append(htmlCode);
			bwr.flush();
			bwr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void generateCategoryItems(ArrayList<String> source, StringBuffer codeWriter) {
		if(source!=null) {
			for (String className : source) {
				codeWriter.append((BlockStrings.HTML_FIELD_TAG).replace("@BLOCKNAME", className.toLowerCase()));
			}
		}
	}
	
	private void writeToHierarchicalObjectList() {
		ArrayList<String> rootCategory = categorizedItems.get(CategorySelector.prefix);
		StringBuffer htmlCode = new StringBuffer();
		String fullPath = getHtmlProjectPath();
		File projectHtmlDirectory = new File(fullPath);
		projectHtmlDirectory.mkdirs();
		try {
			BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(fullPath + "toolboxcontent.html"), true));
			categorizedItems.remove(CategorySelector.prefix);
			for(String key : categorizedItems.keySet()) {
				htmlCode.append((BlockStrings.HTML_CATEGORY_TAG).replace("@CATEGORYNAME", key));
				ArrayList<String> classes = categorizedItems.get(key);
				generateCategoryItems(classes, htmlCode);
				htmlCode.append(BlockStrings.HTML_CATEGORY_CLOSE_TAG);
			}
			generateCategoryItems(rootCategory, htmlCode);
			bwr.append(htmlCode);
			bwr.flush();
			bwr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info(htmlCode);
	}

	public void run(String fullClasSName) {
		String packageName = fullClasSName.substring(0, fullClasSName.lastIndexOf('.'));
		String className = fullClasSName.substring(fullClasSName.lastIndexOf('.') + 1);
		System.out.println(packageName + ':' + className);
		run(packageName, className);
	}

	private Constructor<?> getMaxLenConstructor(Constructor<?>[] ctors) {
		Constructor<?> maxLenConstructor = null;
		if (ctors.length > 0) {
			maxLenConstructor = ctors[0];
			for (Constructor<?> constructor : ctors) {
				System.out.print(" constructor: " + constructor.getParameterCount());
				if (maxLenConstructor.getParameterCount() < constructor.getParameterCount()) {
					maxLenConstructor = constructor;
				}
			}
		}
		return maxLenConstructor;
	}

	public void parseConstructors(Constructor<?>[] ctors, BlockDefinition block, BlockGenerator generator) {
		Constructor<?> targetConstructor = getMaxLenConstructor(ctors);
		boolean isOnlyOneConstructorNotDefault = targetConstructor != null && targetConstructor.getParameterCount() > 0 && ctors.length <= 1;
		System.out.println("Not default Constructor #" + isOnlyOneConstructorNotDefault);
		if (targetConstructor != null && targetConstructor.getParameterCount() > 0) {
			//Tooltip
			block.addConstructor(targetConstructor, isOnlyOneConstructorNotDefault);
			generator.addConstructor(targetConstructor, isOnlyOneConstructorNotDefault);
		}
	}

	public void parseMethod(Method method, BlockDefinition block, BlockGenerator generator) {
		if (method.getName().startsWith("set") && !UtilityFunctions.isDeclaredInCoreClasses(method)) {
			logger.info("Declaring class: " + method.getDeclaringClass());
			parametersList.add(method.getName());
			logger.info("Method name: " + method.getName() + " Parameters " + method.getParameterTypes()
					+ " Is required: " + method.isAnnotationPresent(Required.class));
			block.addSetter(method, method.isAnnotationPresent(Required.class));
			generator.addSetter(method, method.isAnnotationPresent(Required.class));
		}
	}
	
	public void analyzePackage(String packageName) {
		ClassEnumerator enumerator = new ClassEnumerator(packageName);
		enumerator.loadClassesWithReflection();
		ArrayList<String> loadedClasses = enumerator.getClasses();
		for (String loadedClass : loadedClasses) {
			run(loadedClass);
		}
		writeToHtmlObjectList();
	}

	/* Da spostare in una funzione */
	public static void main(String[] args) {
		ClassAnalyzer analyzer;
		CliParser parser = new CliParser(args);
		parser.parse();
		CommandLine cmd = parser.getArguments();
		// TODO Use GetOptionValues
		if (cmd.hasOption("d")) {
			logger.info("Option value " + cmd.getOptionValue("d"));
			analyzer = new ClassAnalyzer(cmd.getOptionValue("d"));
		} else {
			analyzer = new ClassAnalyzer();
		}
		if (cmd.hasOption("c") && !cmd.hasOption("p")) {
			analyzer.run(cmd.getOptionValue("c"));
			analyzer.writeToHtmlObjectList();
		} else if (cmd.hasOption("p") && !cmd.hasOption("c")) {
			logger.info("package to analyze " + cmd.getOptionValue("p"));
			if (cmd.getOptionValue("p") != null) {
				//Usare analyzePackage();
				ClassEnumerator enumerator = new ClassEnumerator(cmd.getOptionValue("p"));
				CategorySelector.prefix = cmd.getOptionValue("p");
				ColorSelector.prefix = cmd.getOptionValue("p");
				enumerator.loadClassesWithReflection();
				ArrayList<String> loadedClasses = enumerator.getClasses();
				for (String loadedClass : loadedClasses) {
					analyzer.run(loadedClass);
				}
				//analyzer.writeToHtmlObjectList();
				analyzer.writeToHierarchicalObjectList();
			} else {
				parser.usage();
				System.exit(1);
			}
		} else {
			parser.usage();
			System.exit(1);
		}
	}
}

/*
 * txt = txt.concat("Blockly.Crawler['" + c.getName().toLowerCase() +
 * "'] = function(block) {\n"); txt = txt.
 * concat("var value_id = Blockly.Crawler.valueToCode(block, 'id', Blockly.Crawler.ORDER_ATOMIC);"
 * ); txt = txt.concat("};");
 * 
 * 
 * -c it.uniroma1.lcl.csi.core.actions.PipelinedResettableAction
 */