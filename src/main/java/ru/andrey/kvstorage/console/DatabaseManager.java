package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.logic.Database;

import java.util.*;

public class DatabaseManager implements ExecutionEnvironment {

    private HashMap<String, Database> databases;

    @Override
    public Optional<Database> getDatabase(String name) {
        return Optional.ofNullable(databases.get(name));
    }

    @Override
    public void addDatabase(Database db) {
        databases.put(db.getName(), db);
    }
}
