package com.model.command;

public class ConcreteCommand  implements Command{
    private Receiver receiver;

    public ConcreteCommand(Receiver receiver){
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.action();
    }

    @Override
    public void unDo() {
        receiver.unAction();
    }
}
