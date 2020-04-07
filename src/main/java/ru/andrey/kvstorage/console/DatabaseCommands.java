package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseCommandArgumentException;

public enum DatabaseCommands {

    CREATE_DATABASE {
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args)
                throws DatabaseCommandArgumentException {
            return new CreateSimpleDatabaseFactory().getCommand(env, args);
        }
    },
    CREATE_TABLE {
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args)
                throws DatabaseCommandArgumentException {
            return new CreateTableFactory().getCommand(env, args);
        }
    },
    READ_KEY {
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args)
                throws DatabaseCommandArgumentException {
            return new ReadKeyFactory().getCommand(env, args);
        }
    },
    UPDATE_KEY {
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args)
                throws DatabaseCommandArgumentException {
            return new UpdateKeyFactory().getCommand(env, args);
        }
    };

    public abstract DatabaseCommand getCommand(ExecutionEnvironment env, String... args)
            throws DatabaseCommandArgumentException;
}
