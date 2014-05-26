package mygame;

import javax.swing.JOptionPane;

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
    
    @Override
    /**
     * Initializes states of the game starting with the menu screen
     */
    public void simpleInitApp() {
        inGameState = new InGameState();
        stateManager.attach(inGameState);
    }
    
    /**
     * Updates the non user dependant features of the game
     */
    public void simpleUpdate() {
        
    }

}