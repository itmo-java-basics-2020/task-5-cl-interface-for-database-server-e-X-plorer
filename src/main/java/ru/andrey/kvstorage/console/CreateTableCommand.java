package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;

public class CreateTableCommand implements DatabaseCommand {
    private final String tableName;
    private final String databaseName;
    private final ExecutionEnvironment environment;

    public CreateTableCommand(String databaseName, String tableName, ExecutionEnvironment environment) {
        this.databaseName = databaseName;
        this.tableName = tableName;
        this.environment = environment;
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
