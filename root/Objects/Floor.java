package root.Objects;

import root.RayMarching;
import root.Render.Ray;
import root.Render.Shader;
import root.Vector.vec3;

public class Floor extends Object {
	private double heigh;
	public Floor(vec3 position, double heigh){
		this.position=position;
		this.heigh=heigh;
	}
	
	@Override
	public double map(vec3 ray){
		return (ray.sub(position)).z()-heigh;
	}

	@Override
	public vec3 getShader(Ray ray_, int recDepth){
		vec3 ray=ray_.getRay();
		double fmap=this.map(ray);
		if(fmap<= RayMarching.getTrashHold()){
			vec3 texCol=new vec3(0.9,0.8,0.5);
			if(Math.abs(ray.x()*5)%10<5 == (Math.abs(ray.y()*5)%10>5)){
				texCol=new vec3(0.4, 0.3, 0.2);
			}
			return Shader.getShadowColor(texCol, ray_);
		}
		return null;
	}
	@Override
	public void updateAnimation(double time){

	}
}