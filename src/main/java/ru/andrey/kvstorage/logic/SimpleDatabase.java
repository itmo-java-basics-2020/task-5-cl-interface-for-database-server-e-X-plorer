package ru.andrey.kvstorage.logic;

import ru.andrey.kvstorage.exception.DatabaseException;

import java.util.HashMap;

public class SimpleDatabase implements Database {

    private String name;

    private HashMap<String, HashMap<String, String>> tables;

    public SimpleDatabase(String name) {
        this.name = name;
        tables = new HashMap<String, HashMap<String, String>>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void createTableIfNotExists(String tableName) throws DatabaseException {
        if (tables.get(tableName) != null) {
            throw new DatabaseException("Table already exists");
        }
        tables.put(tableName, new HashMap<String, String>());
    }

    @Override
    public void createTableIfNotExists(String tableName, int segmentSizeInBytes) throws DatabaseException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void write(String tableName, String objectKey, String objectValue) throws DatabaseException {
        var table = tables.get(tableName);
        if (table == null) {
            throw new DatabaseException("Table does not exist");
        }
        table.put(objectKey, objectValue);
    }

    @Override
    public String read(String tableName, String objectKey) throws DatabaseException {
        var table = tables.get(tableName);
        if (table == null) {
            throw new DatabaseException("Table does not exist");
        }
        return tables.get(tableName).get(objectKey);
    }
}
