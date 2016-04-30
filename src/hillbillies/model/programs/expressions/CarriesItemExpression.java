package hillbillies.model.programs.expressions;

import hillbillies.model.Unit;
import hillbillies.model.programs.type.BooleanType;
import hillbillies.model.programs.type.Type;

public class CarriesItemExpression extends BooleanExpression {

	public CarriesItemExpression(UnitExpression e) {
		expression = e;
	}
	
	public Expression<BooleanType> getExpression() {
		return expression;
	}
	
	public Type getExpressionEvaluate(Unit unit) {
		return getExpression().evaluate(unit);
	}
	
	private final Expression<BooleanType> expression;
	
	@Override
	public BooleanType evaluate(Unit unit) {
		return new BooleanType(((Unit) getExpressionEvaluate(unit)).getNbMaterials() != 0);
	}
	
	@Override
	public String toString(){
		return getExpression().toString()+"carriesItem";
	}

}
