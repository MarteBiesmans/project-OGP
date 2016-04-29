package hillbillies.model.programs.expressions;

import hillbillies.model.Unit;

public class CarriesItemExpression extends UnaryExpression {

	public CarriesItemExpression(UnitExpression e) {
		super(e);
	}
	
	@Override
	public Boolean evaluate(Unit unit) {
		return (((Unit) getExpressionEvaluate(unit)).getNbMaterials() != 0);
	}
	
	@Override
	public String toString(){
		return getExpression().toString()+"carriesItem";
	}

}
