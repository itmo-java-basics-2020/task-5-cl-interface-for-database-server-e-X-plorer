package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;

public class UpdateKeyCommand implements DatabaseCommand {
    private final String tableName;
    private final String databaseName;
    private final String key;
    private final String value;
    private final ExecutionEnvironment environment;

    public UpdateKeyCommand(String databaseName, String tableName, String key, String value,
                            ExecutionEnvironment environment) {
        this.databaseName = databaseName;
        this.tableName = tableName;
        this.key = key;
        this.value = value;
        this.environment = environment;
    }

    @Override
    public DatabaseCommandResult execute() {
        var database = environment.getDatabase(databaseName);
        if (database.isEmpty()) {
            return DatabaseCommandResult.Result.error("Database " + databaseName + " not found");
        }

        try {
            database.get().write(tableName, key, value);
        } catch (DatabaseException exception) {
            return DatabaseCommandResult.Result.error(exception.getMessage());
        }

        return DatabaseCommandResult.Result
                .success("Key-value pair (" + key + ", " + value + ") added to table " + tableName + " successfully");
    }
}
