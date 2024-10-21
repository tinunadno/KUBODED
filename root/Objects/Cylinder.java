package root.Objects;

import root.MathAdditions.mat;
import root.RayMarching;
import root.Render.Ray;
import root.Render.Shader;
import root.Vector.vec2;
import root.Vector.vec3;

public class Cylinder extends Object {
	private double radius;
	private double heigh;
	//rotation along Y axis params PS Im to lazy to do normal Q rotation, if u 'wanna fix it
	private vec2 yRot;
	private double tetta=0;
	//====================
	
	public Cylinder(vec3 position, double radius, double heigh){
		this.position=position;
		this.radius=radius;
		this.heigh=heigh;
		yRot=new vec2(0, 1);
	}
	
	@Override
	public double map(vec3 ray){
		ray=ray.sub(position);
		ray=new vec3(yRot.x()*ray.x()-yRot.y()*ray.z(), ray.y(), yRot.y()*ray.x()+yRot.x()*ray.z());
		vec2 d =new vec2(mat.length(ray.x(), ray.y())-radius, Math.abs(ray.z())-heigh);
		return Math.min(Math.max(d.x(), d.y()), 0.0)+mat.clamp(0, Integer.MAX_VALUE ,d).length();
	}

	@Override
	public vec3 getShader(Ray ray_, int recDepth){
		vec3 ray=ray_.getRay();
		double smap1=this.map(ray);
		if(smap1<= RayMarching.getTrashHold()){
			return Shader.getShadowColor(Shader.mirrorRay(ray, this.getPosition(), recDepth), ray_);
		}
		return null;
	}
	@Override
	public void updateAnimation(double time){
		this.setYRotation((Math.PI/4)*RayMarching.getTime());
	}
	public double getRadius(){return radius;}
	
	public void setPosition(vec3 position){this.position=position;}
	public void setRadius(double radius){this.radius=radius;}
	public void setYRotation(double angle){
		tetta=angle;
		yRot=new vec2(Math.cos(tetta), Math.sin(tetta));
	}
}