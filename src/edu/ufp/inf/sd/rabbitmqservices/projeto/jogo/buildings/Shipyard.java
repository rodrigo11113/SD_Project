package edu.ufp.inf.sd.rabbitmqservices.projeto.jogo.buildings;

//import edu.ufp.inf.sd.rabbitmqservices.projeto.jogo.engine.Game;

public class Shipyard extends Base {

	public Shipyard(int owner, int xx, int yy) {
		super(owner, xx, yy);
		name="Capital";
		desc="Creates water edu.ufp.inf.sd.rabbitmqservices.projeto.jogo.units.";
		img = 4;
		Menu = "shipyard";
		//Game.map.map[yy][xx].swim = true;
	}
}
