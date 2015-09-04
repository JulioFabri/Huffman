package graphic_interface.frame;


import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class VentPrin extends JFrame {

	private JPanel contentPane;
	final private String projectBy = "HUFFMAN by Echenique Facundo & Cuadra Nicolas." , working = "Working...";


		public VentPrin() {
		setTitle("Iongeger Huffman");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentPrin.class.getResource("/graphic_interface/Assets/esfera/esfera.png")));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension dim = tool.getScreenSize();
		setBounds((dim.width/2)-150, (dim.height/2)-75, 300, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblOut = new JLabel(projectBy);
		lblOut.setBounds(5, 80, 300, 36);
		contentPane.add(lblOut);
		
		JButton btnCoder = new JButton("Codificar");
		btnCoder.addActionListener(new JChooserListener(true,lblOut,working,projectBy,this));
		btnCoder.setBounds(23, 32, 119, 37);
		contentPane.add(btnCoder);
		
		JButton btnDecoder = new JButton("Decodificar");
		btnDecoder.addActionListener(new JChooserListener(false,lblOut,working,projectBy,this));
		btnDecoder.setBounds(160, 32, 119, 37);
		contentPane.add(btnDecoder);
		
		JButton btnExplorer = new JButton("cargar Txt");
		btnExplorer.setBounds(167, 39, 89, 23);
//		contentPane.add(btnExplorer);
		
		this.setVisible(true);
		this.setResizable(false);
	}
}
