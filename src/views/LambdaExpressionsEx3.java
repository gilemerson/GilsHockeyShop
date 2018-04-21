
package views;

/**
 *
 * @author gilemerson
 */
public class LambdaExpressionsEx3 {

   public static void main(String args[]) {
      LambdaExpressionsEx3 tester = new LambdaExpressionsEx3();
		
      //with type declaration
      MathOperation addition = (int a, int b) -> a + b;
		
      //with out type declaration
      /**
       * No need to declare the type of a parameter. The compiler can inference the same from the value of the parameter.
       */
      MathOperation subtraction = (a, b) -> a - b;
		
      //with return statement along with curly braces
      MathOperation multiplication = (int a, int b) -> { return a * b; };
		
      //without return statement and without curly braces
      /**
       * The compiler automatically returns the value if the body has a single expression to return the value. Curly braces are required to indicate that expression returns a value.
       * No need to use curly braces in expression body if the body contains a single statement.
       */
      MathOperation division = (int a, int b) -> a / b;
		
      System.out.println("5 + 5 = " + tester.operate(5, 5, addition));
      System.out.println("20 - 10 = " + tester.operate(20, 10, subtraction));
      System.out.println("10 x 1 = " + tester.operate(10, 1, multiplication));
      System.out.println("100 / 10 = " + tester.operate(100, 10, division));
		
      //without parenthesis
      /**
       * No need to declare a single parameter in parenthesis. For multiple parameters, parentheses are required.
       */
      GreetingService greetService1 = message ->
      System.out.println("Hello " + message);
		
      //with parenthesis
      GreetingService greetService2 = (message) ->
      System.out.println("Hello " + message);
		
      greetService1.sayMessage("Gil");
      greetService2.sayMessage("Gil");
   }
	
   interface MathOperation {
      int operation(int a, int b);
   }
	
   interface GreetingService {
      void sayMessage(String message);
   }
	
   private int operate(int a, int b, MathOperation mathOperation) {
      return mathOperation.operation(a, b);
   }
}

