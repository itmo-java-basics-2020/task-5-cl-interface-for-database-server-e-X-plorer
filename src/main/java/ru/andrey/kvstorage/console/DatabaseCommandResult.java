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

        private final String errorMessage;

        private final DatabaseCommandStatus status;

        private Result(String message, DatabaseCommandStatus status) {
            this.status = status;
            switch (status) {
                case SUCCESS:
                    result = message;
                    errorMessage = "No error";
                    break;
                case FAILED:
                    result = null;
                    errorMessage = message;
                    break;
                default:
                    throw new IllegalStateException("Unsupported database command status " + status);
            }
        }

        public static DatabaseCommandResult success(String result) {
            return new Result(result, DatabaseCommandStatus.SUCCESS);
        }

        public static DatabaseCommandResult error(String message) {
            return new Result(message, DatabaseCommandStatus.FAILED);
        }

        @Override
        public Optional<String> getResult() {
            return Optional.ofNullable(result);
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
            return errorMessage;
        }
    }
}