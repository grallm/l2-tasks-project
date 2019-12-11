package network;
import java.util.ArrayList;

public class Network {
	private ArrayList<Member> network;
	private String name; 
	
	public Network(String name) {
		this.network = new ArrayList<Member>();
		this.name = name; 
	}
	
	public void addMember(Member member) {
		network.add(member); 
	}
	
	public void removeMember(Member member) {
		int indice = 0; 
		
		while(!(member.getName().equals(member.getName()))){
			indice ++; 
		}
		
		network.remove(indice); 
	}
}