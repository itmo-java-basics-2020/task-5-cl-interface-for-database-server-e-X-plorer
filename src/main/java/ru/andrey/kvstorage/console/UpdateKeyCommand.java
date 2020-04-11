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
        var optionalDatabase = environment.getDatabase(databaseName);
        if (optionalDatabase.isEmpty()) {
            return DatabaseCommandResult.error("Database " + databaseName + " not found");
        }
        var database = optionalDatabase.get();

        try {
            database.write(tableName, key, value);
        } catch (DatabaseException exception) {
            return DatabaseCommandResult.error(exception.getMessage());
        }

        return DatabaseCommandResult
                .success("Key-value pair (" + key + ", " + value + ") added to table " + tableName + " successfully");
    }
}
