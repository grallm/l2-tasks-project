package exe;

import exceptions.*;
import network.*;

public class Main {

	public static void main(String[] args) {
		// TestMember();
		// TestNetwork();
		// TestServices();
		// TestTask();
		TestScenario(); 
	}

	/*
	 * Create Member
	 * Credit and Debit wallet
	 * Debit more than the balance
	 * */
	public static void TestMember(){
		// Try to create a Member
		Network network = null;
		try {
			network = (new Admin(100, "admin", new SocialClassNormal())).createNetwork("Network");
		} catch (AlreadyInNetwork alreadyInNetwork) {
			alreadyInNetwork.printStackTrace();
		}
		Member member1 = new Member(0, "Thomas", new SocialClassHalf(), network);

		// Add in and remove from the wallet
		/*member1.creditWallet(1000);
		System.out.println(member1.getWallet());
		try {
			member1.debitWallet(1000);
		} catch (MissAmountException e) {
			e.printStackTrace();
		}
		System.out.println(member1.getWallet());


		// Member.toString()
		System.out.println(member1);*/


		// Debit more than what he has
		/*try {
			member1.debitWallet(1000);
		} catch (MissAmountException e) {
			e.printStackTrace();
		}
		System.out.println(member1.getWallet());*/

		// What is the Network when unset ? null
		// System.out.println(member1.getNetwork());
	}

	/* All tests around Network and Admin linked with Network
	 *
	 * Create Network
	 * Member already in Networks
	 * Add Admin to Network
	 * Create Member from Admin and Network
	 * Add and Remove Member from Network
	 * */
	public static void TestNetwork(){
		Admin admin = new Admin(1000, "admin", new SocialClassNormal());

	//	Create a Network
		Network network = null;
		try {
			network = admin.createNetwork("First Network");
		} catch (AlreadyInNetwork alreadyInNetwork) {
			alreadyInNetwork.printStackTrace();
		}


	// Test if already in Network
		/*try {
			admin.createNetwork("First Network");
		} catch (AlreadyInNetwork alreadyInNetwork) {
			System.err.println(alreadyInNetwork.getNetwork().getName());
			System.err.println(alreadyInNetwork.getMessage());
		}
		try {
			admin.setNetwork(admin.getNetwork());
		} catch (AlreadyInNetwork alreadyInNetwork) {
			System.err.println(alreadyInNetwork.getNetwork().getName());
			System.err.println(alreadyInNetwork.getMessage());
		}*/


	//	Try to add an Admin to a Network
		/*try {
			admin.setNetwork(admin.getNetwork());
		} catch (AlreadyInNetwork | CantSetNetworkAdmin alreadyInNetwork) {
			alreadyInNetwork.printStackTrace();
		}*/
		/*try {
			admin.getNetwork().addMember(admin);
		} catch (AlreadyInNetwork | CantSetNetworkAdmin alreadyInNetwork) {
			alreadyInNetwork.printStackTrace();
		}*/


	//	Create a Member directly in Network
		/*System.out.println(
				admin.getNetwork()
						.createMember(100, "Member", new SocialClassZero()).getNetwork().getName()
		); // Check the name of the created Member's Network*/


	//	Add a Member by Network
		/*Member member1 = new Member(0, "Thomas", new SocialClassHalf());
		try {
			admin.getNetwork().addMember(member1);
		} catch (AlreadyInNetwork | CantSetNetworkAdmin alreadyInNetwork) {
			alreadyInNetwork.printStackTrace();
		}
		System.out.println(member1.getNetwork().getName());

	//	Remove from Network
		try {
			admin.getNetwork().removeMember(member1);
		} catch (NotInNetwork notInNetwork) {
			notInNetwork.printStackTrace();
		}
		System.out.println(member1.getNetwork());
		System.out.println(admin.getNetwork()); // Test Network.toString()*/


		//	Create a Member by Admin
		/*try {
			System.out.println(admin.createMember(100, "Member", new SocialClassZero()).getNetwork().getName());
		} catch (NotInNetwork alreadyInNetwork) {
			alreadyInNetwork.printStackTrace();
		}*/

	//	Destruct a Network after adding different Member
		network.createMember(0, "AAAAAA", new SocialClassZero());
		network.createMember(0, "BBBBBB", new SocialClassNormal());
		network.createMember(0, "CCCCCC", new SocialClassHalf());

		System.out.println(network);
		network.delete();
		// Should set network to null
		System.out.println(network);
	}

