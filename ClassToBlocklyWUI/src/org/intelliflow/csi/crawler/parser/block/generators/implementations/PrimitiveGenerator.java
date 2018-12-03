package org.intelliflow.csi.crawler.parser.block.generators.implementations;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.intelliflow.csi.crawler.parser.block.BlockStrings;
import org.intelliflow.csi.crawler.parser.block.generators.interfaces.Generator;
import org.intelliflow.csi.crawler.parser.utils.SharedStrings;
import org.intelliflow.csi.crawler.parser.utils.UtilityFunctions;

public class PrimitiveGenerator implements Generator {
	
	Logger logger = Logger.getLogger(PrimitiveGenerator.class);
	
	private char SPACE_CHARACTER = ' ';
	private char SINGLE_QUOTE_CHARACTER='\'';
	private char SEMICOLUMN_CHARACTER=';';
	private String SINGLE_QUOTE_SPACE = "\"\' \"";

	private ArrayList<String> suitableTypes = new ArrayList<String>(Arrays.asList("int", "long", "boolean", "java.lang.String", "float",
			"double", "char", "java.lang.Integer", "java.lang.Boolean", "java.lang.Double", "java.lang.Long", "java.lang.Float", "java.lang.Character" ));

	@Override
	public String generateCode(String variableName) {		
		String code = "code += (" + variableName + "_field ? " + SharedStrings.DOUBLE_QUOTE_CHARACTER + "<property name=\'" +  variableName +  "\' value=\'" + SharedStrings.DOUBLE_QUOTE_CHARACTER + " + " + variableName +  "_field + " + SharedStrings.DOUBLE_QUOTE_CHARACTER + "\' />\\n\" : " + SharedStrings.EMPTYSTRING + ");\n";
		logger.info(code);
		return code;
	}

	@Override
	public String generateVariable(String variableName) {
		if(variableName != null) {
			return "var " + variableName + "_field = block.getFieldValue('" + variableName + "');\n";
			//var text_asd = block.getFieldValue('ASD');
		}	
		return null;
	}

	@Override
	public boolean suitableFor(String query) {
		if(suitableTypes.contains(query)) {
			System.out.println(query + " is Suitable for primitive type!!!!");
			return true;
		}
		return false;
	}

	@Override
	public String generateHeader(String fieldName, String fieldType) {
		return generateHeader(fieldName, fieldType, false);
		/*return BlockStrings.HEADER_VALUEINPUT_JS.replace("@FIELDNAME", fieldName.toLowerCase()) +
			BlockStrings.HEADER_FIELD_JS.replace("@FIELDNAME", fieldName.toLowerCase() + " | " + fieldType);*/
		// TODO Auto-generated method stub
		/*
		 * this.appendDummyInput()
        .appendField("Label")
        .appendField(new Blockly.FieldNumber(0), "asd");
		 * */

	}

	private String createField(String fieldName, String fieldType) {
		switch(fieldType) {
		case "java.lang.String":
		case "boolean":
		case "java.lang.Boolean":
		case "char":
		case "int":
		case "java.lang.Integer":
		case "java.lang.Float":
		case "float":
		case "double":
		case "java.lang.Double":
		case "java.lang.Byte":
		case "byte":
		case "long":
		case "java.lang.Long":
		case "short":
		case "java.lang.Short":
			return BlockStrings.HEADER_FIELD_TEXT_INPUT_JS.replace("@FIELDNAME", UtilityFunctions.toCamelCase(fieldName));
		default: 
			return null;	
		}
	}

	@Override
	public String generateHeader(String fieldName, String fieldType, boolean isRequired) {
		String realType = UtilityFunctions.extractType(fieldType);
		String finalFieldName = (isRequired) ? fieldName + "*" : fieldName;
		String code = BlockStrings.HEADER_DUMMYINPUT_JS +
				BlockStrings.HEADER_FIELD_INPUT_JS.replace("@CLASSNAME", realType + " : " + finalFieldName);
		String inputField = createField(fieldName, realType);
		return code + inputField;
	}

	@Override
	public String generateConstructor(String constructorName, int index) {
		// TODO Auto-generated method stub
		//String code = "code += (" + constructorName + "_value ? " + SharedStrings.DOUBLE_QUOTE_CHARACTER + "<constructor-arg index=\'" +  index +  "\'>" + SharedStrings.DOUBLE_QUOTE_CHARACTER + " + " + variableName +  "_value + " + SharedStrings.DOUBLE_QUOTE_CHARACTER + "</property>\\n\" : " + SharedStrings.EMPTYSTRING + ");\n";
		String code = "code += (" + constructorName + "_field ? " + SharedStrings.DOUBLE_QUOTE_CHARACTER + "<constructor-arg index=\'" +  index +  "\' value=\'" + SharedStrings.DOUBLE_QUOTE_CHARACTER + " + " + constructorName +  "_field + " + SharedStrings.DOUBLE_QUOTE_CHARACTER + "\' />\\n\" : " + SharedStrings.EMPTYSTRING + ");\n";
		return code;
	}

}
