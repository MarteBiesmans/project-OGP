package hillbillies.model.programs.statements;

import hillbillies.model.Cube;
import hillbillies.model.Unit;
import hillbillies.model.programs.expressions.Expression;

public class PrintStatement extends Statement {
	
	public PrintStatement(Expression<?> value) {
		this.message = value;
	}
	
	public final Expression<?> getMessage() {
		return this.message;
	}
	
	final Expression<?> message;
	
	@Override
	public void execute() {
		System.out.println(this.getMessage().evaluate().toString());
		this.setCompleted(true);
	}

	@Override
	public boolean isMutable() {
		return true;
	}
	
}
