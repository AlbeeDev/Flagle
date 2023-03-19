import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("serial")
public class StatsPanel extends JPanel{
	String theme;
	String username;
	boolean fullscreen;
	JFrame frame;
	JLabel title = new JLabel();
	JButton home = new JButton();
	JLabel easy= new JLabel();
	JLabel ewin=new JLabel();
	JLabel elost=new JLabel();
	JLabel estreak=new JLabel();
	JLabel medium= new JLabel();
	JLabel mwin=new JLabel();
	JLabel mlost=new JLabel();
	JLabel mstreak=new JLabel();
	JLabel hard= new JLabel();
	JLabel hwin=new JLabel();
	JLabel hlost=new JLabel();
	JLabel hstreak=new JLabel();
	JLabel extreme=new JLabel();
	JLabel xwin=new JLabel();
	JLabel xlost=new JLabel();
	JLabel xstreak=new JLabel();
	public StatsPanel(JFrame framef) {
		this.frame=framef;
		setBounds(0,0,frame.getContentPane().getWidth(),frame.getContentPane().getHeight());
		setBackground(Color.lightGray);
		setLayout(null);
		
		try {
			
			JsonData data = new ObjectMapper().readValue(new File("data/loader.json"), JsonData.class);
			theme=data.getAspect().getTheme();
			username=data.getUserData().getUsername();
			fullscreen=data.getAspect().isFullscreen();
			ewin.setText(Integer.toString(data.getUserData().getDifficulty().getEasy().getWin()));
			mwin.setText(Integer.toString(data.getUserData().getDifficulty().getMedium().getWin()));
			hwin.setText(Integer.toString(data.getUserData().getDifficulty().getHard().getWin()));
			xwin.setText(Integer.toString(data.getUserData().getDifficulty().getExtreme().getWin()));
			elost.setText(Integer.toString(data.getUserData().getDifficulty().getEasy().getLost()));
			mlost.setText(Integer.toString(data.getUserData().getDifficulty().getMedium().getLost()));
			hlost.setText(Integer.toString(data.getUserData().getDifficulty().getHard().getLost()));
			xlost.setText(Integer.toString(data.getUserData().getDifficulty().getExtreme().getLost()));
			estreak.setText(Integer.toString(data.getUserData().getDifficulty().getEasy().getWinstreak()));
			mstreak.setText(Integer.toString(data.getUserData().getDifficulty().getMedium().getWinstreak()));
			hstreak.setText(Integer.toString(data.getUserData().getDifficulty().getHard().getWinstreak()));
			xstreak.setText(Integer.toString(data.getUserData().getDifficulty().getExtreme().getWinstreak()));
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
		
		title.setText(username+"'s stats");
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font("",Font.BOLD,60));
		add(title);
		
		easy.setText("Easy");
		easy.setHorizontalAlignment(JLabel.CENTER);
		easy.setFont(new Font("",Font.BOLD,30));
		add(easy);
		
		ewin.setText("Wins: "+ewin.getText());
		ewin.setHorizontalAlignment(JLabel.CENTER);
		ewin.setFont(new Font("",Font.BOLD,20));
		add(ewin);
		
		elost.setText("Lost: "+elost.getText());
		elost.setHorizontalAlignment(JLabel.CENTER);
		elost.setFont(new Font("",Font.BOLD,20));
		add(elost);
		
        estreak.setText("Winstreak: "+estreak.getText());
		estreak.setHorizontalAlignment(JLabel.CENTER);
		estreak.setFont(new Font("",Font.BOLD,20));
		add(estreak);
		
		medium.setText("Medium");
		medium.setHorizontalAlignment(JLabel.CENTER);
		medium.setFont(new Font("",Font.BOLD,30));
		add(medium);
		
		mwin.setText("Wins: "+mwin.getText());
		mwin.setHorizontalAlignment(JLabel.CENTER);
		mwin.setFont(new Font("",Font.BOLD,20));
		add(mwin);
		
		mlost.setText("Lost: "+mlost.getText());
		mlost.setHorizontalAlignment(JLabel.CENTER);
		mlost.setFont(new Font("",Font.BOLD,20));
		add(mlost);
		
        mstreak.setText("Winstreak: "+mstreak.getText());
		mstreak.setHorizontalAlignment(JLabel.CENTER);
		mstreak.setFont(new Font("",Font.BOLD,20));
		add(mstreak);
		
		hard.setText("Hard");
		hard.setHorizontalAlignment(JLabel.CENTER);
		hard.setFont(new Font("",Font.BOLD,30));
		add(hard);
		
		hwin.setText("Wins: "+hwin.getText());
		hwin.setHorizontalAlignment(JLabel.CENTER);
		hwin.setFont(new Font("",Font.BOLD,20));
		add(hwin);
		
		hlost.setText("Lost: "+hlost.getText());
		hlost.setHorizontalAlignment(JLabel.CENTER);
		hlost.setFont(new Font("",Font.BOLD,20));
		add(hlost);

	    hstreak.setText("Winstreak: "+hstreak.getText());
		hstreak.setHorizontalAlignment(JLabel.CENTER);
		hstreak.setFont(new Font("",Font.BOLD,20));
		add(hstreak);

		extreme.setText("Extreme");
        extreme.setHorizontalAlignment(JLabel.CENTER);
        extreme.setFont(new Font("",Font.BOLD,30));
        add(extreme);
        
        xwin.setText("Wins: "+xwin.getText());
        xwin.setHorizontalAlignment(JLabel.CENTER);
        xwin.setFont(new Font("",Font.BOLD,20));
        add(xwin);
        
        xlost.setText("Lost: "+xlost.getText());
        xlost.setHorizontalAlignment(JLabel.CENTER);
        xlost.setFont(new Font("",Font.BOLD,20));
        add(xlost);

		xstreak.setText("Winstreak: "+xstreak.getText());
        xstreak.setHorizontalAlignment(JLabel.CENTER);
        xstreak.setFont(new Font("",Font.BOLD,20));
        add(xstreak);
		
		//theme
		if(theme.equals("dark")) {
			setBackground(Color.DARK_GRAY);
			home.setBackground(Color.DARK_GRAY);
			home.setForeground(Color.white);
			title.setForeground(Color.white);
			ewin.setForeground(Color.darkGray);
			mwin.setForeground(Color.darkGray);
			hwin.setForeground(Color.darkGray);
			xwin.setForeground(Color.darkGray);
			elost.setForeground(Color.darkGray);
			mlost.setForeground(Color.darkGray);
			hlost.setForeground(Color.darkGray);
			xlost.setForeground(Color.darkGray);
			estreak.setForeground(Color.darkGray);
			mstreak.setForeground(Color.darkGray);
			hstreak.setForeground(Color.darkGray);
			xstreak.setForeground(Color.darkGray);			
		}
		else {
			setBackground(Color.lightGray);
			home.setBackground(Color.lightGray);
			home.setForeground(Color.black);
			title.setForeground(Color.black);
			ewin.setForeground(Color.white);
			mwin.setForeground(Color.white);
			hwin.setForeground(Color.white);
			xwin.setForeground(Color.white);
			elost.setForeground(Color.white);
			mlost.setForeground(Color.white);
			hlost.setForeground(Color.white);
			xlost.setForeground(Color.white);
			estreak.setForeground(Color.white);
			mstreak.setForeground(Color.white);
			hstreak.setForeground(Color.white);
			xstreak.setForeground(Color.white);
		}
		easy.setForeground(Color.green);
		medium.setForeground(Color.yellow);
		hard.setForeground(Color.orange);
		extreme.setForeground(Color.red);
		
		//size
		home.setBounds(0,0,convwidth(200),convheight(50));
    	title.setBounds(convwidth(200),convheight(100),convwidth(600),convheight(100));
    	easy.setBounds(convwidth(200),convheight(200),convwidth(200),convheight(100));
    	ewin.setBounds(convwidth(200),convheight(300),convwidth(200),convheight(100));
    	elost.setBounds(convwidth(200),convheight(350),convwidth(200),convheight(100));
		estreak.setBounds(convwidth(200),convheight(400),convwidth(200),convheight(100));
    	medium.setBounds(convwidth(600),convheight(200),convwidth(200),convheight(100));
    	mwin.setBounds(convwidth(600),convheight(300),convwidth(200),convheight(100));
    	mlost.setBounds(convwidth(600),convheight(350),convwidth(200),convheight(100));
		mstreak.setBounds(convwidth(600),convheight(400),convwidth(200),convheight(100));
    	hard.setBounds(convwidth(200),convheight(550),convwidth(200),convheight(100));
    	hwin.setBounds(convwidth(200),convheight(650),convwidth(200),convheight(100));
    	hlost.setBounds(convwidth(200),convheight(700),convwidth(200),convheight(100));
		hstreak.setBounds(convwidth(200),convheight(750),convwidth(200),convheight(100));
		extreme.setBounds(convwidth(600),convheight(550),convwidth(200),convheight(100));
		xwin.setBounds(convwidth(600),convheight(650),convwidth(200),convheight(100));
		xlost.setBounds(convwidth(600),convheight(700),convwidth(200),convheight(100));
		xstreak.setBounds(convwidth(600),convheight(750),convwidth(200),convheight(100));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(theme.equals("dark")) {
			g.setColor(Color.LIGHT_GRAY);
		}
		else {
			g.setColor(Color.DARK_GRAY);
		}
		g.fillRect(convwidth(200),convheight(200),convwidth(200),convheight(300));
		g.fillRect(convwidth(600),convheight(200),convwidth(200),convheight(300));
		g.fillRect(convwidth(200),convheight(550),convwidth(200),convheight(300));
		g.fillRect(convwidth(600),convheight(550),convwidth(200),convheight(300));
	}
	
	int convwidth(int size) {
		return (int) ((frame.getContentPane().getWidth()*size)/1000.0);
	}
	
	int convheight(int size) {
		return (int) ((frame.getContentPane().getHeight()*size)/900.0);
	}
}
