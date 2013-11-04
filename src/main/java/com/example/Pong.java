/**
 * Pong Applet
 * Coded by MountainDew
 */

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Pong extends Applet implements MouseMotionListener, KeyListener {

	int my,bx,by,px,py,compx,compy,width,height,speedx,speedy,bwidth,bheight,pwidth,pheight,score;
	boolean started;
	private Timer timer1;
	
	public void init() {
		setSize(800,500);
		width = getSize().width;
		height = getSize().height;
		setBackground(Color.black);
		pheight = 120;
		pwidth = 15;
		bheight = 30;
		bwidth = 30;
		addKeyListener(this);
		addMouseMotionListener(this);
		px = 35;
		compx = width - 35 - pwidth;
		newgame();
		timer1 = new Timer(10,new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			height = getSize().height;
    			width = getSize().width;
				bx += speedx;
				by += speedy;
				if (by <= 0 || by + bheight >= height) {
					speedy = -speedy;
				}
				if (bx <= px + pwidth && by + bheight >= py && by <= py + pheight && bx > px) {
					speedx = -speedx;
					++score;
				}
				if (bx + bwidth >= compx && by + bheight >= compy && by <= compy + pheight && bx < compx + pwidth) {
					speedx = -speedx;
				}
				if (speedx < 0) {
					if (compy + pheight / 2 != height / 2) {
						if (compy + pheight / 2 > height / 2) {
							compy -= -speedx;
						}
						else {
							compy += -speedx;
						}
					}
				}
				else {
					if (by + bheight / 2 <= compy + pheight / 2) {
						compy -= speedx;
					}
					else {
						compy += speedx;
					}
				}
				if (compy < 0) {
					compy = 0;
				}
				if (compy + pheight > height) {
					compy = height - pheight;
				}
				if (bx + bwidth < 0) {
					py = height / 2 - pheight / 2;
					timer1.stop();
					started = false;
				}
				repaint();
    		}    
		});
	}
   
	public void mouseMoved(MouseEvent e) {
		if (started) {
			my = e.getY();
			if (my + pheight / 2 > height) {
				my = height - pheight / 2;
			}
			if (my < pheight / 2) {
				my = pheight / 2;
			}
			py = my - pheight / 2;
			repaint();
		}
	}

	public void mouseDragged(MouseEvent e) { }

	public void paint(Graphics g) {
		Font font1 = new Font("Arial", Font.BOLD, 18);
		Font font2 = new Font("Arial", Font.BOLD,40);
		g.setColor(Color.white);
		g.drawRect(0,0,width - 1,height - 1);
		g.fillRect(px,py,pwidth,pheight);
		g.fillRect(compx,compy,pwidth,pheight);
		g.setFont(font1);
		g.drawString("Score: " + score,20,20);
		if (started) {
			g.fillArc(bx,by,bwidth,bheight,0,360);
		}
		else {
			g.setFont(font2);
			g.setColor(Color.green);
			g.drawString("Pong",width / 2 - 46,height / 2 - 16);
			g.setFont(font1);
			g.drawString("Press 's' to start",width / 2 - 69,height / 2 + 30);
		}
	}
	
	public void newgame() {
		py = height / 2 - pheight / 2;
		compy = py;
		bx = width / 2 - bwidth / 2;
		by = height / 2 - bheight / 2;
		speedx = 10;
		speedy = 10;
		score = 0;
	}
    
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == 's') {
			started = true;
			newgame();
			timer1.start();
		}
	}
    
	public void keyTyped(KeyEvent e) { }

	public void keyReleased(KeyEvent e) { }

}

