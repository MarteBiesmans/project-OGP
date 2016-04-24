package hillbillies.model.programs.expressions;

public class IsSolidExpression extends UnaryExpression {

	public IsSolidExpression(CubePositionExpression e1) {
		super(e1);
	}
	
	@Override
	public Boolean evaluate() {
		return false;
	}
	
	@Override
	public String toString(){
		return "NOT"+getExpression().toString();
	}

}
