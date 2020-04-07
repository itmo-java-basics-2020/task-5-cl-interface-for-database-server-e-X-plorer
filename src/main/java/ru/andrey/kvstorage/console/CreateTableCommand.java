package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseCommandArgumentException;
import ru.andrey.kvstorage.exception.DatabaseException;

public class CreateTableCommand implements DatabaseCommand {

    private static final int ARGS_LENGTH = 2;
    private static final int DATABASE_NAME_ARG_INDEX = 0;
    private static final int TABLE_NAME_ARG_INDEX = 1;

    private final String tableName;
    private final String databaseName;
    private final ExecutionEnvironment environment;

    private CreateTableCommand(String databaseName, String tableName, ExecutionEnvironment environment) {
        this.databaseName = databaseName;
        this.tableName = tableName;
        this.environment = environment;
    }

    public static DatabaseCommand GetCommand(ExecutionEnvironment env, String... args)
            throws DatabaseCommandArgumentException {
        if (args.length != ARGS_LENGTH) {
            throw new DatabaseCommandArgumentException("This command requires exactly " + ARGS_LENGTH + " arguments");
        }
        return new CreateTableCommand(args[DATABASE_NAME_ARG_INDEX], args[TABLE_NAME_ARG_INDEX], env);
    }

    @Override
    public DatabaseCommandResult execute() {
        var database = environment.getDatabase(databaseName);
        if (database.isEmpty()) {
            return DatabaseCommandResult.Result.error("Database " + databaseName + " not found");
        }

        try {
            database.get().createTableIfNotExists(tableName);
        } catch (DatabaseException exception) {
            return DatabaseCommandResult.Result.error(exception.getMessage());
        }

        return DatabaseCommandResult.Result
                .success("Table " + tableName + " added to database " + databaseName + " successfully");
    }
}
