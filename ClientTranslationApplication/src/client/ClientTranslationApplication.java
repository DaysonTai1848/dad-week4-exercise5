package client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * 
 * @author daysontai
 *
 */

public class ClientTranslationApplication {

	public static void main(String[] args) {
		
		try {
			
			Scanner scanner = new Scanner(System.in);
			
			// Connect to server at localhost
			int portNo = 4800;
			Socket socket = new Socket(InetAddress.getLocalHost(), portNo);
			
			// Choose English text
			System.out.println("Choose text to be translated");
			System.out.println("1. Good morning");
			System.out.println("2. Good night");
			System.out.println("3. How are you?");
			System.out.println("4. Thank you");
			System.out.println("5. Goodbye");
			System.out.println("6. What's up");
			System.out.println("Choice: ");
			
			// index of english text stored as integer
			int englishTextIndex = scanner.nextInt();
			
			// Choose target language
			System.out.println("Choose text to be translated");
			System.out.println("1. Bahasa Malaysia");
			System.out.println("2. Arabic");
			System.out.println("3. Korean");
			System.out.println("Choice: ");
			
			// index of target language stored as integer
			int languageIndex = scanner.nextInt();
			
			// Create Output Stream
			DataOutputStream outputStream = 
					new DataOutputStream(socket.getOutputStream());
			
			// Write English text and target language into the outputstream 
			outputStream.writeInt(englishTextIndex);
			outputStream.writeInt(languageIndex);	
		
			
			// Create input stream
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			
			// Read from network and display the result of translation
			String text = bufferedReader.readLine();
			System.out.println(text);
			
			// Close everything
			bufferedReader.close();
			socket.close();
					
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
