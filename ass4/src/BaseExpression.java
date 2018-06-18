import java.util.Map;

public abstract class BaseExpression{
    
    /**
     * evaluate the expression with a given assigment.
     *
     * @param assignment the assignment of vars.
     * @return the value of evaluate expression.
     * @throws Exception if assigment does not contain all vars.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return 0;
    }
   
    /**
     * evaluate this expression with no vars.
     *
     * @return the evaluate expression.
     * @throws Exception if expression includes var.
     */
    public abstract double evaluate() throws Exception;

}
