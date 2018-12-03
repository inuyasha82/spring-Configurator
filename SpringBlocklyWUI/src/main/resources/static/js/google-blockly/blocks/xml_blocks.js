Blockly.Blocks['multiplefetchers'] = {
	init: function(block) {
		this.appendDummyInput()
		.appendField('MultipleFetchers');
		this.appendValueInput('action')
			.appendField('core.actions.ActionIF : action*');
		this.appendStatementInput("fetchers")
			.appendField('java.util.List : fetchers');
		this.setInputsInline(false);
		this.setOutput(true, null);
		this.setColour(336);
		this.setPreviousStatement(true, null);
		this.setNextStatement(true, null);
	}
};

/*---------*/
Blockly.Blocks['useragenthandler'] = {
	init: function(block) {
		this.appendDummyInput()
		.appendField('UserAgentHandler');
		this.appendValueInput('useragentfilepath')
			.appendField('java.lang.String : useragentfilepath*');
		this.appendValueInput('defaultuseragent')
			.appendField('java.lang.String : defaultuseragent*');
		this.setInputsInline(false);
		this.setOutput(true, null);
		this.setColour(339);
		this.setPreviousStatement(true, null);
		this.setNextStatement(true, null);
	}
};

/*---------*/
Blockly.Blocks['downloader'] = {
	init: function(block) {
		this.appendDummyInput()
		.appendField('Downloader');
		this.appendValueInput('action')
			.appendField('core.actions.ActionIF : action');
		this.appendValueInput('httpclient')
			.appendField('http.client.HttpClient : httpclient');
		this.appendValueInput('tofetchurls')
			.appendField('util.concurrent.BlockingQueue : tofetchurls');
		this.setInputsInline(false);
		this.setOutput(true, null);
		this.setColour(167);
		this.setPreviousStatement(true, null);
		this.setNextStatement(true, null);
	}
};

/*---------*/
Blockly.Blocks['idleconnectionmonitorthread'] = {
	init: function(block) {
		this.appendDummyInput()
		.appendField('IdleConnectionMonitorThread');
		this.appendValueInput('connmgr')
			.appendField('http.conn.HttpClientConnectionManager : connmgr');
		this.setInputsInline(false);
		this.setOutput(true, null);
		this.setColour(43);
		this.setPreviousStatement(true, null);
		this.setNextStatement(true, null);
	}
};

/*---------*/
Blockly.Blocks['configuration'] = {
	init: function(block) {
		this.appendDummyInput()
		.appendField('Configuration');
		this.appendDummyInput()
			.appendField('int : MaxTotalPerRoute')
			.appendField(new Blockly.FieldNumber(0), 'maxtotalperroute');
		this.appendValueInput('defaultuseragent')
			.appendField('java.lang.String : defaultuseragent');
		this.appendDummyInput()
			.appendField('int : ConnectionTimeout')
			.appendField(new Blockly.FieldNumber(0), 'connectiontimeout');
		this.appendDummyInput()
			.appendField('int : MaxTotalConnection')
			.appendField(new Blockly.FieldNumber(0), 'maxtotalconnection');
		this.appendValueInput('useragent')
			.appendField('java.lang.String : useragent');
		this.setInputsInline(false);
		this.setOutput(true, null);
		this.setColour(122);
		this.setPreviousStatement(true, null);
		this.setNextStatement(true, null);
	}
};

/*---------*/
Blockly.Blocks['robotfilehandler'] = {
	init: function(block) {
		this.appendDummyInput()
		.appendField('RobotFileHandler');
		this.appendValueInput('httpclient')
			.appendField('http.client.HttpClient : httpclient');
		this.setInputsInline(false);
		this.setOutput(true, null);
		this.setColour(119);
		this.setPreviousStatement(true, null);
		this.setNextStatement(true, null);
	}
};

/*---------*/
Blockly.Blocks['fetcher'] = {
	init: function(block) {
		this.appendDummyInput()
		.appendField('Fetcher');
		this.appendDummyInput()
			.appendField('int : NumberOfDownloaderWorker*')
			.appendField(new Blockly.FieldNumber(0), 'numberofdownloaderworker');
		this.appendValueInput('useragenthandler')
			.appendField('fetcher.fetcherTask.UserAgentHandler : useragenthandler*');
		this.appendValueInput('action')
			.appendField('core.actions.ActionIF : action*');
		this.appendValueInput('robothandler')
			.appendField('fetcher.robotFile.RobotFileHandler : robothandler*');
		this.appendValueInput('connmanager')
			.appendField('http.conn.HttpClientConnectionManager : connmanager*');
		this.appendValueInput('requestbuilder')
			.appendField('client.config.RequestConfig$Builder : requestbuilder*');
		this.setInputsInline(false);
		this.setOutput(true, null);
		this.setColour(250);
		this.setPreviousStatement(true, null);
		this.setNextStatement(true, null);
	}
};

/*---------*/
