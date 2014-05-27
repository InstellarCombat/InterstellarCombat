/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AppStates;

import java.io.Serializable;

import mygame.*;
import interfaces.*;

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
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.CameraControl.ControlDirection;
import com.jme3.scene.shape.Quad;
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
	private BulletAppState    physics;
	private Main w;
	
		// In game fields
	private RigidBodyControl  ship_phy;
	private SpaceshipControl  ship_control;
	private Node shipNode;
	private Spatial ship;
	private CameraNode camNode;
	private boolean forward;
	private boolean rotate;
		
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
		this.rotate = false;
		this.w = ((InterstellarCombat)app).getMain();
		
		stateManager.attach(physics);
		initKeys();
		initSpaceship();
		initDirectionalLight();
		initSky();
		initChaseCam();
		ship_phy.setGravity(new Vector3f(0, 0, 0));
		
		Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		mat.setTexture("ColorMap", assetManager.loadTexture("Interface/Logo/Monkey.jpg"));
		Geometry ground = new Geometry("ground", new Quad(50, 50));
		ground.setLocalRotation(new Quaternion().fromAngleAxis(-FastMath.HALF_PI, Vector3f.UNIT_X));
		ground.setLocalTranslation(-25, -1, 25);
		ground.setMaterial(mat);
		rootNode.attachChild(ground);
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
		inputManager.addMapping("rotateRight", new MouseAxisTrigger(MouseInput.AXIS_X, true));
		inputManager.addMapping("rotateLeft", new MouseAxisTrigger(MouseInput.AXIS_X, false));
		inputManager.addMapping("rotateUp", new MouseAxisTrigger(MouseInput.AXIS_Y, true));
		inputManager.addMapping("rotateDown", new MouseAxisTrigger(MouseInput.AXIS_Y, false));
		inputManager.addMapping("toggleRotate", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));

		inputManager.addMapping("Shoot", new KeyTrigger(KeyInput.KEY_SPACE));
		inputManager.addMapping("Emergency", new KeyTrigger(KeyInput.KEY_E));
				
		// Add the names to the action listener.
		inputManager.addListener(actionListener, "Pause", "Shoot", "toggleRotate");
		inputManager.addListener(analogListener, "Left", "Right", "Accelerate", "Decelerate", "Up", "Down", "Emergency");
		inputManager.addListener(analogListener, "rotateRight", "rotateLeft", "rotateUp", "rotateDown");
	}
		
	private ActionListener actionListener = new ActionListener() {
		public void onAction(String name, boolean keyPressed, float tpf) {
			if (name.equals("Shoot") && !keyPressed) {
				System.out.println("noob");
			}

			if (name.equals("Pause") && !keyPressed) {
				System.out.println("noob2");
			}
			
			if (name.equals("toggleRotate") && keyPressed) {
				rotate = true;
				inputManager.setCursorVisible(false);
			}

			if (name.equals("toggleRotate") && !keyPressed) {
				rotate = false;
				inputManager.setCursorVisible(true);
			}
		}
	};
 
	private AnalogListener analogListener = new AnalogListener() {
		public void onAnalog(String name, float value, float tpf) {
			int MAX_ROT_SPEED = 10;

			if (name.equals("Accelerate")) {
				accelerateShip();
			}
			
			if (name.equals("Decelerate")) {
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
			
			if (name.equals("rotateRight") && rotate) {
				ship_phy.applyTorqueImpulse(new Vector3f(0, -tpf, 0));
				if (Math.abs(ship_phy.getAngularVelocity().y) > (float)MAX_ROT_SPEED) {
					Vector3f aV = ship_phy.getAngularVelocity();
					float max = (aV.y/Math.abs(aV.y)) * MAX_ROT_SPEED;
					ship_phy.setAngularVelocity(new Vector3f(aV.x, max, aV.z));
				}
			}
			
			if (name.equals("rotateLeft") && rotate) {
				ship_phy.applyTorqueImpulse(new Vector3f(0, tpf, 0));
				if (Math.abs(ship_phy.getAngularVelocity().y) > (float)MAX_ROT_SPEED) {
					Vector3f aV = ship_phy.getAngularVelocity();
					float max = (aV.y/Math.abs(aV.y)) * MAX_ROT_SPEED;
					ship_phy.setAngularVelocity(new Vector3f(aV.x, max * 20, aV.z));
				}     	
			}
			
			if (name.equals("rotateUp") && rotate) {
				ship_phy.applyTorqueImpulse(new Vector3f(0, 0, -tpf));
				if (Math.abs(ship_phy.getAngularVelocity().z) > (float)MAX_ROT_SPEED) {
					Vector3f aV = ship_phy.getAngularVelocity();
					float max = (aV.z/Math.abs(aV.z)) * MAX_ROT_SPEED;
					ship_phy.setAngularVelocity(new Vector3f(aV.x, aV.y, max * 50));
				}     	
			}
			
			if (name.equals("rotateDown") && rotate) {
				ship_phy.applyTorqueImpulse(new Vector3f(0, 0, tpf));
				if (Math.abs(ship_phy.getAngularVelocity().z) > (float)MAX_ROT_SPEED) {
					Vector3f aV = ship_phy.getAngularVelocity();
					float max = (aV.z/Math.abs(aV.z)) * MAX_ROT_SPEED;
					ship_phy.setAngularVelocity(new Vector3f(aV.x, aV.y, max * 50));
				}     	
			}      
			
			if (name.equals("Emergency")) {
				clear();
			}
		}
	};
	
	private void initSpaceship() {
		shipNode = new Node("shipNode");
		
		ship = assetManager.loadModel("Models/space_frigate_63DS/space_frigate_6.j3o");
		//com.jme3.bullet.collision.shapes.CollisionShape shipShape = CollisionShapeFactory.createDynamicMeshShape(ship);
		//ship_phy =new RigidBodyControl(shipShape,2000);
		ship_phy = new RigidBodyControl(1.0f);
		ship_control = new SpaceshipControl();
		//ship_phy.setGravity(new Vector3f(0, 0, 0));
		ship.addControl(ship_phy);
		ship.addControl(ship_control);
		physics.getPhysicsSpace().add(ship_phy);
		shipNode.attachChild(ship);
		rootNode.attachChild(shipNode);
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
	
	private void makeBullets() {
		
	}
	
	private void initChaseCam() {
		//creating the camera Node
		camNode = new CameraNode("CamNode", app.getCamera());
		//Setting the direction to Spatial to camera, this means the camera will copy the movements of the Node
		camNode.setControlDir(ControlDirection.SpatialToCamera);
		//attaching the camNode to the ship (which is a node)
		((Node)ship).attachChild(camNode);
		//setting the local translation of the cam node to move it away from the ship a bit
		camNode.setLocalTranslation(new Vector3f(30, 10, 0));
		//setting the camNode to look at the ship
		camNode.lookAt(ship.getLocalTranslation(), Vector3f.UNIT_Y);

		//disable the default 1st-person flyCam
		app.getFlyByCamera().setEnabled(false);
	}
	
	private void initSky() {
		rootNode.attachChild(SkyFactory.createSky(
							assetManager, "Textures/Sky/Bright/BrightSky.dds", false));
	}
	
	/**
	 * Accelerates the ship
	 */
	public void accelerateShip() {
		Vector3f direction=new Vector3f(-10f,0,0);
		ship_phy.getPhysicsRotation().multLocal(direction);
		ship_phy.applyCentralForce(direction);
	//ship_phy.applyCentralForce(new Vector3f(-10,0,0));
	}
	
	/**
	 * Brake function for the ship
	 */
	public void decelerateShip() {
		Vector3f direction=new Vector3f(10f,0,0);
		ship_phy.getPhysicsRotation().multLocal(direction);
		ship_phy.applyCentralForce(direction);
	}
	
	/** 
	 * Rotates the ship up
	 */
	public void rotateUp() {
		ship_phy.applyImpulse(new Vector3f(0,.005f,0), Vector3f.UNIT_X);
	}
	
	public void moveUp() {
		Vector3f direction=new Vector3f(0,10f,0);
		ship_phy.getPhysicsRotation().multLocal(direction);
		ship_phy.applyCentralForce(direction);
	}
	
	/**
	 * Rotates the ship down
	 */
	public void rotateDown() {
		ship_phy.applyImpulse(new Vector3f(0,-.005f,0), Vector3f.UNIT_X);
	}
	
	public void moveDown() {
		Vector3f direction=new Vector3f(0,-10f,0);
		ship_phy.getPhysicsRotation().multLocal(direction);
		ship_phy.applyCentralForce(direction);
	}
	
	public void moveLeft() {
		Vector3f direction=new Vector3f(0,0,10f);
		ship_phy.getPhysicsRotation().multLocal(direction);
		ship_phy.applyCentralForce(direction);
	}
	
	public void moveRight() {
		Vector3f direction=new Vector3f(0,0,-10f);
		ship_phy.getPhysicsRotation().multLocal(direction);
		ship_phy.applyCentralForce(direction);
	}
	
	/** 
	 * Sets velocity of ship to 0
	 */
	public void clear() {
		ship_phy.setLinearVelocity(new Vector3f(0, 0, 0));
		ship_phy.setAngularVelocity(new Vector3f(0, 0, 0));
	}
}