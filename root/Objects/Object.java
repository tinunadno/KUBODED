package root.Objects;

import root.Render.Ray;
import root.Vector.vec3;

public abstract class Object{
	protected vec3 position;
	
	public abstract double map(vec3 map);
	public vec3 getPosition(){return position;}
	public abstract vec3 getShader(Ray ray, int recDepth);
	public abstract void updateAnimation(double time);
}