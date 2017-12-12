package test;

public class Test3 {

/**
 *
 * @author Do Hung Cuong
 */
	public static void main(String[] args) {
		Test3 t = new Test3();
		System.out.println(t.outerMethod1());
	    
	}
	
	
	public String outerMethod1() {
		if(innerMethod())
			return "test";
		int a = 5;
		int b = a;
		int c = b;
		return a+b+c+"method1";
	}
	
	public String outerMethod2() {
		if(innerMethod())
			return "test";
		int a = 9;
		int b = a;
		int c = b;
		return a+b+c+"method2";
	}
	
	public String outerMethod3() {
		if(innerMethod())
			return "test";
		int a = 15;
		int b = a;
		int c = b;
		return a+b+c+"method3";
	}
	
	public boolean innerMethod() {
		int a = 5;
		int b = a;
		int c = b;
		if(a == c) 
			return true;
		return false;
	}
	
	
}
