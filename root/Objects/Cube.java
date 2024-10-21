package root.Objects;

import root.MathAdditions.mat;
import root.RayMarching;
import root.Render.Map;
import root.Render.Ray;
import root.Render.Shader;
import root.Vector.vec2;
import root.Vector.vec3;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.nio.Buffer;

public class Cube extends Object {
	private double size;
	//rotation along Z axis params PS Im to lazy to do normal rotation
	private vec2 zRot;
	private BufferedImage texture;
	private double tetta=0;
	//====================
	
	public Cube(vec3 position, double size){
		try{
			texture = ImageIO.read(new File("./root/Render/CUBODED.png"));
		}
		catch(Exception e){
			System.out.println("No texture found_!");
			System.exit(0);
		}
		this.position=position;
		this.size=size;
		zRot=new vec2(0, 1);
	}
	@Override
	public double map(vec3 ray){
		ray=new vec3(zRot.x()*ray.x()-zRot.y()*ray.y(), zRot.y()*ray.x()+zRot.x()*ray.y(), ray.z());
		vec3 d=new vec3(Math.abs(ray.x()-position.x())-size, Math.abs(ray.y()-position.y())-size, Math.abs(ray.z()-position.z())-size);
		return Math.min(Math.max(d.x(), Math.max(d.y(), d.z())), 0.0)+(mat.clamp(0, Integer.MAX_VALUE, d)).length();
	}

	@Override
	public vec3 getShader(Ray ray_, int recDepth){
		vec3 ray=ray_.getRay();
		double map= this.map(ray);
		if(RayMarching.getTrashHold()>map) {
			ray=ray.sub(position);
			ray=new vec3(zRot.x()*ray.x()-zRot.y()*ray.y(), zRot.y()*ray.x()+zRot.x()*ray.y(), ray.z());
			//i don't know, why texture doesn't fits without this cofficients
			double xAngle = mat.getAngle(ray.x(), ray.y())+Math.PI/2;
			double y=((size-(ray.z()-0.05+size/2))/size)/2.65+size*1.19;

			double x=(xAngle/Math.PI*texture.getWidth())/2+texture.getWidth()/8;
			if(ray.z()-0.4>size/2){
				x=(ray.x()-0.3-size/2)/size*texture.getWidth()/8;
				y=(ray.y()-0.3-size/2)/size*Math.PI/8;
			}
			vec3 textureColor= mat.intToRgb(texture.getRGB(Math.abs(((int) (x )) % (texture.getWidth())), Math.abs(((int) (y / (Math.PI) * (texture.getWidth()))) % (texture.getHeight()))));

			vec3 mirroredColor=Shader.mirrorRay(ray, this.getPosition(), recDepth);
			return Shader.getShadowColor(mat.mixColor(textureColor, mirroredColor, 0.1),ray_);
		}
		return null;
	}
	@Override
	public void updateAnimation(double time){
		this.setZRotation((Math.PI/4)*RayMarching.getTime());
	}
	public double getSize(){return size;}
	public double getZAngle(){return tetta;}
	
	public void setPosition(vec3 position){this.position=position;}
	public void setSize(double size){this.size=size;}
	public void setZRotation(double angle){
		tetta=angle;
		zRot=new vec2(Math.cos(tetta), Math.sin(tetta));
	}
}