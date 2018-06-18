import java.util.List;
import java.util.Map;
/**
 * A UnaryExpression abstract class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public abstract class UnaryExpression extends BaseExpression {

    private Expression exp;

    /**
     * Expression Constructor.
     *
     * @param expression the expression to create expression with.
     */
    public UnaryExpression(Expression expression) {
        this.exp = expression;
    }

    /**
     * empty Constructor.
     */
    public UnaryExpression() {
        this.exp = null;
    }

    /**
     * Constructor by string.
     *
     * @param var a string to create expression.
     */
    public UnaryExpression(String var) {
        this.exp = new Var(var);
    }

    /**
     * Constructor by double.
     *
     * @param num the number to create expression.
     */
    public UnaryExpression(double num) {
        this.exp = new Num(num);
    }

    /**
     * returns this.expression.
     *
     * @return this.expression.
     */
    protected Expression getExp() {
        return this.exp;
    }

    /**
     * checks if assigment include vars.
     *
     * @param assignment the assignment to check.
     * @return true if include, false otherwise.
     */
    protected boolean expIncludeVar(Map<String, Double> assignment) {
        boolean res = true;
        List<String> variables = this.exp.getVariables();
        if (variables.isEmpty()) {
            return true;
        }
        for (String var : variables) {
            if (!assignment.containsKey(var)) {
                res = false;
                break;
            }
        }

        return res;
    }

    /**
     * Returns a list of the variables in the expression.
     *
     * @return this.expression.getVariables().
     */
    public List<String> getVariables() {
        return this.exp.getVariables();
    }
 
    /**
     * return a simplification of this expression.
     *
     * @return this simplified expression.
     */
    public Expression simplify() {
        try {
            return new Num(evaluate());
        } catch (Exception exception) {
            return null;
        }
    }
    
    
}
