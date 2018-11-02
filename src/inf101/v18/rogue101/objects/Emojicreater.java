package inf101.v18.rogue101.objects;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class Emojicreater {

	public static ArrayList<String> getEmojiList(int n) {
		ArrayList<String> listFromFile = new ArrayList<String>();

		try {
			
			File file = new File("foodList.txt");
			if (n==1) {
				file = new File("foodList.txt");
			}
			if (n==2) {
				file = new File("enemyList.txt");
			}
			if (n==3) {
				file = new File("armorList.txt");
			}
			if (n==4) {
				file = new File("weaponList.txt");
			}

			Scanner inFile = new Scanner(file);
			
			while (inFile.hasNextLine()) {
				listFromFile.add(inFile.nextLine());
			}
			inFile.close();
		} 
		
		catch (FileNotFoundException e) {
			System.out.println(e);
		}
		return listFromFile;
	}
}
