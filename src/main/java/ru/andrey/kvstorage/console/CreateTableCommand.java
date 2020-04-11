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
