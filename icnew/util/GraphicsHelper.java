package util;

import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * @(#)GraphicsHelper.java
 *
 *
 * Assists with Graphics functions. Based off of MovingImage. Used this
 * for my intro project gg
 *
 * @author Aditya Sampatb
 * @version 5/11/2013
 */

public class GraphicsHelper {

	private int x, y;
	private int width, height;
	private Image image;
	private boolean isImage, isVisible;


	public GraphicsHelper(String img, int xPos, int yPos, int w, int h) {
		try {
			File file = new File(img);
            System.out.println("Is the file there ? " + file.exists());
            image = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		x = xPos;
		y = yPos;
		width = w;
		height = h;
		isImage = true;
	}

	public GraphicsHelper(int xPos, int yPos, int w, int h) {
    	x = xPos;
    	y = yPos;
    	width = w;
    	height = h;
    	isImage = false;
    }

	// METHODS

	public void moveTo(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void moveBy(int x, int y) {
		this.x += x;
		this.y += y;
	}

	public void applyWindowLimits(int windowWidth, int windowHeight) {
		x = Math.min(x,windowWidth-this.width);
		y = Math.min(y,windowHeight-this.height);
		x = Math.max(0,x);
		y = Math.max(0,y);
	}

	public boolean isPointInImage(int mouseX, int mouseY) {
		if (mouseX >= x && mouseY >= y && mouseX <= x + width && mouseY <= y + height)
			return true;
		return false;
	}
	
	public Image getImage () {
		return image;
	}

	public void draw(Graphics g, ImageObserver io) {
		g.drawImage(image,x,y,width,height,io);
	}

	public void draw(Graphics g, Image img, ImageObserver io, int xPos, int yPos, int w, int h) {
		g.drawImage(img,xPos,yPos,w,h,io);
	}

	public void draw(Graphics g) {
		g.drawRect(x,y,width,height);
	}

	public void drawRotatedImage (Graphics g, Image img, ImageObserver io, int xPos, int yPos, int w, int h) {
		Graphics2D g2 = (Graphics2D)g;
		AffineTransform at = g2.getTransform();
		g2.translate(xPos+w/2, yPos+h/2);
		g2.rotate(Math.PI/2);
		g.drawImage(img,(int)(-w/2),(int)(-h/2),(int)w,(int)h,io);
		g2.setTransform(at);
	}

	public void resize(int w, int h) {
		width = w;
		height = h;
	}

	public void showImage(Graphics g, ImageObserver io) {
		if (isVisible)
			draw(g,io);
	}

	public void toggleVisibility() {
		isVisible = !isVisible;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public void setImage (String filename) {
		if(isImage) image = (new ImageIcon(filename)).getImage();
	}

}
