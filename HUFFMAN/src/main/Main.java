package main;

import graphic_interface.frame.Presentation;
import graphic_interface.frame.VentPrin;

public class Main {

	public static void main(String[] args) {
		    Presentation presentation = null;
			presentation = new Presentation ("/graphic_interface/Assets/IongegerLogo.png","src/graphic_interface/Assets/music/DBZ.mp3",2);
			presentation.setVisible(true);
	        Thread thread = new Thread(presentation);
	        presentation.setThread(thread);
	        presentation.getThread().start();
	        while(true){
	        	if(!presentation.getThread().isAlive()){
	        		new VentPrin();
	        		break;
	        	}
		    }
	}
}
