import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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
	JLabel medium= new JLabel();
	JLabel mwin=new JLabel();
	JLabel mlost=new JLabel();
	JLabel hard= new JLabel();
	JLabel hwin=new JLabel();
	JLabel hlost=new JLabel();
	public StatsPanel(JFrame framef) {
		this.frame=framef;
		setBounds(0,0,frame.getContentPane().getWidth(),frame.getContentPane().getHeight());
		setBackground(Color.lightGray);
		setLayout(null);
		
		try {
			
			JsonData data = new ObjectMapper().readValue(new File("src/loader.json"), JsonData.class);
			theme=data.getAspect().getTheme();
			username=data.getUserData().getUsername();
			fullscreen=data.getAspect().isFullscreen();
			ewin.setText(Integer.toString(data.getUserData().getDifficulty().getEasy().getWin()));
			mwin.setText(Integer.toString(data.getUserData().getDifficulty().getMedium().getWin()));
			hwin.setText(Integer.toString(data.getUserData().getDifficulty().getHard().getWin()));
			elost.setText(Integer.toString(data.getUserData().getDifficulty().getEasy().getLost()));
			mlost.setText(Integer.toString(data.getUserData().getDifficulty().getMedium().getLost()));
			hlost.setText(Integer.toString(data.getUserData().getDifficulty().getHard().getLost()));
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
		
		//theme
		if(theme.equals("dark")) {
			setBackground(Color.DARK_GRAY);
			home.setBackground(Color.DARK_GRAY);
			home.setForeground(Color.white);
			title.setForeground(Color.white);
			ewin.setForeground(Color.darkGray);
			mwin.setForeground(Color.darkGray);
			hwin.setForeground(Color.darkGray);
			elost.setForeground(Color.darkGray);
			mlost.setForeground(Color.darkGray);
			hlost.setForeground(Color.darkGray);
		}
		else {
			setBackground(Color.lightGray);
			home.setBackground(Color.lightGray);
			home.setForeground(Color.black);
			title.setForeground(Color.black);
			ewin.setForeground(Color.white);
			mwin.setForeground(Color.white);
			hwin.setForeground(Color.white);
			elost.setForeground(Color.white);
			mlost.setForeground(Color.white);
			hlost.setForeground(Color.white);
		}
		easy.setForeground(Color.green);
		medium.setForeground(Color.orange);
		hard.setForeground(Color.red);
		
		//auto size
		addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
            	home.setBounds(0,0,convwidth(200),convheight(50));
            	title.setBounds(convwidth(200),convheight(100),convwidth(600),convheight(100));
            	easy.setBounds(convwidth(100),convheight(200),convwidth(200),convheight(100));
            	ewin.setBounds(convwidth(100),convheight(300),convwidth(200),convheight(100));
            	elost.setBounds(convwidth(100),convheight(350),convwidth(200),convheight(100));
            	medium.setBounds(convwidth(400),convheight(200),convwidth(200),convheight(100));
            	mwin.setBounds(convwidth(400),convheight(300),convwidth(200),convheight(100));
            	mlost.setBounds(convwidth(400),convheight(350),convwidth(200),convheight(100));
            	hard.setBounds(convwidth(700),convheight(200),convwidth(200),convheight(100));
            	hwin.setBounds(convwidth(700),convheight(300),convwidth(200),convheight(100));
            	hlost.setBounds(convwidth(700),convheight(350),convwidth(200),convheight(100));
            }
        });
		home.setBounds(0,0,convwidth(200),convheight(50));
    	title.setBounds(convwidth(200),convheight(100),convwidth(600),convheight(100));
    	easy.setBounds(convwidth(100),convheight(200),convwidth(200),convheight(100));
    	ewin.setBounds(convwidth(100),convheight(300),convwidth(200),convheight(100));
    	elost.setBounds(convwidth(100),convheight(350),convwidth(200),convheight(100));
    	medium.setBounds(convwidth(400),convheight(200),convwidth(200),convheight(100));
    	mwin.setBounds(convwidth(400),convheight(300),convwidth(200),convheight(100));
    	mlost.setBounds(convwidth(400),convheight(350),convwidth(200),convheight(100));
    	hard.setBounds(convwidth(700),convheight(200),convwidth(200),convheight(100));
    	hwin.setBounds(convwidth(700),convheight(300),convwidth(200),convheight(100));
    	hlost.setBounds(convwidth(700),convheight(350),convwidth(200),convheight(100));
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
		g.fillRect(convwidth(100),convheight(200),convwidth(200),convheight(300));
		g.fillRect(convwidth(400),convheight(200),convwidth(200),convheight(300));
		g.fillRect(convwidth(700),convheight(200),convwidth(200),convheight(300));
	}
	
	int convwidth(int size) {
		return (int) ((frame.getContentPane().getWidth()*size)/1000.0);
	}
	
	int convheight(int size) {
		return (int) ((frame.getContentPane().getHeight()*size)/900.0);
	}
}
