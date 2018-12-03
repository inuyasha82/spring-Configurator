Blockly.Crawler['ref'] = function(block) {
	var bean_name = block.getFieldValue('bean_name');
	var code = "<ref bean=\'" + bean_name + "\' />";
	console.log("Code: " + code);
	return [ code, Blockly.Crawler.ORDER_ATOMIC ];
};

Blockly.Crawler['constructor_ref'] = function(block) {
	var ref_name = block.getFieldValue('ref_name');
	var code = "ref=\'" + ref_name + "\'";
	console.log("Code: " + code);
	return [ code, Blockly.Crawler.ORDER_ATOMIC ];
};

Blockly.Crawler['constructor_value'] = function(block) {
	var ref_name = block.getFieldValue('value_input');
	var code = "value=\'" + ref_name + "\'";
	console.log("Code: " + code);
	return [ code, Blockly.Crawler.ORDER_ATOMIC ];
};

Blockly.Crawler['import'] = function(block) {
	var code = '<import resource="extractor_beans/put_to.xml" />\n';
	return code;
};

Blockly.Crawler['import_generic'] = function(block) {
	var text_filename = block.getFieldValue('fileName');
	var code = '<import resource="' + text_filename + '" />\n';
	return code;
};

Blockly.Crawler['custom_code'] = function(block) {
	  var text_customcode = block.getFieldValue('customCode');
	  return [ text_customcode, Blockly.Crawler.ORDER_ATOMIC ];
	};
