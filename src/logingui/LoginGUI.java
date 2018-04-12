
package logingui;

import java.util.ArrayList;

/**
 *
 * @author gilemerson
 */
public class LoginGUI {

    /**
     * @param args the command line arguments
     * ArrayList - Professional NHL Hockey Teams
     */
    public static void main(String[] args)  {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Chicago Blackhawks");
        arrayList.add("Colorado Avalanche");
        arrayList.add("Calgary Flames");
        arrayList.add("Columbus Blue Jackets");
        arrayList.add("Toronto Maple leafs");
       
         System.out.println("Professional NHL Hockey Teams");
        for (int i=0; i<arrayList.size();i++)
        {
            System.out.printf("Index: %d Name: %s%n", i, arrayList.get(i));
        }
        
         //filter for names starting with the letter - C 
        arrayList.stream()
                 .peek(name -> System.out.printf("%nname before filter: %s", name))
                 .filter(name ->name.substring(0, 1).equals("C"))
                 .peek(name -> System.out.printf(" name after filter: %s%n", name))
                 .forEach(name -> System.out.println());
    }
    
}
