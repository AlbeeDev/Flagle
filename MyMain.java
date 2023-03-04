import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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
		tf.setBounds(150, 500, 600, 50);
		tf.setBackground(Color.white);
		tf.setFont(new Font("",Font.BOLD,30));
		f.add(tf);

		JComboBox<String> cb = new JComboBox<String>();
		for(File file:lista){
			cb.addItem(file.getName().replaceFirst("[.][^.]+$", ""));
		}
		cb.setFont(new Font("",Font.BOLD,40));
		cb.setBounds(150,550,500,100);
		cb.setEditable(false);
		cb.setBackground(Color.lightGray);
		f.add(cb);
		
		JButton b = new JButton();
		b.setBounds(650,550,100,100);
		b.setBackground(Color.LIGHT_GRAY);
		b.setFont(new Font("",Font.BOLD,60));
		b.setText("5");
		f.add(b);
		
		MioAscoltatore al = new MioAscoltatore(cb, blank,cflag, cname, p);	
		b.addActionListener(al);

		tf.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				filter();
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				filter();
			}

			void filter(){
				cb.removeAllItems();;
				for(File file:lista){
					if(file.getName().contains(tf.getText())){
						cb.addItem(file.getName().replaceFirst("[.][^.]+$", ""));
					}
				}
			}
			
		});
		
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