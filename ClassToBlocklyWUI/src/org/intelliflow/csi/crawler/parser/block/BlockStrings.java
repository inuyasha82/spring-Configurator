package org.intelliflow.csi.crawler.parser.block;

public class BlockStrings {
	
	private static String HEADER_BLOCK_DECLARATION_JS = "Blockly.Blocks['@CLASSNAME'] = "; 
	private static String CODE_BLOCK_DECLARATION_JS = "Blockly.Crawler['@CLASSNAME'] = ";
	public static String HEADER_DECLARATION_JS = 
			HEADER_BLOCK_DECLARATION_JS + "{\n\tinit: function(block) {\n";
	
	public static String CODE_DECLARATION_JS = 
			CODE_BLOCK_DECLARATION_JS + "function(block) {\n";
	
	public static String HEADER_VALUEINPUT_JS =  "\t\tthis.appendValueInput('@FIELDNAME')\n";
	public static String HEADER_STATEMENTINPUT_JS = "\t\tthis.appendStatementInput(\"@FIELDNAME\")\n";
	public static String HEADER_CHECK_JS = "\t\t\t.setCheck('@FIELDTYPE')\n";
	public static String HEADER_FIELD_JS = "\t\t\t.appendField('@FIELDNAME');\n";
	public static String HEADER_INLINE_JS = "\t\tthis.setInputsInline(@BOOLEANVALUE);\n";
	public static String HEADER_SET_OUTPUT = "\t\tthis.setOutput(true, null);\n";
	public static String HEADER_SET_PREV_STATEMENT = "\t\tthis.setPreviousStatement(true, null);\n";
	public static String HEADER_SET_NEXT_STATEMENT = "\t\tthis.setNextStatement(true, null);\n";
	
	public static String TAB_BLOCK_CLOSE = "\t}\n";
	public static String FUNCTION_CLOSE = "};\n";
	
	public static String HEADER_DUMMYINPUT_JS = "\t\tthis.appendDummyInput()\n";
	public static String HEADER_BLOCK_FIELD_JS = "\t\t.appendField('@CLASSNAME');\n";
	public static String HEADER_BLOCKNAME_LABEL_JS = HEADER_DUMMYINPUT_JS + BlockStrings.HEADER_BLOCK_FIELD_JS;
	
	public static String HEADER_FIELD_INPUT_JS = "\t\t\t.appendField('@CLASSNAME')\n";
	public static String HEADER_FIELD_TEXT_INPUT_JS = "\t\t\t.appendField(new Blockly.FieldTextInput(''), '@FIELDNAME');\n";
	public static String HEADER_FIELD_NUMERIC_INPUT_JS = "\t\t\t.appendField(new Blockly.FieldNumber(), '@FIELDNAME');\n";
	public static String HEADER_COLOR_JS = "\t\tthis.setColour(@COLORCODE);\n";
	public static String HEADER_TOOLTIP_JS = "\t\tthis.setTooltip(\"@TOOLTIP\");\n";
	public static String HEADER_URL_JS = "\t\tthis.setHelpUrl(\"@DOCUMENTATIONURL\");";
	
	public static String HEADER_ID_FIELD = "\t\tthis.appendDummyInput()\n" + 
			"\t\t\t.appendField('java.lang.String : id')\n" + 
			"\t\t\t.appendField(new Blockly.FieldTextInput(''), 'blockId');\n";
	
	public static String CODE_ID_READ = "var blockId_value = block.getFieldValue('blockId');\n";
	
	public static String HTML_FIELD_TAG = "<block type=\"@BLOCKNAME\"></block>\n";
	
	public static String HTML_CATEGORY_TAG = "<category name=\"@CATEGORYNAME\">\n";
	public static String HTML_CATEGORY_CLOSE_TAG = "</category>\n";
	
	public static String CONSTRUCTOR_FIELD_TAG = "<constructor-arg index=\'@POSITION\' >\" + code_value + \"</constructor-arg>\"";
	
}
