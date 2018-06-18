import java.util.Map;
/**
 * A Mult class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class Mult extends BinaryExpression implements Expression {

    /**
     * Basic constructor.
     *
     * @param expRight
     *            right expression.
     * @param expLeft
     *            left expression.
     */
    public Mult(Expression expRight, Expression expLeft) {
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
    public Mult(String strRight, String strLeft) {
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
    public Mult(String strRight, double numLeft) {
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
    public Mult(double numRight, String strLeft) {
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
    public Mult(double numRight, double numLeft) {
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
    public Mult(Expression expRight, double numLeft) {
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
    public Mult(double numRight, Expression expLeft) {
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
    public Mult(Expression expRight, String strLeft) {
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
    public Mult(String strRight, Expression expLeft) {
        super(strRight, expLeft);
    }

    /**
     * Mult to String.
     *
     * @return the converted Expression as a String.
     */
    public String toString() {
        String strMult = "(" + super.getExpRight().toString() + " * " + super.getExpLeft().toString() + ")";
        return strMult;

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
        Mult assMult = new Mult(super.getExpRight().assign(var, expression),
                super.getExpLeft().assign(var, expression));
        return assMult;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (!expIncludeVar(assignment)) {
            throw new Exception("there is no var in the expression");
        } else {
            return super.getExpRight().evaluate(assignment) * super.getExpLeft().evaluate(assignment);
        }
    }

    @Override
    public double evaluate() throws Exception {
        if (!super.getExpRight().getVariables().isEmpty() || !super.getExpLeft().getVariables().isEmpty()) {
            throw new Exception("No var assigment");
        } else {
            return super.getExpRight().evaluate() * super.getExpLeft().evaluate();
        }
    }
    /**
     * return the diffrential of this expression given a var to diffrentiate accord.
     *
     * @param var the var we differintiate according to.
     * @return the diff "var" of this expression.
     */
    public Expression differentiate(String var) {
        Expression diffExpRight = new Mult(super.getExpRight().differentiate(var), super.getExpLeft());
        Expression diffExpLeft = new Mult(super.getExpLeft().differentiate(var), super.getExpRight());
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
        Expression one = new Num(1);
        Expression zero = new Num(0);
        if (expRight.toString().equals(zero.toString()) || expRight.toString().equals(zero.toString())) {
            return zero;
        }
        if (expRight.toString().equals(one.toString())) {
            return expLeft;
        } else if (expLeft.toString().equals(one.toString())) {
            return expRight;
        }
        return new Mult(expRight, expLeft);
    }

}
