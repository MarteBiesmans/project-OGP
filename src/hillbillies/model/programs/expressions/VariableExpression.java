package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Unit;
import worms.model.Worm;

public class VariableExpression extends Expression<Object> {

	public VariableExpression(String name) {
		variableName = name;
	}
	
	private String variableName;	

	@Override
	public Object evaluate(Unit unit, Cube cube) {
		return getGlobal(variableName).getValue();
	}
	
	@Override
	public String toString(){
		return variableName;
	}

}
