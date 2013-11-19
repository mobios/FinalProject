package coachingTools;

import java.util.ArrayList;
import java.util.Random;

public class SoccerGame extends Game {

	public SoccerGame() {
		super();

		gameField = new SoccerField();
		team1 = new SoccerTeam("BestTeamEver");
		team2 = new SoccerTeam("BesterTeamEver");
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

	public void cornerKick(){
		
		
		
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
