import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;

/**
 * 这个类代表贪吃蛇的活动场所
 * @author bjsxt
 * @version 1.0
 */
public class Yard extends Frame {

	PaintThread paintThread = new PaintThread();
	private boolean gameOver = false; //游戏是否结束
	public final int SIZE = 5;
	/**
	 * 行数
	 */
	public static final int ROWS = 48;
	public static final int COLS = 36;
	public static final int BLOCK_SIZE = 12;
	
	private Font fontGameOver = new Font("宋体", Font.BOLD, 50);
	private Font scoreFont =new Font("楷体",Font.BOLD,30);
	private int score = 0;
	
	Snake s = new Snake(this);
    //Egg e = new Egg();
	 ArrayList<Egg> eggs=new ArrayList<Egg>();
	public Yard()
	{
	
	for(int i=0;i<SIZE;i++)
	{
	   	
		Egg e = new Egg();
		eggs.add(e);
		
	}	
	}
	Image offScreenImage = null;
	
	public void launch() {
		this.setLocation(400, 200);
		this.setSize(COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		this.setVisible(true);
		this.addKeyListener(new KeyMonitor());
		
		new Thread(paintThread).start();
	}
	
	
	
	public static void main(String[] args) {
		new Yard().launch();
	}
	
	
	public void stop() {
		gameOver = true;
	}
	
	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
		g.setColor(Color.white);
		//画出横线
		for(int i=1; i<ROWS; i++) {
			g.drawLine(0, BLOCK_SIZE * i, COLS * BLOCK_SIZE, BLOCK_SIZE * i);
		}
		for(int i=1; i<COLS; i++) {
			g.drawLine(BLOCK_SIZE * i, 0, BLOCK_SIZE * i, BLOCK_SIZE * ROWS);
		}
		
		g.setColor(Color.red);
		g.setFont(scoreFont);
		g.drawString("score:" + score, 10, 60);
		
		if(gameOver) {
			g.setFont(fontGameOver);
			g.drawString("游戏结束", 120, 180);
			
			paintThread.pause();
		}
		
		g.setColor(c);
		for(int i=0;i<SIZE;i++)
		{
		s.eat(eggs.get(i));
		eggs.get(i).draw(g);
		
		}	
		s.draw(g);
		
	}
	
	@Override
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
		}
		Graphics gOff = offScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offScreenImage, 0, 0,  null);
	}
	
	private class PaintThread implements Runnable {
		private boolean running = true;
		private boolean pause = false;
		public void run() {
			while(running) {
				try
				{
				Thread.sleep(10);
				}catch(InterruptedException e){}
				
				if(pause) continue; 
				else repaint();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		public void pause() {
			this.pause = true;
			//System.out.println("nihao");
		}
		
		public void reStart() {
			this.pause = false;
			s = new Snake(Yard.this);
			gameOver = false;
			//System.out.println("nihao111");
			
		}
		
		public void gameOver() {
			running = false;
			
		}
		
	}
	
	private class KeyMonitor extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
		
			if(key == KeyEvent.VK_A) {
				//System.out.println(nihao111");
				paintThread.reStart();
			}
			s.keyPressed(e);
		}
		
	}
	
	/**
	 * 拿到所得的分数
	 * @return 分数
	 */
	
	public int getScore() {
		return score;
	}
	
	/**
	 * 设置所得的分数
	 * @param score 分数
	 */
	
	public void setScore(int score) {
		this.score = score;
	}

}
