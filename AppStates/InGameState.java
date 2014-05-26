/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AppStates;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import projectiles.DamageBullet;
import projectiles.Projectile;
import projectiles.SpeedBullet;
import ship.PlayerSpaceship;
import ship.SpaceshipControl;

import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CompoundCollisionShape;
import com.jme3.bullet.collision.shapes.SphereCollisionShape;
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
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.BillboardControl;
import com.jme3.scene.control.CameraControl.ControlDirection;
import com.jme3.scene.shape.Quad;
import com.jme3.scene.shape.Sphere;
import com.jme3.scene.shape.Sphere.TextureMode;
import com.jme3.util.SkyFactory;

/**
 *
 * @author Adi
 */
public class InGameState extends AbstractAppState implements PhysicsCollisionListener {
    private AppStateManager stateManager;
    private SimpleApplication app;
    private Node              rootNode;
    private AssetManager      assetManager;
    private InputManager      inputManager;
    private BulletAppState    physics;
  
    // In game fields
    private PlayerSpaceship spaceShip;
    private PlayerSpaceship otherShip;
    private CameraNode cam;
    
    private BitmapText hudText;
    
    
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
        
        this.physics = new BulletAppState();
        physics.setThreadingType(BulletAppState.ThreadingType.PARALLEL);
        stateManager.attach(physics);
   
        initKeys();
        initSpaceship();
        initCrosshairs();
        initDirectionalLight();
        initSky();
        initChaseCam();
        prepareHealthBar();
        spaceShip.getRigidBodyControl().setGravity(new Vector3f(0, 0, 0));
        
