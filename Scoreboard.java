

import java.util.ArrayList;



public class Scoreboard
{
	private ArrayList<Player> players;

	public Scoreboard(ArrayList<Player> p){
		players = new ArrayList<>(p);
	}

	public String toString(){
		StringBuilder b = new StringBuilder();

		for(int i=0;i<players.size();i++)
		{
			b.append(players.get(i).toString());
			b.append("\n");
		}

		return b.toString();
	}

	
}