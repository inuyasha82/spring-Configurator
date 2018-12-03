package org.intelliflow.csi.crawler.parser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import com.google.common.reflect.ClassPath;

public class ClassEnumerator {

	private ArrayList<String> classes;
	private ClassLoader classLoader;
	private String packageName;

	public ClassEnumerator(String packageName){
		this.classLoader = Thread.currentThread().getContextClassLoader();
		this.packageName = packageName;
	}

	public void loadClasses(){
		try {
			ClassPath classPath = ClassPath.from(classLoader);
			if(classes == null){
				classes = new ArrayList<String>();
			} else {
				classes.clear();
			}
			System.out.println(packageName);
			System.out.println(classLoader);
			System.out.println(classPath.getTopLevelClassesRecursive(packageName).size());
			System.out.println(System.getProperty("java.class.path"));
			for(ClassPath.ClassInfo info : classPath.getTopLevelClassesRecursive(packageName)){
//				System.out.println(info.getName());
				classes.add(info.getName());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadClassesWithReflection() {
		Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));

		Set<Class<? extends Object>> allClasses = 
				reflections.getSubTypesOf(Object.class);
		allClasses.addAll(reflections.getSubTypesOf(Thread.class));
		if(classes == null){
			classes = new ArrayList<String>();
		} else {
			classes.clear();
		}
		for(Class currentClass : allClasses) {
//			System.out.println(currentClass.getName());
			classes.add(currentClass.getName());
		}
	}
	public ArrayList<String> getClasses(){
		return classes;
	}

	public static void main(String[] args){
		ClassEnumerator enumerator = new ClassEnumerator("it.uniroma1.lcl.csi.fetcher");
		enumerator.loadClasses();
		System.out.println("-------");
		enumerator.loadClassesWithReflection();
	}
}
