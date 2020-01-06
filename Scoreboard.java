

import java.util.ArrayList;



public class Scoreboard
{
	private ArrayList<Player> players;

	public Scoreboard(ArrayList<Player> p){
		players = p;
	}

	public String toString(){
		StringBuilder b = new StringBuilder();

		for(int i=0;i<players.size();i++)
		{
			b.append(players.get(i).getName());
			b.append(" "+players.get(i).getScore());
			b.append("\n\t");
		}

		return b.toString();
	}

	public int getScore(int x){

		int ret=0;
		if(x==1){
			ret=25;
		}else if(x==2){
			ret=-35;
		}else if(x==3){
			ret=10;
		}else if(x==4){
			ret=5;
		}else if(x==5){
			ret=10;
		}else if(x==6){
			ret=5;
		}else if(x==7){
			ret=15;
		}else if(x==8){
			ret=5;
		}else if(x==9){
			ret =30;
		}

		return ret;
	}

	
}