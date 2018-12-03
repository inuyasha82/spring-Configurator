package org.intelliflow.csi.crawler.parser.block.generators;

import org.intelliflow.csi.crawler.parser.block.BlockStrings;
import org.intelliflow.csi.crawler.parser.block.generators.implementations.DefaultGenerator;
import org.intelliflow.csi.crawler.parser.block.generators.implementations.GeneratorList;
import org.intelliflow.csi.crawler.parser.block.generators.implementations.PrimitiveGenerator;
import org.intelliflow.csi.crawler.parser.block.generators.interfaces.Generator;

public class GeneratorSelector {
	
	private Generator[] generators;
	private Generator defaultGenerator = new DefaultGenerator();
	
	private char SPACE_CHARACTER = ' ';
	private char SINGLE_QUOTE_CHARACTER='\'';
	private char DOUBLE_QUOTE_CHARACTER='\"';
	private char SEMICOLUMN_CHARACTER=';';
	private String EMPTYSTRING = "\"\"";
	private String SINGLE_QUOTE_SPACE = "\"\' \"";
	
	public GeneratorSelector(){
		generators = new Generator[4];
		generators[0] = new GeneratorList();
		generators[1] = new PrimitiveGenerator();
	}
	
	public Generator getGenerator(String type){
		for(Generator generator : generators){
			if(generator != null && generator.suitableFor(type))
				return generator;
		}
		return defaultGenerator;
	}
	
	public Generator getDefaultGenerator() {
		return defaultGenerator;
	}
	
	public String generateBody(String fullClassName){
		StringBuffer buffer = new StringBuffer();
		buffer.append(BlockStrings.CODE_ID_READ);
		buffer.append("var code=" + DOUBLE_QUOTE_CHARACTER);
		//buffer.append("<bean id=\'" + DOUBLE_QUOTE_CHARACTER + " + (blockId_value ? blockId_value : 'defaultId') + " + DOUBLE_QUOTE_CHARACTER + "\' ");
		buffer.append("<bean " + DOUBLE_QUOTE_CHARACTER + " + (blockId_value ? " + DOUBLE_QUOTE_CHARACTER + "id=\'" + DOUBLE_QUOTE_CHARACTER + "+ blockId_value + " + SINGLE_QUOTE_SPACE + " : " + EMPTYSTRING + ") + " + DOUBLE_QUOTE_CHARACTER );
		if(fullClassName != null) {
			buffer.append("class=\'" + fullClassName + "\'");
		}
		buffer.append(">\\n" + DOUBLE_QUOTE_CHARACTER + SEMICOLUMN_CHARACTER +"\n");
		return buffer.toString();
	}
	
	/*public String generatePropertyBlock(String name){
		StringBuffer buffer = new StringBuffer();
		buffer.append("code+=" + SINGLE_QUOTE_CHARACTER + "<property");  
		if(name!=null){
			buffer.append(SPACE_CHARACTER + "name=" + DOUBLE_QUOTE_CHARACTER + name + DOUBLE_QUOTE_CHARACTER); 
		}
		buffer.append(">" + SINGLE_QUOTE_CHARACTER + SEMICOLUMN_CHARACTER);
		buffer.append('\n');
		return buffer.toString();
	}*/

}
