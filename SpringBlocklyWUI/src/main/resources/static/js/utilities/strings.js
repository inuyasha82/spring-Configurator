/**
 * strings.js
 * Contains list of constant strings 
 */

var Strings = {
		XML_HEADER : "<?xml version='1.0' encoding='UTF-8'?>",
		XML_SPRING_HEADER : "<beans xmlns='http://www.springframework.org/schema/beans'" + "\n" +
		"xmlns:c='http://www.springframework.org/schema/c'" +  "\n" + 
		       "xmlns:p='http://www.springframework.org/schema/p' xmlns:util='http://www.springframework.org/schema/util'" + "\n" +
		           "xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'" + "\n" +
		           "xmlns:context='http://www.springframework.org/schema/context'" + "\n" +
		           "xsi:schemaLocation='http://www.springframework.org/schema/beans  http://www.springframework.org/schema/beans/spring-beans-3.0.xsd"+ "\n" + 
		    	"http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util-3.0.xsd" + " \n" + 
		    	" http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.0.xsd'  >" + "\n"
		    	+ "<context:annotation-config/>\n",
		XML_SPRING_FOOTER : "</beans>",
		NL : "\n"
		    			
}