        physics.getPhysicsSpace().addCollisionListener(this);
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
    	System.out.println(spaceShip.getHealth()+"");
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
	    	  Projectile p = initBullet(spaceShip);
	          spaceShip.shoot(p);
	      }
	    }
  };
 
  private AnalogListener analogListener = new AnalogListener() {
    public void onAnalog(String name, float value, float tpf) {
        if (name.equals("Accelerate")) {
        	spaceShip.accelerateShip();	
        }
        if (name.equals("Decelerate")) {
        	spaceShip.decelerateShip();
        }
        if (name.equals("Left")) {
        	spaceShip.moveLeft();
        }
        if (name.equals("Right")) {
        	spaceShip.moveRight();
        }
        if (name.equals("Up")) {
        	spaceShip.moveUp();
        }
        if (name.equals("Down")) {
        	spaceShip.moveDown();
        }
        if (name.equals("Emergency")) {
        	spaceShip.clear();
        }
  
    }
  };
  
  private void initSpaceship() {
      Spatial ship = assetManager.loadModel("Models/space_frigate_63DS/space_frigate_6.j3o");
      CollisionShape ship_shape = CollisionShapeFactory.createDynamicMeshShape(ship);
      RigidBodyControl ship_phy = new RigidBodyControl(ship_shape, 1.0f);
      
      System.out.println("1");
      
      String[] options = {"Big", "Small"};
      //int pane = JOptionPane.showOptionDialog(null, "Choose your ship. A bigger ship has less speed but more damage,  while a smaller ship has greater speed but less damage.", "Choose your ship", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
      int pane = 1;
      if (pane == 0)
      {
      	System.out.println("Big");
      	spaceShip = new PlayerSpaceship(ship, ship_phy, ship_shape, 0f, 0f, 0f, 0, 0, true);
      }
      else if (pane == 1)
      {
    	System.out.println("Small");
    	spaceShip = new PlayerSpaceship(ship, ship_phy, ship_shape, 0f, 0f, 0f, 0, 0, false);
      }
      
      System.out.println(spaceShip.getSpeed());
      
      physics.getPhysicsSpace().add(spaceShip.getRigidBodyControl());
   
      rootNode.attachChild(ship);

  }
  
  private Projectile initBullet(PlayerSpaceship ship) {
	  SphereCollisionShape bullet_shape = new SphereCollisionShape(1f);
	  RigidBodyControl bullet_phy = new RigidBodyControl(bullet_shape,.0000001f);
	 
	  Projectile bullet;
	  if (spaceShip.getSize())
	  {
		  bullet = new DamageBullet(32, 32, 0.4f, true, false, bullet_phy, bullet_shape);
	  }
	  else
	  {
		  bullet = new SpeedBullet(32, 32, 0.25f, true, false, bullet_phy, bullet_shape);
	  }
	  
	  bullet.getGeometry().setMaterial(new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md"));
	  rootNode.attachChild(bullet.getGeometry());

	  /** Position the cannon ball  */

	  bullet.getGeometry().setLocalTranslation(ship.getSpatial().getLocalTranslation().add(new Vector3f(-5f, 0f, 0f)));
		System.out.println(bullet.getGeometry().getLocalTranslation());

	  /** Make the ball physcial with a mass > 0.0f */

	  /** Add physical ball to physics space. */
		bullet.getGeometry().addControl(bullet_phy);
	  physics.getPhysicsSpace().add(bullet.getRigidBodyControl());

      bullet.getRigidBodyControl().setGravity(new Vector3f(0,0,0));
      
      return bullet;
  }
  
  private void initDirectionalLight() {
      DirectionalLight sun = new DirectionalLight();
      sun.setDirection(new Vector3f(-0.1f, -0.7f, -1.0f));
      rootNode.addLight(sun);
  }
  
  private void initCrosshairs() {
    BitmapFont myFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
    BitmapText ch = new BitmapText(myFont, false);
    ch.setSize(myFont.getCharSet().getRenderedSize() * 2);
   
    ch.setText("+");        // fake crosshairs :)
    ch.setLocalTranslation( // center
    app.getContext().getSettings().getWidth() ,
    app.getContext().getSettings().getHeight() / 2, 0);
    
    app.getGuiNode().attachChild(ch);
    
  }
  

  private void initChaseCam() {
    app.getFlyByCamera().setEnabled(false);
    CameraNode camNode = new CameraNode("CamNode",app.getCamera());
    camNode.setControlDir(ControlDirection.SpatialToCamera);
    ((Node)spaceShip.getSpatial()).attachChild(camNode);
    camNode.setLocalTranslation(new Vector3f(20, 5, 0));
    camNode.lookAt(spaceShip.getSpatial().getLocalTranslation(), Vector3f.UNIT_Y); 
    
    cam = camNode;
  }
  
  private void initSky() {
	  rootNode.attachChild(SkyFactory.createSky(
	            assetManager, "Textures/Sky/Bright/BrightSky.dds", false));
  }
  
  public void prepareHealthBar() {
	 
	  hudText = new BitmapText(assetManager.loadFont("Interface/Fonts/Default.fnt"), false);
	  hudText.setSize(assetManager.loadFont("Interface/Fonts/Default.fnt").getCharSet().getRenderedSize());      // font size
	  hudText.setColor(ColorRGBA.Blue);                             // font color
	  hudText.setText("Health: " + spaceShip.getHealth() + "");             // the text
	  hudText.setLocalTranslation(0, app.getContext().getSettings().getHeight(), 0); // position
      app.getGuiNode().attachChild(hudText);
     
  }

  public void updateHealthBar() {
	
	  app.getGuiNode().detachChild(hudText);
	  hudText = new BitmapText(assetManager.loadFont("Interface/Fonts/Default.fnt"), false);
	  hudText.setSize(assetManager.loadFont("Interface/Fonts/Default.fnt").getCharSet().getRenderedSize());      // font size
	  hudText.setColor(ColorRGBA.Blue);                             // font color
	  hudText.setText("Health: " + spaceShip.getHealth() + "");             // the text
	  hudText.setLocalTranslation(0, app.getContext().getSettings().getHeight(), 0); // position
      app.getGuiNode().attachChild(hudText);
     
  }
  
  @Override
  public void collision(PhysicsCollisionEvent arg0) {
	// TODO Auto-generated method stub
	  Spatial bullet = arg0.getNodeB();
	  bullet.removeFromParent();
	
	System.out.println("COLLISION");
	int health = spaceShip.getHealth();
	health -= 1;
	
	if (health <= 0)
	{
		rootNode.detachChild(spaceShip.getSpatial());
	}
	
	spaceShip.setHealth(health);
	updateHealthBar();
  }
  
}