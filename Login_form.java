import java.io.BufferedReader;
import java.io.FileReader;
import java.security.MessageDigest;
import java.util.Scanner;

public class Login_form {
	
	public String encryptPassword(String passwordToHash) throws Exception {
		// Create MessageDigest instance for MD5
		MessageDigest md = MessageDigest.getInstance("MD5");
		// Add password bytes to digest
		md.update(passwordToHash.getBytes());
		// Get the hash's bytes
		byte[] bytes = md.digest();
		// This bytes[] has bytes in decimal format;
		// Convert it to hexadecimal format
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		// Get complete hashed password in hex format
		return sb.toString();
	}
	
	public boolean validateCredentials(String username,String password) {
		String filePath = "./loginDetails.txt";
        Scanner scanner = new Scanner(System.in);
        boolean loginValidated=false;
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
            String line[];
            while(!bufferedReader.readLine().equals(null)) {
            	line = bufferedReader.readLine().split("-");
                if (line[0].equals(username)) {		
                	if(line[1].equals(password)) {
                		loginValidated = true;
                		break;
                	}
                }
            }
        }catch(Exception e) {
        	
        }
		return loginValidated;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String username = "", password = "", generatedPassword = "";
			Login_form login = new Login_form();
			Scanner scanner = new Scanner(System.in);
			System.out.println("##### WELCOME #####");
			System.out.println("Enter Username : ");
			username = scanner.nextLine();
			System.out.println("Enter Password : ");
			password = scanner.nextLine();
			if(login.validateCredentials(username,login.encryptPassword(password))) {
				System.out.println("Login successful !!");
			}else {
				System.out.println("Invalid userId or password !!");
			}
			
	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
