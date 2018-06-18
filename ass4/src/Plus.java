import java.util.Map;
/**
 * A Plus class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class Plus extends BinaryExpression implements Expression {

    /**
     * Basic constructor.
     *
     * @param expRight
     *            right expression.
     * @param expLeft
     *            left expression.
     */
    public Plus(Expression expRight, Expression expLeft) {
        super(expRight, expLeft);
    }

    /**
     * Constructor by strings.
     *
     * @param strRight
     *            right expression (a var).
     * @param strLeft
     *            lect expression (a var).
     */
    public Plus(String strRight, String strLeft) {
        super(strRight, strLeft);
    }

    /**
     * Constructor by string and double.
     *
     * @param strRight
     *            right expression (a var).
     * @param numLeft
     *            left expression (a num).
     */
    public Plus(String strRight, double numLeft) {
        super(strRight, numLeft);
    }

    /**
     * Constructor by double and string.
     *
     * @param numRight
     *            right expression (a num)
     * @param strLeft
     *            left expression (a var)
     */
    public Plus(double numRight, String strLeft) {
        super(numRight, strLeft);
    }

    /**
     * Constructor by doubles.
     *
     * @param numRight
     *            right expression (a num)
     * @param numLeft
     *            left expression (a num)
     */
    public Plus(double numRight, double numLeft) {
        super(numRight, numLeft);
    }

    /**
     * Constructor by expression and double.
     *
     * @param expRight
     *            right expression.
     * @param numLeft
     *            left expression (a num)
     */
    public Plus(Expression expRight, double numLeft) {
        super(expRight, numLeft);
    }

    /**
     * Construcor by double and expression.
     *
     * @param numRight
     *            right expression (a num)
     * @param expLeft
     *            left expression
     */
    public Plus(double numRight, Expression expLeft) {
        super(numRight, expLeft);
    }

    /**
     * Constructor by expression and string.
     *
     * @param expRight
     *            right expression
     * @param strLeft
     *            left expression (a var)
     */
    public Plus(Expression expRight, String strLeft) {
        super(expRight, strLeft);
    }

    /**
     * Constructor by string and expression.
     *
     * @param strRight
     *            right expression (a var)
     * @param expLeft
     *            left expression
     */
    public Plus(String strRight, Expression expLeft) {
        super(strRight, expLeft);
    }

    /**
     * Plus to String.
     *
     * @return the converted Expression as a String.
     */
    public String toString() {
        String strPlus = "(" + super.getExpRight().toString() + " + " + super.getExpLeft().toString() + ")";
        return strPlus;

    }

    /**
     * assign a value to a variable.
     *
     * @param var
     *            the variable we assign to.
     * @param expression
     *            the expression to assign in var.
     * @return the new expression after assignment.
     */
    public Expression assign(String var, Expression expression) {
        Plus assPlus = new Plus(super.getExpRight().assign(var, expression),
                super.getExpLeft().assign(var, expression));
        return assPlus;
    }
   
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (!expIncludeVar(assignment)) {
            throw new Exception("there is no var in the expression");
        } else {
            return super.getExpRight().evaluate(assignment) + super.getExpLeft().evaluate(assignment);
        }
    }
    
    @Override
    public double evaluate() throws Exception {
        if (!super.getExpRight().getVariables().isEmpty() || !super.getExpLeft().getVariables().isEmpty()) {
            throw new Exception("No var assigment");
        } else {
            return super.getExpRight().evaluate() + super.getExpLeft().evaluate();
        }
    }
    
    /**
     * return the diffrential of this expression given a var to diffrentiate accord.
     *
     * @param var the var we differintiate according to.
     * @return the diff "var" of this expression.
     */
    public Expression differentiate(String var) {
        Expression diffExpRight = (super.getExpRight()).differentiate(var);
        Expression diffExpLeft = (super.getExpLeft()).differentiate(var);
        return new Plus(diffExpRight, diffExpLeft);
    }
   
    /**
     * return a simplification of this expression.
     *
     * @return this simplified expression.
     */
    public Expression simplify() {
        Expression expRight = super.getExpRight().simplify();
        Expression expLeft = super.getExpLeft().simplify();
        Expression sim = super.simplify();
        if (sim != null) {
            return sim;
        }
        Expression zero = new Num(0);
        if (expLeft.toString().equals(zero.toString())) {
            return expRight;
        }
        if (expRight.toString().equals(zero.toString())) {
            return expLeft;
        }
        return new Plus(expRight, expLeft);
    }
    
}
