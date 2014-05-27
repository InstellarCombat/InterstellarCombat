package mygame;

import com.jme3.app.*;
import com.jme3.system.*;

import info.GameInfo;
import interfaces.NetworkGUI;

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.Callable;

import javax.swing.*;

import panels.MenuPanel;
import panels.OptionPanel;

public class Main {

    private JmeCanvasContext context;
    private Application app;
    private JFrame frame;
    private JPanel cardPanel;
    private Container canvasPanel;
    private GameInfo information;
    private NetworkManager netManager;
    private static final String appClass = "mygame.InterstellarCombat";
    
    public Main () {
    	information = new GameInfo();
    	netManager = new NetworkManager();
    }
    
    private void begin () {
        createCanvas(appClass);
        
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {}
        
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                JPopupMenu.setDefaultLightWeightPopupEnabled(false);

                createFrame();
                
                //frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setSize(1200, 900);
                frame.setVisible(true);
            }
        });
    }
    
    private void createFrame() {
        CardLayout cl = new CardLayout();
        MenuPanel menu = new MenuPanel(this);
        OptionPanel opts = new OptionPanel(this);
        frame = new JFrame("InterstellarCombat");
        cardPanel = new JPanel();
        canvasPanel = new JPanel();
        
        frame.setBounds(100, 100, 1000, 750);
        frame.setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosed(WindowEvent e) {
                app.stop();
            }
        });
        
        cardPanel.setOpaque(true); 
        cardPanel.setLayout(cl);

        canvasPanel.setLayout(new BorderLayout());
        
        cardPanel.add(menu, "main");
        cardPanel.add(opts, "option");
        cardPanel.add(canvasPanel, "play");

        frame/*.getContentPane()*/.add(cardPanel);
 
        createMenu();
    }
    
    private void createMenu(){
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu menuTortureMethods = new JMenu("Canvas Torture Methods");
        menuBar.add(menuTortureMethods);
        
        final JMenuItem itemSwitchTab = new JMenuItem("Switch Panel");
        menuTortureMethods.add(itemSwitchTab);
        itemSwitchTab.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
        	   ((CardLayout)cardPanel.getLayout()).next(cardPanel);
           } 
        });
    }
    
    public GameInfo getInfo () {
    	return information;
    }
    
    public NetworkManager getNetManager () {
    	return netManager;
    }

    public void createCanvas(String appClass){
        try{
            Class<? extends Application> clazz = (Class<? extends Application>) Class.forName(appClass);
            app = clazz.newInstance();
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }catch (InstantiationException ex){
            ex.printStackTrace();
        }catch (IllegalAccessException ex){
            ex.printStackTrace();
        } finally {
        	if (app == null) return;
        }

        ((InterstellarCombat)app).setMain(this);
        netManager.setGUI((InterstellarCombat)app);
    }

    public Application getApp () {
    	return app;
    }
    
    public Container getCanvas () {
    	return canvasPanel;
    }
    
    public void changePanel(String panel) { 
        ((CardLayout)cardPanel.getLayout()).show(cardPanel, panel); 
        frame.requestFocus(); 
    }

    public static void main(String[] args){
    	Main game = new Main();
    	game.begin();
    }
}