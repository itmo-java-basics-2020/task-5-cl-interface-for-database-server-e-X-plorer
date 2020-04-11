package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseCommandArgumentException;
import ru.andrey.kvstorage.logic.SimpleDatabase;

public class CreateSimpleDatabaseCommand implements DatabaseCommand {
    private static final int ARGS_LENGTH = 1;
    private static final int DATABASE_NAME_ARG_INDEX = 0;

    private final String databaseName;
    private final ExecutionEnvironment environment;

    public CreateSimpleDatabaseCommand(ExecutionEnvironment environment, String... args)
            throws DatabaseCommandArgumentException {
        if (args == null || args.length != ARGS_LENGTH) {
            throw new DatabaseCommandArgumentException("This command requires exactly " + ARGS_LENGTH + " arguments");
        }
        if (environment == null) {
            throw new DatabaseCommandArgumentException("Execution environment not specified");
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
