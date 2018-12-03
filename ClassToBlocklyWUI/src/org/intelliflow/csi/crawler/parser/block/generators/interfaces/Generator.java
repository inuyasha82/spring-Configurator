package org.intelliflow.csi.crawler.parser.block.generators.interfaces;

public interface Generator {
	
	public String generateCode(String variableName);
	public String generateConstructor(String constructorName, int index);
	public String generateVariable(String variableName);
	boolean suitableFor(String query);
	public String generateHeader(String fieldName, String fieldType);
	public String generateHeader(String fieldName, String fieldType, boolean isRequired);
}
