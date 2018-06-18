import java.util.Map;
import java.util.TreeMap;
/**
 * A ExpressionsTest class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class ExpressionsTest {
    /**
     * Code test.
     *
     * @param args from user.
     * @throws Exception evaluate func throws expesion.
     */
    public static void main(String[] args) throws Exception {
        Expression expression = new Plus(new Mult(2, "x"), new Plus(new Sin(new Mult(4, "y")), new Pow("e", "x")));
        System.out.println(expression.toString());
        Map<String, Double> assignment = new TreeMap<String, Double>();
        assignment.put("x", 2.0);
        assignment.put("y", 0.25);
        assignment.put("e", 2.71);
        System.out.println(expression.evaluate(assignment));
        Expression difExpression = expression.differentiate("x");
        System.out.println(difExpression);
        System.out.println(difExpression.evaluate(assignment));
        System.out.println(difExpression.simplify());
        System.out.println(difExpression);
        
    }
}
