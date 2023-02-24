package wordle;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class MioAscoltatore implements ActionListener{
	int wincount=0;
	JTextField tf;
	JLabel[] lvec;
	JPanel[] pvec;
	String parola;
	Boolean isInside=false;
	public MioAscoltatore(JTextField tf, JLabel[] lvec,JPanel[] pvec,String parola) {
		this.lvec=lvec;
		this.pvec=pvec;
		this.tf=tf;
		this.parola=parola;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton b = (JButton) e.getSource();
		if(tf.getText().length()!=5) return;
		
		for(int i=0;i<5;i++) {
			pvec[i].setBackground(Color.LIGHT_GRAY);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<5;i++) {
			pvec[i].setBackground(Color.LIGHT_GRAY);
			sb.append(tf.getText().charAt(i));
			if(sb.charAt(0)==parola.charAt(i)) {
				wincount+=1;
				pvec[i].setBackground(Color.GREEN);
			}
			else {
				for(int j=0;j<5;j++) {
					if(sb.charAt(0)==parola.charAt(j)) {
						isInside=true;
					}
				}
			}
			if(isInside) {
				pvec[i].setBackground(Color.yellow);
			}
			isInside=false;
			lvec[i].setText(sb.toString());
			sb.delete(0, sb.length());
		}
		if(wincount==5) {
			b.setEnabled(false);
			b.setBackground(Color.LIGHT_GRAY);
			b.setText("");
		}
		else {
			int tentativi = Integer.parseInt(b.getText());
			if(tentativi==1) {
				b.setEnabled(false);
				b.setBackground(Color.LIGHT_GRAY);
				b.setText("");
			}
			else {
				tentativi-=1;
				b.setText(Integer.toString(tentativi));
			}
		}
		wincount=0;
		tf.setText("");
		isInside=false;
	}

	

}
