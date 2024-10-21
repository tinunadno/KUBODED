package root.Vector;

public class vec2 implements MathVectorMethods<vec2>{
	private double x;
	private double y;
	
	//constructors
	public vec2(){
		x=0;
		y=0;
	}
	public vec2(double val){
		x=val;
		y=val;
	}
	public vec2(double x, double y){
		this.x=x;
		this.y=y;
	}
	//vector operations with other vector
	@Override
	public vec2 add(vec2 vec){return new vec2(this.x+vec.x(), this.y+vec.y());}
	@Override
	public vec2 sub(vec2 vec){return new vec2(this.x-vec.x(), this.y-vec.y());}
	
	//vector operations with scalar
	@Override
	public vec2 add(double value){return new vec2(this.x+value, this.y+value);}
	@Override
	public vec2 sub(double value){return new vec2(this.x-value, this.y-value);}
	@Override
	public vec2 mult(double value){return new vec2(this.x*value, this.y*value);}
	@Override
	public vec2 div(double value){return new vec2(this.x/value, this.y/value);}
	
	//I made it just for uv, do not use in calculations, this function !!IGNORE MATH!!
	public vec2 divide(vec2 vec){
		return new vec2(this.x/vec.x(), this.y/vec.y());
	}
	//===========================
	
	@Override
	public double length(){return Math.sqrt(x*x+y*y);}
	@Override
	public vec2 normalize(){return this.div(this.length());}
	public vec2 abs(){return new vec2(Math.abs(this.x()), Math.abs(this.y()));}

	
	//getters
	public double x(){return this.x;}
	public double y(){return this.y;}
	
	//setters
	public void setX(double x){this.x=x;}
	public void setY(double y){this.y=y;}
}