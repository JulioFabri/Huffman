package graphic_interface.frame;

import huffman.HuffmanTree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class JChooserListener implements ActionListener{

	
	private boolean code;
	private String initialState , finalState;
	private JLabel label;
	private JFrame frameToRepaint;
	
	public JChooserListener(boolean code , JLabel label , String initialState , String finalState, JFrame frameToRepaint) {
		this.code = code;
		this.label = label;
		this.initialState = initialState;
		this.finalState = finalState;
		this.frameToRepaint = frameToRepaint;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser choose = new JFileChooser();
		FileNameExtensionFilter filter, filter2;
        if(this.code){
        	filter = new FileNameExtensionFilter("Texto","txt");
        	filter2 = new FileNameExtensionFilter("RAW","raw");
        	choose.setFileFilter(filter);
        	choose.setFileFilter(filter2);
        }else{
        	filter = new FileNameExtensionFilter("DBZ","dbz");
        	choose.setFileFilter(filter);
        }
        
        choose.setFileFilter(filter);
        int opcion = choose.showOpenDialog(choose);
        if (opcion == JFileChooser.APPROVE_OPTION) {
        	
        	this.label.setText(initialState);
        	this.frameToRepaint.repaint();
        	String extension = choose.getSelectedFile().getPath();
        	if("txt".equals(extension.substring(extension.length()-3, extension.length())) || "txt".equals(extension.substring(extension.length()-7, extension.length()-4)))
        		new HuffmanTree(choose.getSelectedFile().getPath(), code, "utf-8");
        	else
        		new HuffmanTree(choose.getSelectedFile().getPath(), code);
        	this.label.setText(finalState);
        	this.frameToRepaint.repaint();
        	JOptionPane.showMessageDialog(null, "DONE!");
        }
        
        
    }
		
}

