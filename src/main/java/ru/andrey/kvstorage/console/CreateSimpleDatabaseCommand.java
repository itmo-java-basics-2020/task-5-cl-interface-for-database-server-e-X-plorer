package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.logic.SimpleDatabase;

public class CreateSimpleDatabaseCommand implements DatabaseCommand {
    private static final int ARGS_LENGTH = 1;
    private static final int DATABASE_NAME_ARG_INDEX = 0;

    private final String databaseName;
    private final ExecutionEnvironment environment;

    public CreateSimpleDatabaseCommand(ExecutionEnvironment environment, String... args) {
        if (args == null || args.length != ARGS_LENGTH) {
            throw new IllegalArgumentException("This command requires exactly " + ARGS_LENGTH + " arguments");
        }
        if (environment == null) {
            throw new IllegalArgumentException("Execution environment not specified");
        }

        this.environment = environment;
        databaseName = args[DATABASE_NAME_ARG_INDEX];
    }

    @Override
    public DatabaseCommandResult execute() {
        environment.addDatabase(new SimpleDatabase(databaseName));
        return DatabaseCommandResult.success("Database " + databaseName + " added or updated successfully");
    }
}
