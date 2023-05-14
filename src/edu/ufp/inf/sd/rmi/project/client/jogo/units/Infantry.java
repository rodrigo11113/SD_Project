package edu.ufp.inf.sd.rmi.project.client.jogo.units;

public class Infantry extends Base {
	public Infantry(int owner, int xx, int yy, boolean active) {
		super(owner, xx, yy, active);
		name = "Infantry";
		nick = "Inf";
		desc = "Weakest edu.ufp.inf.sd.rmi.project.client.jogo.edu.ufp.inf.sd.rabbitmqservices.projeto.jogo.edu.ufp.inf.sd.rabbitmqservices.projeto.jogo.buildings.edu.ufp.inf.sd.rabbitmqservices.projeto.jogo.units here.";
		img = 0;
		speed = 4;
		raider = true;
	}
}