	/* Tests around Services and Member
	* Add and Remove a Service to a Member
	* Check if 2 Services are same
	* */
	public static void TestServices(){
	//	2 Services are equal
		/*Service serv1 = new Service("Cooking", 10);
		Service serv2 = new Service("Cooking", 10);
		Service serv3 = new Service("Cooking", 5);
		System.out.println(serv1.equals(serv2)); // true
		System.out.println(serv1.equals(serv3)); // true*/


	//	Add and Remove Services of Member
		Service cooking = new Service("Cooking", 10);
		Service washing = new Service("Washing Clothes", 15);
		Service mowing = new Service("Mowing the Lawn", 5);
		Admin admin = new Admin(100, "Thomas", new SocialClassHalf());
		try {
			admin.createNetwork("THE");
		} catch (AlreadyInNetwork alreadyInNetwork) {
			alreadyInNetwork.printStackTrace();
		}
		try {
			Member member = admin.createMember(100, "Thomas", new SocialClassHalf());
			member.addService(cooking);
			member.addService(washing);
			System.out.println(member);
			member.removeService(washing);
			System.out.println(member);
			member.removeService(mowing);
		} catch (NotInNetwork | AlreadyHasService | DontHaveService notInNetwork) {
			notInNetwork.printStackTrace();
		}
	}

	/*
	* Try to create a Task and execute it to check the balances
	* */
	public static void TestTask(){
	//	Create the Admin, the Network and create some Members with the Service and not
		Admin admin = new Admin(1000, "admin", new SocialClassNormal());
		Network network = null;
		try {
			network = admin.createNetwork("The Network");
		} catch (AlreadyInNetwork alreadyInNetwork) {
			alreadyInNetwork.printStackTrace();
		}
		Member member1 = network.createMember(100, "Malo", new SocialClassHalf());
		Member member2 = network.createMember(0, "Thomas", new SocialClassZero());
		Member member3 = network.createMember(1000, "Paul", new SocialClassNormal());

		Service cooking = new Service("Cooking", 10);
		Service washing = new Service("Washing Clothes", 15);
		Service mowing  = new Service("Mowing the Lawn", 5);

		try {
			member2.addService(cooking);
			member2.addService(washing);
			member2.addService(mowing);

			member3.addService(washing);
		} catch (AlreadyHasService alreadyHasService) {
			alreadyHasService.printStackTrace();
		}

		System.out.println(network);
		System.out.println(network.getNetworkList() + "\n");

		// Test with only 1 potential participant
		/*Task task = null;
		try {
			task = member1.createTasks(cooking, 1, 4.5, false);
		} catch (NotInNetwork | MissAmountException | TaskAlreadyExecuted | NotEnoughPotentielParticipants notInNetwork) {
			notInNetwork.printStackTrace();
		}
		System.out.println(task.getParticipants());
		System.out.println(task.getCost()+ "\n");
		System.out.println(network.getNetworkList());
		try {
			task.execute();
		} catch (MissAmountException | TaskAlreadyExecuted e) {
			e.printStackTrace();
		}
		System.out.println(network.getNetworkList());*/


		// Test with multiple potential participant (1 needed and 2 needed)
		// Gets aleatory member2 or member3
		// Cost 34 coins
		/*Task task = null;
		try {
			task = member1.createTasks(washing, 1, 4.5, false);
		} catch (NotInNetwork | MissAmountException | TaskAlreadyExecuted | NotEnoughPotentielParticipants notInNetwork) {
			notInNetwork.printStackTrace();
		}
		System.out.println(task.getCost());
		try {
			task.execute();
		} catch (MissAmountException | TaskAlreadyExecuted e) {
			e.printStackTrace();
		}
		System.out.println(network.getNetworkList() + "\n");*/


		// Test with multiple potential participant (2 needed)
		/*Task task = null;
		try {
			task = member1.createTasks(washing, 2, 4.5, false);
		} catch (NotInNetwork | MissAmountException | TaskAlreadyExecuted | NotEnoughPotentielParticipants notInNetwork) {
			notInNetwork.printStackTrace();
		}
		System.out.println(task.getCost());
		try {
			task.execute();
		} catch (MissAmountException | TaskAlreadyExecuted e) {
			e.printStackTrace();
		}
		System.out.println(network.getNetworkList() + "\n");*/


	// Test when beneficiary doesn't have enough when creates task and when executes (debits between)
		/*Task task = null;
		// Doesn't have enough when Task created --> not created
		try {
			// Set the beneficiary to 0
			System.out.println(member1.debitWallet(100));
		} catch (MissAmountException e) {
			e.printStackTrace();
		}
		try {
			task = member1.createTasks(washing, 2, 4.5, false);
		} catch (NotInNetwork | MissAmountException | TaskAlreadyExecuted | NotEnoughPotentielParticipants notInNetwork) {
			notInNetwork.printStackTrace();
		}
		System.out.println(network.getNetworkList() + "\n");*/

		/*Task task = null;
		// Doesn't have enough when Task executed --> not finished
		try {
			task = member1.createTasks(washing, 2, 4.5, false);
		} catch (NotInNetwork | MissAmountException | TaskAlreadyExecuted | NotEnoughPotentielParticipants notInNetwork) {
			notInNetwork.printStackTrace();
		}
		try {
			// Set the beneficiary to 0
			System.out.println(member1.debitWallet(100));
		} catch (MissAmountException e) {
			e.printStackTrace();
		}
		try{
			task.execute();
		} catch (MissAmountException | TaskAlreadyExecuted e) {
			e.printStackTrace();
		}
		System.out.println(network.getNetworkList() + "\n");*/
	}
	
