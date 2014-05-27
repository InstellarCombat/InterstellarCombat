package mygame;

import java.io.*;

import interfaces.*;
import AppStates.InGameState;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
/**
*
* @author Adi
*/
public class InterstellarCombat extends SimpleApplication implements NetworkGUI {
    
    private InGameState inGameState;
    private NetworkHandler handler;
    private Main w;
    
    @Override
    /**
     * Initializes states of the game starting with the menu screen
     */
    public void simpleInitApp() {
        inGameState = new InGameState();
        stateManager.attach(inGameState);
    }
    
    public void setMain (Main m) {
    	w = m;
    }
    
    public Main getMain() {
    	return w;
    }
    
    public InGameState getGameState () {
    	return inGameState;
    }
    
    public NetworkHandler getHandler () {
    	return handler;
    }
    public void setHandler (NetworkHandler nh) {
    	handler = nh;
    }
    
    /**
     * Updates the non user dependant features of the game
     */
    public void simpleUpdate() {
        
    }

	@Override
	public void receive(Serializable s) {
		
	}

}