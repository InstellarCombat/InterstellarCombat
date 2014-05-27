/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.*;
import com.jme3.system.*;

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.Callable;

import javax.swing.*;
/**
 * 
 * @author Aditya Sampath
 * The main class
 *
 */
public class Main {

    private static JmeCanvasContext context;
    private static Canvas canvas;
    private static Application app;
    private static JFrame frame;
    private static JPanel cardPanel;
    private static Container canvasPanel;
    private static final String appClass = "mygame.InterstellarCombat";
    
    public Main () {}
    
    private void begin () {
        createCanvas(appClass);
        
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {}
        
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                JPopupMenu.setDefaultLightWeightPopupEnabled(false);

                createFrame();
                
                canvasPanel.add(canvas, BorderLayout.CENTER);
                //frame.pack();
                startApp();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
    
    private void createFrame() {
        CardLayout cl = new CardLayout();
        MenuPanel menu = new MenuPanel(this);
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
    
    /**
     * This method creates a JSwing Canvas
     * @param appClass - the name of the application class
     */
    public void createCanvas(String appClass){
        AppSettings settings = new AppSettings(true);
        settings.setWidth(640);
        settings.setHeight(480);

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

        app.setPauseOnLostFocus(false);
        app.setSettings(settings);
        app.createCanvas();
        app.startCanvas();

        context = (JmeCanvasContext) app.getContext();
        canvas = context.getCanvas();
        canvas.setSize(settings.getWidth(), settings.getHeight());
    }
    
    /**
     * This method starts the app inside the JSwing
     */
    public void startApp(){
        app.startCanvas();
        app.enqueue(new Callable<Void>(){
            public Void call(){
                if (app instanceof SimpleApplication){
                    SimpleApplication simpleApp = (SimpleApplication) app;
                    simpleApp.getFlyByCamera().setDragToRotate(true);
                }
                return null;
            }
        });
    }
    
    /**
     * 
     * @param panel -
     */
    public void changePanel(String panel) { 
        ((CardLayout)cardPanel.getLayout()).show(cardPanel, panel); 
        frame.requestFocus(); 
    }
    
    /**
     * Main method of the program
     * @param args - arguments in main
     */
    public static void main(String[] args){
    	Main game = new Main();
    	game.begin();
    	//InterstellarCombat app = new InterstellarCombat();
    	//app.start();
    }

}

