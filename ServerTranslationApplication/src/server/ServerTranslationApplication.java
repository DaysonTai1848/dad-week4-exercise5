package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author daysontai
 *
 */

public class ServerTranslationApplication {

	public static void main(String[] args) throws IOException {
		
		// Declare socket
		ServerSocket serverSocket = null;
		
		try {
			
			// Bind Serversocket to a port
			int portNo = 4800;
			serverSocket = new ServerSocket(portNo);
			
			// Data for Multilingual text
			String[][] database = {
					{"Selamat pagi", "حابص ریخلا", "좋은 아침"},
					{"Selamat malam", "باط كؤاسم", "안녕히 주무세요"},
					{"Apa khabar?", "فیك ؟كلاح" ,"어떻게 지내세요?"},
					{"Terima kasih", "اركش كل" , "감사합니다"},
					{"Selamat tinggal", "عم ةملاسلا", "안녕"},
					{"Ada apa?", "ام ؟كرابخأ ", "뭐야?"},
			};
			
			// Server waiting for client to send request
			System.out.println("Waiting for request");
			
			while(true) {
				
				// Accept client request for connection
				Socket clientSocket = serverSocket.accept();
							
				// create input stream
				InputStreamReader inputStream = 
						new InputStreamReader(clientSocket.getInputStream());
				
				BufferedReader bufferedReader = new BufferedReader(inputStream);
				
				// Get English text index
				int englishTextIndex = bufferedReader.read();
				
				// Get Target Language index
				int languageIndex = bufferedReader.read();
				
				// Get translated text from 2d array
				String translatedText = 
						database[englishTextIndex][languageIndex];
				
				// Create output stream
				DataOutputStream outputStream = 
						new DataOutputStream(clientSocket.getOutputStream());
			
				// Send translated text back to client 
				outputStream.writeUTF(translatedText);
				
				// Close socket
				clientSocket.close();
				
			}
		} catch (IOException ioe) {
			if (serverSocket != null) {
				serverSocket.close();
			}
			
			ioe.printStackTrace();
		}
		
	}

}
