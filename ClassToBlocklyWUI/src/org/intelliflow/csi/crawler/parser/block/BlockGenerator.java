package org.intelliflow.csi.crawler.parser.block;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.intelliflow.csi.crawler.parser.block.generators.GeneratorSelector;
import org.intelliflow.csi.crawler.parser.block.generators.interfaces.Generator;
import org.intelliflow.csi.crawler.parser.utils.UtilityFunctions;

public class BlockGenerator extends BlockAbstract{

	//private ArrayList<String> statementsDeclarations;
	//private ArrayList<String> statementsImplementation;
	private GeneratorSelector generatorSelector;
	private final String subfolderName = "generators/crawler/";

	public BlockGenerator(String packageName, String className){
		super(packageName, className);
	//	statementsDeclarations = new ArrayList<>();
	//	statementsImplementation = new ArrayList<>();
		generatorSelector = new GeneratorSelector();
	}
	
	public BlockGenerator(String packageName, String className, String projectFolder) {
		this(packageName, className);
		this.projectFolder = projectFolder;
	}

	@Override
	public void writeToJsFile(){
		try {
			String fullPath = getProjectPath();
			File projectDirectory = new File(fullPath);
			projectDirectory.mkdirs();
			BufferedWriter bwr = new BufferedWriter(new FileWriter(fullPath + "xml_blocks.js", true));
			codeBuffer.append(BlockStrings.CODE_DECLARATION_JS.replace("@CLASSNAME", className.toLowerCase()));
			//codeBuffer.append("Blockly.Crawler['" +  className.toLowerCase() + "'] = function(block) {\n");
			//codeBuffer.append("<property></property>");
			createStatement();
			codeBuffer.append(BlockStrings.FUNCTION_CLOSE);
			bwr.append(codeBuffer.toString()+ "\n/*-----*/\n");
			bwr.flush();
			bwr.close();
			System.out.println(codeBuffer.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createStatement(){
		for(String key : fields.keySet()){
			System.out.println("--Key : " + key + " Value: " + fields.get(key));
			//Aggiungere classe +
			Generator codeGenerator = generatorSelector.getGenerator(UtilityFunctions.extractType(key));
			if(codeGenerator!=null){
				codeBuffer.append(codeGenerator.generateVariable(UtilityFunctions.toCamelCase(fields.get(key))));
			}
			codeBuffer.append('\n');
		}
		if(isOnlyOneConstructorNotDefault) {
			generateConstructorVariables();
		} else if(addConstructorToBlock) {
			codeBuffer.append(generatorSelector.getDefaultGenerator().generateVariable("constructor"));
		}
		codeBuffer.append(generatorSelector.generateBody(packageName + '.' + className));
		if(isOnlyOneConstructorNotDefault) {
			generateConstructorCode();
		} else if(addConstructorToBlock) {
			codeBuffer.append("code += (constructor_value ? constructor_value : \"\");\n");
		}
		for(String key : fields.keySet()){
			Generator codeGenerator = generatorSelector.getGenerator(UtilityFunctions.extractType(key));
			if(codeGenerator != null){
				codeBuffer.append(codeGenerator.generateCode(UtilityFunctions.toCamelCase(fields.get(key))));
			}
		}
		codeBuffer.append("code += \"</bean>\";\n");
		codeBuffer.append("return [code, Blockly.Crawler.ORDER_ATOMIC];\n");
	}
	
	private void generateConstructorCode() {
		if(constructors!=null) {
			for(int i=0; i< constructors.size(); i++) {
				String constructorField = constructors.get(i);
				String constructorName = constructorField.substring(0, constructorField.indexOf("#"));
				String constructorType = UtilityFunctions.extractType(constructorField);
				logger.info("Construtor field values: " + constructorName + " Type: " + constructorType);
				Generator codeGenerator = generatorSelector.getGenerator(constructorType);
				codeBuffer.append(codeGenerator.generateConstructor(constructorName, i));
			}	
		}
	}

	private void generateConstructorVariables() {
		if(constructors!=null) {
			for(int i=0; i < constructors.size(); i++) {
				String constructorField = constructors.get(i);
				String constructorName = constructorField.substring(0, constructorField.indexOf("#"));
				String constructorType = UtilityFunctions.extractType(constructorField);
				logger.info("Construtor field: " + constructorName + " Type: " + constructorType);
				Generator codeGenerator = generatorSelector.getGenerator(constructorType);
				codeBuffer.append(codeGenerator.generateVariable(constructorName));
			}
		}
	}

	@Override
	public String getProjectPath() {
		StringBuffer partialPath = new StringBuffer();
		if(this.projectFolder != null && !this.projectFolder.equals("")) {
			partialPath.append(this.projectFolder + "/");
		}
		return partialPath.append(subfolderName + "/").toString();
	}

}