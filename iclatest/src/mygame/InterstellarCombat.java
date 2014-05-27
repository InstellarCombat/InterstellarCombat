package mygame;

import java.io.*;

import info.BulletInfo;
import info.GameInfo;
import info.SetupInfo;
import info.ShipInfo;
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
    	GameInfo info = w.getInfo();
    	while (!info.start()) {
    		try {
				Thread.sleep(100);
			} catch (InterruptedException e) {}
    	}
    	handler.send(new SetupInfo(info.isBigShip(), info.getStartCoords()));
        inGameState = new InGameState();
        stateManager.attach(inGameState);
        
        Geometry geo = new Geometry("Box", new Box(1, 1, 1));
        geo.setMaterial(new Material(assetManager, "Common/MatDefs/Misc/ShowNormals.j3md"));
        rootNode.attachChild(geo);
        geo.move(0, 0, 2);
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
		if (s instanceof SetupInfo) {
			inGameState.setupGame((SetupInfo)s);
		} else if (s instanceof ShipInfo) {
			inGameState.updateOtherCoords((ShipInfo)s);
		} else if (s instanceof BulletInfo) {
			inGameState.addBullet((BulletInfo)s);
		}
	}

}