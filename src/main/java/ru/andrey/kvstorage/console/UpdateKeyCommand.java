package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;

public class UpdateKeyCommand implements DatabaseCommand {
    private static final int ARGS_LENGTH = 4;
    private static final int DATABASE_NAME_ARG_INDEX = 0;
    private static final int TABLE_NAME_ARG_INDEX = 1;
    private static final int KEY_ARG_INDEX = 2;
    private static final int VALUE_ARG_INDEX = 3;

    private final String tableName;
    private final String databaseName;
    private final String key;
    private final String value;
    private final ExecutionEnvironment environment;

    public UpdateKeyCommand(ExecutionEnvironment environment, String... args) {
        if (args == null || args.length != ARGS_LENGTH) {
            throw new IllegalArgumentException("This command requires exactly " + ARGS_LENGTH + " arguments");
        }
        if (environment == null) {
            throw new IllegalArgumentException("Execution environment not specified");
        }

        this.environment = environment;
        databaseName = args[DATABASE_NAME_ARG_INDEX];
        tableName = args[TABLE_NAME_ARG_INDEX];
        key = args[KEY_ARG_INDEX];
        value = args[VALUE_ARG_INDEX];
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
