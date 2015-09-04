package graphic_interface.frame;

import java.io.FileInputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class PlayerMP3 implements Runnable{

	private boolean pause = false;
	private Player pl = null;
	
	/**
	 * 
	 * @param path String: Ruta del MP3
	 */
	public PlayerMP3(String path) {
		try{
			pl = new Player(new FileInputStream(path));
		}catch(Exception e){}
	}
	
	public void SetPause(boolean pause){
		this.pause = pause;
	}
	
	@Override
	public void run() {
		try {
            while (true) {
               if (!pause) {
                  if (!pl.play(1)) {
                     break;
                  }
               }
            }
         } catch (JavaLayerException e) {
            e.printStackTrace();
         }
		
	}
	
	
	
}
