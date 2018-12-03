package org.intelliflow.csi.crawler.parser;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class CliParser {
	
	private String[] args;
	private Options options;
	private CommandLine cmd;
	
	
	public CliParser(String[] args){
		this.args = args;
		options = new Options();
		initOptions();
	}
	
	private void initOptions(){
		if(options!=null){
			options.addOption("c", true,"Specify class to analyze");
			options.addOption("p", true, "Specify the package to analyze");
			options.addOption("d", true, "Specify the destination folder");
		}
	}
	
	public void parse(){
		CommandLineParser parser = new BasicParser();
		try {
			cmd = parser.parse(options, args);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public CommandLine getArguments(){
		return cmd;
	}
	
	public void  usage(){
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("ClassAnalyzer", options);
	}
}
