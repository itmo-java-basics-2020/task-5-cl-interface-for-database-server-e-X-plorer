package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseCommandArgumentException;
import ru.andrey.kvstorage.logic.SimpleDatabase;

public class CreateSimpleDatabaseCommand implements DatabaseCommand {

    private static final int ARGS_LENGTH = 1;
    private static final int DATABASE_NAME_ARG_INDEX = 0;

    private final String databaseName;
    private final ExecutionEnvironment environment;

    private CreateSimpleDatabaseCommand(String databaseName, ExecutionEnvironment environment) {
        this.databaseName = databaseName;
        this.environment = environment;
    }

    public static DatabaseCommand GetCommand(ExecutionEnvironment env, String... args)
            throws DatabaseCommandArgumentException {
        if (args.length != ARGS_LENGTH) {
            throw new DatabaseCommandArgumentException("This command requires exactly " + ARGS_LENGTH + " arguments");
        }
        return new CreateSimpleDatabaseCommand(args[DATABASE_NAME_ARG_INDEX], env);
    }

    @Override
    public DatabaseCommandResult execute() {
        environment.addDatabase(new SimpleDatabase(databaseName));
        return DatabaseCommandResult.Result
                .success("Database " + databaseName + " added or updated successfully");
    }
}
