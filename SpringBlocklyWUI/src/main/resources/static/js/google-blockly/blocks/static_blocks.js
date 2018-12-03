Blockly.Blocks['lista'] = {
		init: function() {
			this.appendDummyInput()
			.appendField("list");
			this.appendStatementInput("statement")
			.setCheck(null);
			this.setPreviousStatement(true, null);
			this.setOutput(true, "Array");
			this.setColour(330);
			this.setTooltip('');
			this.setHelpUrl('http://www.example.com/');
		}
};

Blockly.Blocks['ref'] = {
		init: function() {
			this.appendValueInput("bean")
			.setCheck("String")
			.appendField("ref");
			this.setColour(214);
			this.setOutput(true, null);
			this.setPreviousStatement(true, null);
			this.setNextStatement(true, null);
			this.setTooltip('');
			this.setHelpUrl('http://www.example.com/');
		}
};
