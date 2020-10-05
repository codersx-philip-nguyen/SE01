package ch15.command;


public class CmdApp {
  
  public static void invoke(Command cmd) {
    // an operation that needs works performed by command object    
    cmd.doWork();
  }

  public static void main(String[] args) {
    // initialise the command and its receiver
    Receiver rec = new Receiver();
    Command cmd = new ConcreteCommand(rec);    

    // at some point during the running of the application
    // we would like to send a command to the receiver for execution
    CmdApp.invoke(cmd);
  }
}

class ConcreteCommand implements Command {
  private Receiver receiver;
  
  public ConcreteCommand(Receiver receiver) {
    this.receiver = receiver;
  }
  
 // an implementation of Command for this application
  public void doWork() {
    // this actually does not do any work by itself, 
    // instead it invokes a method of the CmdApp to do the work
    receiver.someMethod();
  }
}

class Receiver {
 public void someMethod() {
   // invoked by ConcreteCommand.doWork() to actually perform the required work
   // this method operates on the state of this application
 }
}

