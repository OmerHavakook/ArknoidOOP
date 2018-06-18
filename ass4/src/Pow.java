import java.util.Map;
/**
 * A Pow class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class Pow extends BinaryExpression implements Expression {

    /**
     * Basic constructor.
     *
     * @param expRight
     *            right expression.
     * @param expLeft
     *            left expression.
     */
    public Pow(Expression expRight, Expression expLeft) {
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
    public Pow(String strRight, String strLeft) {
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
    public Pow(String strRight, double numLeft) {
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
    public Pow(double numRight, String strLeft) {
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
    public Pow(double numRight, double numLeft) {
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
    public Pow(Expression expRight, double numLeft) {
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
    public Pow(double numRight, Expression expLeft) {
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
    public Pow(Expression expRight, String strLeft) {
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
    public Pow(String strRight, Expression expLeft) {
        super(strRight, expLeft);
    }

    /**
     * Pow to String.
     *
     * @return the converted Expression as a String.
     */
    public String toString() {
        String strPow = "(" + super.getExpRight().toString() + " ^ " + super.getExpLeft().toString() + ")";
        return strPow;

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
        Pow assPow = new Pow(super.getExpRight().assign(var, expression), super.getExpLeft().assign(var, expression));
        return assPow;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (!expIncludeVar(assignment)) {
            throw new Exception("there is no var in the expression");
        } else {
            return Math.pow(super.getExpRight().evaluate(assignment), super.getExpLeft().evaluate(assignment));
        }
    }

    @Override
    public double evaluate() throws Exception {
        if (!super.getExpRight().getVariables().isEmpty() || !super.getExpLeft().getVariables().isEmpty()) {
            throw new Exception("No var assigment");
        } else {
            return Math.pow(super.getExpRight().evaluate(), super.getExpLeft().evaluate());
        }
    }
    /**
     * return the diffrential of this expression given a var to diffrentiate accord.
     *
     * @param var the var we differintiate according to.
     * @return the diff "var" of this expression.
     */
    public Expression differentiate(String var) {
        Expression fPowG = new Pow(this.getExpRight(), this.getExpLeft());
        Expression fMultGDivf = new Mult(this.getExpRight().differentiate(var),
                new Div(this.getExpLeft(), this.getExpRight()));
        Expression gDiffMultLanF = new Mult(this.getExpLeft().differentiate(var),
                new Log("e", this.getExpRight()));
        return new Mult(fPowG, new Plus(fMultGDivf, gDiffMultLanF));
    }
    /**
     * return a simplification of this expression.
     *
     * @return this simplified expression.
     */
    public Expression simplify() {
        if (super.simplify() == null) {
            if (getExpLeft().simplify() == null) {
                if (getExpRight().simplify() == null) {
                    return this;
                } else {
                    return new Pow(getExpRight().simplify(), getExpLeft());
                }
            } else {
                return new Pow(getExpRight(), getExpLeft().simplify());
            }
        }
        return super.simplify();
    }

}
