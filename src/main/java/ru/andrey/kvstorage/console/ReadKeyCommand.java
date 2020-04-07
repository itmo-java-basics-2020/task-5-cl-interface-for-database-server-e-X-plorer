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

    private ReadKeyCommand(String databaseName, String tableName, String key,
                           ExecutionEnvironment environment) {
        this.databaseName = databaseName;
        this.tableName = tableName;
        this.key = key;
        this.environment = environment;
    }

    public static DatabaseCommand GetCommand(ExecutionEnvironment env, String... args)
            throws DatabaseCommandArgumentException {
        if (args.length != ARGS_LENGTH) {
            throw new DatabaseCommandArgumentException("This command requires exactly " + ARGS_LENGTH + " arguments");
        }
        return new ReadKeyCommand(args[DATABASE_NAME_ARG_INDEX], args[TABLE_NAME_ARG_INDEX], args[KEY_ARG_INDEX], env);
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
