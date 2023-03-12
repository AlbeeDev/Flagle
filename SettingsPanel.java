import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@SuppressWarnings("serial")
public class SettingsPanel extends JPanel{
	String theme;
	String seldiff;
	String username;
	boolean fullscreen;
	JFrame frame;
	JButton home= new JButton();
	JLabel userlabel = new JLabel();
	JTextField userfield= new JTextField();
	JLabel difficulty=new JLabel();
	JButton easy=new JButton();
	JButton medium=new JButton();
	JButton hard= new JButton();
	JLabel fslabel= new JLabel();
	JCheckBox fscheck= new JCheckBox();
	JLabel background= new JLabel();
	JButton dark= new JButton();
	JButton light= new JButton();
	JButton save= new JButton();
	
	public SettingsPanel(JFrame framef) {
		this.frame=framef;
		setBounds(0,0,frame.getContentPane().getWidth()-100,frame.getContentPane().getHeight());
		setLayout(null);
		
		try {
			
			JsonData data = new ObjectMapper().readValue(new File("src/loader.json"), JsonData.class);
			theme=data.getAspect().getTheme();
			seldiff=data.getUserData().getDifficultyselect();
			username=data.getUserData().getUsername();
			fullscreen=data.getAspect().isFullscreen();
			
			
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
		
		userlabel.setText("Username:");
		userlabel.setHorizontalAlignment(JLabel.RIGHT);
		userlabel.setFont(new Font("",Font.PLAIN,20));
		add(userlabel);
		
		userfield.setText(username);
		userfield.setFont(new Font("",Font.PLAIN,20));
		add(userfield);
		
		difficulty.setText("Difficulty:");
		difficulty.setHorizontalAlignment(JLabel.RIGHT);
		difficulty.setFont(new Font("",Font.PLAIN,20));
		add(difficulty);
		
		easy.setBorderPainted(false);
		easy.setFocusPainted(false);
		easy.setText("easy");
		easy.setActionCommand(easy.getText());
		add(easy);
		
		medium.setBorderPainted(false);
		medium.setFocusPainted(false);
		medium.setText("medium");
		medium.setActionCommand(medium.getText());
		add(medium);
		
		hard.setBorderPainted(false);
		hard.setFocusPainted(false);
		hard.setText("hard");
		hard.setActionCommand(hard.getText());
		add(hard);
		
		ActionListener diff = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton) e.getSource();
				
				seldiff=b.getActionCommand();
				if(theme.equals("light")) {
					easy.setBackground(Color.white);
					medium.setBackground(Color.white);
					hard.setBackground(Color.white);
					easy.setForeground(Color.black);
					medium.setForeground(Color.black);
					hard.setForeground(Color.black);
				}
				else {
					easy.setBackground(Color.darkGray);
					medium.setBackground(Color.darkGray);
					hard.setBackground(Color.darkGray);
					easy.setForeground(Color.white);
					medium.setForeground(Color.white);
					hard.setForeground(Color.white);
				}
				if(seldiff.equals("easy")) {
					easy.setBackground(Color.green);
				}
				else if(seldiff.equals("hard")) {
					hard.setBackground(Color.red);
				}
				else {
					medium.setBackground(Color.orange);
				}
				
			}
		};
		
		easy.addActionListener(diff);
		medium.addActionListener(diff);
		hard.addActionListener(diff);
		
		if(seldiff.equals("easy")) {
			easy.doClick();
		}
		else if(seldiff.equals("hard")) {
			hard.doClick();
		}
		else {
			medium.doClick();
		}
		
		fslabel.setText("Fullscreen:");
		fslabel.setHorizontalAlignment(JLabel.RIGHT);
		fslabel.setFont(new Font("",Font.PLAIN,20));
		add(fslabel);
		
		fscheck.setSelected(false);
		fscheck.setBackground(Color.lightGray);
		add(fscheck);
		
		if(fullscreen) {
			fscheck.doClick();
		}
		
		ChangeListener checker = new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				fullscreen=fscheck.isSelected();
				if(fscheck.isSelected()) {
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				}
				else {
					frame.setExtendedState(JFrame.NORMAL);
				}
				
			}
		};
		
		fscheck.addChangeListener(checker);
		
		save.setText("Save changes");
		save.setFont(new Font("",Font.BOLD,20));
		save.setBackground(Color.green);
		add(save);
		
		ActionListener saver = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//write to json
				try {
					ObjectMapper om = new ObjectMapper();
					JsonData data = om.readValue(new File("src/loader.json"), JsonData.class);
					data.getAspect().setTheme(theme);
					data.getAspect().setFullscreen(fullscreen);
					data.getUserData().setDifficultyselect(seldiff);
					data.getUserData().setUsername(userfield.getText());
					om.enable(SerializationFeature.INDENT_OUTPUT);
					om.setDefaultPrettyPrinter(new DefaultPrettyPrinter());
					om.writeValue(new File("src/loader.json"), data);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		};
		
		save.addActionListener(saver);
		
		background.setText("Theme:");
		background.setHorizontalAlignment(JLabel.RIGHT);
		background.setFont(new Font("",Font.PLAIN,20));
		add(background);
		
		dark.setBorderPainted(false);
		dark.setFocusPainted(false);
		dark.setText("dark");
		dark.setActionCommand(dark.getText());
		dark.setBounds(375,500,75,50);
		dark.setBackground(Color.black);
		dark.setForeground(Color.white);
		add(dark);
		
		light.setBorderPainted(false);
		light.setFocusPainted(false);
		light.setText("light");
		light.setActionCommand(light.getText());
		light.setBounds(450,500,75,50);
		light.setBackground(Color.white);
		light.setForeground(Color.black);
		add(light);
		
		ActionListener themer = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				JButton b = (JButton) e.getSource();
				
				theme=b.getActionCommand();
				if(theme.equals("light")) {
					setBackground(Color.LIGHT_GRAY);
					home.setBackground(Color.LIGHT_GRAY);
					fscheck.setBackground(Color.DARK_GRAY);
					fscheck.setForeground(Color.white);
					userfield.setBackground(Color.white);
					userfield.setForeground(Color.black);
					userlabel.setForeground(Color.white);
					fslabel.setForeground(Color.white);
					background.setForeground(Color.white);
					home.setForeground(Color.black);
					difficulty.setForeground(Color.white);
					if(easy.getBackground()==Color.DARK_GRAY) {
						easy.setBackground(Color.white);
					}
					easy.setForeground(Color.BLACK);
					if(medium.getBackground()==Color.DARK_GRAY) {
						medium.setBackground(Color.white);
					}
					medium.setForeground(Color.BLACK);
					if(hard.getBackground()==Color.DARK_GRAY) {
						hard.setBackground(Color.white);
					}
					hard.setForeground(Color.BLACK);
				}
				else {
					setBackground(Color.darkGray);
					home.setBackground(Color.darkGray);
					fscheck.setBackground(Color.LIGHT_GRAY);
					fscheck.setForeground(Color.black);
					userfield.setBackground(Color.DARK_GRAY);
					userfield.setForeground(Color.white);
					userlabel.setForeground(Color.black);
					fslabel.setForeground(Color.black);
					background.setForeground(Color.black);
					home.setForeground(Color.white);
					difficulty.setForeground(Color.black);
					if(easy.getBackground()==Color.white) {
						easy.setBackground(Color.darkGray);
					}
					easy.setForeground(Color.white);
					if(medium.getBackground()==Color.white) {
						medium.setBackground(Color.darkGray);
					}
					medium.setForeground(Color.white);
					if(hard.getBackground()==Color.white) {
						hard.setBackground(Color.darkGray);
					}
					hard.setForeground(Color.white);
				}
				if(seldiff.equals("easy")) {
					easy.setBackground(Color.green);
				}
				else if(seldiff.equals("medium")) {
					medium.setBackground(Color.orange);
				}
				else {
					hard.setBackground(Color.red);
				}
				
			}
		};
		
		dark.addActionListener(themer);
		light.addActionListener(themer);
		
		//theme
		if(theme.equals("dark")) {
			dark.doClick();
		}
		else {
			light.doClick();
		}
		
		
		//auto size
		addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
            	home.setBounds(0,0,convwidth(200),convheight(50));
            	userlabel.setBounds(convwidth(200),convheight(200),convwidth(150),convheight(50));
            	userfield.setBounds(convwidth(375),convheight(200),convwidth(300),convheight(50));
            	difficulty.setBounds(convwidth(200),convheight(300),convwidth(150),convheight(50));
            	easy.setBounds(convwidth(375),convheight(300),convwidth(100),convheight(50));
        		medium.setBounds(convwidth(475),convheight(300),convwidth(100),convheight(50));
        		hard.setBounds(convwidth(575),convheight(300),convwidth(100),convheight(50));
        		fslabel.setBounds(convwidth(200),convheight(400),convwidth(150),convheight(50));
        		fscheck.setBounds(convwidth(375),convheight(400),convwidth(100),convheight(50));
        		background.setBounds(convwidth(200),convheight(500),convwidth(150),convheight(50));
        		dark.setBounds(convwidth(375),convheight(500),convwidth(75),convheight(50));
        		light.setBounds(convwidth(450),convheight(500),convwidth(75),convheight(50));
        		save.setBounds(convwidth(375),convheight(625),convwidth(250),convheight(75));
            }
        });
		home.setBounds(0,0,convwidth(200),convheight(50));
    	userlabel.setBounds(convwidth(200),convheight(200),convwidth(150),convheight(50));
    	userfield.setBounds(convwidth(375),convheight(200),convwidth(300),convheight(50));
    	difficulty.setBounds(convwidth(200),convheight(300),convwidth(150),convheight(50));
    	easy.setBounds(convwidth(375),convheight(300),convwidth(100),convheight(50));
		medium.setBounds(convwidth(475),convheight(300),convwidth(100),convheight(50));
		hard.setBounds(convwidth(575),convheight(300),convwidth(100),convheight(50));
		fslabel.setBounds(convwidth(200),convheight(400),convwidth(150),convheight(50));
		fscheck.setBounds(convwidth(375),convheight(400),convwidth(100),convheight(50));
		background.setBounds(convwidth(200),convheight(500),convwidth(150),convheight(50));
		dark.setBounds(convwidth(375),convheight(500),convwidth(75),convheight(50));
		light.setBounds(convwidth(450),convheight(500),convwidth(75),convheight(50));
		save.setBounds(convwidth(375),convheight(625),convwidth(250),convheight(75));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(theme.equals("dark")) {
			g.setColor(Color.lightGray);
		}
		else {
			g.setColor(Color.darkGray);
		}
		g.fillRect(convwidth(200),convheight(150),convwidth(600),convheight(600));
	}
	
	int convwidth(int size) {
		return (int) ((frame.getContentPane().getWidth()*size)/1000.0);
	}
	
	int convheight(int size) {
		return (int) ((frame.getContentPane().getHeight()*size)/900.0);
	}
	
	

}
