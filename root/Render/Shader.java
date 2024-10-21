package root.Render;

import root.MathAdditions.mat;
import root.RayMarching;
import root.Vector.vec3;

public class Shader {
     public static vec3 getShadowColor(vec3 outVec, Ray ray_){
        ray_.setOrigin(ray_.getRay().add(new vec3(Math.random()/50)));
        ray_.setRotation(Map.getLightSource().sub(ray_.getOrigin()).normalize().mult(0.01));
        ray_.march();
        if((Map.map(ray_.getRay()))<= RayMarching.getTrashHold())outVec= mat.mixColor(outVec, new vec3(), 0.5);
        return outVec;
    }

    public static vec3 mirrorRay(vec3 ray, vec3 objectPos, int recDepth){
        Ray ray1=(new Ray(ray, ray.sub(objectPos).normalize()));
        ray1.march();
        return Map.colorMap(ray1, recDepth+1);
    }
}
