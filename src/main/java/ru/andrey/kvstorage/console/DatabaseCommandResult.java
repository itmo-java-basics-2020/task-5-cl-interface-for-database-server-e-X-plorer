package ru.andrey.kvstorage.console;

import java.util.Optional;

public interface DatabaseCommandResult {

    static DatabaseCommandResult success(String resultMessage) {
        return new Result(resultMessage, Result.ERROR_MESSAGE_ON_SUCCESS, DatabaseCommandStatus.SUCCESS);
    }

    static DatabaseCommandResult error(String errorMessage) {
        return new Result(null, errorMessage, DatabaseCommandStatus.FAILED);
    }

    Optional<String> getResult();

    DatabaseCommandStatus getStatus();

    boolean isSuccess();

    String getErrorMessage();

    enum DatabaseCommandStatus {
        SUCCESS, FAILED
    }

    class Result implements DatabaseCommandResult {

        private static final String ERROR_MESSAGE_ON_SUCCESS = "No error";

        private final String resultMessage;

        private final String errorMessage;

        private final DatabaseCommandStatus status;

        private Result(String resultMessage, String errorMessage, DatabaseCommandStatus status) {
            this.resultMessage = resultMessage;
            this.errorMessage = errorMessage;
            this.status = status;
        }

        @Override
        public Optional<String> getResult() {
            return Optional.ofNullable(resultMessage);
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