package root.Screen;

import root.RayMarching;
import root.Render.Iteration;
import root.Vector.vec2;

public class ScreenThread extends Thread{
	private vec2 start;
	private vec2 end;
	public ScreenThread(vec2 start, vec2 end){
		this.start=start;
		this.end=end;
	}
	@Override
	public void run(){
		for(int i=(int)(start.y());i<end.y();i++)
			for(int j=(int)(start.x());j<end.x();j++)
				RayMarching.setPixel(j, i, Iteration.mainImage(new vec2(j, i)));
		RayMarching.finishThread();
	}
}