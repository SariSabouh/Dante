import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class generator {
	int number = 0000;
	String oldFile = "";
	String output = "";
	String oldOutput = "";
	boolean undo = false;
	String lastNumber = "";
	
	public generator() throws IOException{
		readFile();
	}
	
	public void readFile() throws IOException{
		oldFile = "";
		BufferedReader file = new BufferedReader(new FileReader(new File("All Serials.txt")));
		String line;
		while ((line = file.readLine()) != null) {
			oldFile += line + "\n";
	       char[] lastLine = line.toCharArray();
	       if(!line.equals("")) lastNumber = "" + lastLine[6] + lastLine[7] + lastLine[8] + lastLine[9];  
		}
		oldOutput = output;
		file.close();
	}
	
	public int addNumber(int num){
		if(num < 500) return num;
		else if(num == 500){
			number = 0;
			return num;
		}
		else{
			int extra = num - 500;
			number = 0;
			return(number+extra);
		}
	}
	
	
	public void createSerial(String mm, String dd, String yy, String amount) throws IOException{
		readFile();
		number = Integer.parseInt(lastNumber);
		for(int i = 0; i<Integer.parseInt(amount); i++){
			if(number>=0 && number<9) output += mm + dd + yy + "000" + addNumber(number+i+1) + "\r\n";
			else if(number>8 && number<99) output += mm + dd + yy + "00" + addNumber(number+i+1) + "\r\n";
			else if(number>98 && number<1000) output += mm + dd + yy + "0" + addNumber(number+i+1) + "\r\n";
			else output += mm + dd + yy + addNumber(number+i+1) + "\r\n";
		}
		FileWriter write = new FileWriter("New Serials.txt");
		FileWriter write2 = new FileWriter("All Serials.txt");
		String newFile = "";
		if(undo){
			newFile = output;
		}
		else{
			newFile += oldFile + output;
		}
		write2.write(newFile);
		write2.close();
		write.write(output);
		write.close();
		System.out.println(output);
		undo = true;
	}
	
	public void undoSerial() throws IOException{
		if(undo){
			output = oldOutput;
			FileWriter write = new FileWriter("New Serials.txt");
			FileWriter write2 = new FileWriter("All Serials.txt");
			write2.write(oldFile);
			write2.close();
			write.write("");
			write.close();
			undo = false;
		}
	}
}


