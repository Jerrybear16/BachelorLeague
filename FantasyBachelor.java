


import java.io.*;
import java.util.*;





public class FantasyBachelor
{

	private static void Say(String s){
		System.out.println("\t"+s);
	}

	private static Player makePlayer(String[] ps,HashMap<Integer, Contestant> c){
		Player np;
		String playerName = ps[0];
		int playerID = Integer.parseInt(ps[1]);
		int playerScore = Integer.parseInt(ps[2]);

		np = new Player(playerName,playerID,playerScore);

		if(ps.length>=4){
			addFFs(np,c,ps[3]);
		}

		return np;
	}

	private static void addFFs(Player p, HashMap<Integer, Contestant> c,String s){
		String[] intS = s.split(",");
		for(int i=0;i<intS.length;i++)
		{
			p.addFF(c.get(Integer.parseInt(intS[i])));
		}

	}

	private static void readPlayers(ArrayList<Player> players, Scanner s, HashMap<Integer, Contestant> c){
		String nextPlayer = s.nextLine();
		String[] playerSplit = nextPlayer.split(" ");
		Player newPlayer = makePlayer(playerSplit,c);
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
			Collection<Contestant> c;
			for(int i=0;i<players.size();i++)
			{
				c = players.get(i).getFF().values();
				b = new StringBuilder();
				b.append(players.get(i).getName()+ " ");
				b.append(players.get(i).getID()+ " ");
				b.append(players.get(i).getScore()+" ");

				for(Contestant cont: c){
					b.append(cont.getID()+",");
				}

				p.println(b.toString());
				p.flush();
			}

		}

	private static void readConts(HashMap<Integer,Contestant> c, Scanner s){
		String nextCont = s.nextLine();
		String[] contSplit = nextCont.split(",");
		Contestant cont = makeCont(contSplit);

		c.put(cont.getID(),cont);
	}
		private static Contestant makeCont(String[] cs){
			Contestant nc;
			String contName = cs[0];
			int id = Integer.parseInt(cs[1]);
			nc = new Contestant(contName, id);

			return nc;
		}

	private static void printMenu(){
		Say("Enter the action you want to take: ");
		Say("\t1. Enter player contestant selections (final fours)");
		Say("\t2. Enter player contestant selections (wildcards)");
		Say("\t3. Enter a score");
		Say("\t4. Exit");
	}

	private static int getInput(Scanner s){
		String str = s.nextLine();
		int ans=0;
		try{
			
			ans = Integer.parseInt(str);
		}
		catch(NumberFormatException e){
			Say("Entry is not a number");
		}

		return ans;
	}

	public static void main(String[] args)
	{
		//declare necessary varialbles
		File playerF;//player file
		File contF;//contestant file
		Scanner s =null;
		Scanner input = new Scanner(System.in);
		ArrayList<Player> players;
		HashMap<Integer,Contestant> conts;
		Scoreboard sb;
		PrintWriter p;

		boolean running=true;
		int entry;

		//initialize list of players
		players = new ArrayList<>();
		conts = new HashMap<>();

		//initialize player file
		playerF = new File("Players.txt");
		contF = new File("contestants.txt");

		//initialize scanner to contestant file
		try{
			s = new Scanner(contF);
		}
		catch(FileNotFoundException e)
		{
			Say("Contestant File not found");
			System.exit(0);
		}

		//read all the lines from the contestant file
		while(s.hasNext())
		{
			readConts(conts,s);
		}

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
			readPlayers(players,s,conts);
		}
		
		//sort the players by score and add them to the scoreboard
		sortByScore(players);
		sb = new Scoreboard(players);
		s.close();

		
		//user interaction loop
		while(running){

			printMenu();
			entry = getInput(input);

			switch(entry){

				case 1:{
					Say("Select player");
					break;
				}
				case 2:{
					Say("Select player");
					break;
				}
				case 3:{
					Say("Select contstant");
					break;
				}
				case 4:{
					Say("Thanks for playing!");
					running = false;
					break;
				}
				default:{
					Say("Invalid entry");
					break;

				}
			}

		}
		

		//save the player data back to the file
		savePlayers(playerF,players);
		//saveContestants(contF,conts);

		s.close();
	}
}