package edu.ufp.inf.sd.rmi.project.client.jogo.engine;

import edu.ufp.inf.sd.rmi.project.server.State;
import edu.ufp.inf.sd.rmi.project.server.SubjectImpl;
import edu.ufp.inf.sd.rmi.project.server.SubjectRI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;

/**
 * Keyboard handling for the game along with the mouse setup for game handling.
 * Menus are being moved to edu.ufp.inf.sd.rmi.project.client.jogo.edu.ufp.inf.sd.rabbitmqservices.projeto.jogo.edu.ufp.inf.sd.rabbitmqservices.projeto.jogo.buildings.edu.ufp.inf.sd.rabbitmqservices.projeto.jogo.gui.gms
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
	SubjectRI subject;
	public InputHandler(SubjectRI subjectri) {
		Game.gui.addKeyListener(this);
		Game.gui.addMouseListener(this);
		subject=subjectri;
	}

	int DevPathing = 1;
	public void keyPressed(KeyEvent e) {
		int i=e.getKeyCode();
		if (i==exit) {System.exit(0);}
		if (Game.GameState==Game.State.PLAYING) {
			edu.ufp.inf.sd.rmi.project.client.jogo.players.Base ply = Game.player.get(Game.btl.currentplayer);
			
			if (i==up) {
				State state= null;
				try {
					state = new State(Game.observer.getId(),"up play");
				} catch (RemoteException remoteException) {
					remoteException.printStackTrace();
				}
				try {
					subject.setState(state);
				} catch (RemoteException remoteException) {
					remoteException.printStackTrace();
				}

				//ply.selecty--;if (ply.selecty<0) {ply.selecty++;
				}
			else if (i==down) {
				State state= null;
				try {
					state = new State(Game.observer.getId(),"down play");
				} catch (RemoteException remoteException) {
					remoteException.printStackTrace();
				}
				try {
					subject.setState(state);
				} catch (RemoteException remoteException) {
					remoteException.printStackTrace();
				}
				//ply.selecty++;if (ply.selecty>=Game.map.height) {ply.selecty--;}
				}
			else if (i==left) {
				State state= null;
				try {
					state = new State(Game.observer.getId(),"left play");
				} catch (RemoteException remoteException) {
					remoteException.printStackTrace();
				}
				try {
					subject.setState(state);
				} catch (RemoteException remoteException) {
					remoteException.printStackTrace();
				}
				//ply.selectx--;if (ply.selectx<0) {ply.selectx++;}
				}
			else if (i==right) {
				State state= null;
				try {
					state = new State(Game.observer.getId(),"right play");
				} catch (RemoteException remoteException) {
					remoteException.printStackTrace();
				}
				try {
					subject.setState(state);
				} catch (RemoteException remoteException) {
					remoteException.printStackTrace();
				}
				//ply.selectx++;if (ply.selectx>=Game.map.width) {ply.selectx--;}
				}
			else if (i==select) {
				State state= null;
				try {
					state = new State(Game.observer.getId(),"select play");
				} catch (RemoteException remoteException) {
					remoteException.printStackTrace();
				}
				try {
					subject.setState(state);
				} catch (RemoteException remoteException) {
					remoteException.printStackTrace();
				}
				//Game.btl.Action();
				}
			else if (i==cancel) {
				State state= null;
				try {
					state = new State(Game.observer.getId(),"cancel play");
				} catch (RemoteException remoteException) {
					remoteException.printStackTrace();
				}
				try {
					subject.setState(state);
				} catch (RemoteException remoteException) {
					remoteException.printStackTrace();
				}
				//Game.player.get(Game.btl.currentplayer).Cancle();
			}
			else if (i==start) {
				State state= null;
				try {
					state = new State(Game.observer.getId(),"start play");
				} catch (RemoteException remoteException) {
					remoteException.printStackTrace();
				}
				try {
					subject.setState(state);
				} catch (RemoteException remoteException) {
					remoteException.printStackTrace();
				}
				//new edu.ufp.inf.sd.rmi.project.client.jogo.menus.Pause();
			}
		}
		if (Game.GameState==Game.State.EDITOR) {
			if (i==up) {
				State state= null;
				try {
					state = new State(Game.observer.getId(),"up  edit");
				} catch (RemoteException remoteException) {
					remoteException.printStackTrace();
				}
				try {
					subject.setState(state);
				} catch (RemoteException remoteException) {
					remoteException.printStackTrace();
				}
				//Game.edit.selecty--;if (Game.edit.selecty<0) {Game.edit.selecty++;} Game.edit.moved = true;
			}
			else if (i==down) {
				State state= null;
				try {
					state = new State(Game.observer.getId(),"down  edit");
				} catch (RemoteException remoteException) {
					remoteException.printStackTrace();
				}
				try {
					subject.setState(state);
				} catch (RemoteException remoteException) {
					remoteException.printStackTrace();
				}
				//Game.edit.selecty++;if (Game.edit.selecty>=Game.map.height) {Game.edit.selecty--;} Game.edit.moved = true;
			}
			else if (i==left) {
				State state= null;
				try {
					state = new State(Game.observer.getId(),"left  edit");
				} catch (RemoteException remoteException) {
					remoteException.printStackTrace();
				}
				try {
					subject.setState(state);
				} catch (RemoteException remoteException) {
					remoteException.printStackTrace();
				}
				//Game.edit.selectx--;if (Game.edit.selectx<0) {Game.edit.selectx++;} Game.edit.moved = true;
			}
			else if (i==right) {
				State state= null;
				try {
					state = new State(Game.observer.getId(),"right  edit");
				} catch (RemoteException remoteException) {
					remoteException.printStackTrace();
				}
				try {
					subject.setState(state);
				} catch (RemoteException remoteException) {
					remoteException.printStackTrace();
				}
				//Game.edit.selectx++;if (Game.edit.selectx>=Game.map.width) {Game.edit.selectx--;} Game.edit.moved = true;
			}
			else if (i==select) {
				State state= null;
				try {
					state = new State(Game.observer.getId(),"select  edit");
				} catch (RemoteException remoteException) {
					remoteException.printStackTrace();
				}
				try {
					subject.setState(state);
				} catch (RemoteException remoteException) {
					remoteException.printStackTrace();
				}
				//Game.edit.holding = true;
				}
			else if (i==cancel) {
				State state= null;
				try {
					state = new State(Game.observer.getId(),"cancel  edit");
				} catch (RemoteException remoteException) {
					remoteException.printStackTrace();
				}
				try {
					subject.setState(state);
				} catch (RemoteException remoteException) {
					remoteException.printStackTrace();
				}
				//Game.edit.ButtButton();
			}
			else if (i==start) {
				State state= null;
				try {
					state = new State(Game.observer.getId(),"start  edit");
				} catch (RemoteException remoteException) {
					remoteException.printStackTrace();
				}
				try {
					subject.setState(state);
				} catch (RemoteException remoteException) {
					remoteException.printStackTrace();
				}
				//new edu.ufp.inf.sd.rmi.project.client.jogo.menus.EditorMenu();
			}
		}
		
		if (i==dev1) {
			State state= null;
			try {
				state = new State(Game.observer.getId(),"dev1");
			} catch (RemoteException remoteException) {
				remoteException.printStackTrace();
			}
			try {
				subject.setState(state);
			} catch (RemoteException remoteException) {
				remoteException.printStackTrace();
			}
			//Game.gui.LoginScreen();
			}
		else if (i==dev2) {
			State state= null;
			try {
				state = new State(Game.observer.getId(),"dev2");
			} catch (RemoteException remoteException) {
				remoteException.printStackTrace();
			}
			try {
				subject.setState(state);
			} catch (RemoteException remoteException) {
				remoteException.printStackTrace();
			}
			//Game.load.LoadTexturePack("Test");
			}
		else if (i==dev3) {
			DevPathing++;
			switch (DevPathing) {

				case 1://Game.pathing.ShowCost=false;
					State state= null;
					try {
						state = new State(Game.observer.getId(),"dev3 1");
					} catch (RemoteException remoteException) {
						remoteException.printStackTrace();
					}
					try {
						subject.setState(state);
					} catch (RemoteException remoteException) {
						remoteException.printStackTrace();
					}
				break;
				case 2://Game.pathing.ShowHits=true;
					State state1= null;
					try {
						state1 = new State(Game.observer.getId(),"dev3 2");
					} catch (RemoteException remoteException) {
						remoteException.printStackTrace();
					}
					try {
						subject.setState(state1);
					} catch (RemoteException remoteException) {
						remoteException.printStackTrace();
					}
				break;
				case 3:
					State state3= null;
					try {
						state3 = new State(Game.observer.getId(),"dev3 3");
					} catch (RemoteException remoteException) {
						remoteException.printStackTrace();
					}
					try {
						subject.setState(state3);
					} catch (RemoteException remoteException) {
						remoteException.printStackTrace();
					}
					//Game.pathing.ShowHits=false;Game.pathing.ShowCost=true;DevPathing=0;
					break;
			}
		}
		else if (i==dev4) {
			State state= null;
			try {
				state = new State(Game.observer.getId(),"dev4");
			} catch (RemoteException remoteException) {
				remoteException.printStackTrace();
			}
			try {
				subject.setState(state);
			} catch (RemoteException remoteException) {
				remoteException.printStackTrace();
			}
			//Game.btl.EndTurn();
		}
		else if (i==dev5) {
			State state= null;
			try {
				state = new State(Game.observer.getId(),"dev5");
			} catch (RemoteException remoteException) {
				remoteException.printStackTrace();
			}
			try {
				subject.setState(state);
			} catch (RemoteException remoteException) {
				remoteException.printStackTrace();
			}
			//Game.player.get(Game.btl.currentplayer).npc = !Game.player.get(Game.btl.currentplayer).npc; Game.btl.EndTurn();
		}
		else if (i==dev6) {
			State state= null;
			try {
				state = new State(Game.observer.getId(),"dev6");
			} catch (RemoteException remoteException) {
				remoteException.printStackTrace();
			}
			try {
				subject.setState(state);
			} catch (RemoteException remoteException) {
				remoteException.printStackTrace();
			}
			//new edu.ufp.inf.sd.rmi.project.client.jogo.menus.StartMenu();
		}
	}
	public void keyReleased(KeyEvent e) {
		int i=e.getKeyCode();
		if (Game.GameState==Game.State.EDITOR) {
			if (i==select) {
				State state= null;
				try {
					state = new State(Game.observer.getId(),"edit");
				} catch (RemoteException remoteException) {
					remoteException.printStackTrace();
				}
				try {
					subject.setState(state);
				} catch (RemoteException remoteException) {
					remoteException.printStackTrace();
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
