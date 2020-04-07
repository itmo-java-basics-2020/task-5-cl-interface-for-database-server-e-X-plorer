package ru.andrey.kvstorage.console;

import java.util.Optional;

public interface DatabaseCommandResult {

    Optional<String> getResult();

    DatabaseCommandStatus getStatus();

    boolean isSuccess();

    String getErrorMessage();

    enum DatabaseCommandStatus {
        SUCCESS, FAILED
    }

    class Result implements DatabaseCommandResult {

        private final String result;

        private final boolean isSuccess;

        private Result(String message, boolean isSuccess) {
            this.isSuccess = isSuccess;
            result = message;
        }

        public static DatabaseCommandResult success(String result) {
            return new Result(result, true);
        }

        public static DatabaseCommandResult error(String message) {
            return new Result(message, false);
        }

        @Override
        public Optional<String> getResult() {
            return isSuccess() ? Optional.of(result) : Optional.empty();
        }

        @Override
        public DatabaseCommandStatus getStatus() {
            return isSuccess() ? DatabaseCommandStatus.SUCCESS : DatabaseCommandStatus.FAILED;
        }

        @Override
        public boolean isSuccess() {
            return isSuccess;
        }

        @Override
        public String getErrorMessage() {
            return isSuccess() ? null : result;
        }
    }
}