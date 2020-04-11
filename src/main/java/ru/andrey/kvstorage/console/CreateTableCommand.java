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

    public CreateTableCommand(ExecutionEnvironment environment, String... args) throws DatabaseCommandArgumentException {
        if (args == null || args.length != ARGS_LENGTH) {
            throw new DatabaseCommandArgumentException("This command requires exactly " + ARGS_LENGTH + " arguments");
        }
        if (environment == null) {
            throw new DatabaseCommandArgumentException("Execution environment not specified");
        }

        this.environment = environment;
        databaseName = args[DATABASE_NAME_ARG_INDEX];
        tableName = args[TABLE_NAME_ARG_INDEX];
    }

    @Override
    public DatabaseCommandResult execute() {
        var optionalDatabase = environment.getDatabase(databaseName);
        if (optionalDatabase.isEmpty()) {
            return DatabaseCommandResult.error("Database " + databaseName + " not found");
        }
        var database = optionalDatabase.get();

        try {
            database.createTableIfNotExists(tableName);
        } catch (DatabaseException exception) {
            return DatabaseCommandResult.error(exception.getMessage());
        }

        return DatabaseCommandResult
                .success("Table " + tableName + " added to database " + databaseName + " successfully");
    }
}
