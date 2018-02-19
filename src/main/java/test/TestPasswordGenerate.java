package test;

import statics.helper.StringUtils;

public class TestPasswordGenerate {
	
	public static void genChar() {
		for (int i = 33; i < 125; i++) {
			char charGen = (char) i;
			if (charGen != '"' && charGen != '\\') {
				StringUtils.numCharSymbol[i - 33] = charGen;
				System.out.print("'" + charGen + "', " );
			}
		}
	}
	
	public static void genPW() {
		for(int i = 0; i < 100; i++)
			System.out.println("Password generated " + (i+1) + ": " + StringUtils.getRandomStringLen16());
	}

	public static void main(String[] args) {
		genPW();

	}

}
