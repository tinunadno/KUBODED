package root.Vector;

public class vec3 implements MathVectorMethods<vec3>{
	private double x;
	private double y;
	private double z;
	
	//constructors
	public vec3(){
		x=0;
		y=0;
		z=0;
	}
	public vec3(double val){
		x=val;
		y=val;
		z=val;
	}
	public vec3(double x, double y, double z){
		this.x=x;
		this.y=y;
		this.z=z;
	}
	public vec3(vec2 vec, double z){
		this.x=vec.x();
		this.y=vec.y();
		this.z=z;
	}
	public vec3(double x, vec2 vec){
		this.x=x;
		this.y=vec.x();
		this.z=vec.y();
	}
	//vector operations with other vector
	@Override
	public vec3 add(vec3 vec){return new vec3(this.x+vec.x(), this.y+vec.y(), this.z+vec.z());}
	@Override
	public vec3 sub(vec3 vec){return new vec3(this.x-vec.x(), this.y-vec.y(), this.z-vec.z());}
	
	//vector operation with scalar
	@Override
	public vec3 add(double value){return new vec3(this.x+value, this.y+value, this.z+value);}
	@Override
	public vec3 sub(double value){return new vec3(this.x-value, this.y-value, this.z-value);}
	@Override
	public vec3 mult(double value){return new vec3(this.x*value, this.y*value, this.z*value);}
	@Override
	public vec3 div(double value){return new vec3(this.x/value, this.y/value, this.z/value);}
	
	@Override
	public double length(){return Math.sqrt(x*x+y*y+z*z);}
	@Override
	public vec3 normalize(){return this.mult(invSqrt(x*x+y*y+z*z));}
	public vec3 abs(){return new vec3(Math.abs(this.x()), Math.abs(this.y()), Math.abs(this.z()));}
	
	private static double invSqrt(double x) {
		double xhalf = 0.5d * x;
		long i = Double.doubleToLongBits(x);
		i = 0x5fe6ec85e7de30daL - (i >> 1);
		x = Double.longBitsToDouble(i);
		x *= (1.5d - xhalf * x * x);
		return x;
	}
	
	//getters
	public double x(){return this.x;}
	public double y(){return this.y;}
	public double z(){return this.z;}
	
	//setters
	public void setX(double x){this.x=x;}
	public void setY(double y){this.y=y;}
	public void setZ(double z){this.z=z;}
}