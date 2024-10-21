package root.Render;

import root.RayMarching;
import root.Vector.vec2;
import root.Vector.vec3;

public class Iteration{
	public static vec3 mainImage(vec2 coords){
		vec3 outColor=new vec3(1);
		
		//normalizing uv
		vec2 uv=coords.divide(RayMarching.getSceneResolution()).sub(0.5).mult(2);
		uv.setX(uv.x()*RayMarching.getCorrect());
		//==============
		
		//camera vectors
		vec3 origin=new vec3(-2.5, 0, 1.9);
		vec3 rot=new vec3(-1.7, 0, 1.4);
		//==============
		
		Ray ray=new Ray(origin, rot.sub(origin).add(new vec3(0, uv)).normalize());
		//double rayDistance=
		ray.march();
		
		//if(rayDistance<=Main.getTrashHold())
		outColor=Map.colorMap(ray, 0);
		//if(rayDistance<=Main.getTrashHold())outColor=new vec3(0.5);
		
		return outColor;
	}
}