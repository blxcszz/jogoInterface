package main;

import java.awt.*;
import javax.swing.*;

public class jogo extends Canvas implements Runnable{
	
	public static JFrame frame;
	public static boolean isRunning = true;
	public Thread thread;
	public static int largura = 240;
	public static int altura = 160;
	public static int escala = 3;
	
	public jogo () {
		this.setPreferredSize(new Dimension(largura*escala, altura*escala));		
		initFrame();
	}
	public void initFrame() {
		frame = new JFrame("Teste 01");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	
	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main (String args []) {
		
		jogo jogo = new jogo();
		jogo.start();
	}
	
	public void tick() {
		
	}
	
	public void render() {
		
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountfTicks = 60.0;
		double ns = 100000000 / amountfTicks;
		double delta = 0;
		double frames = 0;
		double timer = System.currentTimeMillis();
		while (isRunning) {
			long now = System.nanoTime();
			delta +=(now - lastTime)/ns;
			lastTime = now;
			if (delta >= 1 ) {
				tick();
				render();
				frames++;
				delta--;
			}
			if (System.currentTimeMillis() - timer >= 1000) {
				
				System.out.println("FPS: " + frames);
				frames = 0;
				timer += 1000;
			}
		}
		
stop();		
		
	}
	
	

}
