/* Edmund Sin 
 * Bank Accounts  Using Inheritance, Polymorphism, AbstractClasses and Methods 
 */

import java.util.Calendar;
import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Bank_7{

public static void main (String args[])throws IOException{
  
  int numAccts;
  char choice;
  boolean notDone = true;
  Bank bank = new Bank();
  int count = 0;
         
  File testFile = new File("mytestcases4.txt");
  Scanner kybd = new Scanner(testFile);
  PrintWriter outFile = new PrintWriter("myoutput.txt");
  
  //read in totals using readDataTotals method in Bank class
  Bank.readDataTotals();
  //read in data from myinputs4.txt
  readAccts( bank);
  //print database to myoutput.txt
  printAccts(outFile, bank);
  //call method in Bank class and set variable with return value
  numAccts = bank.getNumAccts();
  
   Calendar calendar = Calendar.getInstance();

     do {
   //print the menu
   menu();

   //prompt to make a selection
   System.out.print("enter selection: ");
   //read the selection
   choice = kybd.next().charAt(0);
         
   //process the selection
   switch(choice)
   {
    case 'Q':
    case 'q':
     notDone = false;
     printAccts(outFile, bank);
     break;
    case 'W':
    case 'w':
     withdrawal(kybd, bank, outFile);
     break;
    case 'D':
    case 'd':
     deposit(kybd, bank, outFile);
     break;
     case 'C':
     case 'c':
     clearCheck(kybd, bank, outFile);
     break;                         
    case 'N':
    case 'n':
      newAcct(kybd,bank,outFile);
     break;
    case 'S':
    case 's':
      closeAccount(kybd,bank,outFile);
      break;
    case 'R':
    case 'r':
      reopenAccount(kybd,bank,outFile);
      break;
    case 'X':
    case 'x':
      deleteAccount(kybd,bank,outFile);
       break;
    case 'B':
    case 'b':
      balance(kybd,bank,outFile);
     break;
    case 'I':
    case 'i':
    accountInfo(kybd,bank,outFile);
     break;
    case 'H':
    case 'h':
    accountInfo(kybd,bank,outFile);
    accountInfoHistory(kybd,bank,outFile);  
     break;
    default:
     outFile.println("Error: " + choice + 
                     " is an invalid selection -  try again");
      outFile.println();
      outFile.flush();
     break;
   }
  } while (notDone);
  
  //print to console to show program completion
  System.out.println("The program is terminating");

  //close the keyboard
  kybd.close();
     
  //close the output file
  outFile.close();
 
 }
  
  /* Method printAccts:
  * Input:
  *  outFile - reference to the output file
  *  bank - Bank class reference
  * Process:
  *  create local reference variables
  *  prints database table header
  *  for loop goes through account arraylist linearly until num_accts
  *  prints element values at each index with the use of getters 
  * Output:
  *  prints database header and all existing info for each arraylist
  *  element
  */   
 
public static void printAccts(PrintWriter outFile, Bank bank)
 {
  
  Depositor myDepositor;
  Account myAccount;
  
  //print header
  outFile.println("\t\t\t\t Accounts in the Database");
  outFile.println();
  //print table column headings
  outFile.printf("%-20s%-13s%-11s%-15s%-9s%-13s",
    "Name","SSN","Account","Account Type", "Status","Balance");
  outFile.println();
  
  for (int count = 0; count < bank.getNumAccts(); count++)
  {
    myAccount = bank.getAcct(count);
    myDepositor = myAccount.getDepositor();
    //uses overloaded toString() method in Account class 
    outFile.println(myAccount.toString());
    
  }
  
   //print Total Balances table header
   outFile.println();
   outFile.println("\t\t\t\t Total Balances");
   outFile.println();
   outFile.printf("%-20s%-20s%-20s%-20s",
                  "Savings" , "Checking", "CD", "All Accounts");
   outFile.println();
   //print total balances with static get-methods from Bank class
   outFile.printf("$%,-19.2f" , Bank.getSavingsTotal());
   outFile.printf("$%,-19.2f" , Bank.getCheckingTotal());
   outFile.printf("$%,-19.2f" , Bank.getCDTotal());
   outFile.printf("$%,.2f" , Bank.getAllTotal());
   outFile.println();
   outFile.println();
   outFile.println();
   outFile.flush();
   
 
}

/* Method readAccts:
  * Input:
  *  Bank class object
  * Process:
  *  sets variable declarations
  *  set new reference object of "myinputs4.txt"
  *  while myFile hasNext(), each line is tokenized 
  *  data from each line of myFile is set using parametized constructors
  *  call addAccount method in Bank class, adds to Account arraylist
  * Output:
  *  given an existing file and data inside, 
  *  all information is read with Scanner and assigned using parameters
  *  Account arraylist is set with addAccount method
  */ 

 public static void readAccts( Bank bank) throws IOException{
   //local variable
   
   String line;
   String tempStr;
   String statusO = "OPEN";
   //open the BankAccounts input file
   File myFile = new File("myinputs4.txt");
   
   //Create a Scanner object to read the input file
   Scanner rFile = new Scanner(myFile);
   
   while (rFile.hasNext())
   {
     
     //read next ine of data
     line = rFile.nextLine();
     StringTokenizer myLine = new StringTokenizer(line);
     double temp;
     
     
     //extract the data from the line read
     //set class elements with parametized constructor
    Name myName = new Name(
                         myLine.nextToken(),
                         myLine.nextToken());
    Depositor depositor = new Depositor(
                         myLine.nextToken(),
                         myName);
    Account acct = new Account(
                         depositor, 
                         Integer.parseInt(myLine.nextToken()),
                         myLine.nextToken(),
                         temp = Double.parseDouble(myLine.nextToken()),
                         statusO
                         );
    
     bank.addAccount(acct); //set the array element 
      
    }
    rFile.close();
   
   
 }
 
 /* Method menu()
  * Input:
  *  none
  * Process:
  *  Prints the menu of transaction choices
  * Output:
  *  Prints the menu of transaction choices
  */  
 
 public static void menu()
 {
     System.out.println();
     System.out.println("Select one of the following transactions:");
     System.out.println("\t****************************");
     System.out.println("\t    List of Choices         ");
     System.out.println("\t****************************");
     System.out.println("\t     W -- Withdrawal");
     System.out.println("\t     D -- Deposit");
     System.out.println("\t     C -- Clear Check");
     System.out.println("\t     N -- New Account");
     System.out.println("\t     S -- Close Account");
     System.out.println("\t     R -- Reopen Account");
     System.out.println("\t     B -- Balance Inquiry");
     System.out.println("\t     I -- Account Info");
     System.out.println("\t     H -- Account History");
     System.out.println("\t     X -- Delete Account");
     System.out.println("\t     Q -- Quit");
     System.out.println();
     System.out.print("\tEnter your selection: ");
 }
 
  /* Method withdrawal:
  * Input: 
  *  kybd - Scanner reference, System.in
  *  bank - Bank class reference
  *  num_accts - arraylist length 
  *  outFile - reference to the output file
  * Process:
  *  prompt for account number
  *  check if account exists
  *  check if account status is OPEN before continuing 
  *  prompt for withdrawal amount
  *  check if account type is CD 
  *  if true prompt for new maturity term
  *  check if maturity entered term is valid
  *  call makeDeposit method in Account class 
  *  else, prints error for CD withdrawal 
  *  this method also makes regular withdrawal 
  *  else, prints error that account is closed
  * Output: 
  *  Given existing account, OPEN status and maturity term met
  *  CD withdrawal is made, or regular withdrawal is made
  *  Otherwise, error message is printed
  */ 
 
 public static void withdrawal(Scanner kybd, Bank bank,
                               PrintWriter outFile)
 {
     int requestedAccount;
     int index;
     double amountToWithdraw;
     double balance;
     Account acct;
     TransactionReceipt tReceipt;
     TransactionTicket tTicket;
     String type;
     Calendar today = Calendar.getInstance();
     
     
     System.out.println();
     System.out.println("Enter the account number: "); 
     requestedAccount = kybd.nextInt();      

     //call findAcct to search if requestedAccount exists
     index = bank.findAcct(requestedAccount);
     
     if (index == -1)                            //invalid account
     {
         outFile.println("Transaction Requested: Withdrawal");
         outFile.println("Error: Account number " + requestedAccount 
                           + " does not exist");
         outFile.println();
         return;
     }
     
     acct = bank.getAcct(index);
     String status = acct.getStatus();
     
     //checks if account status is OPEN before continuing 
    if (status.equals("OPEN")){
    
     //checks if the account type is CD
      if( bank.getAcct(index).getAcctType().equals("CD"))
       {
       Calendar calendar = Calendar.getInstance();
       
       outFile.println("Transaction Requested: CD Withdrawal");
       outFile.println("Account Number: " + acct.getAcctNum());
       System.out.println("Enter amount to Withdraw: ");
       double amount = kybd.nextDouble();
       
       
       //prompt for new maturity term
       System.out.println
         ("Enter new maturity term (6,12,18,or 24 months): ");
       int maturity = kybd.nextInt();
       //check for valid maturity term
       if(maturity != 6 && maturity != 12 && maturity != 18 
         && maturity != 24)
         outFile.println("Error: Invalid maturity term selection");
       else{
       //initialize transaction type variable
       type = "CD Withdrawal";
       //initialize class objects with parametized constructors
       TransactionTicket t = new TransactionTicket
         (calendar, type, amount, maturity);
       TransactionReceipt r = new TransactionReceipt
         (bank.getAcct(index).getAcctBalance(),0.0, calendar,true,t);
       
       //call makeWithdrawal method in Account class for calculations
         acct.makeWithdrawal(t, r, maturity);
       
       //set rcptCount to the latest receipt created
       int rcptCount = acct.getReceiptSize() - 1;
      
       TransactionReceipt recpt = acct.getReceipt(rcptCount);
       
       
       if(recpt.getTransactionSuccessIndicatorFlag() == true){
       
       //USE overloaded toString() method to print data
       outFile.print(recpt.toString());
       
       outFile.println("New Maturity Date: " +
                      recpt.getPostTransactionMaturityDate().getTime());
       }
       else
       {
       //new object with return TransactionReceipt from makeWithdrawl method
         TransactionReceipt error = acct.makeWithdrawal(t,r,maturity);
         //makeWithdrawal method returns receipt with error message string
         outFile.println
           (error.getTransactionFailureReason());
         outFile.println("Account Maturity Date: " + 
           error.getPostTransactionMaturityDate().getTime());              
       }
       
       
     }
     }
     else                                                    //valid account
     {
          //prompt for amount to Withdraw
         System.out.print("Enter amount to Withdraw: ");  
         amountToWithdraw = kybd.nextDouble(); 

         if (amountToWithdraw <= 0.00 )                  
         {
          //invalid amount to Withdraw
          outFile.println("Transaction Requested: Withdrawal");
          outFile.println("Account Number: " + requestedAccount);
          outFile.println("Amount to Withdrawal: $" + amountToWithdraw);
          outFile.printf("Error: $%.2f is an invalid amount"
                           , amountToWithdraw);
          outFile.println();
         } 
         // Withdrawal exceeds balance error
         else if 
           (amountToWithdraw > bank.getAcct(index).getAcctBalance())
         {
          outFile.println("Transaction Requested: Withdrawal");
          outFile.println("Account Number: " + requestedAccount);
          outFile.println("Amount to Withdraw: $" + amountToWithdraw);
          outFile.printf("Account Balance: $%.2f", 
                         bank.getAcct(index).getAcctBalance());
          outFile.println();
          outFile.println("Error: Insufficient Funds"); 
          outFile.println();
       
         }
         
         // makes regular withdrawal 
         else
         {
           //check account type
           if(acct.getAcctType().equals("Savings"))
           /*add negative amount (subtraction) to the relative
             totals static member in Bank class
             */
                Bank.addSavingsTotal(-amountToWithdraw);
           
           else if(acct.getAcctType().equals("Checking"))
                Bank.addCheckingTotal(-amountToWithdraw);
           
          //subtract from all-total static member in Bank class
           Bank.addAllTotal(-amountToWithdraw);
           
           
              
          acct = bank.getAcct(index); 
          type = "Withdrawal";
          double pre =  bank.getAcct(index).getAcctBalance();
          //instantiate objects with parametized constructor 
          tTicket = new TransactionTicket(today,type,amountToWithdraw);
          TransactionReceipt tRcptW = new TransactionReceipt
                         (pre, 0.0, tTicket);
          //set tRcptW object equal to returned TransactionReceipt 
          //from makeWithdrawal method 
          tRcptW = acct.makeWithdrawal(tTicket,tRcptW,0);
          
          outFile.println("Transaction Requested: Withdrawal");
          outFile.println("Account Number: " + requestedAccount);
          //use overloaded toString() to print data
          outFile.print(tRcptW.toString());
          
         
          
          
         }
     }
    
    }   else //account status is CLOSED, does not allow withdrawal
     {
       outFile.println("Transaction Requested: Withdrawal");
       outFile.println("Account Number: " + acct.getAcctNum());
       outFile.println("Error: This Account is CLOSED");
       outFile.println("Reopen the account to make transactions");
       outFile.println();
     }
     
       outFile.println();

       outFile.flush(); 
 }
 
 /* Method deposit:
  * Input: 
  *  kybd - Scanner reference, System.in
  *  bank - Bank class reference
  *  num_accts - arraylist length 
  *  outFile - reference to the output file
  * Process:
  *  prompt for account number
  *  check if account exists
  *  check if account status is OPEN before continuing 
  *  prompt for deposit amount
  *  check if account type is CD 
  *  if true prompt for new maturity term
  *  check if maturity entered term is valid
  *  call makeDeposit method in Account class 
  *  else, prints error for CD deposit 
  *  this method also makes regular deposit 
  *  else, prints error that account is closed
  * Output: 
  *  Given existing account, OPEN status and maturity term met
  *  CD deposit is made, or regular deposit is made
  *  Otherwise, error message is printed
  */ 
 
 public static void deposit(Scanner kybd, Bank bank,
                               PrintWriter outFile)
 {
     int requestedAccount;
     int index;
     double amountToDeposit;
     double balance;
     Account acct;
     TransactionReceipt tReceipt;
     TransactionTicket tTicket;
     String type;
     Calendar today = Calendar.getInstance();
     
     
     System.out.println();
     System.out.println("Enter the account number: "); 
     //prompt for account number
     requestedAccount = kybd.nextInt();      

     //call findAcct to search if requestedAccount exists
     index = bank.findAcct(requestedAccount);
       
     if (index == -1)                               //invalid account
     {
         outFile.println("Transaction Requested: Deposit");
         outFile.println("Requested Account Number: " + requestedAccount);
         outFile.println("Error: Account number does not exist"); 
         outFile.println();                
         return;
     }
     
     acct = bank.getAcct(index);
     String status = acct.getStatus();
     
      //checks if account status is OPEN before continuing 
      if (status.equals("OPEN")){
     
    
     //checks if the account type is CD
      if( bank.getAcct(index).getAcctType().equals("CD"))
     {
       Calendar calendar = Calendar.getInstance();
       //initialize Account object (acct) 
       acct = bank.getAcct(index);
       
       outFile.println("Transaction Requested: CD Deposit");
       outFile.println("Account Number: " + acct.getAcctNum());
       System.out.println("Enter amount to Deposit: ");
       double amount = kybd.nextDouble();
       
       
       //prompt for new maturity term
       System.out.println
         ("Enter new maturity term (6,12,18,or 24 months): ");
       int maturity = kybd.nextInt();
       //check for valid maturity term
       if(maturity != 6 && maturity != 12 && maturity != 18 
         && maturity != 24)
         outFile.println("Error: Invalid maturity term selection");
       else{
       //initialize transaction type variable
       type = "CD Deposit";
       //initialize class objects with parametized constructors
       TransactionTicket t = new TransactionTicket
         (calendar, type, amount, maturity);
       TransactionReceipt r = new TransactionReceipt
         (bank.getAcct(index).getAcctBalance(),0, calendar,true,t);
       
       //call makeDeposit method in Account class for calculations
       acct.makeDeposit(t, r, maturity);
       
       //set rcptCount to the latest receipt created
       int rcptCount = acct.getReceiptSize() - 1;
      
       TransactionReceipt recpt = acct.getReceipt(rcptCount);
       
       
       if(recpt.getTransactionSuccessIndicatorFlag() == true){
        
       //USE OVERLOADED toString() method to print data 
       outFile.print(recpt.toString());
       outFile.println("New Maturity Date: " +
                      recpt.getPostTransactionMaturityDate().getTime());
       }
       else
       {
         //new object with return TransactionReceipt from makeDeposit method
         TransactionReceipt error = acct.makeDeposit(t,r,maturity);
         //makeDeposit method returns receipt with error message string
         outFile.println
           (error.getTransactionFailureReason());
         outFile.println("Account Maturity Date: " + 
           error.getPostTransactionMaturityDate().getTime());              
       }
       
     }
     }
     else                                            //valid account
     {
          //prompt for amount to Deposit
         System.out.print("Enter amount to Deposit: ");  
         amountToDeposit = kybd.nextDouble(); 
         //read-in the amount to Deposit

         if (amountToDeposit <= 0.00 )                  
         {
          //invalid amount to Deposit
          outFile.println("Transaction Requested: Deposit");
          outFile.println("Account Number: " + requestedAccount);
          outFile.println("Amount to Deposit: $" + amountToDeposit);
          outFile.printf("Error: $%.2f is an invalid amount"
                           , amountToDeposit);
          outFile.println();
         } 
        
         // makes regular Deposit 
         else
         {
           //ADD TO BANK STATIC MEMBERS 
           //check account type
           if(acct.getAcctType().equals("Savings"))
           /*add amount to the relative
             totals static member in Bank class
             */
                Bank.addSavingsTotal(amountToDeposit);
           
           else if(acct.getAcctType().equals("Checking"))
                Bank.addCheckingTotal(amountToDeposit);
           
          //add to all-total static member in Bank class
           Bank.addAllTotal(amountToDeposit); 
           
         
          acct = bank.getAcct(index); 
          
          type = "Deposit";
          double pre =  bank.getAcct(index).getAcctBalance();
          //instantiate objects with parametized constructor 
          tTicket = new TransactionTicket(today,type,amountToDeposit);
          TransactionReceipt tRcptW = new TransactionReceipt
                         (pre, 0.0, tTicket);
          
          //set tRcptW object equal to returned TransactionReceipt 
          //from makeDeposit method 
          tRcptW = acct.makeDeposit(tTicket,tRcptW,0);
          
          outFile.println("Transaction Requested: Deposit");
          outFile.println("Account Number: " + requestedAccount);
         
          //USE OVERLOADED toString method to print data
          outFile.print(tRcptW.toString());
          
          
          
         }
     }
       
     } else //account status is CLOSED, does not allow deposit
     {
       outFile.println("Transaction Requested: Deposit");
       outFile.println("Account Number: " + acct.getAcctNum());
       outFile.println("Error: This Account is CLOSED");
       outFile.println("Reopen the account to make transactions");
       outFile.println();
     }
       outFile.println();
       outFile.flush(); 
 }
 
/* Method accountInfoHistory:
  * Input:
  *  kybd - Scanner reference, System.in
  *  bank - Bank class reference
  *  outFile - reference to the output file
  * Process:
  *  prompt for account number
  *  set index with return int of bank.findAcct
  *  set acct object with returned Account object of Bank class arraylist
  *  print all receipts of specific account 
  * Output: 
  *  Given existing account number, all transaction receipts are printed
  *  Otherwise, error message is printed
  */ 
 
 public static void accountInfoHistory
                  (Scanner kybd, Bank bank, PrintWriter outFile)
 {
  Account acct;
  int reqAccount, index; 
  
  outFile.println("Additional Account History Info");
  System.out.println("Enter your account number: ");
  reqAccount = kybd.nextInt();
  
  index = bank.findAcct(reqAccount);
  if (index == -1)                               //invalid account
     {
     
      outFile.println("Requested Account Number: " + reqAccount);
      outFile.println("Error: Account number does not exist"); 
      outFile.println();                
       return;
     }
  
  acct = bank.getAcct(index);
  
  //print all receipts for specific account 
  for(int count = 0; count<acct.getReceiptSize(); count++){
    TransactionReceipt rcpt = acct.getReceipt(count);
    
    TransactionTicket tic = rcpt.getTransactionTicket();
    
    outFile.println("Account Number: " + reqAccount);
    outFile.println("Type of Transaction: " + tic.getTransactionType());
    
    if(acct.getAcctType().equals("CD")){
      outFile.println("Term of CD (Months): " + tic.getTermOfCD());
      outFile.println("Post Transaction Maturity Date: " 
                   + rcpt.getPostTransactionMaturityDate().getTime());
    }
    //use overloaded toString() method to print additional data
    outFile.println(rcpt.toString());
  }
  
   outFile.println();
   outFile.flush();
 }
   
 /* Method accountInfo:
  * Input:
  *  kybd - Scanner reference, System.in
  *  bank - Bank class reference
  *  outFile - reference to the output file
  * Process:
  *  prompt for ssn
  *  linear array search for matching ssn
  *  prints info of all accounts with matching ssn
  *  if no accounts match ssn, error is printed
  * Output: 
  *  Given a valid ssn with matching accounts, 
  *  info of all accounts matching are printed
  *  Otherwise, error is printed 
  */ 
  
 public static void accountInfo
   (Scanner kybd, Bank bank, PrintWriter outFile)
                                
 {
    String requestedSsn;
    int count = 0;
    
    Name myName;
    Depositor depositor;
    Account acct;
    
    outFile.println("Transaction Requested: Account Info");
    System.out.println("Enter your Social Security Number: ");
    requestedSsn = kybd.next();
    
    //linear array search for matching SSN
    for (int i = 0; i < bank.getNumAccts(); i++){
       acct = bank.getAcct(i);
       depositor = acct.getDepositor();
       myName = depositor.getName();
       
      if (depositor.getSsn().equals(requestedSsn)){
       
     //prints all accounts with matching SSN
     
     //use toString() method to print depositor name and ssn   
     outFile.println(depositor.toString());
     
     outFile.println("Bank Account Number: " + 
                     acct.getAcctNum());
     outFile.println("Account Type: " + 
                     acct.getAcctType());
     outFile.println("Account Status: " + acct.getStatus());
     outFile.printf("Account Balance: $%.2f" , 
                    acct.getAcctBalance());
     outFile.println();
     outFile.println();
       
      } 
      
      //at each index, count will be incremented if the SSNs do not match
      else count++;

   }
    //if the total number of accounts equals the count value,
    // then no SSNs have matched at any index
    if (count == bank.getNumAccts()){         
      
      outFile.println("Error: Social Security Number does not exist");  
      outFile.println("Requested SSN: " + requestedSsn); 
      outFile.println();
     }
  outFile.flush();
  
 }
 
 /* Method newAcct:
  * Input:
  *  kybd - Scanner reference, System.in
  *  bank - Bank class reference
  *  outFile - reference to the output file
  * Process:
  *  prompt for new account number
  *  checks if number is 6 digits
  *  checks if account number already exists in database
  *  prompts for all information required for a new account
  *  sets objects with paremtized constructor 
  *  adds objects into Bank class arraylist of Accounts
  *  set TransactionReceipt and TransactionTicket
  *  adds objects into Account class arraylist of Receipts
  *  prints new account info 
  * Output: 
  *  Given valid information entered, new account is added into arraylist
  *  new account info is printed
  *  Otherwise, error is printed
  */ 
 
 public static void newAcct(Scanner kybd, Bank bank, PrintWriter outFile)                          
 {
   int newAccount; // 6 digit number of new account
  
   System.out.println();
   //prompt for new account number
   System.out.println("Enter a 6 digit account number: ");   
   newAccount = kybd.nextInt();      //read-in the new account number
   
   // sets the value of length based on the account number's total digits
   int length = String.valueOf(newAccount).length(); 
   
   // error if the account number entered has more or less than 6 digits
   if (length > 6 || length < 6) 
   {
     outFile.println("Transaction Requested: New Account");
     outFile.println("Account Number: " + newAccount);
     outFile.println("Error: Please enter a 6 digit account number");
   }
   //error. if the account number already exists, 
   // findAcct method will return the index value of 0 or greater 
   else if (bank.findAcct(newAccount) >= 0)
   {
     outFile.println("Transaction Requested: New Account");
     outFile.println("Account Number: " + newAccount);
     outFile.println("Error: Account number already exists");
   }
   // creates a new account in Bank class arraylist
   else 
   {
     String nameFirst, nameLast, acctType, ssn;
     double depositAmount;
     int cdTerm;
     String statusO = "OPEN";
     
     //prompts for new account info and declares variables 
     System.out.println("Enter your first name: ");
     nameFirst = kybd.next();
     System.out.println("Enter your last name: ");
     nameLast = kybd.next();
     System.out.println("Enter your Social Security Number: ");
     ssn = kybd.next();
     System.out.println("Enter your account type ");
     acctType = kybd.next();
     System.out.println("Enter Maturity Term (6,12,18, or 24 months");
     cdTerm = kybd.nextInt();
     System.out.println("Enter opening deposit amount: ");
     depositAmount = kybd.nextDouble();
     
     //CHECK ACCOUNT TYPE AND ADD TO BANK STATIC MEMBERS
     
     if(acctType.equals("Savings"))
       Bank.addSavingsTotal(depositAmount);
     else if(acctType.equals("Checking"))
       Bank.addCheckingTotal(depositAmount);
     else if(acctType.equals("CD"))
       Bank.addCDTotal(depositAmount);
     
     Bank.addAllTotal(depositAmount);
     
     //new object instatiated with parametized constructor
     Name myName = new Name(nameFirst, nameLast);
     Depositor depositor = new Depositor(ssn,myName);
     Account acct = new Account(
                         depositor, 
                         newAccount,
                         acctType,
                         depositAmount,
                         statusO
                         );
     //add Account into Bank class arraylist
     bank.addAccount(acct);
     
     String type = "New Account";
     Calendar today = Calendar.getInstance();
     //add to TransactionReceipt array with objects
     TransactionTicket t = new TransactionTicket
       (today, type,depositAmount,cdTerm);
     TransactionReceipt r = new TransactionReceipt
       (0.0, depositAmount,t);
     
     //call openNewAcct method in Bank class 
     bank.openNewAcct(t, r, bank);
     
     
     outFile.println("Transaction Requested: New Account");
     //use toString() method to print depositor name and ssn
     outFile.println(depositor.toString());
     outFile.println("Account Number: " + newAccount);
     outFile.println("Account Type: " + acct.getAcctType());
     //use toString() method to print receipt data
     outFile.print(r.toString());
     
      }      
    
     outFile.println();

     outFile.flush();
   
    
 }
   
 /* Method clearCheck:
  *Input:
  *  kybd - Scanner reference, System.in
  *  bank - Bank class object
  *  outFile - PrintWriter object  
  *Process:
  * create Calendar instances 
  * prompt for account number
  * call findAcct and set index with return value
  * check if account type at arraylist index is equal to Checking
  * prompts for month, date, year
  * sets variables with values
  * sets calendar instance with variables
  * set Check class object with parameter
  * makes withdrawal given valid time comparison
  *Output:
  * Given valid amount, account, and check date, withdrawal made 
  * otherwise, error is printed
  */
 
 public static void clearCheck(Scanner kybd, Bank bank, PrintWriter outFile)
                             
                                 
  {
    int acctNum;
    int index;
    double balance;
    double checkAmount;
    int month,date,year;
    boolean flag;
    //Calendar instances for comparisons
    Calendar calendar = Calendar.getInstance();
    Calendar today = Calendar.getInstance();
    Calendar sixBefore = Calendar.getInstance();
    
    //six months ago from today's date
    sixBefore.add(Calendar.MONTH, -6);
    
    
    outFile.println("Transaction Requested: Clear Check");
    System.out.println("Enter Account Number: ");
    //prompt for account number
    acctNum = kybd.nextInt();
    
    //call findAcct from Bank and set index with returned value
    index = bank.findAcct(acctNum);
    if(index == -1)
    {
      outFile.println("Account number: " + acctNum);
      outFile.println("Error: Account does not exist");
      outFile.println();
    }
    else
    {
       Account acct = bank.getAcct(index);
    
       //prompt for check month, date, year and set calendar object
       System.out.println("Enter Check Month: ");
       month = kybd.nextInt();
       calendar.set(Calendar.MONTH, month-1);
       System.out.println("Enter Check Date: ");
       date = kybd.nextInt();
       calendar.set(Calendar.DATE, date);
       System.out.println("Enter Check Year: ");
       year = kybd.nextInt();
       calendar.set(Calendar.YEAR, year);
       
             
          
       System.out.println("Enter Check Amount: ");
       checkAmount = kybd.nextDouble();
       //instantiate check object with parametized constructor
       Check check = new Check(acctNum,checkAmount,calendar);
       
       //create POLYMORPHIC reference object 
       Account cAccount = new CheckingAccount(acct);
       
       //call clearCheck method in checkingAccount class for calculations
       TransactionReceipt returnRcpt = cAccount.clearCheck(check, bank);
       
       flag = returnRcpt.getTransactionSuccessIndicatorFlag();
       
       //if successIndicatorFlag is false, error prints accordingly
       //print error string returned from returnRcpt with getter 
       if(flag == false)
       {
        String error = returnRcpt.getTransactionFailureReason();
        outFile.println("Account Number: " + acctNum);
        outFile.println(error);
        outFile.printf
          ("Current Account Balance: $%.2f"
             , acct.getAcctBalance());
        outFile.println();
     }
       //flag == true, print account info after transaction
       else {
         
         //use toString() method to print check data
         outFile.println(check.toString());
         //use toString() method to print receipt data
         outFile.print(returnRcpt.toString());
         
          if(acct.getAcctBalance() < 2500){
           outFile.println
             ("Account Balance is under $2,500. $1.5 Penalty Incurred");
           outFile.println();
          }
       }
       
    outFile.println();
    
    outFile.flush();
  
  }
    
 }
 
 /* Method closeAccount
  *Input:
  * kybd- Scanner reference, System.in
  * bank - Bank class reference
  * outFile - PrintWriter reference
  *Process: 
  * declare variables
  * prompt for account number
  * check if account exists
  * sets acct object with getAcct method returned account object 
  * set TransactionTicket and TransactionReceipt with constructors
  * call closeAccount method in Account class 
  * print transaction info
  *Output: 
  * Given existing account number, account is closed 
  * transaction info is printed
  * Otherwise, error is printed
  */ 
 
 public static void closeAccount
   (Scanner kybd, Bank bank, PrintWriter outFile)
 {
   int acctNum, index;
   double balance;
   Calendar today = Calendar.getInstance();
   String type = "Close Account";
   
  outFile.println("Transaction Requested: Close Account");
  System.out.println("Enter Account Number: ");
  acctNum = kybd.nextInt();
     
  //call findAcct from Bank and set index with returned value
  index = bank.findAcct(acctNum);
    if(index == -1)
    {
      outFile.println("Account number: " + acctNum);
      outFile.println("Error: Account does not exist");
      outFile.println();
    }
    else
    {
       Account acct = bank.getAcct(index);
       Depositor depositor = acct.getDepositor();
       balance = acct.getAcctBalance();
       //set with parametized constructors
       TransactionTicket tic = new TransactionTicket
         (today,type,0.0);
       TransactionReceipt rcpt = new TransactionReceipt
         (balance,balance,tic);
       //call closeAccount method in Account class
       acct.closeAccount(tic,rcpt);
       
       //use toString() method to print depositor name and ssn
       outFile.println(depositor.toString());
       outFile.println("Account Number: " + acctNum);
       outFile.printf("Remaining Balance: $%.2f", balance);
       outFile.println();
       outFile.println("This account has been CLOSED successfully");
       outFile.println();
       
     }
    outFile.flush();
  
  }
 /* Method reopenAccount
  *Input:
  * kybd- Scanner reference, System.in
  * bank - Bank class reference
  * outFile - PrintWriter reference
  *Process: 
  * declare variables
  * prompt for account number
  * check if account exists
  * sets acct object with getAcct method returned account object 
  * set TransactionTicket and TransactionReceipt with constructors
  * call reopenAccount method in Account class 
  * print transaction info
  *Output: 
  * Given existing account number, account is reopened
  * transaction info is printed
  * Otherwise, error is printed
  */ 
public static void reopenAccount
   (Scanner kybd, Bank bank, PrintWriter outFile)
 {
   int acctNum, index;
   double balance;
   Calendar today = Calendar.getInstance();
   String type = "Reopen Account";
   
  outFile.println("Transaction Requested: Reopen Account");
  System.out.println("Enter Account Number: ");
  acctNum = kybd.nextInt();
     
  //call findAcct from Bank and set index with returned value
  index = bank.findAcct(acctNum);
 
    if(index == -1)
    {
      outFile.println("Account number: " + acctNum);
      outFile.println("Error: Account does not exist");
      outFile.println();
      
      return;
    }
    
    Account acct = bank.getAcct(index);
    Depositor depositor = acct.getDepositor();
  
    if(acct.getStatus().equals("OPEN")){
      outFile.println("Account number: " + acctNum);
      outFile.println("Error: This account is already OPEN");
      outFile.println();
    }
    else {
       balance = acct.getAcctBalance();
       TransactionTicket tic = new TransactionTicket
         (today,type,0.0);
       TransactionReceipt rcpt = new TransactionReceipt
         (balance,balance,tic);
       acct.reopenAccount(tic,rcpt);
       
       //use toString() method to print depositor name and ssn
       outFile.println(depositor.toString());
       outFile.println("Account Number: " + acctNum);
       outFile.printf("Balance: $%.2f", balance);
       outFile.println();
       outFile.println("This account has been REOPENED successfully");
       outFile.println();
       
    }
  outFile.flush();
}
 
/* Method deleteAcct:
  * Input:
  *  kybd - Scanner reference, System.in
  *  bank - Bank class reference
  *  outFile - reference to the output file
  * Process:
  *  sets variable declarations
  *  prompt for account number
  *  check if account exists
  *  check if there is a remaining balance
  *  set TransactionTicket object with parametized constructor 
  *  call deleteAccount method in Bank class
  *  print transaction info
  * Output: 
  *  Given existing account and no balance, account is deleted 
  *  prints transaction info
  *  Otherwise, error is printed
  */ 
  
 
 public static void deleteAccount
   (Scanner kybd, Bank bank, PrintWriter outFile)
 {
    int acctNum, index;
    double balance;
    Account acct;
    String type = "Delete Account";
    
    outFile.println("Transaction Requested: Delete Account");
    System.out.println("Enter Account Number: ");
    //prompt for account number
    acctNum = kybd.nextInt();
    
    //call findAcct from Bank and set index with returned value
    index = bank.findAcct(acctNum);
      
    if(index == -1)
    {
      outFile.println("Account number: " + acctNum);
      outFile.println("Error: Account does not exist");
      outFile.println();
      
      return;
    }
     //set account object from specific arraylist index in Bank
     acct = bank.getAcct(index);
     Depositor depositor = acct.getDepositor();
     balance = acct.getAcctBalance();
     
     //check for remaining balance
     if(balance > 0)
    {
      outFile.println("Account number: " + acctNum);
      outFile.printf("Account Balance: $%.2f", balance);
      outFile.println();
      outFile.println("Error: Account has a remaining balance");
      outFile.println();
      
    } 
    else
    {
    
      Calendar today = Calendar.getInstance();
      TransactionTicket tic = new TransactionTicket
        (today, type);
      //call deleteAccount method in Bank class 
      bank.deleteAccount(tic,index);
      
      //use toString() method to print depositor name and ssn
      outFile.println(depositor.toString());
      outFile.println("Account number: " + acctNum);
      outFile.printf("Account Balance: $%.2f", balance);
      outFile.println();
      outFile.println("Account has been successfully deleted");
      outFile.println();
      
    }
  outFile.flush();
}
 
 /* Method balance:
  * Input:
  *  kybd - Scanner reference, System.in
  *  bank - Bank class reference
  *  outFile - reference to the output file
  * Process:
  *  sets variable declarations
  *  prompt for account number
  *  check if account exists
  *  prints account info at index 
  * Output: 
  *  Given existing account, info is printed 
  *  Otherwise, error is printed 
  */  
 
 public static void balance(Scanner kybd, Bank bank, PrintWriter outFile)
 {
    int acctNum, index;
    double balance;
    Account acct;
    String type = "Balance Inquiry";
    
    outFile.println("Transaction Requested: Balance Inquiry");
    System.out.println("Enter Account Number: ");
    //prompt for account number
    acctNum = kybd.nextInt();
    
    //call findAcct from Bank and set index with returned value
    index = bank.findAcct(acctNum);
      
    if(index == -1)
    {
      outFile.println("Account number: " + acctNum);
      outFile.println("Error: Account does not exist");
      outFile.println();
    }
    else
   {
    //set account object from specific arraylist index in Bank
    acct = bank.getAcct(index);
    Depositor depositor = acct.getDepositor();
    balance = acct.getAcctBalance();
     
     //use toString() method to print depositor name and ssn
     outFile.println(depositor.toString());
     outFile.println("Account Number: " + acctNum);
     outFile.println("Account Type: " + acct.getAcctType());
     outFile.printf("Account Balance: $%.2f" , balance);
     outFile.println();
     outFile.println();
   }
  outFile.flush();
}
 
}

  
 
