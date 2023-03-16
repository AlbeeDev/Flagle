import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("serial")
public class SummaryPanel extends JPanel{
	JFrame frame;
	Image flag;
	String fname;
	boolean status;
	String theme;
	boolean fullscreen;
	String seldiff;
	int streak;
	JButton home = new JButton();
	JLabel title = new JLabel();
	JLabel msgflag = new JLabel();
	JLabel msgstreak = new JLabel();
	JButton save = new JButton();
	public SummaryPanel(JFrame framef, Image flag, String fname, boolean status) {
		this.frame=framef;
		this.flag=flag;
		this.status=status;
		setBounds(0,0,frame.getContentPane().getWidth(),frame.getContentPane().getHeight());
		setBackground(Color.lightGray);
		setLayout(null);
		
		try {
			
			JsonData data = new ObjectMapper().readValue(new File("data/loader.json"), JsonData.class);
			theme=data.getAspect().getTheme();
			fullscreen=data.getAspect().isFullscreen();
			seldiff=data.getUserData().getDifficultyselect();
			if(seldiff.equals("easy")) {
				streak=data.getUserData().getDifficulty().getEasy().getWinstreak();
			}
			else if(seldiff.equals("medium")) {
				streak=data.getUserData().getDifficulty().getMedium().getWinstreak();
			}
			else if(seldiff.equals("medium")) {
				streak=data.getUserData().getDifficulty().getHard().getWinstreak();
			}
			else {
				streak=data.getUserData().getDifficulty().getExtreme().getWinstreak();
			}
			
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		if(fullscreen) {
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		}
		else {
			frame.setExtendedState(JFrame.NORMAL);
		}
		
		home.setBorderPainted(false);
		home.setFocusPainted(false);
		home.setText("< Main Menu");
		home.setFont(new Font("",Font.PLAIN,20));
		home.setHorizontalAlignment(JLabel.LEFT);
		home.setForeground(Color.white);
		add(home);
		
		ActionListener menu = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				MainPanel main = new MainPanel(frame);
				frame.add(main);
			}
		};
		
		home.addActionListener(menu);
		
		if(status) {
			title.setText("Correct!");
			title.setForeground(Color.green);
		}
		else {
			title.setText("Wrong!");
			title.setForeground(Color.red);
		}
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font("",Font.BOLD,60));
		add(title);
		
		msgflag.setText("The correct flag was "+fname.replaceFirst("[.][^.]+$", "")+" !");
		msgflag.setHorizontalAlignment(JLabel.CENTER);
		msgflag.setFont(new Font("",Font.BOLD,30));
		add(msgflag);
		
		msgstreak.setText("Current win streak is: "+streak+" wins");
		msgstreak.setHorizontalAlignment(JLabel.CENTER);
		msgstreak.setFont(new Font("",Font.BOLD,30));
		add(msgstreak);
		
		save.setText("Select");
		save.setFont(new Font("",Font.BOLD,20));
		save.setBackground(Color.green);
        save.setForeground(Color.black);
		add(save);
		
		ActionListener nexter = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				GamePanel gamep = new GamePanel(frame);
				frame.add(gamep);
			}
		};
		save.addActionListener(nexter);
		
		//theme
		if(theme.equals("dark")) {
			setBackground(Color.DARK_GRAY);
			home.setBackground(Color.DARK_GRAY);
			home.setForeground(Color.white);
			msgflag.setForeground(Color.white);
			msgstreak.setForeground(Color.white);
		}
		else {
			setBackground(Color.lightGray);
			home.setBackground(Color.lightGray);
			home.setForeground(Color.black);
			msgflag.setForeground(Color.black);
			msgstreak.setForeground(Color.black);
		}
		
		//size
		home.setBounds(0,0,convwidth(200),convheight(50));
    	title.setBounds(convwidth(0),convheight(500),convwidth(1000),convheight(100));
    	msgflag.setBounds(convwidth(0),convheight(600),convwidth(1000),convheight(100));
    	msgstreak.setBounds(convwidth(0),convheight(650),convwidth(1000),convheight(100));
    	save.setBounds(convwidth(200),convheight(750),convwidth(600),convheight(100));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = convwidth(600);
        int height = convheight(400);
        g.drawImage(flag.getScaledInstance(width, height,Image.SCALE_FAST), convwidth(200), convheight(100), null);
	}
	
	int convwidth(int size) {
		return (int) ((frame.getContentPane().getWidth()*size)/1000.0);
	}
	
	int convheight(int size) {
		return (int) ((frame.getContentPane().getHeight()*size)/900.0);
	}
	
}
