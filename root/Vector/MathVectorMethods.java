package root.Vector;

public interface MathVectorMethods<T>{
	//add vector to vector
	public T add(T vec);
	//substract vector from vector
	public T sub(T vec);
	//add value to all vectors coordinates
	public T add(double val);
	//substract value to all vectors coordinates
	public T sub(double val);
	//multiply vector by scalar
	public T mult(double val);
	//divide vector by scalar
	public T div(double val);
	//get vector length
	public double length();
	//get normalized vector
	public T normalize();
}