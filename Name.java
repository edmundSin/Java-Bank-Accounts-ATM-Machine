/* Edmund Sin 
 * Bank Accounts  Using Inheritance, Polymorphism, AbstractClasses and Methods 
 */

public class Name{
  
  private String first;
  private String last;
  
  //no-arg constructor
  public Name(){
    first = " ";
    last = " ";
  }
  
  //copy constructor
  public Name(Name name){
    first = name.first;
    last = name.last;
  }
  
  //parametized constructor
  public Name(String f, String l){
    first = f;
    last = l;
  }
  
  //toString() method - uses String static method .format()
  public String toString(){
    String str = String.format("Name: %s, %s", 
                                getFirst(),
                                getLast()
                                );
    return str;
  }
  
   //.equals() method
  public boolean equals(Name myName)
  {
   if(last.equals(myName.last) && first.equals(myName.first))
   return true; //myName found
    else
   return false; //myName not found
   }
  
  
  //getters
  public String getFirst(){
    return first;
  }
 
  public String getLast(){
    return last;
  }
  
}
