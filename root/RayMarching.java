package root;

import root.Objects.Cube;
import root.Objects.Cylinder;
import root.Objects.Floor;
import root.Objects.Sphere;
import root.Render.Map;
import root.Screen.Screen;
import root.Screen.ScreenThread;
import root.Vector.vec2;
import root.Vector.vec3;

public class RayMarching{
	//this class just a set up, 'callin mainImage func
	private static final vec2 RESOLUTION=new vec2(700, 400);
	private static final double CORRECT=RESOLUTION.x()/RESOLUTION.y();
	private static final double LOW_MARCH_TRASH_HOLD=0.001;
	private static final int THREAD_COUNT=8;
	private static double time=0;
	private static double prevFrameTime=0;
	private static double start;
	private static int frame=0;
	private static int workingThreadCount=0;
	private static Screen scrn=new Screen((int)RESOLUTION.x(), (int)RESOLUTION.y(), true);
	public static void main(String[] args){
		Cube cub=new Cube(new vec3(), 0.8);
		Floor flr=new Floor(new vec3(), -2);
		Map.addObject(cub);
		Map.addObject(flr);
		start=System.currentTimeMillis();
		renderFrame();
	}
	
	private static void renderFrame(){
		frame++;
		scrn.refresh();
		for(int i=0;i<THREAD_COUNT/2;i++)
			for(int j=0;j<THREAD_COUNT/2;j++){
				workingThreadCount++;
				ScreenThread scThr=new ScreenThread(new vec2(RESOLUTION.x()/(THREAD_COUNT/2)*j, RESOLUTION.y()/(THREAD_COUNT/2)*i), new vec2(RESOLUTION.x()/(THREAD_COUNT/2)*(j+1), RESOLUTION.y()/(THREAD_COUNT/2)*(i+1)));
				scThr.start();
			}
	}
	private static void showRenderedFrame(){
		scrn.show();
		time=System.currentTimeMillis()-start;
		Map.upgradeMap();
		System.out.println("["+(int)(1/(getTime()-prevFrameTime))+" fps]["+frame+" total frames]["+getTime()+" total time]");
		renderFrame();
		prevFrameTime=getTime();
	}
	public static void finishThread(){
		workingThreadCount--;
		if(workingThreadCount==0)showRenderedFrame();
	}
	
	public static void setPixel(int x, int y, vec3 col){scrn.setPixel(x, y, col);}
	public static vec2 getSceneResolution(){return RESOLUTION;}
	public static double getCorrect(){return CORRECT;}
	public static double getTime(){return time/1000.0;}
	public static double getFrame(){return frame;}
	public static double getTrashHold(){return LOW_MARCH_TRASH_HOLD;}
}