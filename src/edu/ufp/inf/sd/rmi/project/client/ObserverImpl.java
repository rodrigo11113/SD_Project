package edu.ufp.inf.sd.rmi.project.client;






import edu.ufp.inf.sd.rmi.project.client.jogo.engine.Game;
import edu.ufp.inf.sd.rmi.project.client.jogo.menus.MenuHandler;
import edu.ufp.inf.sd.rmi.project.server.State;
import edu.ufp.inf.sd.rmi.project.server.SubjectRI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class ObserverImpl extends UnicastRemoteObject implements ObserverRI {
private String id;
private State LastObserverState;
protected SubjectRI subjectRI;




    public ObserverImpl(String username) throws RemoteException {
        this.id=username;

    }
    @Override
    public String getId() {
        return id;
    }
    @Override
    public void setId(String id) {
        this.id = id;
    }
    @Override
    public void setLastObserverState(State lastObserverState) {
        LastObserverState = lastObserverState;
    }
    @Override
    public SubjectRI getSubjectRI() {
        return subjectRI;
    }
    @Override
    public void setSubjectRI(SubjectRI subjectRI) {
        this.subjectRI = subjectRI;
    }
    @Override
    public State getLastObserverState() {
        return LastObserverState;
    }


    @Override
    public void update() throws RemoteException {

        this.LastObserverState= subjectRI.getState();
        if(this.LastObserverState.getInfo().equals("start")){
            //new Game(subjectRI.getMapa(),subjectRI);
            PingRunnable p=new PingRunnable(this.subjectRI,this);
           new Thread(p).start();
        }
        edu.ufp.inf.sd.rmi.project.client.jogo.players.Base ply;
        if(this.LastObserverState.getInfo().equals("up play")){
             ply = Game.player.get(Game.btl.currentplayer);
            ply.selecty--;if (ply.selecty<0) {ply.selecty++;
        }}
        if(this.LastObserverState.getInfo().equals("down play")){
             ply = Game.player.get(Game.btl.currentplayer);
            ply.selecty++;if (ply.selecty>=Game.map.height) {ply.selecty--;}
        }
        if(this.LastObserverState.getInfo().equals("left play")){
            ply = Game.player.get(Game.btl.currentplayer);
            ply.selectx--;if (ply.selectx<0) {ply.selectx++;
        } }
        if(this.LastObserverState.getInfo().equals("right play")){
            ply = Game.player.get(Game.btl.currentplayer);
            ply.selectx++;if (ply.selectx>=Game.map.width) {ply.selectx--;}
        }
        if(this.LastObserverState.getInfo().equals("select play")){
            Game.btl.Action();
        }
        if(this.LastObserverState.getInfo().equals("cancel play")){
            Game.player.get(Game.btl.currentplayer).Cancle();
        }
        if(this.LastObserverState.getInfo().equals("start play")){
            new edu.ufp.inf.sd.rmi.project.client.jogo.menus.Pause();
        }
        if(this.LastObserverState.getInfo().equals("up  edit")){
            Game.edit.selecty--;if (Game.edit.selecty<0) {Game.edit.selecty++;} Game.edit.moved = true;
        }
        if(this.LastObserverState.getInfo().equals("down  edit")){
            Game.edit.selecty++;if (Game.edit.selecty>=Game.map.height) {Game.edit.selecty--;} Game.edit.moved = true;
        }
        if(this.LastObserverState.getInfo().equals("left  edit")){
            Game.edit.selectx--;if (Game.edit.selectx<0) {Game.edit.selectx++;} Game.edit.moved = true;
        }
        if(this.LastObserverState.getInfo().equals("right  edit")){
            Game.edit.selectx++;if (Game.edit.selectx>=Game.map.width) {Game.edit.selectx--;} Game.edit.moved = true;
        }
        if(this.LastObserverState.getInfo().equals("select  edit")){
            Game.edit.holding = true;
        }
        if (this.LastObserverState.getInfo().equals("cancel  edit")){
            Game.edit.ButtButton();
        }
        if (this.LastObserverState.getInfo().equals("start  edit")){
            new edu.ufp.inf.sd.rmi.project.client.jogo.menus.EditorMenu();
        }
        if(this.LastObserverState.getInfo().equals("dev1")){
            Game.gui.LoginScreen();
        }
        if (this.LastObserverState.getInfo().equals("dev2")){
            Game.load.LoadTexturePack("Test");
        }
        if (this.LastObserverState.getInfo().equals("dev3 1")){
            Game.pathing.ShowCost=false;
        }
        if(this.LastObserverState.getInfo().equals("dev3 2")){
            Game.pathing.ShowHits=true;
        }
        if(this.LastObserverState.getInfo().equals("dev3 3")){
            Game.pathing.ShowHits=false;Game.pathing.ShowCost=true;//DevPathing=0;
        }
        if(this.LastObserverState.getInfo().equals("dev4")){
            Game.btl.EndTurn();
        }
        if(this.LastObserverState.getInfo().equals("dev5")){
            Game.player.get(Game.btl.currentplayer).npc = !Game.player.get(Game.btl.currentplayer).npc; Game.btl.EndTurn();
        }
        if(this.LastObserverState.getInfo().equals("dev6")){
            new edu.ufp.inf.sd.rmi.project.client.jogo.menus.StartMenu();
        }
        if(this.LastObserverState.getInfo().equals("edit")){
            Game.edit.holding = false;
        }

        if(this.LastObserverState.getInfo().equals("end turn")){
            MenuHandler.CloseMenu();
			Game.btl.EndTurn();
        }
        if(this.LastObserverState.getInfo().contains("buy")){
            String[] arrOfStr = this.LastObserverState.getInfo().split(",");
            Game.btl.Buyunit(Integer.parseInt(arrOfStr[1]), Integer.parseInt(arrOfStr[2]),Integer.parseInt(arrOfStr[3]));
            MenuHandler.CloseMenu();
        }

}}
