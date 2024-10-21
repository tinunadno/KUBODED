package root.Objects;

import root.Objects.Object;
import root.RayMarching;
import root.Render.Ray;
import root.Render.Shader;
import root.Vector.vec3;

public class Sphere extends Object {
	private double radius;
	
	public Sphere(vec3 position, double radius){
		this.position=position;
		this.radius=radius;
	}
	@Override
	public double map(vec3 ray){
		return (ray.sub(position)).length()-radius;
	}

	@Override
	public vec3 getShader(Ray ray_, int recDepth){
		vec3 ray=ray_.getRay();
		double smap=this.map(ray);
		if(smap< RayMarching.getTrashHold()) {
			return Shader.getShadowColor(Shader.mirrorRay(ray, this.getPosition(), recDepth), ray_);
		}
		return null;
	}
	@Override
	public void updateAnimation(double time){
		this.setPosition(new vec3(Math.sin(RayMarching.getTime())/2, Math.abs((RayMarching.getTime()%10.0-5.0))-2.5, Math.sin(RayMarching.getTime())));
	}
	public double getRadius(){return radius;}
	
	public void setPosition(vec3 position){this.position=position;}
	public void setRadius(double radius){this.radius=radius;}
}