package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseCommandArgumentException;

public class UpdateKeyFactory implements DatabaseCommandFactory {
    private static final int ARGS_LENGTH = 4;
    private static final int DATABASE_NAME_ARG_INDEX = 0;
    private static final int TABLE_NAME_ARG_INDEX = 1;
    private static final int KEY_ARG_INDEX = 2;
    private static final int VALUE_ARG_INDEX = 3;

    @Override
    public DatabaseCommand getCommand(ExecutionEnvironment env, String... args)
            throws DatabaseCommandArgumentException {
        if (args == null || args.length != ARGS_LENGTH) {
            throw new DatabaseCommandArgumentException("This command requires exactly " + ARGS_LENGTH + " arguments");
        }
        if (env == null) {
            throw new DatabaseCommandArgumentException("Execution environment not specified");
        }
        return new UpdateKeyCommand(args[DATABASE_NAME_ARG_INDEX], args[TABLE_NAME_ARG_INDEX], args[KEY_ARG_INDEX],
                args[VALUE_ARG_INDEX], env);
    }
}
