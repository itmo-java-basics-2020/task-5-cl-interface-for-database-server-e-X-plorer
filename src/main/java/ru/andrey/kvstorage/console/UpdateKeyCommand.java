package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseCommandArgumentException;
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

    private UpdateKeyCommand(String databaseName, String tableName, String key, String value,
                             ExecutionEnvironment environment) {
        this.databaseName = databaseName;
        this.tableName = tableName;
        this.key = key;
        this.value = value;
        this.environment = environment;
    }

    public static DatabaseCommand GetCommand(ExecutionEnvironment env, String... args)
            throws DatabaseCommandArgumentException {
        if (args.length != ARGS_LENGTH) {
            throw new DatabaseCommandArgumentException("This command requires exactly " + ARGS_LENGTH + " arguments");
        }
        return new UpdateKeyCommand(args[DATABASE_NAME_ARG_INDEX], args[TABLE_NAME_ARG_INDEX], args[KEY_ARG_INDEX],
                args[VALUE_ARG_INDEX], env);
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
