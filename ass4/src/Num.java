import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A Num class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class Num implements Expression {
    private double value;
    
    /**
     * creating a new number.
     * @param number the value to set.
     */
    public Num(double number) {
        this.value=number;
    }
     
    /**
     * getting the number's value.
     * @return the value to set.
     */
    public double getValue() {
        return value;
    }
    /**
     * evaluate the expression.
     */
    public double evaluate(Map<String, Double> assignment) {
        return getValue();
    }
    /**
     * evaluate the expression.
     */
    public double evaluate() {
        return getValue();
    }
    /**
     * returns an empty string list (no vars in num).
     *
     * @return empty string list
     */
    public List<String> getVariables() {
        java.util.List<String> emptyList = new ArrayList<String>();
        return emptyList;
    }
    /**
     * assign a value to a variable.
     *
     * @param var        the variable we assign to.
     * @param expression the expression to assign in var.
     * @return the new expression after assignment.
     */
    public Expression assign(String var, Expression expression) {
        return this;
    }
    /**
     * convert number to string.
     *
     * @return String.
     */
    public String toString() {
        return Double.toString(this.value);
    }
    /**
     * returns this number (number is already simplify).
     *
     * @return this.
     */
    public Expression simplify() {
        return this;
    }
    /**
     * return the differentiate of this number (const differentiate is always 0).
     *
     * @param var the var to differentiate by.
     * @return new Num(0).
     */
    public Expression differentiate(String var) {
        return new Num(0);
    }

}