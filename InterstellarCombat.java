/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import AppStates.InGameState;
import AppStates.MenuScreenState;
import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
/**
*
* @author Adi
*/
public class InterstellarCombat extends SimpleApplication {
    
    private InGameState inGameState;
    private MenuScreenState menuScreenState;
    
    @Override
    /**
     * Initializes states of the game starting with the menu screen
     */
    public void simpleInitApp() {
        inGameState = new InGameState();
        stateManager.attach(inGameState);
        
        Geometry geo = new Geometry("Box",new Box(1,1,1));
        Material mat_geo = new Material(assetManager,"Common/MatDefs/Misc/ShowNormals.j3md");
        geo.setMaterial(mat_geo);
        rootNode.attachChild(geo);
        geo.move(0, 0, 2);
        }
    
    /**
     * Updates the non user dependant features of the game
     */
    public void simpleUpdate() {
        
    }

}