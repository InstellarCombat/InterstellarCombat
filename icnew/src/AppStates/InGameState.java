/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AppStates;

import java.awt.Color;

import mygame.SpaceshipControl;

import com.bulletphysics.collision.shapes.CollisionShape;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CompoundCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.control.VehicleControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.input.ChaseCamera;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.CameraControl.ControlDirection;
import com.jme3.scene.shape.Sphere;
import com.jme3.scene.shape.Sphere.TextureMode;
import com.jme3.util.SkyFactory;

/**
 *
 * @author Adi
 */
public class InGameState extends AbstractAppState {
    private AppStateManager stateManager;
    private SimpleApplication app;
    private Node              rootNode;
    private AssetManager      assetManager;
    private InputManager      inputManager;
    private BulletAppState    shipPhysics;
    private BulletAppState    bulletPhysics;
  
    // In game fields
    private RigidBodyControl  ship_phy;
    private SpaceshipControl  ship_control;
    private Spatial ship;
    private CameraNode cam;
    
    // bullets
    private RigidBodyControl  bullet_phy;
    private Sphere bullet;
    private Material matBullet;
    
    
    
    @Override
    /**
     * This method initializes the inGameState, including Keybindings, Spaceships, Light,
     * Sky, Camera, and GUI features that the player see's.
     * 
     * @param stateManager - manages the states of the game, used internally
     * @param app - running application, used internally to start game
     */
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app); 
        this.app = (SimpleApplication)app;
        this.rootNode = this.app.getRootNode();
        this.assetManager = this.app.getAssetManager();
        this.inputManager = this.app.getInputManager();
        this.shipPhysics = new BulletAppState();
        this.bulletPhysics = new BulletAppState();
        stateManager.attach(shipPhysics);
        stateManager.attach(bulletPhysics);
        initKeys();
        initSpaceship();
        initDirectionalLight();
        initSky();
        initChaseCam();
        ship_phy.setGravity(new Vector3f(0, 0, 0));
    }
    
    @Override
    /**
     * This method detaches everything from the inGameState before exiting the state
     */
    public void cleanup() {
      super.cleanup();
      // unregister all my listeners, detach all my nodes, etc...
      rootNode.detachAllChildren();
      inputManager.clearMappings();
    }
    
    @Override
    /**
     * This method enables the state or disables it
     * @param enabled - whether the state is enabled or not
     */
    public void setEnabled(boolean enabled) {
      // Pause and unpause
      super.setEnabled(enabled);
      if(enabled){
        // init stuff that is in use while this state is RUNNING
        
      } else {
        // take away everything not needed while this state is PAUSED
        
      }
    }
    
    @Override
    /**
     * This method updates the non-user dependent features of the state
     * @param tpf
     */
    public void update(float tpf) {
      // do the following while game is RUNNING
      // modify scene graph...
      // call some methods...

    }
    
    /**
     * This method initializes the keybindings used by player to control the spaceship
     */
    public void initKeys() {
        inputManager.addMapping("Pause", new KeyTrigger(KeyInput.KEY_P));
        inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_LEFT));
        inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_RIGHT));
        inputManager.addMapping("Accelerate", new KeyTrigger(KeyInput.KEY_UP));
        inputManager.addMapping("Decelerate", new KeyTrigger(KeyInput.KEY_DOWN));
        inputManager.addMapping("Up", new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("Down", new KeyTrigger(KeyInput.KEY_Z));
        inputManager.addMapping("Shoot", new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addMapping("Emergency", new KeyTrigger(KeyInput.KEY_E));
        
        // Add the names to the action listener.
        inputManager.addListener(actionListener, "Pause", "Shoot");
        inputManager.addListener(analogListener, "Left", "Right", "Accelerate", "Decelerate", "Up", "Down", "Emergency");
    }
    
    private ActionListener actionListener = new ActionListener() {
    public void onAction(String name, boolean keyPressed, float tpf) {
      if (name.equals("Shoot") && !keyPressed) {
    	  System.out.println("SHOOT");
          shoot();
      }
    }
  };
 
  private AnalogListener analogListener = new AnalogListener() {
    public void onAnalog(String name, float value, float tpf) {
     
        if (name.equals("Accelerate")) {
        
        	accelerateShip();
        	
        }
        if (name.equals("Decelerate"))
        {
        	decelerateShip();
        }
        if (name.equals("Left")) {
        	moveLeft();
        }
        if (name.equals("Right")) {
        	moveRight();
        }
        if (name.equals("Up")) {
        	moveUp();
        }
        if (name.equals("Down")) {
            moveDown();
        }
        if (name.equals("Emergency")) {
        	clear();
        }
  
    }
  };
  
  private void initSpaceship() {
      ship = assetManager.loadModel("Models/space_frigate_63DS/space_frigate_6.j3o");
      //com.jme3.bullet.collision.shapes.CollisionShape shipShape = CollisionShapeFactory.createDynamicMeshShape(ship);
      //ship_phy =new RigidBodyControl(shipShape,2000);
      ship_phy = new RigidBodyControl(1.0f);
      ship_control = new SpaceshipControl();
      //ship_phy.setGravity(new Vector3f(0, 0, 0));
      ship.addControl(ship_phy);
      ship.addControl(ship_control);
      shipPhysics.getPhysicsSpace().add(ship_phy);
      rootNode.attachChild(ship);
     
  
  }
  
  private void initDirectionalLight() {
      DirectionalLight sun = new DirectionalLight();
      sun.setDirection(new Vector3f(-0.1f, -0.7f, -1.0f));
      rootNode.addLight(sun);
  }
  
  private void initCrosshairs() {
    app.getGuiNode().detachAllChildren();
    BitmapFont myFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
    BitmapText ch = new BitmapText(myFont, false);
    ch.setSize(myFont.getCharSet().getRenderedSize() * 2);
    ch.setText("+");        // fake crosshairs :)
    ch.setLocalTranslation( // center
    app.getContext().getSettings().getWidth() / 2 - myFont.getCharSet().getRenderedSize() / 3 * 2,
    app.getContext().getSettings().getHeight() / 2 + ch.getLineHeight() / 2, 0);
    app.getGuiNode().attachChild(ch);
  }
  
  private void prepareBullet() {
	  bullet = new Sphere(32, 32, 0.4f, true, false);
	  bullet.setTextureMode(TextureMode.Projected);
	  Geometry ball_geo = new Geometry("bullet", bullet);
	  matBullet = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
	  ball_geo.setMaterial(matBullet);
	  rootNode.attachChild(ball_geo);
	  
	  /** Position the cannon ball  */
	    ball_geo.setLocalTranslation(ship.getLocalTranslation());
	    /** Make the ball physcial with a mass > 0.0f */
	    bullet_phy = new RigidBodyControl(1f);
	    
	    /** Add physical ball to physics space. */
	    ball_geo.addControl(bullet_phy);
	    bulletPhysics.getPhysicsSpace().add(bullet_phy);
	    
	    bullet_phy.setGravity(new Vector3f(0,0,0));
	    
  }
  public void shoot() {
	  /** Create a cannon ball geometry and attach to scene graph. */
	 
	    prepareBullet();
	    
	    /** Accelerate the physcial ball to shoot it. */
	    bullet_phy.setLinearVelocity(new Vector3f(-1,0,0).mult(200));
  }
  
  private void initChaseCam() {
    app.getFlyByCamera().setEnabled(false);
    CameraNode camNode = new CameraNode("CamNode",app.getCamera());
    camNode.setControlDir(ControlDirection.SpatialToCamera);
    ((Node)ship).attachChild(camNode);
    camNode.setLocalTranslation(new Vector3f(30, 10, 0));
    camNode.lookAt(ship.getLocalTranslation(), Vector3f.UNIT_Y); 
    
    cam = camNode;
  }
  
  private void initSky() {
	  rootNode.attachChild(SkyFactory.createSky(
	            assetManager, "Textures/Sky/Bright/BrightSky.dds", false));
  }
  
  /**
   * Accelerates the ship
   */
  public void accelerateShip() {
	  Vector3f direction=new Vector3f(-15f,0,0);
	  ship_phy.getPhysicsRotation().multLocal(direction);
	  ship_phy.applyCentralForce(direction);
	//ship_phy.applyCentralForce(new Vector3f(-10,0,0));
  }
  
  /**
   * Brake function for the ship
   */
  public void decelerateShip() {
	  Vector3f direction=new Vector3f(15f,0,0);
	  ship_phy.getPhysicsRotation().multLocal(direction);
	  ship_phy.applyCentralForce(direction);
	//ship_phy.applyCentralForce(new Vector3f(10,0,0));
  }
  
  /** 
   * Rotates the ship up
   */
  public void rotateUp() {
	  ship_phy.applyImpulse(new Vector3f(0,.005f,0), Vector3f.UNIT_X);
  }
  
  public void moveUp() {
	  Vector3f direction=new Vector3f(0,15f,0);
	  ship_phy.getPhysicsRotation().multLocal(direction);
	  ship_phy.applyCentralForce(direction);
	//ship_phy.applyCentralForce(new Vector3f(10,0,0));
  }
  
  /**
   * Rotates the ship down
   */
  public void rotateDown() {
	  ship_phy.applyImpulse(new Vector3f(0,-.005f,0), Vector3f.UNIT_X);
  }
  
  public void moveDown() {
	  Vector3f direction=new Vector3f(0,-15f,0);
	  ship_phy.getPhysicsRotation().multLocal(direction);
	  ship_phy.applyCentralForce(direction);
	//ship_phy.applyCentralForce(new Vector3f(10,0,0));
  }
  
  /**
   * Rotates the ship left
   */
  public void rotateLeft() {
	  ship_phy.applyImpulse(new Vector3f(0,0,.005f), Vector3f.UNIT_Y);
  }
  
  public void moveLeft() {
	  Vector3f direction=new Vector3f(0,0,15f);
	  ship_phy.getPhysicsRotation().multLocal(direction);
	  ship_phy.applyCentralForce(direction);
	//ship_phy.applyCentralForce(new Vector3f(10,0,0));
  }
  
  /** 
   * Rotates the ship right
   */
  public void rotateRight() {
	  ship_phy.applyImpulse(new Vector3f(0,0,-.005f), Vector3f.UNIT_Y);
  }
  
  public void moveRight() {
	  Vector3f direction=new Vector3f(0,0,-15f);
	  ship_phy.getPhysicsRotation().multLocal(direction);
	  ship_phy.applyCentralForce(direction);
	//ship_phy.applyCentralForce(new Vector3f(10,0,0));
  }
  
  /** 
   * Sets velocity of ship to 0
   */
  public void clear() {
	  ship_phy.setLinearVelocity(new Vector3f(0, 0, 0));
	  ship_phy.setAngularVelocity(new Vector3f(0, 0, 0));
  }
  
}
