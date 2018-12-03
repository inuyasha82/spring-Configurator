Blockly.Crawler['lista'] = function(block) {
	var statements_name = Blockly.Crawler.statementToCode(block, 'statement');
	console.log("inside lista: " + statements_name);
	var code = '<list>'  + statements_name + '</list>';
	console.log("inside lista: " + code);
	// TODO: Change ORDER_NONE to the correct strength.
	return code ;
};

Blockly.Crawler['ref'] = function(block) {
	var bean_name = Blockly.Crawler.valueToCode(block, 'bean');
	var code = '<ref bean='  + bean_name+ ' />';
	console.log("Code: " + code);
	return [code, Blockly.Crawler.ORDER_ATOMIC];
};
