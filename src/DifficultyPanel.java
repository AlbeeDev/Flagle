import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@SuppressWarnings("serial")
public class DifficultyPanel extends JPanel {
    String theme;
    String seldiff;
    String username;
    boolean fullscreen;
    JFrame frame;
    JLabel difficulty=new JLabel();
	JButton easy=new JButton();
	JButton medium=new JButton();
	JButton hard= new JButton();
    JButton extreme= new JButton();
    JTextArea description = new JTextArea();
    JButton home = new JButton();
    JButton save = new JButton();
    public DifficultyPanel(JFrame framef) {
        this.frame=framef;
		setBounds(0,0,frame.getContentPane().getWidth()-100,frame.getContentPane().getHeight());
		setLayout(null);

        try {
			
			JsonData data = new ObjectMapper().readValue(new File("data/loader.json"), JsonData.class);
			theme=data.getAspect().getTheme();
			seldiff=data.getUserData().getDifficultyselect();
			username=data.getUserData().getUsername();
			fullscreen=data.getAspect().isFullscreen();
			
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}

        if(fullscreen){
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
        else{
            frame.setExtendedState(JFrame.NORMAL);
        }

        home.setBorderPainted(false);
		home.setFocusPainted(false);
		home.setText("< Main Menu");
		home.setFont(new Font("",Font.PLAIN,20));
		home.setHorizontalAlignment(JLabel.LEFT);
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

        difficulty.setText("Select difficulty:");
		difficulty.setHorizontalAlignment(JLabel.CENTER);
		difficulty.setFont(new Font("",Font.BOLD,30));
		add(difficulty);
		
		easy.setBorderPainted(false);
		easy.setFocusPainted(false);
		easy.setText("easy");
		easy.setActionCommand(easy.getText());
        easy.setFont(new Font("",Font.PLAIN,20));
		add(easy);
		
		medium.setBorderPainted(false);
		medium.setFocusPainted(false);
		medium.setText("medium");
		medium.setActionCommand(medium.getText());
        medium.setFont(new Font("",Font.PLAIN,20));
		add(medium);
		
		hard.setBorderPainted(false);
		hard.setFocusPainted(false);
		hard.setText("hard");
		hard.setActionCommand(hard.getText());
        hard.setFont(new Font("",Font.PLAIN,20));
		add(hard);

        extreme.setBorderPainted(false);
        extreme.setFocusPainted(false);
        extreme.setText("extreme");
        extreme.setActionCommand(extreme.getText());
        extreme.setFont(new Font("",Font.PLAIN,20));
        add(extreme);

        ActionListener diff = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton) e.getSource();
				
				seldiff=b.getActionCommand();
				if(theme.equals("light")) {
					easy.setBackground(Color.white);
					medium.setBackground(Color.white);
					hard.setBackground(Color.white);
                    extreme.setBackground(Color.white);
					easy.setForeground(Color.black);
					medium.setForeground(Color.black);
					hard.setForeground(Color.black);
                    extreme.setForeground(Color.black);
				}
				else {
					easy.setBackground(Color.darkGray);
					medium.setBackground(Color.darkGray);
					hard.setBackground(Color.darkGray);
                    extreme.setBackground(Color.darkGray);
					easy.setForeground(Color.white);
					medium.setForeground(Color.white);
					hard.setForeground(Color.white);
                    extreme.setForeground(Color.white);
				}
				if(seldiff.equals("easy")) {
					easy.setBackground(Color.green);
                    description.setText("Very easy flags, only americans could get them wrong.");
				}
				else if(seldiff.equals("hard")) {
					hard.setBackground(Color.orange);
                    description.setText("Harder flags, a good way to challenge your flag knowledge");
				}
				else if(seldiff.equals("medium")) {
					medium.setBackground(Color.yellow);
                    description.setText("Less common flags, normal difficulty");
				}
                else{
                    extreme.setBackground(Color.red);
                    description.setText("Mostly african flags, guessing the right flag is hard... but not as hard as finding water");
                }
				
			}
		};
		
		easy.addActionListener(diff);
		medium.addActionListener(diff);
		hard.addActionListener(diff);
        extreme.addActionListener(diff);

        if(seldiff.equals("easy")){
            easy.doClick();
        }
        else if(seldiff.equals("medium")){
             medium.doClick();
        }
        else if(seldiff.equals("hard")){
            hard.doClick();
        }
        else{
            extreme.doClick();
        }
        description.setEditable(false);
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setFont(new Font("",Font.PLAIN,20));
        add(description);

        save.setText("Select");
		save.setFont(new Font("",Font.BOLD,20));
		save.setBackground(Color.green);
        save.setForeground(Color.black);
		add(save);
		
		ActionListener saver = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//write to json
                
				try {
					ObjectMapper om = new ObjectMapper();
					JsonData data = om.readValue(new File("data/loader.json"), JsonData.class);
					data.getUserData().setDifficultyselect(seldiff);
					om.enable(SerializationFeature.INDENT_OUTPUT);
					om.setDefaultPrettyPrinter(new DefaultPrettyPrinter());
					om.writeValue(new File("src/loader.json"), data);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
                
                setVisible(false);
				GamePanel gamep = new GamePanel(frame);
				frame.add(gamep);
			}
		};
		
		save.addActionListener(saver);
        
        //theme
        if(theme.equals("dark")){
            setBackground(Color.darkGray);
            home.setForeground(Color.white);
			home.setBackground(Color.darkGray);
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
            if(extreme.getBackground()==Color.white) {
                extreme.setBackground(Color.darkGray);
            }
            extreme.setForeground(Color.white);
        }
        else{
        	setBackground(Color.lightGray);
            home.setForeground(Color.black);
 			home.setBackground(Color.lightGray);
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
            if(extreme.getBackground()==Color.DARK_GRAY) {
                extreme.setBackground(Color.white);
            }
            extreme.setForeground(Color.BLACK);
        }
        //auto resize
        home.setBounds(0,0,convwidth(200),convheight(50));
    	difficulty.setBounds(convwidth(375),convheight(200),convwidth(250),convheight(50));
    	easy.setBounds(convwidth(300),convheight(300),convwidth(100),convheight(50));
		medium.setBounds(convwidth(400),convheight(300),convwidth(100),convheight(50));
		hard.setBounds(convwidth(499),convheight(300),convwidth(100),convheight(50));
        extreme.setBounds(convwidth(599),convheight(300),convwidth(100),convheight(50));
        description.setBounds(convwidth(300), convheight(400), convwidth(400), convheight(200));    
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