/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import java.util.ResourceBundle.Control;

/**
 *
 * @author Adi
 */
public class SpaceshipControl extends AbstractControl {
    //fields
    private int health;
    private int attack;
    private int defence;
    private int speed;
	
    public SpaceshipControl() {
        
    }
    
    public void setSpatial(Spatial spatial) {
        super.setSpatial(spatial);
        if (spatial != null) {
            
        } else {
            
        }
    }
    
    @Override
    protected void controlUpdate(float tpf) {
       if (spatial != null) {
           
       }
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
       
    }
    
    @Override
    public SpaceshipControl cloneForSpatial(Spatial spatial){
        final SpaceshipControl control = new SpaceshipControl();
        /* Optional: use setters to copy userdata into the cloned control */
        // control.setIndex(i); // example
        control.setSpatial(spatial);
        return control;
    }
    
    public void left(float amt) {
       // spatial.rotate(xAngle, yAngle, zAngle)
    	spatial.rotate(0, 0, -amt*10);
    }
    
    public void right(float amt) {
        
    }
    
    public void up() {
        
    }
    
    public void down() {
        
    }
    
    public void accelerate() {
    	
    }
}
