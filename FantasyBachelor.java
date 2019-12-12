


import java.io.*;
import java.util.*;





public class FantasyBachelor
{

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
		String nextPlayer = s.nextLine();
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




	private static void savePlayers(File pFile, ArrayList<Player> players){
		PrintWriter p=null;
		File f = new File("PlayerTemp.txt");

		try{
			f.createNewFile();
		}
		catch(IOException e)
		{
			Say("Something went wrong");
			System.exit(0);
		}
		

		try{
			p = new PrintWriter(f);
		}
		catch(FileNotFoundException e)
		{
			Say("Temp Player File not found");
			System.exit(0);
		}

		//write the new player data to the temp file
		writePlayers(p, players);

		//set temp file to the original, close the writer, and delete the temp
		f.renameTo(pFile);
		p.close();
		f.delete();

	}

		private static void writePlayers(PrintWriter p, ArrayList<Player> players){

			StringBuilder b;
			for(int i=0;i<players.size();i++)
			{
				b = new StringBuilder();
				b.append(players.get(i).getName()+ " ");
				b.append(players.get(i).getID()+ " ");
				b.append(players.get(i).getScore());
				p.println(b.toString());
				p.flush();
			}

		}

	private static void readConts(ArrayList<Contestant> c, Scanner s){
		String nextCont = s.nextLine();
		String[] contSplit = nextCont.split(",");
		Contestant cont = makeCont(contSplit);

		c.add(cont);
	}
		private static Contestant makeCont(String[] cs){
			Contestant nc;
			String contName = cs[0];
			int id = Integer.parseInt(cs[1]);
			nc = new Contestant(contName, id);

			return nc;
		}

	public static void main(String[] args)
	{
		//declare necessary varialbles
		File playerF;//player file
		File contF;//contestant file
		Scanner s =null;
		ArrayList<Player> players;
		ArrayList<Contestant> conts;
		Scoreboard sb;
		PrintWriter p;

		//initialize list of players
		players = new ArrayList<>();
		conts = new ArrayList<>();
		//initialize player file
		playerF = new File("Players.txt");
		contF = new File("contestants.txt");

		//initialize scanner to player file
		try{
			s = new Scanner(playerF);
		}
		catch(FileNotFoundException e){
			Say("Player File Not Found");
			System.exit(0);
		}


		//read all the lines form the player file
		while(s.hasNext())
		{
			readPlayers(players,s);
		}
		
		//sort the players by score and add them to the scoreboard
		sortByScore(players);
		sb = new Scoreboard(players);
		s.close();

		//initialize scanner to contestant file
		try{
			s = new Scanner(contF);
		}
		catch(FileNotFoundException e)
		{
			Say("Contestant File not found");
			System.exit(0);
		}

		while(s.hasNext())
		{
			readConts(conts,s);
		}

		for(int i=0;i<conts.size();i++)
		{
			Say(conts.get(i).toString());
		}

		//save the player data back to the file
		savePlayers(playerF,players);

		s.close();
	}
}