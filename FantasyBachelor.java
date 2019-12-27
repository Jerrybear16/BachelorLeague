


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
		for(int i=0;i<intS.length-1;i++)
		{
			p.addFF(c.get(Integer.parseInt(intS[i])));
		}
		p.setWinner(c.get(Integer.parseInt(intS[intS.length-1])));

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
				b.append(players.get(i).getWinner().getID());
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
			boolean active=false;

			if(Integer.parseInt(cs[2])==0){
				active = false;
			}
			else if(Integer.parseInt(cs[2])==1){
				active = true;
			}
			nc = new Contestant(contName, id,active);

			return nc;
		}

	private static void printMenu(int x){

		switch(x){

			case 0:{
				Say("Enter the action you want to take: ");
				Say("\t1. Enter player contestant selections (final fours)");
				Say("\t2. Enter player contestant selections (wildcards)");
				Say("\t3. Enter player winner selection");
				Say("\t4. Enter a score");
				Say("\t5. Reset scores");
				Say("\t6. Exit");
				break;
			}
			case 1:{
				Say("Enter the scoring play");
				Say("\t1. One-on-One date");
				Say("\t2. One-on-One date, but did not recieve rose");
				Say("\t3. 'Not Here for the right reasons'");
				Say("\t4. Cries");
				Say("\t5. Tells the Bachelor she's in love or falling in love with him");
				Say("\t6. Girl gets last rose of the rose ceremony");
				Say("\t7. Recieves a group date rose");
				Say("\t8. Winning team of group date (only valid for main girl)");
				Say("\t9. On 2 on 1 date");
				break;
			}
		}
		
	}

	private static void updatePlayers(Contestant c, ArrayList<Player> p, int s,int sp){

		for(Player player:p){
			if(player.isWinner(c)){
				Say(player.getName()+" selected "+c.getName()+" as the winner. Adding bonus");
				if(sp==1||sp==7){
					s+=10;
				}else if(sp==2){
					s-=10;
				}else if(sp==3||sp==4||sp==5||sp==6){
					s+=5;
				}
			}
			if(player.getFF().containsValue(c)){
				Say(player.getName()+" has "+c.getName()+" in final 4");
				player.setScore(s);
				Say(player.getName()+" score is now "+player.getScore());
			}
			if(player.getWC().containsValue(c)){
				Say(player.getName()+" has "+c.getName()+" this week");
				player.setScore(s);
				Say(player.getName()+" score is now "+player.getScore());
			}
		}
	}

	private static void printMenu(ArrayList<Player> p){

		Say("Select a Player\n");
		for(int i=0;i<p.size();i++){
			Say((i+1)+". "+p.get(i).toString());
		}
	}
	private static void printMenu(HashMap<Integer,Contestant> c){
		Collection<Contestant> conts = c.values();
		Say("Select a contestant\n");
		for(Contestant cont:conts){
			Say(cont.getID()+". "+cont.toString());
		}

	}

	private static Player getPlayer(Scanner s, ArrayList<Player> p){
		int x;
		
		Player ret;

		while(true)
		{
			x = getInput(s);
			if(x>0 && x<=p.size()){
				break;
			}
			Say("invalid entry, try again");
		}
		

		ret = p.get(x-1);

		return ret;
	}

	
	private static Contestant getContestant(Scanner s, HashMap<Integer,Contestant> c){
		Contestant ret;
		int x;

		while(true)
		{
			x = getInput(s);
			if(x>0 && x<=c.size()){
				break;
			}
			Say("Invalid entry, try again");
		}

		ret = c.get(x);

		return ret;
	}

	private static int getScoringPlay(Scanner s){
		
		int x=0;


		while(true){
			x = getInput(s);
			if(x>0 && x<=9){
				break;
			}
			Say("invalid entry, try again");
		}
		

		return x;

	}

	private static int getInput(Scanner s){
		int ans=0;
		boolean invalid =true;

		while(invalid){
			try{
				invalid =false;
				ans = Integer.parseInt(s.nextLine());
			}
			catch(NumberFormatException e){
				Say("Entry is not a number, try again");
				invalid = true;
			}
		}
		

		return ans;
	}

	public static void main(String[] args)
	{
		//declare necessary varialbles
		File playerF;//player file
		File contF;//contestant file
		Scanner s =null;//for scanning files
		Scanner input = new Scanner(System.in);//for user input
		ArrayList<Player> players;//contains players
		HashMap<Integer,Contestant> conts;//contains contestants
		Scoreboard sb;//for displaying current game state and 
		PrintWriter p;//for saving data back to files
		Player pl;//for storing a player to be acted on
		Contestant c;//for storing a contestant to be acted on

		boolean running=true;//main loop boolean
		int entry;//user input for main menue
		int scoringPlay;//user input for what play was scored
		int score;//score to be added to players

		//initialize list of players and contestants
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

			Say("");
			Say(sb.toString());
			printMenu(0);
			entry = getInput(input);


			switch(entry){

				case 1:{//print menue of players, select a player, add a contestant to their list
					printMenu(players);
					pl = getPlayer(input,players);
					Say(pl.toString());
					printMenu(conts);
					c = getContestant(input,conts);
					Say(c.toString());
					pl.addFF(c);
					break;
				}
				case 2:{//same thing but for wildcard list
					printMenu(players);
					pl = getPlayer(input,players);
					Say(pl.toString());
					printMenu(conts);
					c = getContestant(input,conts);
					Say(c.toString());
					pl.addWC(c);
					break;
				}
				case 3:{//same thing but for winner
					printMenu(players);
					pl = getPlayer(input,players);
					Say(pl.toString());
					printMenu(conts);
					c = getContestant(input,conts);
					Say(c.toString());
					pl.setWinner(c);
					break;
				}
				case 4:{//print contetant list, select contestant, select a scoring play, update players who have that contestant
					printMenu(conts);
					c = getContestant(input,conts);
					printMenu(1);
					scoringPlay = getScoringPlay(input);
					score = sb.getScore(scoringPlay);
					updatePlayers(c,players,score,scoringPlay);
					sortByScore(players);
					break;
				}
				case 5:{//take every score and make it 0
					for(Player play:players){
						play.setScore(-play.getScore());
					}
					break;
				}
				case 6:{//exit game
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