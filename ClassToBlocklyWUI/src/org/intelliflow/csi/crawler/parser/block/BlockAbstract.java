package org.intelliflow.csi.crawler.parser.block;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

public abstract class BlockAbstract {

	static final Logger logger = Logger.getLogger(BlockAbstract.class);

	String packageName;
	String className;
	//fields: per campo di classe: ClassName, VariableName
	HashMap<String,String> fields;
	StringBuffer codeBuffer;
	String projectFolder;
	ArrayList<String> requiredFields;
	ArrayList<String> constructors;
	boolean addConstructorToBlock = false;
	boolean isOnlyOneConstructorNotDefault = false;

	public BlockAbstract(String packageName, String className){
		this.packageName = packageName;
		this.className = className;
		this.fields = new HashMap<String, String>();
		this.codeBuffer = new StringBuffer();
		this.requiredFields = new ArrayList<String>();
		this.constructors = new ArrayList<String>();
	}

	public abstract String getProjectPath();
	public abstract void createStatement();
	public abstract void writeToJsFile();

	public String getFullClassName() {
		return packageName + "." + className;
	}

	public void setProjectFolder(String projectFolder) {
		this.projectFolder = projectFolder;
	}

	public void addConstructor(Constructor<?> targetConstructor, boolean isOnlyOneConstructorNotDefault){
		if(isOnlyOneConstructorNotDefault) {
			System.out.println("Not default constructor found");
			this.isOnlyOneConstructorNotDefault = isOnlyOneConstructorNotDefault;
			for(Parameter parameter : targetConstructor.getParameters()){
				logger.info(" Parameter: " + parameter.getName() + " Type: " + parameter.getType());
				String parameterName = parameter.getName();
				putConstructor(parameter.getType().getName(), parameterName);
			}
		} else {
			addConstructorToBlock = true;
		}
	}
	
	public void putConstructor(String fieldType, String fieldName) {
		if(!constructors.contains(fieldName.toLowerCase())) {
			constructors.add(fieldName.toLowerCase() + "#" + fieldType);
		}
	}

	public void putField(String fieldType, String fieldName) {
		if(!fields.containsValue(fieldName.toLowerCase())) {
			fields.put(fieldName.toLowerCase() + "#" + fieldType, fieldName);
		}
	}

	public boolean isEmpty() {
		if(fields != null && fields.size() > 0) {
			return false;
		}
		return true;
	}

	public String extractType(Method method){
		String fullType = method.getParameterTypes()[0].getName();
		String fieldType = getTypeName(fullType);
		return fieldType;
	}

	protected String getTypeName(String fullType) {
		String fieldType;
		if(fullType.lastIndexOf('.') > 0 ){
			fieldType = fullType.substring(fullType.lastIndexOf('.')+1, fullType.length());
		} else {				
			fieldType = fullType;
		}
		return fieldType;
	}

	public void addSetter(Method method, boolean isRequired) {
		String fieldType;
		String fullType = "";
		String fieldName;
		//if getParameterCount() > 1 should throw an exception!
		//TODO: Estrarre la parte di individuazione sottostringa in un metodo
		if(method.getParameterCount() == 1){
			System.out.println(method.getParameterTypes()[0]);
			fullType = method.getParameterTypes()[0].getName();
			fieldType = extractType(method);
			fieldName = method.getName().substring("set".length(), method.getName().length());
			logger.info("+Type: " + fieldType + " Name: " + fieldName);
			putField(fullType, fieldName);
			if(isRequired) {
				requiredFields.add(fieldName);
			}
		}
	}

}
