package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;

public class ReadKeyCommand implements DatabaseCommand {

    private String tableName;

    private String databaseName;

    private String key;

    private ExecutionEnvironment environment;

    public ReadKeyCommand(String databaseName, String tableName, String key,
                          ExecutionEnvironment environment) {
        this.databaseName = databaseName;
        this.tableName = tableName;
        this.key = key;
        this.environment = environment;
    }

    @Override
    public DatabaseCommandResult execute() {
        var database = environment.getDatabase(databaseName);
        if (database.isEmpty()) {
            return DatabaseCommandResult.Result.error("Database " + databaseName + " not found");
        }

        String result;
        try {
            result = database.get().read(tableName, key);
        } catch (DatabaseException exception) {
            return DatabaseCommandResult.Result.error(exception.getMessage());
        }

        if (result == null) {
            return DatabaseCommandResult.Result.error("Key " + key + " not found");
        }
        return DatabaseCommandResult.Result.success(result);
    }
}
