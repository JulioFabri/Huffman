package graphic_interface.frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;


@SuppressWarnings("serial")
public class Presentation extends JFrame implements Runnable{
	
	/*
	 * Atributos de la clase
	 */
	private JPanel contentPane;
    private JProgressBar progressBar;
    private JLabel label, sphere = null, bright = null;
    private BufferedImage sphere_img, brightSphereImg;
    private Thread thread;
    private double seconds;
    
     
    /** 
     * @param img String: Ruta de la imagen que se mostrara
     * en la presentacion.
     * @param mp3Path : Ruta del mp3 que se reproducira
     * @param seconds : double - Cantidad de segundos que dura el Loader
     */
    @SuppressWarnings("deprecation")
    public Presentation(String img, String mp3Path, double seconds) {
    	this.seconds = seconds/2.0f;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //Propiedades que tendra el JFrame de la presentacion
        this.setCursor(Cursor.WAIT_CURSOR);
        this.setUndecorated(true);
        this.getRootPane().setOpaque(false);
        this.setResizable(false);
        this.setBackground(new Color(0,0,0,0));
        //Se crea el label con la iamgen que se recibe por parametro
        label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setIcon(new ImageIcon(Presentation.class.getResource(img)));
        label.setSize(label.getIcon().getIconWidth(), label.getIcon().getIconHeight());
        //Se le asigna un tamaño al Frame acorde a la iamgen recibida y al tamaño de la pantalla
        this.setSize(label.getSize().width, label.getSize().height+15);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        //Se crea una barra de progreso en una ubicacion acorde a la ubicacion de la imagen
        progressBar = new JProgressBar();
        progressBar.setMaximum(50);
        progressBar.setBounds(label.getIcon().getIconWidth()/4, label.getIcon().getIconHeight()-10, (int)(label.getIcon().getIconWidth()*0.5f), 15);
        //Imagenes del Loader
        sphere = new JLabel();
        bright = new JLabel();
        //Asignacion de las imagenes sobre las rutas correspondientes
        try {
			sphere_img = ImageIO.read(getClass().getResourceAsStream("/graphic_interface/Assets/esfera/esfera.png"));
			brightSphereImg = ImageIO.read(getClass().getResourceAsStream("/graphic_interface/Assets/esfera/brillo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        //Propiedades de las imagenes de los Loaders
        sphere.setIcon(new ImageIcon(sphere_img));
        sphere.setSize(sphere_img.getWidth(), sphere_img.getHeight());
        sphere.setBounds(getMiddleProgressX(sphere_img.getWidth()), getMiddleProgressY(), sphere_img.getWidth(), sphere_img.getHeight());
        bright.setIcon(new ImageIcon(brightSphereImg));
        bright.setSize(brightSphereImg.getWidth(), brightSphereImg.getHeight());
        bright.setBounds(getMiddleProgressX(brightSphereImg.getWidth()), getMiddleProgressY(), brightSphereImg.getWidth(), brightSphereImg.getHeight());
        
        //Se crea un contenedor (contentpane) donde se almacenara todo para mostrarse en pantalla
        contentPane = new JPanel();
        contentPane.setSize(1000, 1000);
        contentPane.setBackground(new Color(0, 0, 0, 0));
        contentPane.setLayout(null);
        //contentPane.add(progressBar);
        contentPane.add(label);
        contentPane.add(bright);
        contentPane.add(sphere);
        //se la asigna el contentpane al JFrame
        this.setContentPane(contentPane);
        //reproducto de MP3
//        try {
//			PlayMP3(mp3Path);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    }
 
    /*
     * Getters y Setters de atributos
     */
    public JProgressBar getProgressBar() {
        return progressBar;
    }
 
    public void setProgressBar(JProgressBar progressBar) {
        this.progressBar = progressBar;
    }
    
    public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	
	/*
	 * Metodos para obtener una posicion adecuada para la imagen de Loader
	 */
	private int getMiddleProgressX(int w){
		return this.label.getIcon().getIconWidth()/2-w/2;
	}
	
	private int getMiddleProgressY(){
		return this.label.getIcon().getIconHeight()-100;
	}
	
	/*
	 * Reproduce MP3
	 */
	@SuppressWarnings("static-access")
	private void PlayMP3(String path) throws InterruptedException{
		PlayerMP3 player = new PlayerMP3(path);
		Thread playerThread = new Thread(player);
		playerThread.start();
		playerThread.sleep(10);
	    player.SetPause(true);
	    playerThread.sleep(10);
	    player.SetPause(false);
	}
	
	/*
	 * Implementacion del metodo run() de la interface Runnable
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		double angle = 0;
		try {
            while(true){
            	angle+=Math.PI/180;
	            this.sphere.setIcon(new ImageIcon(this.rotate(sphere_img, angle)));
	            this.repaint();
	             
	            if(angle>Math.PI*this.seconds){
	            	this.dispose();
	                this.getThread().stop();
	                
	            }
            Thread.sleep(10);}
            
        }catch (InterruptedException e) {
        		e.printStackTrace();
        }
    }
	
	/*
	 * Metodo que hace rotar la imagen del Loader
	 * Contra: El ancho y alto de la imagen del Loader tienen que ser iguales (Imagen cuadrada o circular)
	 */
	/**
	 * 
	 * @param image : BufferedImage es la imagen del Loader
	 * @param angle : double Angulo en el que rota
	 * @return Imagen rotada
	 */
	public BufferedImage rotate(BufferedImage image, double angle) {
		int w = image.getWidth(), h = image.getHeight();
	    GraphicsConfiguration gc = getGraphicsConfiguration();
	    BufferedImage result = gc.createCompatibleImage(w, h, Transparency.TRANSLUCENT);
	    Graphics2D g = result.createGraphics();
	    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
	    g.translate(1, 1);
	    g.rotate(angle, w/2, h/2);
	    g.drawRenderedImage(image, null);
	    g.dispose();
	    return result;
	}
	
}	
	    
