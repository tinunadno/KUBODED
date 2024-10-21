package root.Screen;

import root.MathAdditions.mat;
import root.Vector.vec3;

import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
public class Screen{
	private JFrame frame;
	private int xSize;
	private int ySize;
	private BufferedImage img;
	private boolean closeOnExit;
	public Screen(int xSize, int ySize, boolean closeOnExit){
		this.xSize=xSize;
		this.ySize=ySize;
		this.closeOnExit=closeOnExit;
		frame=new JFrame();
		if(closeOnExit)
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		img = new BufferedImage(xSize, ySize, BufferedImage.TYPE_INT_RGB);
	}
	public void setCloseOnExit(boolean val){
		if(val) frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		else frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public void refresh(){
		img = new BufferedImage(xSize, ySize, BufferedImage.TYPE_INT_RGB);
	}
	
	private int colorPoint(vec3 color){
		return (int)(mat.clamp(0, 255, (int)color.x()))<<16 | (int)(mat.clamp(0, 255, (int)color.y()))<<8 | (int)(mat.clamp(0, 255, (int)color.z()));
	}
	
	public void setPixel(int x, int y, vec3 color){
		img.setRGB(x, ySize-y-1, colorPoint(color.mult(255)));
	}
	public void show(){
		frame.getContentPane().removeAll();
		frame.repaint();
		frame.getContentPane().add(new JLabel(new ImageIcon(img)));
		frame.pack();
		frame.repaint();
		frame.setVisible(true);
	}
}