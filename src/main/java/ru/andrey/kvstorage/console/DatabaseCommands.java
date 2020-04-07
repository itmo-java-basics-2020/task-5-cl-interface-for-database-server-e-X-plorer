package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseCommandArgumentException;

public enum DatabaseCommands {

    CREATE_DATABASE {
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args)
                throws DatabaseCommandArgumentException {
            return CreateSimpleDatabaseCommand.GetCommand(env, args);
        }
    },
    CREATE_TABLE {
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args)
                throws DatabaseCommandArgumentException {
            return CreateTableCommand.GetCommand(env, args);
        }
    },
    READ_KEY {
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args)
                throws DatabaseCommandArgumentException {
            return ReadKeyCommand.GetCommand(env, args);
        }
    },
    UPDATE_KEY {
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args)
                throws DatabaseCommandArgumentException {
            return UpdateKeyCommand.GetCommand(env, args);
        }
    };

    public abstract DatabaseCommand getCommand(ExecutionEnvironment env, String... args)
            throws DatabaseCommandArgumentException;
}
