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
		String[] array, arrayCol, arrayCom;
		array = input.split(";");
		for (int i = 0; i < array.length; i++)
		{
			System.out.println(array[i]);
			arrayCol = array[i].split(":");
			if (arrayCol[0].equals("lbl")){
				arrayCom = arrayCol[1].split(",");
				JSONObject temp = new JSONObject();
				temp.put("location", arrayCom[0]);
				temp.put("text", arrayCom[1]);
				jArrayL.add(temp);
			}
			
			else if (arrayCol[0].equals("btn")){
				arrayCom = arrayCol[1].split(",");
				JSONObject temp = new JSONObject();
				temp.put("location", arrayCom[0]);
				temp.put("text", arrayCom[1]);
				jArrayB.add(temp);
			}
			
			else if (arrayCol[0].equals("txt")){
				arrayCom = arrayCol[1].split(",");
				JSONObject temp = new JSONObject();
				temp.put("location", arrayCom[0]);
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
		write("lbl:l1,your label1;btn:b1,your button1;txt:t1,your txt1;lbl:l2,your label2;btn:b2,your button2;txt:t2,your txt2;txt:t3,your txt3;btn:b3,your button3;lbl:l3,your label3");
	}
	*/
	
}
