package coachingTools;

import java.util.ArrayList;
import java.util.Random;

public class SoccerGame extends Game {

	public SoccerGame() {
		super(new SoccerField());

		gameField = new SoccerField();
		//team1 = new SoccerTeam("BestTeamEver");
		//team2 = new SoccerTeam("BesterTeamEver");
	}
	
	public void throwIn(ArrayList<Player> players, Player p){
		
		p.setBall(true);
		
		Random generator = new Random();
		
		int num = generator.nextInt(11);
		
		if(players.get(num) != p){
			p.pass(players.get(num));
		} else {
			if(num < 10){
				num ++;
			} else {
				num--;
			}
			
			p.pass(players.get(num));
		}
		
		
	}

	@Override
	public Team getTeam1() {
		return (SoccerTeam) team1;
	}
	
	@Override
	public Team getTeam2() {
		return (SoccerTeam) team2;
	}

	public void goalKick(Player p, ArrayList<Player> players){

		if (((SoccerPlayer)p).isGoalie()){
			p.setBall(true);

			Random generator = new Random();

			int num = generator.nextInt(11);

			if(players.get(num) != p){
				p.pass(players.get(num));
			} else {
				if(num < 10){
					num ++;
				} else {
					num--;
				}

				p.pass(players.get(num));
			}
		}

	}

	public void cornerKick(Player p, ArrayList<Player> offensiveTeam, ArrayList<Player> defensiveTeam){
		p.setBall(true);
		
		Random generator = new Random();
		int totalPlayers = offensiveTeam.size() + defensiveTeam.size();
		
		int num = generator.nextInt(totalPlayers);
		
		if(offensiveTeam.get(num) != p){
			if(num < offensiveTeam.size()) {
				p.pass(offensiveTeam.get(num));
				
				//about 1 in 40 corner kicks result in a goal
				//part of this was already taken into account when we picked a random player
				//from either team to receive the ball
				int shot = generator.nextInt(20);
				if(shot == 0)
					((SoccerPlayer) offensiveTeam.get(num)).scoreGoal();
			} else {
				p.pass(defensiveTeam.get(num - offensiveTeam.size()));
			}
		} else {
			if(num < totalPlayers/2){
				num++;
			} else {
				num--;
			}
		}
	}
	
	public void kickOff(Player p, ArrayList<Player> players){
		p.setBall(true);
		
		Random generator = new Random();
		
		int num = generator.nextInt(11);
		
		if(players.get(num) != p){
			p.pass(players.get(num));
		} else {
			if(num < 10){
				num ++;
			} else {
				num--;
			}
			
			p.pass(players.get(num));
		}
		
	}
	
}
