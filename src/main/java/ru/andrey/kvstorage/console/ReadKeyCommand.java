package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseCommandArgumentException;
import ru.andrey.kvstorage.exception.DatabaseException;

public class ReadKeyCommand implements DatabaseCommand {
    private static final int ARGS_LENGTH = 3;
    private static final int DATABASE_NAME_ARG_INDEX = 0;
    private static final int TABLE_NAME_ARG_INDEX = 1;
    private static final int KEY_ARG_INDEX = 2;

    private final String tableName;
    private final String databaseName;
    private final String key;
    private final ExecutionEnvironment environment;

    public ReadKeyCommand(ExecutionEnvironment environment, String... args) throws DatabaseCommandArgumentException {
        if (args == null || args.length != ARGS_LENGTH) {
            throw new DatabaseCommandArgumentException("This command requires exactly " + ARGS_LENGTH + " arguments");
        }
        if (environment == null) {
            throw new DatabaseCommandArgumentException("Execution environment not specified");
        }

        this.environment = environment;
        databaseName = args[DATABASE_NAME_ARG_INDEX];
        tableName = args[TABLE_NAME_ARG_INDEX];
        key = args[KEY_ARG_INDEX];
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
