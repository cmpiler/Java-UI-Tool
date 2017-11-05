package test2;

import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;

public class JWriter {

	public JWriter(){
	}
	
	
	public void write(String input){
		JSONObject output = new JSONObject();
		JSONArray jArrayL = new JSONArray();
		JSONArray jArrayB = new JSONArray();
		JSONArray jArrayT = new JSONArray();
		String[] array, arrayCol, arrayCom, arrayLoc;
		String tempString;
		array = input.split(";");
		for (int i = 0; i < array.length; i++)
		{
			System.out.println(array[i]);
			arrayCol = array[i].split(":");
			if (arrayCol[0].equals("lbl")){
				arrayCom = arrayCol[1].split(",");
				JSONObject temp = new JSONObject();
				tempString = arrayCom[0].replace("location-x", "");
				arrayLoc = tempString.split("y");
				temp.put("locationx", arrayLoc[0]);
				temp.put("locationy", arrayLoc[1]);
				temp.put("text", arrayCom[1]);
				jArrayL.add(temp);
			}
			
			else if (arrayCol[0].equals("btn")){
				arrayCom = arrayCol[1].split(",");
				JSONObject temp = new JSONObject();
				tempString = arrayCom[0].replace("location-x", "");
				arrayLoc = tempString.split("y");
				temp.put("locationx", arrayLoc[0]);
				temp.put("locationy", arrayLoc[1]);
				temp.put("text", arrayCom[1]);
				jArrayB.add(temp);
			}
			
			else if (arrayCol[0].equals("txt")){
				arrayCom = arrayCol[1].split(",");
				JSONObject temp = new JSONObject();
				tempString = arrayCom[0].replace("location-x", "");
				arrayLoc = tempString.split("y");
				temp.put("locationx", arrayLoc[0]);
				temp.put("locationy", arrayLoc[1]);
				temp.put("text", arrayCom[1]);
				jArrayT.add(temp);
			}
		}
		output.put("lbl", jArrayL);
		output.put("btn", jArrayB);
		output.put("txt", jArrayT);
		
		String s = output.toJSONString();
		System.out.println(s);
		
		try { 
			FileWriter file = new FileWriter("c:\\js.json"); // Your Directory
			file.write(output.toJSONString());
			file.flush();
			file.close();
		} catch (IOException e) { 
			e.printStackTrace(); 
		}
		
		System.out.println("Success");

	
	}
	
	/*
	public static void main(String[] args){
		//testing area
		write("lbl:location-x100y200,your label1;btn:location-x50y100,your button1;txt:location-x10y20,your txt1;lbl:location-x200y400,your label2;btn:location-x100y200,your button2;txt:location-x20y40,your txt2;txt:location-x40y80,your txt3;btn:location-x200y400,your button3;lbl:location-x400y800,your label3");
	}
	*/
	
}
