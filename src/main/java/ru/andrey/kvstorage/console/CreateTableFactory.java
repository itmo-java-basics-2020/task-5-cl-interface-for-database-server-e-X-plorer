package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseCommandArgumentException;

public class CreateTableFactory implements DatabaseCommandFactory {
    private static final int ARGS_LENGTH = 2;
    private static final int DATABASE_NAME_ARG_INDEX = 0;
    private static final int TABLE_NAME_ARG_INDEX = 1;

    @Override
    public DatabaseCommand getCommand(ExecutionEnvironment env, String... args)
            throws DatabaseCommandArgumentException {
        if (args.length != ARGS_LENGTH) {
            throw new DatabaseCommandArgumentException("This command requires exactly " + ARGS_LENGTH + " arguments");
        }
        return new CreateTableCommand(args[DATABASE_NAME_ARG_INDEX], args[TABLE_NAME_ARG_INDEX], env);
    }
}
