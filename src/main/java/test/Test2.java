package test;

public class Test2 {

	public static void main(String[] args) {
		System.out.println(a());
	}
	
	public static String a() {
		if(!b())
			return "login";
		return "index";
	}
	
	public static boolean b() {
		int a = 6;
		return a==5 ? true: false;
	}


}
