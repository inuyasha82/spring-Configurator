package org.intelliflow.csi.crawler.parser.block.documentation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;

import org.intelliflow.csi.crawler.parser.block.documentation.HtmlDocumentation;;

public class BlockDocumentation {
	
	private String fileName;
	private StringBuffer documentationBuffer;
	private String projectPath;
	private String className;
	private String packageName;

	public BlockDocumentation(String className, String packageName) {
		this.fileName = className + ".html";
		this.documentationBuffer = new StringBuffer();
		this.className = className;
		this.packageName = packageName;
	}
	
	public void addHtmlHeader() {
		documentationBuffer.append(HtmlDocumentation.generateHtmlHeader(className));
	}
	
	public void addHtmlFooter() {
		documentationBuffer.append(HtmlDocumentation.generateHtmlFooter());
	}
	
	public void addPackageName() {
		documentationBuffer.append(HtmlDocumentation.createPackageLine(packageName));
	}
	
	public void addDocumentationClassName() {
		documentationBuffer.append(HtmlDocumentation.createDocumentationPageHeader(className));
	}
	public void addSingleConstructor(ArrayList<String> constructors) {
		if(constructors!=null) {
			documentationBuffer.append(HtmlDocumentation.getConstructorLabel());
			documentationBuffer.append(HtmlDocumentation.getListOpen());
			for(int i=0; i<constructors.size(); i++) {
				String constructorString = constructors.get(i);
				String type = constructorString.substring(constructorString.indexOf("#")+1);
				String name = constructorString.substring(0, constructorString.indexOf("#") - 1);
				documentationBuffer.append(HtmlDocumentation.createSingleConstructorDocumentationListElement(type, name, i));
			}
			documentationBuffer.append(HtmlDocumentation.getListClose());
		}
	}
	
	public void addMultipleConstructors(Constructor[] ctors) {
		documentationBuffer.append(HtmlDocumentation.getLabel("Constructors:"));
		documentationBuffer.append(HtmlDocumentation.getListOpen());
		for(Constructor constructor : ctors) {
			documentationBuffer.append(HtmlDocumentation.createConstructorLine(constructor));
		}
		documentationBuffer.append(HtmlDocumentation.getListClose());
	}
	
	public void addFields(HashMap<String, String> fields) {
		documentationBuffer.append(HtmlDocumentation.getFieldsLabel());
		documentationBuffer.append(HtmlDocumentation.getListOpen());
		for(String key : fields.keySet()) {
			documentationBuffer.append(HtmlDocumentation.createFieldDocumentationListElement(key.substring(key.indexOf("#")+1), fields.get(key)));
		}
		documentationBuffer.append(HtmlDocumentation.getListClose());
	}
	
	public void printDocumentation() {
		System.out.println(documentationBuffer);
	}
	
	public void writeToDocumentationFile(String projectPath) {
		System.out.println(projectPath);
		File projectHtmlDirectory = new File(projectPath + "/documentation");
		projectHtmlDirectory.mkdirs();
		try {
			BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(projectHtmlDirectory + "/" + fileName), false));
			bwr.append(documentationBuffer);
			bwr.flush();
			bwr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
