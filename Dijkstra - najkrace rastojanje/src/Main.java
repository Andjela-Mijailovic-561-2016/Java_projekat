import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import org.eclipse.jface.dialogs.MessageDialog;

import java.util.ArrayList;
import java.util.List;

public class Main {

	protected Shell shell;
	private Text txtFrom;
	private Text txtTo;

	/**
	 * Launch the application.
	
	 */
	public static void main(String[] args) {
		try {
			
			Main window = new Main();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("The Shortest Path");
		shell.setLayout(null);
		
		txtFrom = new Text(shell, SWT.BORDER);
		txtFrom.setBounds(39, 49, 101, 31);
		
		txtTo = new Text(shell, SWT.BORDER);
		txtTo.setBounds(39, 119, 101, 31);
		
		Label lblFrom = new Label(shell, SWT.NONE);
		lblFrom.setBounds(39, 28, 86, 15);
		lblFrom.setText("SourceCity:");
		
		Label lblTo = new Label(shell, SWT.NONE);
		lblTo.setBounds(39, 98, 86, 15);
		lblTo.setText("DestinationCity:");
		
		Label answerLabel = new Label(shell, SWT.NONE);
		answerLabel.setBounds(207, 28, 217, 223);
		answerLabel.setText("The shortest path is:");
		
		Button btnSearch = new Button(shell, SWT.NONE);
		btnSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int sourceCity,destCity;
				try {
					 sourceCity= Integer.parseInt(txtFrom.getText());
					 if(sourceCity<1 || sourceCity>847) {
						 MessageDialog.openError(shell,"Error","City must be in interval 1-847!");
							return;
					 }
				}
				catch(Exception exc) {
					MessageDialog.openError(shell,"Error","Invalid source city!");
					return;
				}
				try {
					 destCity= Integer.parseInt(txtTo.getText());
					 if(destCity<1 || destCity>847) {
						 MessageDialog.openError(shell,"Error","City must be in interval 1-847!");
							return;
					 }
				}
				catch(Exception exc) {
					MessageDialog.openError(shell,"Error","Invalid destination city!");
					return;
				}
				Files fin = new Files();  
		        fin.openFile("Gradovi.txt");
		        fin.readFile(); 
		        fin.closeFile(); 
		        
		        try {
		            List<Path> list = new ArrayList<>();
		    
		    
		           for (int i=0; i<fin.getElemFromFile().length;i+=3){ 
		    	        int city1=fin.getElemFromFile()[i];
		    	        int city2=fin.getElemFromFile()[i+1];
		    	        int dist=fin.getElemFromFile()[i+2];
		    	        list.add( new Path( city1, city2, dist));
		    	        list.add( new Path( city2, city1, dist));
		                                                           } 
	/*	            list.add(new Path (1,2,12));
		            list.add(new Path (1,2,12));
		            
		            list.add(new Path (3,4,131));
		            list.add(new Path (2,4,5));
		            list.add(new Path (1,3,1));    */
		           
		            DijkstraAlgorithm object = new DijkstraAlgorithm(list);
				   
		            object.execute(sourceCity);
		            List<Path> path = object.getPath(destCity,sourceCity);
		            String way="";
		            int distance=0;
		         
		            for (Path path1 : path){
		                way+=  path1.getSource() + " -> " + path1.getDestination() + "   (" + path1.getWeight() + "km)"+ "\n";
		               
		                distance=path1.getWeight();
		            	
		                 }
				   answerLabel.setText("The shortest path is:"+ "\n\n" + way+"\n\n"+ "The shortest distance is: "+ distance + " km");
			   // answerLabel.setText("The shortest path is: \n"+ way);   
		        }
		        catch(NullPointerException ex){
		        	MessageDialog.openError(shell,"Error","Error!");
					return;
		        }
		        }
		});
		btnSearch.setBounds(50, 175, 75, 25);
		btnSearch.setText("Search");
		
		

	}
}
