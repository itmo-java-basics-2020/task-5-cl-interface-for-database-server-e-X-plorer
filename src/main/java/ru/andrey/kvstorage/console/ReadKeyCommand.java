package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;

public class ReadKeyCommand implements DatabaseCommand {
    private final String tableName;
    private final String databaseName;
    private final String key;
    private final ExecutionEnvironment environment;

    public ReadKeyCommand(String databaseName, String tableName, String key,
                          ExecutionEnvironment environment) {
        this.databaseName = databaseName;
        this.tableName = tableName;
        this.key = key;
        this.environment = environment;
    }

    @Override
    public DatabaseCommandResult execute() {
        var optionalDatabase = environment.getDatabase(databaseName);
        if (optionalDatabase.isEmpty()) {
            return DatabaseCommandResult.error("Database " + databaseName + " not found");
        }
        var database = optionalDatabase.get();

        String result;
        try {
            result = database.read(tableName, key);
        } catch (DatabaseException exception) {
            return DatabaseCommandResult.error(exception.getMessage());
        }

        if (result == null) {
            return DatabaseCommandResult.error("Key " + key + " not found");
        }
        return DatabaseCommandResult.success(result);
    }
}
