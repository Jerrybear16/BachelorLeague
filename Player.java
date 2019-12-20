
import java.util.*;







public class Player
{
	private int id;
	private String name;
	private int score;
	private HashMap<Integer,Contestant> finalFour;
	private HashMap<Integer,Contestant> wildcards;


	public Player(String n, int i, int s)
	{
		id = i;
		score = s;
		name = new String(n);
		finalFour = new HashMap<>();
		wildcards = new HashMap<>();
	}

	public void addFF(Contestant c)
	{
		finalFour.put(c.getID(),c);
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
		wildcards.put(c.getID(),c);
	}

	public Contestant findWC(Contestant c){
		Contestant result = wildcards.get(c.getID());
		return result;
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
		score = i;
	}

	public String toString()
	{
		StringBuilder s = new StringBuilder();

		s.append("Player: "+name+", Score: "+score);

		return s.toString();
	}



}