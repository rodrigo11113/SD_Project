package edu.ufp.inf.sd.rabbitmqservices.projeto.jogo.engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

/**
 * Keyboard handling for the game along with the mouse setup for game handling.
 * Menus are being moved to edu.ufp.inf.sd.rabbitmqservices.projeto.jogo.gui.gms
 * @author SergeDavid
 * @version 0.1
 */
@SuppressWarnings("unused")
public class InputHandler implements KeyListener,MouseListener,ActionListener {
	
	//Development buttons and the exit game button (escape key)
	private final int dev1 = KeyEvent.VK_NUMPAD1;
	private final int dev2 = KeyEvent.VK_NUMPAD2;
	private final int dev3 = KeyEvent.VK_NUMPAD3;
	private final int dev4 = KeyEvent.VK_NUMPAD4;
	private final int dev5 = KeyEvent.VK_NUMPAD5;
	private final int dev6 = KeyEvent.VK_NUMPAD6;
	private final int dev7 = KeyEvent.VK_NUMPAD7;
	private final int dev8 = KeyEvent.VK_NUMPAD8;
	private final int dev9 = KeyEvent.VK_NUMPAD9;
	private final int exit = KeyEvent.VK_ESCAPE;
	
	//Movement buttons
	private final int up = KeyEvent.VK_W;
	private final int down = KeyEvent.VK_S;
	private final int left = KeyEvent.VK_A;
	private final int right = KeyEvent.VK_D;

	//Command buttons
	private final int select = KeyEvent.VK_Z;
	private final int cancel = KeyEvent.VK_X;
	private final int start = KeyEvent.VK_ENTER;
	
	//Mouse (right/left clicks)
	private final int main = MouseEvent.BUTTON1;
	private final int alt = MouseEvent.BUTTON1;
	
	public InputHandler() {
		Game.gui.addKeyListener(this);
		Game.gui.addMouseListener(this);
	}

