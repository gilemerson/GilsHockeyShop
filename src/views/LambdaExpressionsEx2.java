
package views;

/**
 * 
 * @author gilemerson
 */
  public class LambdaExpressionsEx2 {
    /**
     * The following example, Calculator, is an example of lambda expressions that take more than one formal parameter:
     */
  
    interface IntegerMath {
        int operation(int a, int b);   
    }
  
    /**
     * The method operateBinary performs a mathematical operation on two integer operands. 
     * The operation itself is specified by an instance of IntegerMath, created above. The example defines two operations with lambda expressions, 
     * addition and subtraction. The example prints the following:
      * 25 + 25 = 50
      * 50 - 25 = 25
     * @param a
     * @param b
     * @param op
     * @return 
     */
    public int operateBinary(int a, int b, IntegerMath op) {
        return op.operation(a, b);
    }
 
    public static void main(String... args) {
    
        LambdaExpressionsEx2 myApp = new LambdaExpressionsEx2();
        IntegerMath addition = (a, b) -> a + b;
        IntegerMath subtraction = (a, b) -> a - b;
        System.out.println("25 + 25 = " +
            myApp.operateBinary(25, 25, addition)); //operateBinary performs addition
        System.out.println("50 - 25 = " +
            myApp.operateBinary(50, 25, subtraction)); //operateBinary performs subtraction    
    }
}