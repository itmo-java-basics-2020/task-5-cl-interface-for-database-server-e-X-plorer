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

        private final String resultMessage;

        private final DatabaseCommandStatus status;

        private Result(String message, DatabaseCommandStatus status) {
            this.status = status;
            resultMessage = message;
        }

        public static DatabaseCommandResult success(String result) {
            return new Result(result, DatabaseCommandStatus.SUCCESS);
        }

        public static DatabaseCommandResult error(String message) {
            return new Result(message, DatabaseCommandStatus.FAILED);
        }

        @Override
        public Optional<String> getResult() {
            return isSuccess() ? Optional.of(resultMessage) : Optional.empty();
        }

        @Override
        public DatabaseCommandStatus getStatus() {
            return status;
        }

        @Override
        public boolean isSuccess() {
            return status == DatabaseCommandStatus.SUCCESS;
        }

        @Override
        public String getErrorMessage() {
            return isSuccess() ? null : resultMessage;
        }
    }
}