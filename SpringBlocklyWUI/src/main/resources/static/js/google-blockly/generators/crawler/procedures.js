/**
 * @license
 * Visual Blocks Language
 *
 * Copyright 2012 Google Inc.
 * https://developers.google.com/blockly/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @fileoverview Generating Crawler for procedure blocks.
 * @author fraser@google.com (Neil Fraser)
 */
'use strict';

goog.provide('Blockly.Crawler.procedures');

goog.require('Blockly.Crawler');


Blockly.Crawler['procedures_defreturn'] = function(block) {
  // Define a procedure with a return value.
  var funcName = Blockly.Crawler.variableDB_.getName(
      block.getFieldValue('NAME'), Blockly.Procedures.NAME_TYPE);
  var branch = Blockly.Crawler.statementToCode(block, 'STACK');
  if (Blockly.Crawler.STATEMENT_PREFIX) {
    branch = Blockly.Crawler.prefixLines(
        Blockly.Crawler.STATEMENT_PREFIX.replace(/%1/g,
        '\'' + block.id + '\''), Blockly.Crawler.INDENT) + branch;
  }
  if (Blockly.Crawler.INFINITE_LOOP_TRAP) {
    branch = Blockly.Crawler.INFINITE_LOOP_TRAP.replace(/%1/g,
        '\'' + block.id + '\'') + branch;
  }
  var returnValue = Blockly.Crawler.valueToCode(block, 'RETURN',
      Blockly.Crawler.ORDER_NONE) || '';
  if (returnValue) {
    returnValue = '  return ' + returnValue + ';\n';
  }
  var args = [];
  for (var x = 0; x < block.arguments_.length; x++) {
    args[x] = Blockly.Crawler.variableDB_.getName(block.arguments_[x],
        Blockly.Variables.NAME_TYPE);
  }
  var code = 'function ' + funcName + '(' + args.join(', ') + ') {\n' +
      branch + returnValue + '}';
  code = Blockly.Crawler.scrub_(block, code);
  Blockly.Crawler.definitions_[funcName] = code;
  return null;
};

// Defining a procedure without a return value uses the same generator as
// a procedure with a return value.
Blockly.Crawler['procedures_defnoreturn'] =
    Blockly.Crawler['procedures_defreturn'];

Blockly.Crawler['procedures_callreturn'] = function(block) {
  // Call a procedure with a return value.
  var funcName = Blockly.Crawler.variableDB_.getName(
      block.getFieldValue('NAME'), Blockly.Procedures.NAME_TYPE);
  var args = [];
  for (var x = 0; x < block.arguments_.length; x++) {
    args[x] = Blockly.Crawler.valueToCode(block, 'ARG' + x,
        Blockly.Crawler.ORDER_COMMA) || 'null';
  }
  var code = funcName + '(' + args.join(', ') + ')';
  return [code, Blockly.Crawler.ORDER_FUNCTION_CALL];
};

Blockly.Crawler['procedures_callnoreturn'] = function(block) {
  // Call a procedure with no return value.
  var funcName = Blockly.Crawler.variableDB_.getName(
      block.getFieldValue('NAME'), Blockly.Procedures.NAME_TYPE);
  var args = [];
  for (var x = 0; x < block.arguments_.length; x++) {
    args[x] = Blockly.Crawler.valueToCode(block, 'ARG' + x,
        Blockly.Crawler.ORDER_COMMA) || 'null';
  }
  var code = funcName + '(' + args.join(', ') + ');\n';
  return code;
};

Blockly.Crawler['procedures_ifreturn'] = function(block) {
  // Conditionally return value from a procedure.
  var condition = Blockly.Crawler.valueToCode(block, 'CONDITION',
      Blockly.Crawler.ORDER_NONE) || 'false';
  var code = 'if (' + condition + ') {\n';
  if (block.hasReturnValue_) {
    var value = Blockly.Crawler.valueToCode(block, 'VALUE',
        Blockly.Crawler.ORDER_NONE) || 'null';
    code += '  return ' + value + ';\n';
  } else {
    code += '  return;\n';
  }
  code += '}\n';
  return code;
};
