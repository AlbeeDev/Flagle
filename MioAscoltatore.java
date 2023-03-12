import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


public class MioAscoltatore implements ActionListener{
	File[] lista = new File("src/flags").listFiles();
	JComboBox<String> cb;
	Image vflag,cflag;
	BufferedImage blank,cflagb,vflagb;
	String cname,vname;
	GamePanel p;
	Boolean end=false;
	JsonData data;
	public MioAscoltatore(JComboBox<String> cb,BufferedImage blank,BufferedImage cflag,String cname, GamePanel p, JsonData data) {
		this.cb=cb;
		this.blank=blank;
		this.cflag=cflag;
		this.cname=cname;
		this.p=p;
		this.data=data;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton b = (JButton) e.getSource();
		
		vname=(String) cb.getSelectedItem();
		vflag=getFlag(vname);
		if(cname.equals(vname+".png")) end=true;
		if(vflag==null) return;
		
		vflagb=new BufferedImage(600, 300, BufferedImage.TYPE_INT_RGB);
		vflagb.getGraphics().drawImage(vflag.getScaledInstance(600, 300, Image.SCALE_FAST),0,0,null);
		cflagb=new BufferedImage(600, 300, BufferedImage.TYPE_INT_RGB);
		cflagb.getGraphics().drawImage(cflag.getScaledInstance(600, 300, Image.SCALE_FAST),0,0,null);
		
		int temp=Integer.parseInt(b.getText());
		b.setText(Integer.toString(temp-1));
		if(temp==1) {
			if(data.getUserData().getDifficultyselect().equals("easy")) {
				data.getUserData().getDifficulty().getEasy().setLost(data.getUserData().getDifficulty().getEasy().getLost()+1);
			}
			else if(data.getUserData().getDifficultyselect().equals("medium")) {
				data.getUserData().getDifficulty().getMedium().setLost(data.getUserData().getDifficulty().getMedium().getLost()+1);
			}
			else {
				data.getUserData().getDifficulty().getHard().setLost(data.getUserData().getDifficulty().getHard().getLost()+1);
			}
			savematch();
			b.setBackground(Color.red);
			p.setflag(cflag);
			p.repaint();
			b.setEnabled(false);
			cb.setEnabled(false);
			if(!end) return;
		}
		
		for(int y=0;y<300;y++) {
			for(int x=0;x<600;x++) {
				
				Color vrgb = new Color(vflagb.getRGB(x, y));
				Color crgb = new Color(cflagb.getRGB(x, y));
				
				if(compareColors(vrgb, crgb,(double) 30)) {
					blank.setRGB(x, y, cflagb.getRGB(x, y));
				}
			}
		}
		
		p.setflag(blank);
		p.repaint();
		
		if(end) {
			if(data.getUserData().getDifficultyselect().equals("easy")) {
				data.getUserData().getDifficulty().getEasy().setWin(data.getUserData().getDifficulty().getEasy().getWin()+1);
			}
			else if(data.getUserData().getDifficultyselect().equals("medium")) {
				data.getUserData().getDifficulty().getMedium().setWin(data.getUserData().getDifficulty().getMedium().getWin()+1);
			}
			else {
				data.getUserData().getDifficulty().getHard().setWin(data.getUserData().getDifficulty().getHard().getWin()+1);
			}
			savematch();
			b.setEnabled(false);
			b.setBackground(Color.green);
			cb.setEnabled(false);
		}
		
	}
	
	void savematch() {
		try {
			ObjectMapper om = new ObjectMapper();
			om.enable(SerializationFeature.INDENT_OUTPUT);
			om.setDefaultPrettyPrinter(new DefaultPrettyPrinter());
			om.writeValue(new File("src/loader.json"), data);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	Image getFlag(String input) {
		File flagf= new File("");
		for(File f: lista) {
			if(f.getName().equals(input+".png")) {
				flagf=f;
			}
		}
		try {
			return ImageIO.read(flagf);
		} catch (IOException e) {}
		return null;
	}
	
	BufferedImage retrieveBlank(){
		return blank;
	}
	
	public static double[] rgbToLab(int r, int g, int b) {
	    double[] lab = new double[3];
	    
	    // Convert RGB to XYZ
	    double[] xyz = new double[3];
	    double rLinear = Math.pow(r / 255.0, 2.2);
	    double gLinear = Math.pow(g / 255.0, 2.2);
	    double bLinear = Math.pow(b / 255.0, 2.2);
	    xyz[0] = (0.4124 * rLinear + 0.3576 * gLinear + 0.1805 * bLinear) * 100;
	    xyz[1] = (0.2126 * rLinear + 0.7152 * gLinear + 0.0722 * bLinear) * 100;
	    xyz[2] = (0.0193 * rLinear + 0.1192 * gLinear + 0.9505 * bLinear) * 100;

	    // Convert XYZ to CIELAB
	    double x = xyz[0] / 95.047;
	    double y = xyz[1] / 100.000;
	    double z = xyz[2] / 108.883;

	    double fx = x > 0.008856 ? Math.pow(x, 1.0 / 3.0) : (7.787 * x + 16.0 / 116.0);
	    double fy = y > 0.008856 ? Math.pow(y, 1.0 / 3.0) : (7.787 * y + 16.0 / 116.0);
	    double fz = z > 0.008856 ? Math.pow(z, 1.0 / 3.0) : (7.787 * z + 16.0 / 116.0);

	    lab[0] = (116.0 * fy) - 16.0;
	    lab[1] = 500.0 * (fx - fy);
	    lab[2] = 200.0 * (fy - fz);

	    return lab;
	}
	
	public static boolean compareColors(Color c1, Color c2, double thresholdDistance) {
	    double[] lab1 = rgbToLab(c1.getRed(), c1.getGreen(), c1.getBlue());
	    double[] lab2 = rgbToLab(c2.getRed(), c2.getGreen(), c2.getBlue());
	    double distance = Math.sqrt(Math.pow(lab1[0] - lab2[0], 2) + Math.pow(lab1[1] - lab2[1], 2) + Math.pow(lab1[2] - lab2[2], 2));
	    return distance <= thresholdDistance;
	}

}