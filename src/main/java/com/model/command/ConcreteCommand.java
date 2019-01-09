package com.model.command;

public class ConcreteCommand  implements Command{
    private Receiver receiver;

    public ConcreteCommand(Receiver receiver){
        this.receiver = receiver;
    }

    public void execute() {
        receiver.action();
    }

    public void unDo() {
        receiver.unAction();
    }
}
