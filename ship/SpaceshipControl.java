/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ship;

import AppStates.InGameState;

import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.KeyTrigger;
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
    private boolean big;
    
    private RigidBodyControl ship_phy;

    public SpaceshipControl(boolean b, RigidBodyControl rgc) {
        big = b;
        if (big)
        {
        	speed = 5;
        }
        else
        {
        	speed = 10;
        }
        ship_phy = rgc;
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
        final SpaceshipControl control = new SpaceshipControl(big, new RigidBodyControl(1f));
        /* Optional: use setters to copy userdata into the cloned control */
        // control.setIndex(i); // example
        control.setSpatial(spatial);
        return control;
    }
    
    public boolean getSize()
    {
    	return big;
    }
    
    public int getSpeed()
    {
    	return speed;
    }
    
    public RigidBodyControl getRBC() {
    	return ship_phy;
    }
    
    public void setRBC(RigidBodyControl rbc) {
    	ship_phy = rbc;
    }
    
    public Spatial getSpatial() {
    	return spatial;
    }
}
