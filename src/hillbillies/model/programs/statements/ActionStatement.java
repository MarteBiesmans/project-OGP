package hillbillies.model.programs.statements;

public abstract class ActionStatement extends Statement {
	
	@Override
	public boolean isMutable() {
		return false;
	}
	
}
