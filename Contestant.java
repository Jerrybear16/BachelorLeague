








public class Contestant
{
	private String name;
	private int id;

	public Contestant(String n, int i)
	{
		name = new String(n);
		id = i;
	}

	public int getID()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}
}