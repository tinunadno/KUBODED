package root.Render;

import root.RayMarching;
import root.Vector.vec3;

public class Ray{
	private final static int iterCount=80;
	private vec3 origin;
	private vec3 rot;
	
	public Ray(vec3 origin, vec3 rot){
		this.origin=origin;
		this.rot=rot;
	}
	//!!!WARNING!!! if u 'wanna do few close to camera objects(not difficult scene) remove normalize from line 20
	public void march(){
		for(int i=0;i<iterCount;i++){
			double mappedRay=Map.map(this.getRay());
			if(mappedRay< RayMarching.getTrashHold() || mappedRay>1000)break;
			this.setRotation(this.getRotation().add(this.getRotation().normalize().mult(mappedRay)));
			//if(this.getRay().length()>30)break;
		}
		//return Map.map(this.getRay());
	}
	
	public void setOrigin(vec3 origin){this.origin=origin;}
	public void setRotation(vec3 rot){this.rot=rot;}
	
	public vec3 getOrigin(){return this.origin;}
	public vec3 getRotation(){return this.rot;}
	public vec3 getRay(){return this.origin.add(rot);}
}