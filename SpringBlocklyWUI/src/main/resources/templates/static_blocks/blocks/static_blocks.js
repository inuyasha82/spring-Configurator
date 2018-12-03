Blockly.Blocks['ref'] = {
	init : function() {
		this.appendDummyInput().appendField("Bean ref").appendField(
				"Enter ref object: ").appendField(
				new Blockly.FieldTextInput("default"), "bean_name");
		this.setColour(214);
		this.setOutput(true, null);
		this.setTooltip('');
	}
};

Blockly.Blocks['constructor_ref'] = {
	init : function() {
		this.appendDummyInput().appendField("Constructor ref").appendField(
				"Enter ref object: ").appendField(
				new Blockly.FieldTextInput("default"), "ref_name");
		this.setColour(214);
		this.setOutput(true, null);
		this.setTooltip('');
	}
};

Blockly.Blocks['constructor_value'] = {
	init : function() {
		this.appendDummyInput().appendField("Constructor value").appendField(
				"Enter value: ").appendField(
				new Blockly.FieldTextInput("default"), "value_input");
		this.setColour(214);
		this.setOutput(true, null);
		this.setTooltip('');
	}
};

Blockly.Blocks['import'] = {
	init : function() {
		this.appendDummyInput().appendField(
				"import resource extractor_beans/put_to.xml");
		this.setColour(300);
		this.setTooltip("");
		this.setHelpUrl("");
	}
};

Blockly.Blocks['import_generic'] = {
	init : function() {
		this.appendDummyInput().appendField("import resource: ").appendField(
				new Blockly.FieldTextInput("default"), "fileName");
		this.setColour(230);
		this.setTooltip("");
		this.setHelpUrl("");
	}
};

Blockly.Blocks['custom_code'] = {
	init : function() {
		this.appendDummyInput().appendField("Enter Custom code:").appendField(
				new Blockly.FieldTextInput("default"), "customCode");
		this.setOutput(true, null);
		this.setColour(230);
		this.setTooltip("");
		this.setHelpUrl("");
	}
};