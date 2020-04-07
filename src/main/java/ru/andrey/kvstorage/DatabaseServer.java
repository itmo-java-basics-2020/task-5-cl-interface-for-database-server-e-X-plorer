package ru.andrey.kvstorage;

import ru.andrey.kvstorage.console.*;

import java.util.Arrays;
import java.util.Scanner;

public class DatabaseServer {

    private final ExecutionEnvironment env;

    public DatabaseServer(ExecutionEnvironment env) {
        this.env = env;
    }

    public static void main(String[] args) {
        DatabaseServer dbServer = new DatabaseServer(new DatabaseManager());
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Enter your command:");
            DatabaseCommandResult result = dbServer.executeNextCommand(input.next());
            System.out.println(result.getResult().orElse(result.getErrorMessage()) + '\n');
        }
    }

    DatabaseCommandResult executeNextCommand(String commandText) {
        if (commandText == null || commandText.isEmpty()) {
            return DatabaseCommandResult.Result.error("No input was given");
        }
        String[] arguments = commandText.split(" ");
        try {
            return DatabaseCommands.valueOf(arguments[0])
                    .getCommand(env, Arrays.copyOfRange(arguments, 1, arguments.length)).execute();
        } catch (IllegalArgumentException exception) {
            return DatabaseCommandResult.Result.error(exception.getMessage());
        }
    }
}
