

public class CryptoManager {
	
	private static final char LOWER_BOUND = 32;
	private static final char UPPER_BOUND = 95;
	private static final int RANGE = UPPER_BOUND - LOWER_BOUND + 1;

	/**
	 * This method determines if a string is within the allowable bounds of ASCII codes 
	 * according to the LOWER_BOUND and UPPER_BOUND characters
	 * @param plainText a string to be encrypted, if it is within the allowable bounds
	 * @return true if all characters are within the allowable bounds, false if any character is outside
	 */
	public static boolean stringInBounds (String plainText) {
		boolean t = true;
		//loop
		for (int i = 0; i < plainText.length(); i ++) {
			if (!((int) plainText.charAt(i) >= LOWER_BOUND &&
					(int)plainText.charAt(i)<= UPPER_BOUND)) {
				t = false;
				break;
			}
			
		}// return if all character are within bounds
		return t;
	}

	/**
	 * Encrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in plainText is replaced by the character \"offset\" away from it 
	 * @param plainText an uppercase string to be encrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the encrypted string
	 */
	public static String encryptCaesar(String plainText, int key) {
		int k = key;
		
		while(k>RANGE) {
			k-=RANGE;
		}
		// encrypted text
		String text = "";
		
			//loop
			for (int i = 0; i < plainText.length(); i++) {
				
				char c = plainText.charAt(i);
				int eChar =((int)c+key);
				while(eChar > UPPER_BOUND) {
					eChar -= RANGE;
				}
				text += (char)eChar;
			}
		
		return text;
	}
	
	
	/**
	 * Encrypts a string according the Bellaso Cipher.  Each character in plainText is offset 
	 * according to the ASCII value of the corresponding character in bellasoStr, which is repeated
	 * to correspond to the length of plainText
	 * @param plainText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the encrypted string
	 */
	public static String encryptBellaso(String plainText, String bellasoStr) {
		// Create String array 
		String[] bellKey = new String[plainText.length()];
		// Turn every the text into a character array
		char[] pChar = plainText.toCharArray();
		// Create encrypted
		String Text = "";
		
		// check if in bounds
		if (stringInBounds(plainText)) {
			// Loop through every character
			for(int i = 0; i < plainText.length();i++) {
				// Check index is greater than length of key then loop back around
				if(i > bellasoStr.length() - 1) {
					bellKey[i] = bellasoStr.split("")[i % bellasoStr.length()];
				} else {
					//instance of index i
					bellKey[i] = bellasoStr.split("")[i];
				}
			}
			
			// Loop through the char array
			for(int i = 0; i < pChar.length; i++) {
				// Encrypt the char
				char eChar = (char)(pChar[i] + bellKey[i].charAt(0));
				
				// Make sure it's in bounds
				while(eChar > UPPER_BOUND)
					eChar -= RANGE;
				
				while(eChar < LOWER_BOUND)
					eChar += RANGE;
				
				// encrypted char to text
				Text += eChar;
			}
			
			// Return
			return Text;
		}
		
		// Return nothing if the string is not in bounds
		return "";
	}
	/**
	 * Decrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in encryptedText is replaced by the character \"offset\" characters before it.
	 * This is the inverse of the encryptCaesar method.
	 * @param encryptedText an encrypted string to be decrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the plain text string
	 */
	public static String decryptCaesar(String encryptedText, int key) {
		//decrypted text
		String text = "";
		
		//loop
		for (int i=0; i < encryptedText.length(); i++) {
			char c = encryptedText.charAt(i);
			int dChar = ((int)c -key);
			while(dChar < LOWER_BOUND) {
				dChar+= RANGE;
			}
			text += (char)dChar;
		}
		return text;
	}
	
	public static int Wrap_around(int key) {
		{
			while(UPPER_BOUND<key){
			key-=(UPPER_BOUND-LOWER_BOUND);
			}
		return key;
	}
	
}
	
	/**
	 * Decrypts a string according the Bellaso Cipher.  Each character in encryptedText is replaced by
	 * the character corresponding to the character in bellasoStr, which is repeated
	 * to correspond to the length of plainText.  This is the inverse of the encryptBellaso method.
	 * @param encryptedText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the decrypted string
	 */
	public static String decryptBellaso(String encryptedText, String bellasoStr) {
		int max = 99;
		// Create key array as before
		String[] bellKey = new String[encryptedText.length()];
		
		// Create encrypted char Array
		char[] eChar = encryptedText.toCharArray();
		
		//  decrypted text
		String Text = "";
		
		// Loop the length of encryptedText
		for(int i = 0; i < encryptedText.length();i++) {
			
			// Check if i is greater than the length of the key
			if(i > bellasoStr.length() - 1) {
				// If true, loop through the key and restart
				bellKey[i] = bellasoStr.split("")[i % bellasoStr.length()];
			} else {
				// index i to index of the key
				bellKey[i] = bellasoStr.split("")[i];
			}
		}
		
		// Loop the length of the encryptedChar
		for(int i = 0; i < eChar.length; i++) {
			switch(max) {
			case 99:	
			// Decrypt char by subtracting from key.
			char dChar = (char)(eChar[i] - bellKey[i].charAt(0));
			
			// Make sure the char is in bounds
			while(dChar < LOWER_BOUND)
				dChar += RANGE;
			
			while(dChar > UPPER_BOUND)
				dChar -= RANGE;
			
			// Add char to text
			Text += dChar;
			}
		}
		
		// Return the decrypted text
		return Text;
	}

	
}