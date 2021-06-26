/* Edmund Sin 
 * Bank Accounts  Using Inheritance, Polymorphism, AbstractClasses and Methods 
 */

public class Depositor {
  
  private Name name; 
  private String ssn;
  
  //no-arg constructor
  public Depositor(){
    ssn = " ";
    name = new Name();
  }
  
  //copy constructor
  public Depositor(Depositor dep){
    name = new Name(dep.name);
    ssn = dep.ssn;
  }
  //parametized constructor
  public Depositor(String s, Name n){
    ssn = s;
    name = n;
  }
  
  //toString() method - uses String static method .format()
  public String toString(){
   /* formatted string:
      Name: first, last
      SSN: ssn
    */
    String str = String.format("Name: %s, %s\nSSN: %s", 
                               name.getFirst(),
                               name.getLast(),
                               ssn
                                 );
    return str;
  }
  
  
  //equals() method
 public boolean equals(Depositor dep) {
  
   if(name.equals(dep.name))
   return true;   //dep found
  else
   return false;   //dep not found
 }
    
  //getters 
  public Name getName(){
    Name nameCopy = new Name(name);
    return nameCopy;
  }
  
  public String getSsn(){
    return ssn;
  }
  
}
