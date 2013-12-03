package coachingTools;

import graphics.frontend.BackgroundImage;
import graphics.frontend.BallModel;

import java.util.ArrayList;
import java.util.Random;

import core.GameEngine;
import util.Point;

public class Game {
	private int period, speed = 100;
	private Team team1;
	private Team team2;
	private BallModel ball;
	private BackgroundImage gameField;
	
	public boolean duringPass;

	public Game() {
		super();
		gameField = new Field();
		team1 = new Team("BestTeamEver", new float[] {1f, 0f, 0f, 1.0f}, Team.FieldHalf.Left);
		team2 = new Team("BesterTeamEver", new float[] {.02f, 0.2f, 1.0f, 1.0f}, Team.FieldHalf.Right);
		ball = new BallModel(new Point(0,0), new float[]{0f,0f,0f,1f});
	}
	
	//throws the ball to a random player on the thrower's team for a ThrowIn
	public void throwIn(ArrayList<Player> players, Player p){
		p.setBall(true);
		int num = GameEngine.rgen.nextInt(11);
		
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

	//kicks the ball to a random player from an arrayList of players (a goal kick is made by the goalie)
	public void goalKick( ArrayList<Player> players, Player p){

		if (((Player)p).isGoalie()){
			p.setBall(true);
			int num = GameEngine.rgen.nextInt(11);

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
	
	// Kicks the ball form the corner to a player on the kickers team with a chance for a goal of interception.
	public void cornerKick(Player p, ArrayList<Player> offensiveTeam, ArrayList<Player> defensiveTeam){
		p.setBall(true);
		
		int totalPlayers = offensiveTeam.size() + defensiveTeam.size();
		
		int num = GameEngine.rgen.nextInt(totalPlayers);
		
		if(offensiveTeam.get(num) != p){
			if(num < offensiveTeam.size()) {
				p.pass(offensiveTeam.get(num));
				
				//about 1 in 40 corner kicks result in a goal
				//part of this was already taken into account when we picked a random player
				//from either team to receive the ball
				int shot = GameEngine.rgen.nextInt(20);
				if(shot == 0)
					((Player) offensiveTeam.get(num)).scoreGoal();
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
	
	//kicks the ball to a random player from an arrayList of players to start a round
	public void kickOff(Player p, ArrayList<Player> players){
		p.setBall(true);
		
		int num = GameEngine.rgen.nextInt(11);
		
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
	
	
	// ------ getters and setters ------ \\
	
	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setTeam1(Team team1) {
		this.team1 = team1;
	}

	public Team getTeam1() {
		return team1;
	}
	
	public Team getTeam2() {
		return team2;
	}

	public void setTeam2(Team team2) {
		this.team2 = team2;
	}

	public BackgroundImage getGameField() {
		return gameField;
	}
	
	public ArrayList<Player> getAllPlayers() {
		ArrayList<Player> allPlayers = new ArrayList<Player>();
		allPlayers.addAll(team1.getPlayers());
		allPlayers.addAll(team2.getPlayers());
		return allPlayers;		
	}
	
	public int getTeam1Score() {
		return team1.getScore();
	}
	
	public int getTeam2Score() {
		return team2.getScore();
	}

	public void setGameField(BackgroundImage gameField) {
		this.gameField = gameField;
	}
	
	public Player getPlayerWithBall(){
		for(Player player: getAllPlayers()){
			if(player.hasBall())
				return player;
		}
		return null;
		
	}
	
	public Team getTeamWithBall(){
		for(Player player: team1.getPlayers()){
			if(player.hasBall())
				return team1;
		}
		
		for(Player player: team2.getPlayers()){
			if(player.hasBall())
				return team2;
		}
		
		return null;
	}
	
	public Player getOpposingPlayerClosestBall(){
		float cdistance = 100f;
		Player ballHolder = getPlayerWithBall();
		float ballX = ballHolder.getDisplay().getRect().getX();
		float ballY = ballHolder.getDisplay().getRect().getY();
		int index = 0;
		
		if(team1 == getTeamWithBall()){
			for(int i = 0; i < team2.getPlayers().size(); i++){
				float x = team2.getPlayers().get(i).getDisplay().getRect().getX();
				float y = team2.getPlayers().get(i).getDisplay().getRect().getY();
				float d = (float) Math.sqrt(Math.pow((x-ballX), 2) + Math.pow((y-ballY), 2));
				if(cdistance > d){
					cdistance = d;
					index = i;
				}	
			}
			return team2.getPlayers().get(index);
		} else {
			for(int i = 0; i < team1.getPlayers().size(); i++){
				float x = team1.getPlayers().get(i).getDisplay().getRect().getX();
				float y = team1.getPlayers().get(i).getDisplay().getRect().getY();
				float d = (float) Math.sqrt(Math.pow((x-ballX), 2) + Math.pow((y-ballY), 2));
				if(cdistance > d){
					cdistance = d;
					index = i;
				}
				
			}
			return team1.getPlayers().get(index);
		}
		
	}
	
	public void dontMoveOnTop(){
		ArrayList<Player> players = getAllPlayers();
		float width = players.get(0).getDisplay().getRect().getWidth()/2;
		float height = players.get(0).getDisplay().getRect().getHeight()/2;
		
		for(Player player: players){
			ArrayList<Player> players2 = getAllPlayers();
			players2.remove(player);
			float x = player.getDisplay().getRect().getX();
			float y = player.getDisplay().getRect().getY();
			
			for(Player player2: players2){
				float x2 = player2.getDisplay().getRect().getX();
				float y2 = player2.getDisplay().getRect().getY();
				if(x+width > x2 - width && x-width < x2-width && Math.abs(y-y2) < height){
					player.move(-0.025f, 0f, 1);
					player2.move(0.025f, 0f, 1);
				}
				if(x-width < x2+width && x+width > x2+width && Math.abs(y-y2) < height) {
					player.move(0.025f, 0f, 1);
					player2.move(-0.025f, 0f, 1);
				}
				if(y+height > y2 - height && y-height < y2-height && Math.abs(x-x2) < width){
					player.move(0f, -0.025f, 1);
					player2.move(0f, 0.025f, 1);
				}
				if(y-height < y2+height && y+height > y2+height && Math.abs(x-x2) < width) {
					player.move(0f, 0.025f, 1);
					player2.move(0f, -0.025f, 1);
				}
			}
		}
	}

	public void go() {
		ArrayList<Player> players1 = team1.getPlayers();
		ArrayList<Player> players2 = team2.getPlayers();
		
		Player player = getPlayerWithBall();
		Team team = getTeamWithBall();
		
		dontMoveOnTop();

		
		float minX = gameField.getRect().getX() - gameField.getRect().getWidth()/2;
		float maxX = gameField.getRect().getX() + gameField.getRect().getWidth()/2;
		float minY = gameField.getRect().getY() - gameField.getRect().getHeight()/2;
		float maxY = gameField.getRect().getY() + gameField.getRect().getHeight()/2;
		
		
		for(int i = 1; i < players1.size(); i++){
			int n = new Random().nextInt(11);
			n -= 5;
			players1.get(i).move((float)(0.01+n*0.005), (float)(n*0.005), 1);
			
			if(players1.get(i).getDisplay().getRect().getX() <= minX)
				players1.get(i).move(0.2f, 0f, 1);
			if(players1.get(i).getDisplay().getRect().getX() >= maxX)
				players1.get(i).move(-0.2f, 0f, 1);
			if(players1.get(i).getDisplay().getRect().getY() <= minY)
				players1.get(i).move(0f, 0.2f, 1);
			if(players1.get(i).getDisplay().getRect().getY() >= maxY)
				players1.get(i).move(0f, -0.2f, 1);
			
			//do stuff depending on distance to ball and closest player using getopposingplayerclosestball

		}
		
		
	}
	
	
	
	
}
