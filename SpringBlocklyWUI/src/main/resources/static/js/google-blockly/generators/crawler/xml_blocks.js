Blockly.Crawler['multiplefetchers'] = function(block) {
var action_value = Blockly.Crawler.valueToCode(block, 'action');

var fetchers_statement = Blockly.Crawler.statementToCode(block, 'fetchers');

var code="<bean class='it.uniroma1.lcl.csi.fetcher.MultipleFetchers'>";
code +="<property name='action'>"+action_value + "</property>\n";
code +="<property name='fetchers'>" +fetchers_statement + "</property>\n";
code += "</bean>";
return [code, Blockly.Crawler.ORDER_ATOMIC];
};

/*-----*/
Blockly.Crawler['useragenthandler'] = function(block) {
var useragentfilepath_value = Blockly.Crawler.valueToCode(block, 'useragentfilepath');

var defaultuseragent_value = Blockly.Crawler.valueToCode(block, 'defaultuseragent');

var code="<bean class='it.uniroma1.lcl.csi.fetcher.fetcherTask.UserAgentHandler'>";
code +="<property name='useragentfilepath'>"+useragentfilepath_value + "</property>\n";
code +="<property name='defaultuseragent'>"+defaultuseragent_value + "</property>\n";
code += "</bean>";
return [code, Blockly.Crawler.ORDER_ATOMIC];
};

/*-----*/
Blockly.Crawler['downloader'] = function(block) {
var action_value = Blockly.Crawler.valueToCode(block, 'action');

var httpclient_value = Blockly.Crawler.valueToCode(block, 'httpclient');

var tofetchurls_value = Blockly.Crawler.valueToCode(block, 'tofetchurls');

var code="<bean class='it.uniroma1.lcl.csi.fetcher.fetcherTask.Downloader'>";
code +="<property name='action'>"+action_value + "</property>\n";
code +="<property name='httpclient'>"+httpclient_value + "</property>\n";
code +="<property name='tofetchurls'>"+tofetchurls_value + "</property>\n";
code += "</bean>";
return [code, Blockly.Crawler.ORDER_ATOMIC];
};

/*-----*/
Blockly.Crawler['idleconnectionmonitorthread'] = function(block) {
var connmgr_value = Blockly.Crawler.valueToCode(block, 'connmgr');

var code="<bean class='it.uniroma1.lcl.csi.fetcher.fetcherTask.IdleConnectionMonitorThread'>";
code +="<property name='connmgr'>"+connmgr_value + "</property>\n";
code += "</bean>";
return [code, Blockly.Crawler.ORDER_ATOMIC];
};

/*-----*/
Blockly.Crawler['configuration'] = function(block) {
var maxtotalperroute_value = Blockly.Crawler.valueToCode(block, 'maxtotalperroute');

var defaultuseragent_value = Blockly.Crawler.valueToCode(block, 'defaultuseragent');

var connectiontimeout_value = Blockly.Crawler.valueToCode(block, 'connectiontimeout');

var maxtotalconnection_value = Blockly.Crawler.valueToCode(block, 'maxtotalconnection');

var useragent_value = Blockly.Crawler.valueToCode(block, 'useragent');

var code="<bean class='it.uniroma1.lcl.csi.fetcher.fetcherTask.Configuration'>";
code +="<property name='maxtotalperroute'>"+maxtotalperroute_value + "</property>\n";
code +="<property name='defaultuseragent'>"+defaultuseragent_value + "</property>\n";
code +="<property name='connectiontimeout'>"+connectiontimeout_value + "</property>\n";
code +="<property name='maxtotalconnection'>"+maxtotalconnection_value + "</property>\n";
code +="<property name='useragent'>"+useragent_value + "</property>\n";
code += "</bean>";
return [code, Blockly.Crawler.ORDER_ATOMIC];
};

/*-----*/
Blockly.Crawler['robotfilehandler'] = function(block) {
var httpclient_value = Blockly.Crawler.valueToCode(block, 'httpclient');

var code="<bean class='it.uniroma1.lcl.csi.fetcher.robotFile.RobotFileHandler'>";
code +="<property name='httpclient'>"+httpclient_value + "</property>\n";
code += "</bean>";
return [code, Blockly.Crawler.ORDER_ATOMIC];
};

/*-----*/
Blockly.Crawler['fetcher'] = function(block) {
var numberofdownloaderworker_value = Blockly.Crawler.valueToCode(block, 'numberofdownloaderworker');

var useragenthandler_value = Blockly.Crawler.valueToCode(block, 'useragenthandler');

var action_value = Blockly.Crawler.valueToCode(block, 'action');

var robothandler_value = Blockly.Crawler.valueToCode(block, 'robothandler');

var connmanager_value = Blockly.Crawler.valueToCode(block, 'connmanager');

var requestbuilder_value = Blockly.Crawler.valueToCode(block, 'requestbuilder');

var code="<bean class='it.uniroma1.lcl.csi.fetcher.Fetcher'>";
code +="<property name='numberofdownloaderworker'>"+numberofdownloaderworker_value + "</property>\n";
code +="<property name='useragenthandler'>"+useragenthandler_value + "</property>\n";
code +="<property name='action'>"+action_value + "</property>\n";
code +="<property name='robothandler'>"+robothandler_value + "</property>\n";
code +="<property name='connmanager'>"+connmanager_value + "</property>\n";
code +="<property name='requestbuilder'>"+requestbuilder_value + "</property>\n";
code += "</bean>";
return [code, Blockly.Crawler.ORDER_ATOMIC];
};

/*-----*/
