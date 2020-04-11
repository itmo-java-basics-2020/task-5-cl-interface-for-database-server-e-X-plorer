package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseCommandArgumentException;

public enum DatabaseCommands {

    CREATE_DATABASE {
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args)
                throws DatabaseCommandArgumentException {
            return new CreateSimpleDatabaseCommand(env, args);
        }
    },
    CREATE_TABLE {
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args)
                throws DatabaseCommandArgumentException {
            return new CreateTableCommand(env, args);
        }
    },
    READ_KEY {
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args)
                throws DatabaseCommandArgumentException {
            return new ReadKeyCommand(env, args);
        }
    },
    UPDATE_KEY {
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args)
                throws DatabaseCommandArgumentException {
            return new UpdateKeyCommand(env, args);
        }
    };

    public abstract DatabaseCommand getCommand(ExecutionEnvironment env, String... args)
            throws DatabaseCommandArgumentException;
}