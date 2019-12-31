
import java.util.*;







public class Player
{
	private int id;
	private String name;
	private int score;
	private HashMap<Integer,Contestant> finalFour;
	private HashMap<Integer,Contestant> wildcards;
	private Contestant winner;


	public Player(String n, int i, int s)
	{
		id = i;
		score = s;
		name = new String(n);
		finalFour = new HashMap<>();
		wildcards = new HashMap<>();
		winner = null;
	}

	public void addFF(Contestant c)
	{
		if(finalFour.size()<4){
			finalFour.put(c.getID(),c);
		}
		else{
			System.out.println("Cannot add more contestants to Final Four");
		}
	}

	public Contestant findFF(Contestant c)
	{
		Contestant result=null;

		if(finalFour.get(c.getID())!=null)
		{
			result = finalFour.get(c.getID());
		}

		return result;
	}

	public HashMap<Integer,Contestant> getFF()
	{
		return finalFour;
	}

	public void addWC(Contestant c){
		if(wildcards.size()<2){
			wildcards.put(c.getID(),c);
		}
		else{
			System.out.println("Cannot add more contestants to wildcards");
		}
	
	}

	public Contestant findWC(Contestant c){
		Contestant result = wildcards.get(c.getID());
		return result;
	}

	public HashMap<Integer,Contestant> getWC(){
		return wildcards;
	}

	public int getID()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public int getScore()
	{
		return score;
	}

	public void setScore(int i)
	{
		score = score+i;
	}

	public Contestant getWinner(){
		return winner;
	}

	public boolean isWinner(Contestant c){
		return c.getID()==winner.getID();
	}
	public void setWinner(Contestant c){
		if(finalFour.containsValue(c)){
			winner = c;
		}
		else{
			System.out.println("Cannot set a winner who is not in final four");
		}
		
	}

	public String toString()
	{
		StringBuilder s = new StringBuilder();

		s.append("Player: "+name+", Score: "+score+"\n");

		if(winner!=null){
			s.append("\t\t\tWinner: "+winner.getName()+"\n");
		}
		

		if(!finalFour.isEmpty()){
			s.append("\t\t\tFinal Four:\n\t\t\t");
			for(Contestant c: finalFour.values()){
				s.append(c.toString()+"\t\t\t");
			}
		}

		

		if(!wildcards.isEmpty()){
			s.append("\t\t\tWildcards:\n\t\t\t");
			for(Contestant c: wildcards.values()){
				s.append(c.toString()+"\t\t\t");
			}

		}


		return s.toString();
	}



}