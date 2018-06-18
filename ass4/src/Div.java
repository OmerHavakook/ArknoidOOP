import java.util.Map;
/**
 * A Div class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class Div extends BinaryExpression implements Expression {


    /**
     * Basic constructor.
     *
     * @param expRight
     *            right expression.
     * @param expLeft
     *            left expression.
     */
    public Div(Expression expRight, Expression expLeft) {
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
    public Div(String strRight, String strLeft) {
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
    public Div(String strRight, double numLeft) {
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
    public Div(double numRight, String strLeft) {
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
    public Div(double numRight, double numLeft) {
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
    public Div(Expression expRight, double numLeft) {
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
    public Div(double numRight, Expression expLeft) {
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
    public Div(Expression expRight, String strLeft) {
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
    public Div(String strRight, Expression expLeft) {
        super(strRight, expLeft);
    }

    /**
     * Div to String.
     *
     * @return the converted Expression as a String.
     */
    public String toString() {
        String strPlus = "(" + super.getExpRight().toString() + " / " + super.getExpLeft().toString() + ")";
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
        Div assDiv = new Div(super.getExpRight().assign(var, expression),
                super.getExpLeft().assign(var, expression));
        return assDiv;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (!expIncludeVar(assignment)) {
            throw new Exception("there is no var in the expression");
        } else {
            return super.getExpRight().evaluate(assignment) / super.getExpLeft().evaluate(assignment);
        }
    }

    @Override
    public double evaluate() throws Exception {
        if (!super.getExpRight().getVariables().isEmpty() || !super.getExpLeft().getVariables().isEmpty()) {
            throw new Exception("No var assigment");
        } else {
            return super.getExpRight().evaluate() / super.getExpLeft().evaluate();
        }
    }
    /**
     * return the diffrential of this expression given a var to diffrentiate accord.
     *
     * @param var the var we differintiate according to.
     * @return the diff "var" of this expression.
     */
    public Expression differentiate(String var) {
        Expression diffExpRight = new Mult(super.getExpRight().differentiate(var),
                super.getExpLeft());
        Expression diffExpLeft = new Mult(super.getExpLeft().differentiate(var),
                super.getExpRight());
        return new Div(new Minus(diffExpRight, diffExpLeft), new Pow(super.getExpLeft(), 2));
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
        if (expLeft.equals(new Num(1))) {
            return expRight;
        }
        if (super.sameExpression(expRight.toString(), expLeft.toString())) {
            return new Num(1);
        }
        if(expLeft.equals(new Num(0)))
        {
            return new Var("NaN");
        }
        return new Div(expRight, expLeft);
    }
    

}
