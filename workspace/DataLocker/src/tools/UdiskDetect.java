package tools;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class UdiskDetect {
	public static void main(String[] args) throws IOException {
		UdiskDetect m = new UdiskDetect();
		String os = System.getProperty("os.name").toLowerCase();// get Os name
		if (os.indexOf("win") > 0) {// checking if os is *nix or windows
			// This is windows
			m.ListFiles(new File("/storage"));// do some staf for windows i am
												// not so sure about '/storage'
												// in windows
			// external drive will be found on
		} else {
			// Some *nix OS
			// all *nix Os here
			m.ListFiles(new File("/media"));// if linux removable drive found on
											// media
			// this is for Linux

		}

	}

	void ListFiles(File fls)// this is list drives methods
			throws IOException {
		while (true) {// while loop

			try {
				Thread.sleep(5000);// repeate a task every 5 minutes
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Process p = Runtime.getRuntime().exec("ls " + fls);// executing
																// command to
																// get the
																// output
			BufferedReader br = new BufferedReader(new InputStreamReader(
					p.getInputStream()));// getting the output
			String line;// output line
			while ((line = br.readLine()) != null) {// reading the output
				System.out.print("removable drives : " + line + "\n");// printing
																		// the
																		// output
			}
			/*
			 * You can modify the code as you wish. To check if external storage
			 * drivers pluged in or removed, compare the lenght Change the time
			 * interval if you wish
			 */
		}

	}
}