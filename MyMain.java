import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class MyMain {
	
	public static void main(String[] args) {
		File[] lista = new File("src/flags").listFiles();
		File cfile= lista[new Random().nextInt(lista.length)];
		BufferedImage cflag=getFlag(cfile);
		String cname=cfile.getName();
		BufferedImage blank=new BufferedImage(600, 300, BufferedImage.TYPE_INT_RGB);
		initBlank(blank);
		
		JFrame f = new JFrame();
		f.setSize(1000,900);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().setBackground(Color.DARK_GRAY);
		f.setLayout(null);
		
		MioPanel p = new MioPanel(blank);
		f.add(p);
		
		JTextField tf = new JTextField();
		tf.setBounds(150, 675, 500, 100);
		tf.setBackground(Color.pink);
		tf.setFont(new Font("",Font.BOLD,60));
		f.add(tf);
		
		JButton b = new JButton();
		b.setBounds(650,675,100,100);
		b.setBackground(Color.LIGHT_GRAY);
		b.setFont(new Font("",Font.BOLD,60));
		b.setText("5");
		f.add(b);
		
		MioAscoltatore al = new MioAscoltatore(tf, blank,cflag, cname, p);
		
		
		b.addActionListener(al);
		
		f.setVisible(true);
	}
	
	static BufferedImage getFlag(File flagf) {
		
		System.out.println(flagf.getName());
		try {
			return ImageIO.read(flagf);
			
		} catch (IOException e) {}
		return null;
	}

	static void initBlank(BufferedImage blank) {
		Color gray = new Color(170,170,170);
		for(int y=0;y<300;y++) {
			for(int x=0;x<600;x++) {
				blank.setRGB(x, y, gray.getRGB());
			}
		}
	}

}
