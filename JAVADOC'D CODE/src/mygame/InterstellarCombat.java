/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import AppStates.InGameState;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
/**
*
* @author Adi Zimmerman
* The SimpleApplication class needed for the JMonkeyEngine
*/
public class InterstellarCombat extends SimpleApplication {
    
    private InGameState inGameState;
    
    @Override
    /**
     * Initializes states of the game starting with the menu screen
     */
    public void simpleInitApp() {
        inGameState = new InGameState();
        stateManager.attach(inGameState);

        Geometry geo = new Geometry("Box", new Box(1, 1, 1));
        geo.setMaterial(new Material(assetManager, "Common/MatDefs/Misc/ShowNormals.j3md"));
        rootNode.attachChild(geo);
        geo.move(0, 0, 2);
        }
    
    /**
     * Updates the non user dependant features of the game
     */
    public void simpleUpdate() {
        
    }

}