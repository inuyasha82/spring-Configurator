package org.intelliflow.csi.crawler.parser.block.generators.implementations;

import org.intelliflow.csi.crawler.parser.block.BlockStrings;
import org.intelliflow.csi.crawler.parser.block.generators.interfaces.Generator;
import org.intelliflow.csi.crawler.parser.utils.UtilityFunctions;

public class GeneratorList implements Generator {

	private static String SUITABLE_TYPE = "List";

	@Override
	public boolean suitableFor(String query) {
		if(query.contains(SUITABLE_TYPE)) return false;
		return false;
	}

	@Override
	public String generateCode(String variableName) {
		return "code +=\"<property name='" + variableName + "'>\" +" + variableName + "_statement + \"</property>\\n\";\n";
	}

	@Override
	public String generateHeader(String fieldName, String fieldType) {
		return generateHeader(fieldName, fieldType, false);
	}

	@Override
	public String generateVariable(String variableName) {
		if(variableName != null) {
			return "var " + variableName + "_statement = Blockly.Crawler.statementToCode(block, '" + variableName + "');\n";
		}
		return null;
	}

	@Override
	public String generateHeader(String fieldName, String fieldType, boolean isRequired) {
		String fieldNameLabel = (isRequired) ? fieldName +"*" : fieldName;
		return BlockStrings.HEADER_STATEMENTINPUT_JS.replace("@FIELDNAME", fieldName.toLowerCase()) +
				BlockStrings.HEADER_FIELD_JS.replace("@FIELDNAME", UtilityFunctions.packageNameExtractAndShorten(fieldType) + " : " + fieldNameLabel.toLowerCase());
	}

	@Override
	public String generateConstructor(String constructorName, int index) {
		// TODO Auto-generated method stub
		//Not used
		return null;
	}

}

/*
 * Blockly.Crawler['pipelinedaction'] = function(block) {
	var text_defaultname = block.getFieldValue('DefaultName');
	var value_id = Blockly.Crawler.valueToCode(block, 'id', Blockly.Crawler.ORDER_ATOMIC);
	var statement_properties = Blockly.Crawler.statementToCode(block, 'properties');
	// TODO: Assemble JavaScript into code variable.
	var code = '<bean ';
	if(value_id){
		code += 'id=' + value_id;
	}
	code+=  ' class="it.uniroma1.lcl.csi.core.actions.PipelinedAction">';
	code+= '<property name="actions">';
	console.log("Value: " + statement_properties);
	if(statement_properties != undefined || statement_properties != ''){
		code += statement_properties;
	}
	code+="</property>"
		code +=  "</bean>";

	return code;
};
 * */
