package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseCommandArgumentException;

public enum DatabaseCommands {

    CREATE_DATABASE(new CreateSimpleDatabaseFactory()),
    CREATE_TABLE(new CreateTableFactory()),
    READ_KEY(new ReadKeyFactory()),
    UPDATE_KEY(new UpdateKeyFactory());

    private final DatabaseCommandFactory factory;

    DatabaseCommands(DatabaseCommandFactory factory) {
        this.factory = factory;
    }

    public DatabaseCommand getCommand(ExecutionEnvironment env, String... args)
            throws DatabaseCommandArgumentException {
        return factory.getCommand(env, args);
    }
}
