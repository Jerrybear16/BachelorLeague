








public class Contestant
{
	private String name;
	private Integer id;
	private boolean active;

	public Contestant(String n, int i,boolean b)
	{
		name = new String(n);
		id = i;
		active = b;
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

		b.append(name);
		if(active){
			b.append(" (active)\n");
		}
		else{
			b.append(" (inactive)\n");
		}

		return b.toString();
	}
}