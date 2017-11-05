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
		String[] array, arrayCol, arrayCom, arrayLoc, arraySize;
		String tempString, tempString2;
		array = input.split(";");
		for (int i = 0; i < array.length; i++)
		{
			System.out.println(array[i]);
			arrayCol = array[i].split(":");
			if (arrayCol[0].equals("lbl")){
				arrayCom = arrayCol[1].split(",");
				JSONObject temp = new JSONObject();
				temp.put("locationx", "");
				temp.put("locationy", "");
				temp.put("width", "");
				temp.put("height", "");
				temp.put("text", "");
				temp.put("type", "");
				for (int j = 0; j < arrayCom.length; j++){
					
					if (arrayCom[j].contains("locationx-")){
						tempString = arrayCom[j].replace("locationx-", "");
						temp.put("locationx", tempString);
					}
					else if (arrayCom[j].contains("locationy-")){
						tempString = arrayCom[j].replace("locationy-", "");
						temp.put("locationy", tempString);
					}
					else if (arrayCom[j].contains("width-")){
						tempString2 = arrayCom[j].replace("width-", "");
						temp.put("width", tempString2);
					}
					else if (arrayCom[j].contains("height-")){
						tempString2 = arrayCom[j].replace("height-", "");
						temp.put("height", tempString2);
					}
					
					else if (arrayCom[j].contains("text-")){
						temp.put("text", arrayCom[j].replace("text-", ""));
					}
					
					else if (arrayCom[j].contains("type-")){
						temp.put("type", arrayCom[j].replace("type-", ""));
					}
				}
				
				jArrayL.add(temp);
			}
			
			else if (arrayCol[0].equals("btn")){
				arrayCom = arrayCol[1].split(",");
				JSONObject temp = new JSONObject();
				temp.put("locationx", "");
				temp.put("locationy", "");
				temp.put("width", "");
				temp.put("height", "");
				temp.put("text", "");
				temp.put("type", "");
				for (int j = 0; j < arrayCom.length; j++){
					
					if (arrayCom[j].contains("locationx-")){
						tempString = arrayCom[j].replace("locationx-", "");
						temp.put("locationx", tempString);
					}
					else if (arrayCom[j].contains("locationy-")){
						tempString = arrayCom[j].replace("locationy-", "");
						temp.put("locationy", tempString);
					}
					else if (arrayCom[j].contains("width-")){
						tempString2 = arrayCom[j].replace("width-", "");
						temp.put("width", tempString2);
					}
					else if (arrayCom[j].contains("height-")){
						tempString2 = arrayCom[j].replace("height-", "");
						temp.put("height", tempString2);
					}
					
					else if (arrayCom[j].contains("text-")){
						temp.put("text", arrayCom[j].replace("text-", ""));
					}
					
					else if (arrayCom[j].contains("type-")){
						temp.put("type", arrayCom[j].replace("type-", ""));
					}
				}
				jArrayB.add(temp);
			}
			
			else if (arrayCol[0].equals("txt")){
				arrayCom = arrayCol[1].split(",");
				JSONObject temp = new JSONObject();
				temp.put("locationx", "");
				temp.put("locationy", "");
				temp.put("width", "");
				temp.put("height", "");
				temp.put("text", "");
				temp.put("type", "");
				for (int j = 0; j < arrayCom.length; j++){
					
					if (arrayCom[j].contains("locationx-")){
						tempString = arrayCom[j].replace("locationx-", "");
						temp.put("locationx", tempString);
					}
					else if (arrayCom[j].contains("locationy-")){
						tempString = arrayCom[j].replace("locationy-", "");
						temp.put("locationy", tempString);
					}
					else if (arrayCom[j].contains("width-")){
						tempString2 = arrayCom[j].replace("width-", "");
						temp.put("width", tempString2);
					}
					else if (arrayCom[j].contains("height-")){
						tempString2 = arrayCom[j].replace("height-", "");
						temp.put("height", tempString2);
					}
					
					else if (arrayCom[j].contains("text-")){
						temp.put("text", arrayCom[j].replace("text-", ""));
					}
					
					else if (arrayCom[j].contains("type-")){
						temp.put("type", arrayCom[j].replace("type-", ""));
					}
				}
				jArrayT.add(temp);
			}
		}
		output.put("lbl", jArrayL);
		output.put("btn", jArrayB);
		output.put("txt", jArrayT);
		
		String s = output.toJSONString();
		System.out.println(s);
		
		try { 
			FileWriter file = new FileWriter("C:\\js.json"); // Your Directory
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
	}
	*/
	
}