	int DevPathing = 1;
	public void keyPressed(KeyEvent e) {
		int i=e.getKeyCode();
		if (i==exit) {System.exit(0);}
		if (Game.GameState==Game.State.PLAYING) {
			edu.ufp.inf.sd.rabbitmqservices.projeto.jogo.players.Base ply = Game.player.get(Game.btl.currentplayer);
			
			if (i==up) {
				try {
					if(Game.observer.getGui().verificatoken(Game.observer.tokenholder)){
					Game.observer.sendMessage("up play");}
					else System.out.println("nao e a tua vez");
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}

				//ply.selecty--;if (ply.selecty<0) {ply.selecty++;}
			}
			else if (i==down) {
				try {
					if(Game.observer.getGui().verificatoken(Game.observer.tokenholder)){
					Game.observer.sendMessage("down play");}
					else System.out.println("nao e a tua vez");
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
				//ply.selecty++;if (ply.selecty>=Game.map.height) {ply.selecty--;}
			}
			else if (i==left) {
				try {
					if(Game.observer.getGui().verificatoken(Game.observer.tokenholder)){
					Game.observer.sendMessage("left play");}
					else System.out.println("nao e a tua vez");
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
				//ply.selectx--;if (ply.selectx<0) {ply.selectx++;}
			}
			else if (i==right) {
				try {
					if(Game.observer.getGui().verificatoken(Game.observer.tokenholder)){
				Game.observer.sendMessage("right play");}
					else System.out.println("nao e a tua vez");
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
				//ply.selectx++;if (ply.selectx>=Game.map.width) {ply.selectx--;}
			}
			else if (i==select) {/*try {
				if(Game.observer.getGui().verificatoken(Game.observer.tokenholder)){
				Game.observer.sendMessage("select play");}
				else System.out.println("nao e a tua vez");
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}*/
				Game.btl.Action();
			}
			else if (i==cancel) {
				try {
					if(Game.observer.getGui().verificatoken(Game.observer.tokenholder)){
					Game.observer.sendMessage("cancel play");}
					else System.out.println("nao e a tua vez");
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
				//Game.player.get(Game.btl.currentplayer).Cancle();
			}
			else if (i==start) {
				/*try {
					if(Game.observer.getGui().verificatoken(Game.observer.tokenholder)){
					Game.observer.sendMessage("start play");}
					else System.out.println("nao e a tua vez");
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}*/
				new edu.ufp.inf.sd.rabbitmqservices.projeto.jogo.menus.Pause();
			}
		}
		if (Game.GameState==Game.State.EDITOR) {
			if (i==up) {
				try {
					if(Game.observer.getGui().verificatoken(Game.observer.tokenholder)){
					Game.observer.sendMessage("up edit");}
					else System.out.println("nao e a tua vez");
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
				//Game.edit.selecty--;if (Game.edit.selecty<0) {Game.edit.selecty++;} Game.edit.moved = true;
			}
			else if (i==down) {
				try {
					if(Game.observer.getGui().verificatoken(Game.observer.tokenholder)){
					Game.observer.sendMessage("down edit");}
					else System.out.println("nao e a tua vez");
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
				//Game.edit.selecty++;if (Game.edit.selecty>=Game.map.height) {Game.edit.selecty--;} Game.edit.moved = true;
			}
			else if (i==left) {
				try {
					if(Game.observer.getGui().verificatoken(Game.observer.tokenholder)){
					Game.observer.sendMessage("left edit");}
					else System.out.println("nao e a tua vez");
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
				//Game.edit.selectx--;if (Game.edit.selectx<0) {Game.edit.selectx++;} Game.edit.moved = true;
			}
			else if (i==right) {
				try {
					if(Game.observer.getGui().verificatoken(Game.observer.tokenholder)){
					Game.observer.sendMessage("right edit");}
					else System.out.println("nao e a tua vez");
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
				//Game.edit.selectx++;if (Game.edit.selectx>=Game.map.width) {Game.edit.selectx--;} Game.edit.moved = true;
			}
			else if (i==select) {
				try {
					if(Game.observer.getGui().verificatoken(Game.observer.tokenholder)){
					Game.observer.sendMessage("select edit");}
					else System.out.println("nao e a tua vez");
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
				//Game.edit.holding = true;
			}
			else if (i==cancel) {
				try {
					if(Game.observer.getGui().verificatoken(Game.observer.tokenholder)){
					Game.observer.sendMessage("cancel edit");}
					System.out.println("nao e a tua vez");
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
				//Game.edit.ButtButton();
			}
			else if (i==start) {
				try {
					if(Game.observer.getGui().verificatoken(Game.observer.tokenholder)){
					Game.observer.sendMessage("start edit");}
					System.out.println("nao e a tua vez");
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
				//new edu.ufp.inf.sd.rabbitmqservices.projeto.jogo.menus.EditorMenu();
			}
		}
		
		if (i==dev1) {
			try {
				if(Game.observer.getGui().verificatoken(Game.observer.tokenholder)){
				Game.observer.sendMessage("dev1");}
				else System.out.println("nao e a tua vez");
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
			//Game.gui.LoginScreen();
		}
		else if (i==dev2) {
			try {
				if(Game.observer.getGui().verificatoken(Game.observer.tokenholder)){
				Game.observer.sendMessage("dev2");}
				else System.out.println("nao e a tua vez");
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
			//Game.load.LoadTexturePack("Test");
		}
		else if (i==dev3) {
			DevPathing++;
			switch (DevPathing) {
				case 1:try {
					if(Game.observer.getGui().verificatoken(Game.observer.tokenholder)){
					Game.observer.sendMessage("dev3 1");}
					else System.out.println("nao e a tua vez");
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
					//Game.pathing.ShowCost=false;
					break;
				case 2:try {
					if(Game.observer.getGui().verificatoken(Game.observer.tokenholder)){
					Game.observer.sendMessage("dev3 2");}
					else System.out.println("nao e a tua vez");
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
					//Game.pathing.ShowHits=true;
					break;
				case 3:
					try {
						if(Game.observer.getGui().verificatoken(Game.observer.tokenholder)){
						Game.observer.sendMessage("dev3 3");}
						else System.out.println("nao e a tua vez");
					} catch (IOException ioException) {
						ioException.printStackTrace();
					}
					//Game.pathing.ShowHits=false;Game.pathing.ShowCost=true;DevPathing=0;
					break;
			}
		}
		else if (i==dev4) {
			try {
				if(Game.observer.getGui().verificatoken(Game.observer.tokenholder)){
				Game.observer.sendMessage("dev4");}
				else System.out.println("nao e a tua vez");
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
			//Game.btl.EndTurn();
		}
		else if (i==dev5) {
			try {
				if(Game.observer.getGui().verificatoken(Game.observer.tokenholder)){
				Game.observer.sendMessage("dev5");}
				else System.out.println("nao e a tua vez");
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
			//Game.player.get(Game.btl.currentplayer).npc = !Game.player.get(Game.btl.currentplayer).npc; Game.btl.EndTurn();
		}
		else if (i==dev6) {
			try {
				if(Game.observer.getGui().verificatoken(Game.observer.tokenholder)){
				Game.observer.sendMessage("dev6");}
				else System.out.println("nao e a tua vez");
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
			//new edu.ufp.inf.sd.rabbitmqservices.projeto.jogo.menus.StartMenu();
		}
	}
	public void keyReleased(KeyEvent e) {
		int i=e.getKeyCode();
		if (Game.GameState==Game.State.EDITOR) {
			if (i==select) {
				try {
					if(Game.observer.getGui().verificatoken(Game.observer.tokenholder)){
					Game.observer.sendMessage("key release");}
					else System.out.println("nao e a tua vez");
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
				//Game.edit.holding = false;
			}
		}
	}
	public void keyTyped(KeyEvent arg0) {}
	public void mousePressed() {}
	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}

	@Override
	public void actionPerformed(ActionEvent e) {
		Game.gui.requestFocusInWindow();
		Object s = e.getSource();
	}
}
