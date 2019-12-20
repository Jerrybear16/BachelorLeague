








public class Contestant
{
	private String name;
	private Integer id;

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

	public String toString(){

		StringBuilder b = new StringBuilder();

		b.append("Contestant: "+name);

		return b.toString();
	}
}