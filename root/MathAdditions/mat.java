package root.MathAdditions;

import root.Vector.vec2;
import root.Vector.vec3;

public class mat{
	//PS I made this class, 'cuz I ain't sure all Math methods works properly
	
	//return ranged value in [start; end]
	public static double clamp(double min, double max, double value){
		if(value<min)return min;
		if(value>max)return max;
		else return value;
	}
	
	public static double mix(double x, double y, double k){
		return x*(1.0-k)+y*k;
	}
	
	public static vec3 clamp(double min, double max, vec3 vec){
		return new vec3(clamp(min, max, vec.x()), clamp(min, max, vec.y()), clamp(min, max, vec.z()));
	}
	
	public static vec2 clamp(double min, double max, vec2 vec){
		return new vec2(clamp(min, max, vec.x()), clamp(min, max, vec.y()));
	}
	
	public static vec3 mixColor(vec3 col1, vec3 col2, double k){
		return new vec3(mix(col1.x(), col2.x(), k), mix(col1.y(), col2.y(), k), mix(col1.z(), col2.z(), k));
	}
	
	public static vec3 rotateVector(vec3 vec, double tetta){
		vec2 rotVec1=new vec2(Math.cos(tetta), Math.sin(tetta));
		vec=new vec3(vec.x()*rotVec1.x()-vec.y()*rotVec1.y(), vec.x()*rotVec1.y()+vec.y()*rotVec1.x(), vec.z());
		return vec;
	}
	
	public static double smin(double a, double b, double k){
		double h=clamp(0.0, 1.0, 0.5+0.5*(a-b)/k);
		return mix(a, b, h)-k*h*(1.0-h);
	}
	
	public static vec3 intToRgb(int value){

		return (new vec3((value>>16)&0xFF, (value>>8)&0xFF, (value)&0xFF)).div(255);

	}
	public static double length(double a, double b){
		return Math.sqrt(a*a+b*b);
	}
	
	public static double getAngle(double real, double imag){
		double tetta=Math.atan(imag/real);
		if(real>0)
			return tetta;
		else
			return tetta+Math.PI;
	}
}