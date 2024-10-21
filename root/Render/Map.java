package root.Render;

import root.Objects.Cube;
import root.Objects.Cylinder;
import root.Objects.Floor;
import root.Objects.Object;
import root.RayMarching;
import root.Objects.Sphere;
import root.Vector.vec3;
import root.MathAdditions.mat;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Map{
	private static ArrayList<Object> objects=new ArrayList<>();
	private static double scale=2;
	private static BufferedImage img=null;
	static{
		try{
			img = ImageIO.read(new File("./root/Render/SKY_CUBODED.jpg"));
		}
		catch(Exception e){
			System.out.println("No texture found0!");
			System.exit(0);
		}
	}
	//==============
	//light sourve vector
	private static vec3 lightSource=new vec3(2,5, 6);
	//===================
	//its calling fom RayMarching class, changing fields, depends on time or frame
	public static void upgradeMap(){
		for(Object obj:objects){
			obj.updateAnimation(RayMarching.getTime());
		}
	}
	public static double map(vec3 ray){
		double min=10000000;
		for(Object obj:objects){
			min=Math.min(obj.map(ray), min);
		}
		return min;
	}
	//shader block, ill do this better later
	public static vec3 colorMap(Ray ray_, int recDepth){
		if(recDepth>5){return new vec3();}
		vec3 ray=ray_.getRay();

		for(Object obj:objects){
			vec3 color=obj.getShader(ray_, recDepth);
			if(color!=null)return color;
		}

		double yAngle=mat.getAngle(ray.x(), ray.y())*5+Math.PI;
		double xAngle=Math.PI-mat.getAngle(mat.length(ray.x(), ray.y()), ray.z())*5-0.3;
		return mat.intToRgb(img.getRGB(Math.abs(((int)(yAngle/Math.PI*img.getWidth()))%(img.getWidth()-1)), Math.abs(((int)(xAngle/(Math.PI)*(img.getHeight()-1)))%(img.getHeight()-1))));
	}

	public static vec3 getLightSource(){return lightSource;}
	public static void addObject(Object obj){
		objects.add(obj);
	}

	//=================================
}