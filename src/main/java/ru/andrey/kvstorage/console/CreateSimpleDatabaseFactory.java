package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseCommandArgumentException;

public class CreateSimpleDatabaseFactory implements DatabaseCommandFactory {
    private static final int ARGS_LENGTH = 1;
    private static final int DATABASE_NAME_ARG_INDEX = 0;

    @Override
    public DatabaseCommand getCommand(ExecutionEnvironment env, String... args)
            throws DatabaseCommandArgumentException {
        if (args.length != ARGS_LENGTH) {
            throw new DatabaseCommandArgumentException("This command requires exactly " + ARGS_LENGTH + " arguments");
        }
        return new CreateSimpleDatabaseCommand(args[DATABASE_NAME_ARG_INDEX], env);
    }
}
