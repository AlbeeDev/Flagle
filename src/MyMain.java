import javax.swing.JFrame;




public class MyMain {
	
	public static void main(String[] args) {
		
		
		JFrame frame = new JFrame();
		frame.setTitle("Flagle 1.0.1");
		frame.setSize(1000,900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		MainPanel mainp = new MainPanel(frame);
		frame.add(mainp);
	

		frame.setVisible(true);
	}
	
	

}