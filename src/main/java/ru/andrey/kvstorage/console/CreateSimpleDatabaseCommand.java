package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.logic.SimpleDatabase;

public class CreateSimpleDatabaseCommand implements DatabaseCommand {
    private final String databaseName;
    private final ExecutionEnvironment environment;

    public CreateSimpleDatabaseCommand(String databaseName, ExecutionEnvironment environment) {
        this.databaseName = databaseName;
        this.environment = environment;
    }

    @Override
    public DatabaseCommandResult execute() {
        environment.addDatabase(new SimpleDatabase(databaseName));
        return DatabaseCommandResult.Result
                .success("Database " + databaseName + " added or updated successfully");
    }
}
