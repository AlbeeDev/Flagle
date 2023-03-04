import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MioPanel extends JPanel{
	Image flag;
	MioAscoltatore al;
	public MioPanel(Image flag) {
		this.flag=flag;
		setBounds(150,100,600,300);
		setBackground(Color.pink);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		System.out.println("updated");
		g.drawImage(flag.getScaledInstance(600, 300,Image.SCALE_FAST), 0, 0, null);	
	}
	
	void setflag(Image flag) {
		this.flag=flag;
	}
}