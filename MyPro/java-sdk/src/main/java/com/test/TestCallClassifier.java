package com.test;
import java.util.Properties;

import org.python.util.PythonInterpreter;

/**
 * @author AUGUSTRUSH
 *没什么用
 */
public class TestCallClassifier {

	public static void main(String[] args) { 
		Properties props = new Properties();
		props.put("python.console.encoding", "UTF-8");
		props.put("python.security.respectJavaAccessibility", "false");
		props.put("python.import.site","false");
		Properties preprops = System.getProperties();
		PythonInterpreter.initialize(preprops, props, new String[0]);
		
		PythonInterpreter interpreter = new PythonInterpreter();
		interpreter.exec("import sys");
		interpreter.exec("sys.path.append('D:/jython2.7.0/Lib')");
		interpreter.exec("sys.path.append('D:/jython2.7.0/Lib/site-packages')");
		interpreter.exec("sys.path.append('E:/business/recognition/InvoiceClassification/Keras-image-classifer-framework/invoice-code')");
		interpreter.execfile("E:/business/recognition/InvoiceClassification/Keras-image-classifer-framework/invoice-code/predict1.py");
	}

}
