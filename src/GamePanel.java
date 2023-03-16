import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("serial")
public class GamePanel extends JPanel{
	File[] lista;
	File cfile;
	BufferedImage cflag;
	String cname;
	BufferedImage blank=new BufferedImage(600, 300, BufferedImage.TYPE_INT_RGB);
	MioAscoltatore al;
	String theme;
	String username;
	String difficulty;
	boolean fullscreen;
	int attempts=5;
	JFrame frame;
	JLabel title;
	JTextField filtro;
	JComboBox<String> box;
	JButton guess;
	JButton home;
	JLabel seltext;
	JLabel seldiff;
	JsonData data;
	public GamePanel(JFrame framef) {
		this.frame=framef;
		initBlank(blank);
		setBounds(0,0,frame.getContentPane().getWidth(),frame.getContentPane().getHeight());
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		try {
			
			data = new ObjectMapper().readValue(new File("data/loader.json"), JsonData.class);
			theme=data.getAspect().getTheme();
			username=data.getUserData().getUsername();
			difficulty=data.getUserData().getDifficultyselect();
			fullscreen=data.getAspect().isFullscreen();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		if(difficulty.equals("easy")){
			lista = new File("data/fl_easy").listFiles();
		}
		else if(difficulty.equals("medium")){
            lista = new File("data/fl_medium").listFiles();
        }
		else if(difficulty.equals("hard")){
			lista = new File("data/fl_hard").listFiles();
        }
		else{
            lista = new File("data/fl_extreme").listFiles();
        }
		cfile= lista[new Random().nextInt(lista.length)];
		cflag=getFlag(cfile);
		cname=cfile.getName();

		if(fullscreen) {
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		}
		else {
			frame.setExtendedState(JFrame.NORMAL);
		}
		
		title= new JLabel("FLAGLE");
		title.setBounds(200,0,600,100);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font("",Font.BOLD,60));
		add(title);
		
		filtro = new JTextField();
		filtro.setBounds(200, 500, 600, 50);
		filtro.setBackground(Color.white);
		filtro.setFont(new Font("",Font.BOLD,30));
		add(filtro);

		box = new JComboBox<String>();
		for(File file:lista){
			box.addItem(file.getName().replaceFirst("[.][^.]+$", ""));
		}
		box.setFont(new Font("",Font.BOLD,40));
		box.setBounds(200,550,500,100);
		box.setEditable(false);
		box.setBackground(Color.lightGray);
		add(box);
		
		guess = new JButton();
		guess.setBounds(700,550,100,100);
		guess.setBackground(Color.LIGHT_GRAY);
		guess.setFont(new Font("",Font.BOLD,50));
		guess.setText(Integer.toString(attempts));
		add(guess);
		
		MioAscoltatore guesser = new MioAscoltatore(frame,lista,box, blank,cflag, cname, this,data);	
		guess.addActionListener(guesser);

		filtro.getDocument().addDocumentListener(new DocumentListener() {

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
				box.removeAllItems();;
				for(File file:lista){
					if(file.getName().contains(filtro.getText())){
						box.addItem(file.getName().replaceFirst("[.][^.]+$", ""));
					}
				}
			}
			
		});
		
		home = new JButton();
		home.setBounds(0,0,200,50);
		home.setBackground(Color.DARK_GRAY);
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
		
		seltext = new JLabel();
		seltext.setBounds(200,650,300,100);
		seltext.setText("Selected difficulty: ");
		seltext.setHorizontalAlignment(JLabel.RIGHT);
		seltext.setFont(new Font("",Font.BOLD,30));
		add(seltext);
		
		seldiff= new JLabel();
		seldiff.setBounds(600,650,200,100);
		seldiff.setText(difficulty);
		seldiff.setHorizontalAlignment(JLabel.LEFT);
		seldiff.setFont(new Font("",Font.BOLD,30));
		add(seldiff);
		
