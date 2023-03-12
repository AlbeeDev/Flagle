import java.awt.Color;
import java.awt.Font;
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
public class MainPanel extends JPanel{
	String theme;
	String username;
	boolean fullscreen;
	JFrame frame;
	JLabel title;
	JLabel intro;
	JButton play;
	JButton settings;
	JButton stats;
	public MainPanel(JFrame framef) {
		this.frame=framef;
		setBounds(0,0,frame.getContentPane().getWidth(),frame.getContentPane().getHeight());
		setBackground(Color.darkGray);
		setLayout(null);
		
		try {
			
			JsonData data = new ObjectMapper().readValue(new File("src/loader.json"), JsonData.class);
			theme=data.getAspect().getTheme();
			username=data.getUserData().getUsername();
			fullscreen=data.getAspect().isFullscreen();
			frame.setTitle("Flagle "+data.getVersion());
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		if(fullscreen) {
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		}
		else {
			frame.setExtendedState(JFrame.NORMAL);
		}
		
		title= new JLabel("FLAGLE");
		title.setBounds(200,100,600,100);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font("",Font.BOLD,60));
		add(title);
		
		intro=new JLabel();
		intro.setText("Welcome back, "+username+"!");
		intro.setBounds(200,200,600,100);
		intro.setHorizontalAlignment(JLabel.CENTER);
		intro.setFont(new Font("",Font.PLAIN,30));
		add(intro);
		
		play= new JButton();
		play.setBounds(350,300,300,100);
		play.setBackground(Color.green);
		play.setText("PLAY");
		play.setFont(new Font("",Font.BOLD,30));
		add(play);
		
		ActionListener playevent = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				GamePanel gamep = new GamePanel(frame);
				frame.add(gamep);
			}
		};
		
		play.addActionListener(playevent);
		
		settings = new JButton();
		settings.setBounds(350,600,300,100);
		settings.setBackground(Color.white);
		settings.setText("SETTINGS");
		settings.setFont(new Font("",Font.BOLD,30));
		add(settings);
		
		ActionListener settingsevent = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				SettingsPanel setp = new SettingsPanel(frame);
				frame.add(setp);
			}
		};
		
		settings.addActionListener(settingsevent);
		
		stats=new JButton();
		stats.setBounds(350,450,300,100);
		stats.setBackground(Color.white);
		stats.setText("STATS");
		stats.setFont(new Font("",Font.BOLD,30));
		add(stats);
		
		ActionListener statser = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				StatsPanel statp= new StatsPanel(frame);
				frame.add(statp);
				
			}
		};
		
		stats.addActionListener(statser);
		
		//tema
		if(theme.equals("dark")) {
    		title.setForeground(Color.white);
    		intro.setForeground(Color.white);
    		setBackground(Color.darkGray);
    	}
    	else {
    		title.setForeground(Color.black);
    		intro.setForeground(Color.black);
    		setBackground(Color.lightGray);
    	}
		//auto size
		addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
            	title.setBounds(convwidth(200),convheight(100),convwidth(600),convheight(100));
            	intro.setBounds(convwidth(200),convheight(200),convwidth(600),convheight(100));
            	play.setBounds(convwidth(350),convheight(300),convwidth(300),convheight(100));
            	settings.setBounds(convwidth(350),convheight(600),convwidth(300),convheight(100));
            	stats.setBounds(convwidth(350),convheight(450),convwidth(300),convheight(100));
            }
        });
		title.setBounds(convwidth(200),convheight(100),convwidth(600),convheight(100));
    	intro.setBounds(convwidth(200),convheight(200),convwidth(600),convheight(100));
    	play.setBounds(convwidth(350),convheight(300),convwidth(300),convheight(100));
    	settings.setBounds(convwidth(350),convheight(600),convwidth(300),convheight(100));
    	stats.setBounds(convwidth(350),convheight(450),convwidth(300),convheight(100));
	}
	
	int convwidth(int size) {
		return (int) ((frame.getContentPane().getWidth()*size)/1000.0);
	}
	
	int convheight(int size) {
		return (int) ((frame.getContentPane().getHeight()*size)/900.0);
	}

}
