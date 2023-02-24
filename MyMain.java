package wordle;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyMain {

	public static void main(String[] args) {
		
		String[] lista = new String[] {"mucca","rullo","lista","spesa","banco","opera","fiore","latte","gioco","notte","bello"};
		String parola = lista[new Random().nextInt(lista.length)];
		
		
		
		JFrame f = new JFrame();
		f.setSize(800,600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(null);
		
		JLabel[] lvec = new JLabel[5];
		JPanel[] pvec = new JPanel[5];
		for(int i=0;i<5;i++) {
			pvec[i]= new JPanel();
			pvec[i].setBounds((i+1)*100+(i*10),300,100,100);
			pvec[i].setBackground(Color.lightGray);
			f.add(pvec[i]);
			lvec[i]=new JLabel();
			lvec[i].setBounds(0,0,100,100);
			lvec[i].setFont(new Font("",Font.BOLD,60));
			pvec[i].add(lvec[i]);
		}
		
		JTextField tf = new JTextField();
		tf.setBounds(125, 75, 500, 100);
		tf.setBackground(Color.pink);
		tf.setFont(new Font("",Font.BOLD,60));
		f.add(tf);
		
		JButton b = new JButton();
		b.setBounds(650,75,100,100);
		b.setBackground(Color.red);
		b.setFont(new Font("",Font.BOLD,60));
		b.setText("5");
		f.add(b);
		
		MioAscoltatore al = new MioAscoltatore(tf,lvec,pvec,parola);
		
		b.addActionListener(al);
		
		f.setVisible(true);
	}

}
