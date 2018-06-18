import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * Var Class.
 */
public class Var implements Expression {

    private String variable;
    /**
     * Basic var Constructor.
     *
     * @param variable the var to create this var with.
     */
    public Var(String variable) {
        this.variable = variable;
    }
 
    /**
     * @return the variable member.
     */
    public String getVariable() {
        return variable;
    }
   
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (assignment.containsKey(this.variable)) {
            return assignment.get(this.variable);
        } else {
            throw new Exception("variable wasn't assigned");
        }
    }

    @Override
    public double evaluate() throws Exception {
        throw new Exception("variable wasn't assigned");
    }

    /**
     * returns this var in a list.
     *
     * @return a list with this var in it.
     */
    public List<String> getVariables() {
        java.util.List<String> listVar = new ArrayList<String>();
        listVar.add(this.variable);
        return listVar;
    }

    /**
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     *
     * @param var        var to assign in.
     * @param expression exprresion to assign into var.
     * @return the expression to assign if var equals this variable, this otherwise.
     */
    public Expression assign(String var, Expression expression) {
        if (var.equals(this.variable)) {
            return expression;
        } else {
            return this;
        }
    }
    /**
     * return the diffrential of this expression given a var to diffrentiate accord.
     *
     * @param var the var we differintiate according to.
     * @return the d"var" of this expression.
     */
    public Expression differentiate(String var) {
        if (this.variable.equals(var)) {
            return new Num(1);
        } else {
            return new Num(0);
        }
    }
    /**
     * convert variable to string.
     *
     * @return String.
     */
    public String toString() {
        return this.variable;
    }
    /**
     * returns this simplified expression (var is already simplified).
     *
     * @return this.
     */
    @Override
    public Expression simplify() {
        return this;
    }

}
