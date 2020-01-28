
import java.io.File; 
import java.io.IOException; 
import java.util.Scanner; 
 


public class Files {
	//skenira zadati fajl i uzima bitne podatke  
		private int n; //ukupan broj gradova 
		private Scanner scan; 
	
	    private int[] elementsFromFile; //niz reci iz fajla 

	        int getN() {
	        	return n; 
	        } 
	     
	        int[] getElemFromFile() {        
	        	return elementsFromFile; 
	        } 
	     
	        void openFile(String file) {      
	        	try { 
	                scan = new Scanner(new File(file)); 
	            } catch (IOException e) { 
	                System.out.println("File does not exsist!"); 
	                System.exit(0); 
	            } 
	        } 
	     
	        void readFile() { 
	            n = Integer.valueOf(scan.next()); //prvi broj iz fajla je ukupan broj gradova       
	            int m = Integer.valueOf(scan.next()); //drugi -||- veza 
	            elementsFromFile= new int[m *3]; //za svaku vezu iz fajla postoje 3 bitna podatka        
	            int i = 0; 
	            while (scan.hasNext()) { //dok ima sledece reci u fajlu 
	                elementsFromFile[i++] = Integer.valueOf(scan.next()); //podatak iz fajla 
	            } 
	        } 
	     
	        void closeFile() {     
	        	scan.close(); 
	        } 
	    } 



