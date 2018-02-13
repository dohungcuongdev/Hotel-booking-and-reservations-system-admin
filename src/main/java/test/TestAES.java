package test;

import services.aes.Decryption;
import services.aes.Encryption;

public class TestAES {

	public static void main(String[] args) throws Exception {
		Encryption en = new Encryption();
		String encryptedWord = en.encrypt("ititiu13170");
		System.out.println("Encrypted word is : " + encryptedWord);
		Decryption de = new Decryption();
		System.out.println("Decrypted word is : " + de.decrypt(encryptedWord));
		
		
		System.out.println("Decrypted word is : " + de.decrypt("f/KiWeZu6xYe23LF7kOoBYbowNiXqwbP0hnsk4cpQ5uvE4Jx8ElpD9mGgpOQuumWjEVIeA=="));

	}

}
