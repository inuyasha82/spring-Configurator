package org.intelliflow.csi.crawler.parser.block.generators.implementations;

import org.intelliflow.csi.crawler.parser.block.BlockStrings;
import org.intelliflow.csi.crawler.parser.block.generators.interfaces.Generator;
import org.intelliflow.csi.crawler.parser.utils.SharedStrings;
import org.intelliflow.csi.crawler.parser.utils.UtilityFunctions;

/**
 * @author Ivan Gualandri
 * 
 * Implementazione generica interfaccia Generator, questa classe va ussata nel caso in cui non si trovano specializzazioni adatte.
 * */
public class DefaultGenerator implements Generator {

	@Override
	public String generateCode(String variableName) {
		String code = "code += (" + variableName + "_value ? " + SharedStrings.DOUBLE_QUOTE_CHARACTER + "<property name=\'" +  variableName +  "\'>" + SharedStrings.DOUBLE_QUOTE_CHARACTER + " + " + variableName +  "_value + " + SharedStrings.DOUBLE_QUOTE_CHARACTER + "</property>\\n\" : " + SharedStrings.EMPTYSTRING + ");\n";		
		return code;
		//return "code +=\"<property name=\'" + variableName + "\'>\"+" + variableName + "_value + \"</property>\\n\";\n";
		//code +="<property name='useragent'>" +useragent_value + "</property>";
	}

	@Override
	public String generateHeader(String fieldName, String fieldType) {
		return generateHeader(fieldName, fieldType, false);
	}

	@Override
	public String generateVariable(String variableName) {	
		return "var " + variableName + "_value = Blockly.Crawler.valueToCode(block, '" + variableName + "');\n";
	}

	@Override
	public boolean suitableFor(String query) {
		//Ritorna true, perchè si tratta di un generatore standard.
		return true;
	}

	@Override
	public String generateHeader(String fieldName, String fieldType, boolean isRequired) {
		String finalFieldName = (isRequired) ? fieldName + "*" : fieldName;
		System.out.println("TEST: " + UtilityFunctions.packageNameExtractAndShorten(fieldType));
		return BlockStrings.HEADER_VALUEINPUT_JS.replace("@FIELDNAME", UtilityFunctions.toCamelCase(fieldName)) +
		BlockStrings.HEADER_FIELD_JS.replace("@FIELDNAME", UtilityFunctions.packageNameExtractAndShorten(fieldType) + " : " + UtilityFunctions.toCamelCase(finalFieldName));
	}

	@Override
	public String generateConstructor(String constructorName, int index) {
		// TODO Auto-generated method stub
		String code = "code += (" + constructorName + "_value ? " + SharedStrings.DOUBLE_QUOTE_CHARACTER + "<constructor-arg index=\'" +  index +  "\'>" + SharedStrings.DOUBLE_QUOTE_CHARACTER + " + " + constructorName +  "_value + " + SharedStrings.DOUBLE_QUOTE_CHARACTER + "</constructor-arg>\\n\" : " + SharedStrings.EMPTYSTRING + ");\n";
		return code;
	}

}
