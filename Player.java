
import java.util.*;







public class Player
{
	private int id;
	private String name;
	private int score;
	private HashMap<Integer,Contestant> contestants;


	public Player(String n, int i, int s)
	{
		id = i;
		score = s;
		name = new String(n);
	}

	public void addContestant(Contestant c)
	{
		contestants.put(c.getID(),c);
	}

	public Contestant findContestant(Contestant c)
	{
		Contestant result=null;

		if(contestants.get(c.getID())!=null)
		{
			result = contestants.get(c.getID());
		}

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

	public String toString()
	{
		StringBuilder s = new StringBuilder();

		s.append("Player: "+name+"\nScore: "+score);

		return s.toString();
	}



}