	public static void TestScenario() {
		
		SocialClassHalf socialClassHalf     = new SocialClassHalf(); 
		SocialClassZero socialClassZero     = new SocialClassZero();
		SocialClassNormal socialClassNormal = new SocialClassNormal();
		
		Network network   = null;
		
		Task task1        = null;
		Task task2        = null;
		Task task3        = null; 
		
		Service Sweep     = new Service("sweep", 50); 
		Service Gardening = new Service("gardening", 25); 
		Service WaxShoes  = new Service("waxShoes", 20); 
		
		Admin admin       = new Admin(100, "Carl", socialClassNormal); 
		
		Member member1; 
		Member member2; 
		Member member3; 
		
		try {
			System.out.println("Création of the professionnal network");	
			network = admin.createNetwork("Professionnal"); 
		}
		
		catch (AlreadyInNetwork alreadyInNetwork) {
			alreadyInNetwork.printStackTrace();
		}
		
			System.out.println("Creation of the member Lucien in the network");
			member1 = admin.createMember(100, "Lucien", socialClassHalf);
			
			System.out.println("Creation of the member Sebastion in the network");
			member2 = admin.createMember(0, "Sebastien", socialClassNormal);
			
			System.out.println("Creation of the member Marie in the network");
			member3 = admin.createMember(150, "Marie", socialClassHalf);
			
			System.out.println("Sebastien has : " + member2.getWallet());
			System.out.println("add 20 jetons to Sebastien"); 
			member2.creditWallet(20); 
			System.out.println("here is the wallet of Sebastion now : " + member2.getWallet());
			
			System.out.println("Marie has : " + member2.getWallet());
			System.out.println("Remove 10 jetons to Marie");
			member3.debitWallet(10); 
			System.out.println("here is the wallet of Marie now : " + member3.getWallet());
		
		try {
			System.out.println("add service to Lucien and can do sweep");
			member1.addService(Sweep);
			
			System.out.println("add service to Sebastien and can do gardening");
			member1.addService(Gardening);
			
			System.out.println("add service to Marie and can do wax the shoes");
			member1.addService(WaxShoes);
		}
		
		catch (AlreadyHasService alreadyHasService) {
			alreadyHasService.printStackTrace();
		}

			System.out.println(network);
			System.out.println("Here is the network list : " + "\n" + network.getNetworkList() + "\n");
			
		try {
			task1 = member1.createTasks(Gardening , 1, 4.5, false);
		} 
		
		catch (NotInNetwork | MissAmountException | TaskAlreadyExecuted | NotEnoughPotentielParticipants notInNetwork) {
			notInNetwork.printStackTrace();
		}
			System.out.println("Here is the participant for the this task : " + task1.getParticipants());
			System.out.println("Here is the cost of this task : " + task1.getCost()+ "\n");
			
		try {
			task1.execute();
		} catch (MissAmountException | TaskAlreadyExecuted exception) {
			exception.printStackTrace();
		}
		
		System.out.println(network);
		System.out.println("Here is the network list : " + "\n" + network.getNetworkList() + "\n");
		
		try {
			task2 = member2.createTasks(sweep, 1, 3, false);
		} 
	
		catch (NotInNetwork | MissAmountException | TaskAlreadyExecuted | NotEnoughPotentielParticipants notInNetwork) {
			notInNetwork.printStackTrace();
		}
			System.out.println("Here is the participant for the this task : " + task1.getParticipants());
			System.out.println("Here is the cost of this task : " + task1.getCost()+ "\n");
		
		try {
			task2.execute();
		} catch (MissAmountException | TaskAlreadyExecuted exception) {
			exception.printStackTrace();
		}
		
		System.out.println(network);
		System.out.println("Here is the network list : " + "\n" + network.getNetworkList() + "\n");
		
		try {
			task3 = member3.createTasks(WaxShoes, 3, 3, false);
		} 
	
		catch (NotInNetwork | MissAmountException | TaskAlreadyExecuted | NotEnoughPotentielParticipants notInNetwork) {
			notInNetwork.printStackTrace();
		}
			System.out.println("Here is the participant for the this task : " + task1.getParticipants());
			System.out.println("Here is the cost of this task : " + task1.getCost()+ "\n");
		
		try {
			task3.execute();
		} catch (MissAmountException | TaskAlreadyExecuted exception) {
			exception.printStackTrace();
		}
	}	
}
