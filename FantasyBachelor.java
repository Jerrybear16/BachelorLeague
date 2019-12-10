


import java.io.*;
import java.util.*;





public class FantasyBachelor
{

	static File playerF;
	static Scanner s;
	static String nextPlayer;
	static ArrayList<Player> players;
	static String[] playerSplit;
	static Player newPlayer;

	private static String getPlayers(Scanner s){
		String ret = new String(s.nextLine());

		return ret;
	}

	private static void Say(String s){
		System.out.println(s);
	}

	private static Player makePlayer(String[] ps){
		Player np;
		String playerName = ps[0];
		int playerID = Integer.parseInt(ps[1]);
		int playerScore = Integer.parseInt(ps[2]);

		np = new Player(playerName,playerID,playerScore);

		return np;
	}

	public static void main(String[] args)
	{
		players = new ArrayList<>();
		playerF = new File("Players.txt");

		try{
			s = new Scanner(playerF);
		}
		catch(FileNotFoundException e){
			Say("File Not Found");
			System.exit(0);
		}



		while(s.hasNext())
		{
			nextPlayer = getPlayers(s);
			playerSplit = nextPlayer.split(" ");
			newPlayer = makePlayer(playerSplit);
			players.add(newPlayer);
		}

		Say(players.toString());
	}
}