package ru.andrey.kvstorage.console;

public enum DatabaseCommands {

    CREATE_DATABASE {
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args) {
            if (args.length != 1) {
                throw new IllegalArgumentException("This command requires exactly 1 argument");
            }
            return new CreateSimpleDatabaseCommand(args[0], env);
        }
    },
    CREATE_TABLE {
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args) {
            if (args.length != 2) {
                throw new IllegalArgumentException("This command requires exactly 2 arguments");
            }
            return new CreateTableCommand(args[0], args[1], env);
        }
    },
    READ_KEY {
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args) {
            if (args.length != 3) {
                throw new IllegalArgumentException("This command requires exactly 3 arguments");
            }
            return new ReadKeyCommand(args[0], args[1], args[2], env);
        }
    },
    UPDATE_KEY {
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args) {
            if (args.length != 4) {
                throw new IllegalArgumentException("This command requires exactly 4 arguments");
            }
            return new UpdateKeyCommand(args[0], args[1], args[2], args[3], env);
        }
    };

    public abstract DatabaseCommand getCommand(ExecutionEnvironment env, String... args);
}
