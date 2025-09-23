package br.com.alura;

public class CommandExecutor {

    public CommandExecutor() {
    }

    void commandExecutor(Command command) {
        command.execute();
    }
}
