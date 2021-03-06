import java.util.Map;
/**
 * A Sin class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class Sin extends UnaryExpression implements Expression {
    /**
     * Basic Constructor.
     *
     * @param expression the Expression the Sin of.
     */
    public Sin(Expression expression) {
        super(expression);
    }

    /**
     * Constructor By string.
     *
     * @param var the var the sin of.
     */
    public Sin(String var) {
        super(var);
    }

    /**
     * Constructor by double.
     *
     * @param num the number the Sin of.
     */
    public Sin(double num) {
        super(num);
    }

    /**
     * evalute this expression with a given assigment.
     *
     * @param assignment the assigment of vars.
     * @return the value of evaluated expression.
     * @throws Exception if assigment does not contain all vars.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (!super.expIncludeVar(assignment)) {
            throw new Exception("there is no var in the expression");
        }
        return Math.sin(Math.toRadians(super.getExp().evaluate(assignment)));
    }

    /**
     * evaluate this expression with no vars.
     *
     * @return the evaluate expression.
     * @throws Exception if expression contains var\s.
     */
    public double evaluate() throws Exception {
        if (!super.getExp().getVariables().isEmpty()) {
            throw new Exception("No var assigment");
        } else {
            return Math.sin(Math.toRadians(super.getExp().evaluate()));
        }
    }

    /**
     * Div to String conversion.
     *
     * @return the converted Expression as a String.
     */
    public String toString() {
        return "Sin(" + super.getExp().toString() + ")";
    }


    /**
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     *
     * @param var        var to assign in.
     * @param expression exprresion to assign into var.
     * @return new Sin(getExpression().assign(var, expression)).
     */
    public Expression assign(String var, Expression expression) {
        return new Sin(super.getExp().assign(var, expression));
    }

    /**
     * return the diffrential of this expression given a var to diffrentiate accord.
     *
     * @param var the var we differintiate according to.
     * @return the d"var" of this expression.
     */
    public Expression differentiate(String var) {
        return new Mult(super.getExp().differentiate(var), new Cos(super.getExp()));
    }

    /**
     * return a simplification of this expression.
     *
     * @return this simplified expression.
     */
    @Override
    public Expression simplify() {
        if (super.simplify() == null) {
            return this;
        }
        return super.simplify();
    }


}
