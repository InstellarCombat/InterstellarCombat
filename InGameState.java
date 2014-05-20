/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AppStates;

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
import com.jme3.math.Vector3f;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.CameraControl.ControlDirection;

/**
 *
 * @author Yoav
 */
public class InGameState extends AbstractAppState {
    private AppStateManager stateManager;
    private SimpleApplication app;
    private Node              rootNode;
    private AssetManager      assetManager;
    private InputManager      inputManager;
    private BulletAppState    physics;
  
    // In game fields
    private RigidBodyControl  ship_phy;
    private CompoundCollisionShape shipShape;
    private VehicleControl shipControl;
    private Spatial ship;
    private CameraNode cam;
    private boolean forward;
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app); 
        this.app = (SimpleApplication)app;
        this.rootNode = this.app.getRootNode();
        this.assetManager = this.app.getAssetManager();
        this.inputManager = this.app.getInputManager();
        this.physics = new BulletAppState();
        stateManager.attach(physics);
        initKeys();
        initSpaceship();
        initDirectionalLight();
        initChaseCam();
        ship_phy.setGravity(new Vector3f(0, 0, 0));
    }
    
    @Override
    public void cleanup() {
      super.cleanup();
      // unregister all my listeners, detach all my nodes, etc...
      rootNode.detachAllChildren();
      inputManager.clearMappings();
    }
    
    @Override
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
    public void update(float tpf) {
      // do the following while game is RUNNING
      // modify scene graph...
      // call some methods...

    }
    
    public void initKeys() {
        inputManager.addMapping("Pause",  new KeyTrigger(KeyInput.KEY_P));
        inputManager.addMapping("Left",   new KeyTrigger(KeyInput.KEY_LEFT));
        inputManager.addMapping("Right",  new KeyTrigger(KeyInput.KEY_RIGHT));
        inputManager.addMapping("Accelerate", new KeyTrigger(KeyInput.KEY_SPACE));                            
        inputManager.addMapping("Up",   new KeyTrigger(KeyInput.KEY_UP));                                 
        inputManager.addMapping("Down",  new KeyTrigger(KeyInput.KEY_DOWN));
        inputManager.addMapping("Shoot", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
                                     
        // Add the names to the action listener.
        inputManager.addListener(actionListener,"Pause","Shoot");
        inputManager.addListener(analogListener,"Left", "Right", "Accelerate","Up","Down");
    }
    
    private ActionListener actionListener = new ActionListener() {
    public void onAction(String name, boolean keyPressed, float tpf) {
      if (name.equals("Shoot") && !keyPressed) {
          
      }
    }
  };
 
  private AnalogListener analogListener = new AnalogListener() {
    public void onAnalog(String name, float value, float tpf) {
     
        if (name.equals("Accelerate")) {
        	forward = !forward;
        	accelerateShip();
        	System.out.println("Go");
        }
        if (name.equals("Left")) {
        	
        }
        if (name.equals("Right")) {
       
        }
        if (name.equals("Up")) {
            
        }
        if (name.equals("Down")) {
            
        }
  
    }
  };
  
  public void initSpaceship() {
      ship = assetManager.loadModel("Models/space_frigate_63DS/space_frigate_6.j3o");
      //com.jme3.bullet.collision.shapes.CollisionShape shipShape = CollisionShapeFactory.createDynamicMeshShape(ship);
      //ship_phy =new RigidBodyControl(shipShape,2000);
      ship_phy = new RigidBodyControl(1.0f);
      //ship_phy.setGravity(new Vector3f(0, 0, 0));
      ship.addControl(ship_phy);
      physics.getPhysicsSpace().add(ship_phy);
      rootNode.attachChild(ship); 
     
  
  }
  
  public void initDirectionalLight() {
      DirectionalLight sun = new DirectionalLight();
      sun.setDirection(new Vector3f(-0.1f, -0.7f, -1.0f));
      rootNode.addLight(sun);
  }
  
  public void initCrosshairs() {
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
  
  public void initChaseCam() {
    app.getFlyByCamera().setEnabled(false);
    CameraNode camNode = new CameraNode("CamNode",app.getCamera());
    camNode.setControlDir(ControlDirection.SpatialToCamera);
    ((Node)ship).attachChild(camNode);
    camNode.setLocalTranslation(new Vector3f(15, 5, 0));
    camNode.lookAt(ship.getLocalTranslation(), Vector3f.UNIT_Y); 
    
    cam = camNode;
  }
  
  public void accelerateShip() {
	ship_phy.applyCentralForce(new Vector3f(-10,0,0));
  }
}
