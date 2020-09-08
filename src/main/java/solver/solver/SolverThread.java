package solver.solver;


public class SolverThread extends Thread{
	private Conversation conv;
	private String thrName;
	
	public SolverThread(String name) {
		// TODO Auto-generated constructor stub
		super();
		this.thrName=name;
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		conv= new Conversation();
		System.out.println("Thread " + thrName + "Started Successfully");
		
	}
	
	public synchronized SolverFacade getSolverIstance() {
		System.out.println("request on Thread " + thrName + "Started Successfully");
		return new SolverFacade(conv);
	}

}
