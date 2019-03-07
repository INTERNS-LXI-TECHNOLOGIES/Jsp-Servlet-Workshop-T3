package com.lxisoft.contactapp.repository;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import com.lxisoft.contactapp.model.Contact;
import java.io.IOException;
import java.util.ArrayList;

public class ContactRepository{
	
	
	File contactDetails=new File("D:/lxisoft.app/apache-tomcat-8.5.38/webapps/contactappv3/data/Contacts.txt");
	File tempFile=new File("D:/lxisoft.app/apache-tomcat-8.5.38/webapps/contactappv3/data/TempFile.txt");
	File tempFile1=new File("D:/lxisoft.app/apache-tomcat-8.5.38/webapps/contactappv3/data/TempFile1.txt");
	FileWriter fw=null;
	BufferedWriter bw=null;
	FileReader fr=null;
	BufferedReader br=null;
	Contact contact;
	public String save(String name,String phone,String number,String email){	
	try{
	fw=new FileWriter(contactDetails,true);
	bw=new BufferedWriter(fw);
	bw.write(name+";"+phone+";"+number+";"+email);
	bw.newLine();
	}catch(IOException e){
			System.out.println("Error");	
		}finally{
			try{
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();}
			catch(IOException e){
				e.printStackTrace();}
	}
	String result="save";
	return result;
	}
	
	public ArrayList<Contact> getFromFile(){
		ArrayList<Contact> contactList= null;
		String line;
		try{
		contactList=new ArrayList<Contact>();	
		fr=new FileReader(contactDetails);
		br=new BufferedReader(fr);	
		while((line=br.readLine())!=null){
		String[] data = line.split(";");
		contact= new Contact(data[0],data[1],data[2],data[3]);
		contactList.add(contact);
		}
		fr.close();br.close();
		}catch(IOException e){
			System.out.println("Error");}
		return contactList;			
	}
	
	public String delete(String name) throws IOException{
		String currentLine,result=" ";
		fr=new FileReader(contactDetails);
		br=new BufferedReader(fr);
		bw=new BufferedWriter(new FileWriter(tempFile));
		while((currentLine=br.readLine())!=null){
			if(!currentLine.contains(name)){
					 bw.write(currentLine + System.getProperty("line.separator"));}
					 if(currentLine.contains(name)){
						 result="delete";
					 }
		}
		fr.close();br.close();
		bw.close();
		if(contactDetails.delete()){
			System.out.println("Success");
			}else{
			System.out.println("file exist");}		
		boolean successful = tempFile.renameTo(contactDetails);
        System.out.println(successful);
		return result;		
	}
	
	public String update(ArrayList<Contact> clist)throws IOException{

	bw=new BufferedWriter(new FileWriter(tempFile1));
	for(Contact con:clist){
		bw.write(con.getName()+";"+con.getPhone()+";"+con.getNumber()+";"+con.getEmail());
		bw.newLine();
	}
	bw.close();
		if(contactDetails.delete()){
			System.out.println("Success");
			}else{
			System.out.println("file exist");}		
		boolean successful = tempFile1.renameTo(contactDetails);
	System.out.println(successful);
	String result="update";
	return result;
	}
}