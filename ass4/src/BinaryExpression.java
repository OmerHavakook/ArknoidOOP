import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
/**
 * A BinaryExpression abstract class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public abstract class BinaryExpression extends BaseExpression {

    private Expression expRight;
    private Expression expLeft;
    
    
    /**
     * Basic constructor.
     *
     * @param expressionRight right expression.
     * @param expressionLeft  left expression.
     */
    public BinaryExpression(Expression expressionRight, Expression expressionLeft) {
        this.expRight = expressionRight;
        this.expLeft = expressionLeft;
    }
    
    /**
     * Constructor by strings.
     *
     * @param stringRight right expression (a var).
     * @param stringLeft  lect expression (a var).
     */
    public BinaryExpression(String stringRight, String stringLeft) {
        this.expRight = new Var(stringRight);
        this.expLeft = new Var(stringLeft);
    }
    
    /**
     * Constructor by string and double.
     *
     * @param stringRight right expression (a var).
     * @param numberLeft  left expression (a num).
     */
    public BinaryExpression(String stringRight, double numberLeft) {
        this.expRight = new Var(stringRight);
        this.expLeft = new Num(numberLeft);
    }

    /**
     * Constructor by double and string.
     *
     * @param numberRight right expression (a num)
     * @param stringLeft  left expression (a var)
     */
    public BinaryExpression(double numberRight, String stringLeft) {
        this.expRight = new Num(numberRight);
        this.expLeft = new Var(stringLeft);
    }

    /**
     * Constructor by doubles.
     *
     * @param numberRight right expression (a num)
     * @param numberLeft  left expression (a num)
     */
    public BinaryExpression(double numberRight, double numberLeft) {
        this.expRight = new Num(numberRight);
        this.expLeft = new Num(numberLeft);
    }

    /**
     * Constructor by expression and double.
     *
     * @param expressionRight right expression.
     * @param numberLeft      left expression (a num)
     */
    public BinaryExpression(Expression expressionRight, double numberLeft) {
        this.expRight = expressionRight;
        this.expLeft = new Num(numberLeft);
    }

    /**
     * Construcor by double and expression.
     *
     * @param numberRight    right expression (a num)
     * @param expressionLeft left expression
     */
    public BinaryExpression(double numberRight, Expression expressionLeft) {
        this.expRight = new Num(numberRight);
        this.expLeft = expressionLeft;
    }

    /**
     * Constructor by expression and string.
     *
     * @param expressionRight right expression
     * @param stringLeft      left expression (a var)
     */
    public BinaryExpression(Expression expressionRight, String stringLeft) {
        this.expRight = expressionRight;
        this.expLeft = new Var(stringLeft);
    }

    /**
     * Constructor by string and expression.
     *
     * @param stringRight    right expression (a var)
     * @param expressionLeft left expression
     */
    public BinaryExpression(String stringRight, Expression expressionLeft) {
        this.expRight = new Var(stringRight);
        this.expLeft = expressionLeft;
    }
    
    /**
     * get the expression left.
     *
     * @return expression.
     */
    public Expression getExpLeft() {
        return this.expLeft;
    }
    
    /**
     * get the expression right.
     *
     * @return expression.
     */
    public Expression getExpRight() {
        return this.expRight;
    }

    /**
     * check if  there is var in the assignment.
     *
     * @param assignment list of vars to check if vars include in the expression.
     * @return true or false.
     */
    protected boolean expIncludeVar(Map<String, Double> assignment) {
        boolean res = false;
        List<String> variabales = this.expRight.getVariables();
        if (variabales.isEmpty()) {
            return true;
        }
        variabales.addAll(this.expLeft.getVariables());
        for (String v : variabales) {
            if (assignment.containsKey(v)) {
                res = true;
                break;
            }
        }
        if (!res) {
            return false;
        }
        return true;
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
    /**
     * get list of all vars in the expression.
     *
     * @return l a list with all vars in this.
     */
    public List<String> getVariables() {
        List<String> l = new ArrayList<>();
        l.addAll(expRight.getVariables());
        l.addAll(expLeft.getVariables());
        return l;
    }
    
    /**
     * Check if two expressions are equals.
     * @param firstStr first expression.
     * @param secondStr second expression.
     * @return true if the expression equals false otherwise.
     */
    public boolean sameExpression(String firstStr, String secondStr) {
        char[] first = firstStr.toCharArray();
        char[] second = secondStr.toCharArray();
        Arrays.sort(first);
        Arrays.sort(second);
        return Arrays.equals(first, second);
      }

}
