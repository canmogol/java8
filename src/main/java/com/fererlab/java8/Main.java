package com.fererlab.java8;

import java.io.FileFilter;
import java.io.File;


public class Main {


	public static void main(String args[]){
		Main main = new Main();
		main.test();
	}

	public void test(){
		FileFilter fileFilterAnonymous = new FileFilter(){
			public boolean accept(File file){
				return file.getName().endsWith(".java");
			}
		};
		System.out.println("anonymous file filter: "+fileFilterAnonymous);	
		FileFilter fileFilterLambda = (File file)->file.getName().endsWith(".java");
		System.out.println("lambda file filter: "+fileFilterLambda);
			
	}
} 
