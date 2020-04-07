package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseCommandArgumentException;

public interface DatabaseCommandFactory {
    DatabaseCommand getCommand(ExecutionEnvironment env, String... args) throws DatabaseCommandArgumentException;
}
