package org.intelliflow.csi.crawler.parser.block;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.intelliflow.csi.crawler.parser.block.documentation.BlockDocumentation;
import org.intelliflow.csi.crawler.parser.block.generators.GeneratorSelector;
import org.intelliflow.csi.crawler.parser.block.generators.interfaces.Generator;
import org.intelliflow.csi.crawler.parser.utils.ColorSelector;
import org.intelliflow.csi.crawler.parser.utils.UtilityFunctions;

import com.google.gson.Gson;

public class BlockDefinition extends BlockAbstract{
	
	private StringBuffer codeBuffer;
	private final String subfolderName = "blocks";
	private boolean isInline = false;
	private GeneratorSelector generatorSelector;
	private final String htmlFolderName = "html";
	private int lastColor;
	BlockDocumentation blockDocumentation;
	
	public BlockDefinition(String packageName, String className){
		super(packageName, 	className);
		this.codeBuffer = new StringBuffer();
		generatorSelector = new GeneratorSelector();
		this.blockDocumentation = new BlockDocumentation(className, packageName);
	}
	
	public BlockDefinition(String packageName, String className, boolean isInline, String projectFolder) {
		this(packageName, className);
		this.isInline = isInline;
		this.projectFolder = projectFolder;
	}
	
	public void writeToJsFile(){
		try {
			Gson jsonObject = new Gson();
			System.out.println("BLOCKDEFINITION");
			String fullPath = getProjectPath();
			File projectDirectory = new File(fullPath);
			projectDirectory.mkdirs();
			BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(fullPath + "xml_blocks.js"), true));
			codeBuffer.append(BlockStrings.HEADER_DECLARATION_JS.replace("@CLASSNAME", className.toLowerCase()));
			codeBuffer.append(BlockStrings.HEADER_BLOCKNAME_LABEL_JS.replace("@CLASSNAME", className));
			/*codeBuffer.append("\t\tthis.appendDummyInput()\n")
				.append("\t\t.appendField('" + className + "');\n");*/
			codeBuffer.append(BlockStrings.HEADER_ID_FIELD);
			if(isOnlyOneConstructorNotDefault) {
				createConstructorStatement();
			} else if(addConstructorToBlock) {
				codeBuffer.append(generatorSelector.getDefaultGenerator().generateHeader("constructor", "Constructors", false));
			}
			if (!isEmpty()) {
				createStatement();
			}
			codeBuffer.append(BlockStrings.HEADER_INLINE_JS.replace("@BOOLEANVALUE", Boolean.toString(isInline)));
			codeBuffer.append(BlockStrings.HEADER_SET_OUTPUT);
			codeBuffer.append(BlockStrings.HEADER_COLOR_JS.replace("@COLORCODE", Integer.toString(ColorSelector.getColor(packageName + "." + className))));
			codeBuffer.append(BlockStrings.HEADER_TOOLTIP_JS.replace("@TOOLTIP", "FullClassName: " + getFullClassName()));
			codeBuffer.append(BlockStrings.HEADER_URL_JS.replace("@DOCUMENTATIONURL", "documentation/" + className + ".html"));
			codeBuffer.append(BlockStrings.TAB_BLOCK_CLOSE)
				.append(BlockStrings.FUNCTION_CLOSE);
			bwr.append(codeBuffer + "\n/*---------*/\n");
			bwr.flush();
			bwr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(codeBuffer.toString());
	}
	
	private void createConstructorStatement() {
		if(constructors!=null) {
			codeBuffer.append(BlockStrings.HEADER_BLOCKNAME_LABEL_JS.replace("@CLASSNAME", "Constructor:"));
			for(int i=0; i<constructors.size(); i++) {
				String constructorField = constructors.get(i);
				System.out.println("Constructor: " + constructors.get(i));
				Generator codeGenerator = generatorSelector.getGenerator(UtilityFunctions.extractType(constructorField));
				System.out.println("Generator Type: " + codeGenerator.getClass().getName());
				codeBuffer.append(codeGenerator.generateHeader(constructorField.substring(0, constructorField.indexOf("#")), UtilityFunctions.extractType(constructorField), true));
			}
			if(fields.size() >0) {
				codeBuffer.append(BlockStrings.HEADER_BLOCKNAME_LABEL_JS.replace("@CLASSNAME", "Fields:"));
			}
		}		
	}

	@Override
	public void createStatement() {
		for(String fieldType : fields.keySet()){
			System.out.println("Real KEY: " + fieldType);
			Generator codeGenerator = generatorSelector.getGenerator(UtilityFunctions.extractType(fieldType));
			System.out.println("Generator Type: " + codeGenerator.getClass().getName());
			codeBuffer.append(codeGenerator.generateHeader(fields.get(fieldType), fieldType, requiredFields.contains(fields.get(fieldType))));
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
	
	private int getRandomColor() {
		Random rand = new Random();
		int newColor = rand.nextInt(360) + 1;
		while(newColor > lastColor - 10 && newColor < lastColor + 10) {
			newColor =  rand.nextInt(360) + 1;
		}
		return newColor;
	}
	
	public void generateDocumentation(Constructor[] ctors, String projectPath) {
		blockDocumentation.addHtmlHeader();
		blockDocumentation.addDocumentationClassName();
		blockDocumentation.addPackageName();
		if(isOnlyOneConstructorNotDefault) {
			blockDocumentation.addSingleConstructor(constructors);
		} else if(addConstructorToBlock) {
			blockDocumentation.addMultipleConstructors(ctors);
		}
		blockDocumentation.addFields(fields);
		blockDocumentation.addHtmlFooter();
		//blockDocumentation.printDocumentation();
		if(projectPath != null) {
			blockDocumentation.writeToDocumentationFile(projectPath);
		}
	}

}

/*
 Blockly.Blocks['pipelinedaction'] = {
		init: function() {
			this.appendDummyInput()
			.appendField("PipeLinedAction");
			this.appendValueInput("id")
			.setCheck("String")
			.setAlign(Blockly.ALIGN_RIGHT)
			.appendField("id:");
			this.appendDummyInput()
			.appendField("actions:list");
			this.appendStatementInput("properties")
			.setCheck(['lista']);
			this.setInputsInline(false);
			this.setPreviousStatement(true, null);
			this.setNextStatement(true, null);
			this.setColour(330);
			this.setTooltip('');
			this.setHelpUrl('http://www.example.com/');
		}
};

 * */
