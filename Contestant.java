








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

	public void eliminate(){
		active =false;
	}
	public void activate(){
		active = true;
	}

	public String activeString(){
		if(active){
			return "1";
		}
		else{
			return "0";
		}
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