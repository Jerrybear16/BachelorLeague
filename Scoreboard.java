

import java.util.ArrayList;



public class Scoreboard
{
	private ArrayList<Player> players;
	private int kisses;

	public Scoreboard(ArrayList<Player> p){
		players = p;
		kisses = 0;
	}

	public int getKisses(){
		return kisses;
	}

	public String toString(){
		StringBuilder b = new StringBuilder();

		for(int i=0;i<players.size();i++)
		{
			b.append(players.get(i).getName());
			b.append(" "+players.get(i).getScore());
			b.append("\n\t");
		}

		return b.toString();
	}

	public int getScore(int x){

		int ret=0;
		if(x==1){
			ret=25;//one on one date
		}else if(x==2){
			ret=-35;//one on one date, but did not recieve rose
		}else if(x==3){
			ret=10;//"not here for the right reasons"
		}else if(x==4){
			ret=5;//cries
		}else if(x==5){
			ret=10;//tells the bachelor she's in love or falling in love with him
		}else if(x==6){
			ret=15;//girl gets last rose of rose ceremony
		}else if(x==7){
			ret=15;//recieves a group date rose
		}else if(x==8){
			ret=5;//winning team of group date
		}else if(x==9){
			ret =30;//on 2 on 1 date
		}else if(x==10){
			ret =100;//first impression rose
		}else if(x==11){
			ret = 5;//tries to steal the bachelor from another girl
		}else if(x==12){
			ret = 5;//kissed the bachelor
			kisses++;
		}else if(x==13){
			ret = 5;//rose recieved
		}else if(x==14){
			ret = 5;//goes on group date
		}

		return ret;
	}

	
}