		//theme
		if(theme.equals("dark")) {
			setBackground(Color.DARK_GRAY);
			home.setForeground(Color.white);
			home.setBackground(Color.darkGray);
			title.setForeground(Color.white);
			filtro.setBackground(Color.white);
			filtro.setForeground(Color.black);
			box.setBackground(Color.LIGHT_GRAY);
			box.setForeground(Color.black);
			guess.setBackground(Color.LIGHT_GRAY);
			guess.setForeground(Color.black);
			seltext.setForeground(Color.white);
		}
		else {
			setBackground(Color.lightGray);
			home.setForeground(Color.black);
			home.setBackground(Color.lightGray);
			title.setForeground(Color.black);
			filtro.setBackground(Color.darkGray);
			filtro.setForeground(Color.white);
			box.setBackground(Color.darkGray);
			box.setForeground(Color.white);
			guess.setBackground(Color.darkGray);
			guess.setForeground(Color.white);
			seltext.setForeground(Color.black);
		}
		if(difficulty.equals("easy")) {
			seldiff.setForeground(Color.green);
		}
		else if(difficulty.equals("medium")) {
			seldiff.setForeground(Color.yellow);
		}
		else if(difficulty.equals("hard")) {
			seldiff.setForeground(Color.orange);
		}
		else {
            seldiff.setForeground(Color.red);
        }
		//auto resize
		addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
            	home.setBounds(0,0,convwidth(200),convheight(50));
            	title.setBounds(convwidth(200),0,convwidth(600),convheight(100));
            	filtro.setBounds(convwidth(200),convheight(500),convwidth(600),convheight(50));
            	box.setBounds(convwidth(200),convheight(550),convwidth(500),convheight(100));
            	guess.setBounds(convwidth(700),convheight(550),convwidth(100),convheight(100));
            	seltext.setBounds(convwidth(200),convheight(650),convwidth(300),convheight(100));
            	seldiff.setBounds(convwidth(600),convheight(650),convwidth(200),convheight(100));
            }
        });
		home.setBounds(0,0,convwidth(200),convheight(50));
    	title.setBounds(convwidth(200),0,convwidth(600),convheight(100));
    	filtro.setBounds(convwidth(200),convheight(500),convwidth(600),convheight(50));
    	box.setBounds(convwidth(200),convheight(550),convwidth(500),convheight(100));
    	guess.setBounds(convwidth(700),convheight(550),convwidth(100),convheight(100));
    	seltext.setBounds(convwidth(200),convheight(650),convwidth(300),convheight(100));
    	seldiff.setBounds(convwidth(600),convheight(650),convwidth(200),convheight(100));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int width = convwidth(600);
        int height = convheight(400);
        g.drawImage(blank.getScaledInstance(width, height,Image.SCALE_FAST), convwidth(200), convheight(100), null);
		g.setColor(Color.white);
		g.drawRect(convwidth(200), convheight(100), width-convwidth(1), height-convheight(1));
		g.setColor(Color.black);
		g.drawRect(convwidth(201), convheight(101), width-convwidth(3), height-convheight(3));
		g.drawRect(convwidth(202), convheight(102), width-convwidth(5), height-convheight(5));
		g.drawRect(convwidth(203), convheight(103), width-convwidth(7), height-convheight(7));
		g.drawRect(convwidth(204), convheight(104), width-convwidth(9), height-convheight(9));
	}
	
	int convwidth(int size) {
		return (int) ((frame.getContentPane().getWidth()*size)/1000.0);
	}
	
	int convheight(int size) {
		return (int) ((frame.getContentPane().getHeight()*size)/900.0);
	}
	
	void setflag(Image flag) {
		this.blank=(BufferedImage) flag;
	}
	
	BufferedImage getFlag(File flagf) {
		
		System.out.println(flagf.getName());
		try {
			return ImageIO.read(flagf);
			
		} catch (IOException e) {}
		return null;
	}

	void initBlank(BufferedImage blank) {
		Color gray = new Color(170,170,170);
		for(int y=0;y<300;y++) {
			for(int x=0;x<600;x++) {
				blank.setRGB(x, y, gray.getRGB());
			}
		}
	}
}