


import java.io.*;
import java.util.*;





public class FantasyBachelor
{

	static File playerF;
	static Scanner s;
	static ArrayList<Player> players;
	static Scoreboard sb;

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

	private static void readPlayers(ArrayList<Player> players, Scanner s){
		String nextPlayer = getPlayers(s);
		String[] playerSplit = nextPlayer.split(" ");
		Player newPlayer = makePlayer(playerSplit);
		players.add(newPlayer);
	}

	private static void sortByScore(ArrayList<Player> p){
		
		mergeSort(p,p.size());
	}

		private static void mergeSort(ArrayList<Player> p,int s){
			if(s<2){
				return;
			}
			int mid = s/2;
			ArrayList<Player> left = new ArrayList<>();
			ArrayList<Player> right = new ArrayList<>();

			for(int i=0;i<mid;i++){
				left.add(p.get(i));
			}
			for(int i=mid;i<s;i++){
				right.add(p.get(i));
			}

			mergeSort(left,mid);
			mergeSort(right,s-mid);
			merge(p,left,right,mid,s-mid);
		}
			private static void merge(ArrayList<Player> p, ArrayList<Player> l, ArrayList<Player> r, int left, int right){
				int i=0, j=0,k=0;

				while(i<left&& j<right)
				{
					if(l.get(i).getScore()>=r.get(j).getScore()){
						p.set(k,l.get(i));
						k++;
						i++;
					}
					else{
						p.set(k,r.get(j));
						k++;
						j++;
					}
				}
				while(i<left){
					p.set(k,l.get(i));
					k++;
					i++;
				}
				while(j<right){
					p.set(k,r.get(j));
					k++;
					j++;
				}
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
			readPlayers(players,s);
		}

		sortByScore(players);
		sb = new Scoreboard(players);
		Say(sb.toString());
	}
}