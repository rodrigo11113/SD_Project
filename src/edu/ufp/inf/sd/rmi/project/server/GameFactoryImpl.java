package edu.ufp.inf.sd.rmi.project.server;





import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;


public class GameFactoryImpl extends UnicastRemoteObject implements GameFactoryRI {
    private DBMockup db;
     private HashMap<String , GameSessionRI> login;
    public GameFactoryImpl() throws RemoteException {
        super();
        this.db=new DBMockup();
        this.login=new HashMap<>();
    }

    public DBMockup getDb() {
        return db;
    }

    @Override
    public boolean register(String username, String pwd) {
        if(db.exists(username,pwd))
            return false;
        db.register(username,pwd);
        return true;
    }

    @Override
    public GameSessionRI login(String username, String pwd) throws RemoteException {
        if(db.exists(username, pwd)){
            if(login.containsKey(username)){
                System.out.println("User efetuou Login com sucesso na sessao");
                return login.get(username);
            }
                else{
            GameSessionRI gameSessionRI=new GameSessionImpl(this,username);
                login.put(username,gameSessionRI);
                System.out.println("Sessao criada para o User com sucesso");
                return gameSessionRI;}

        }
        return null;
    }

    @Override
    public void removeSession(String username) throws RemoteException{
        this.login.remove(username);
    }
}
