package com.script.ymy.vo.resp;


public class Result<T> {
    public static final int SUCCESS_CODE = 0;
    public static final int FAILED_CODE = -1;
    public static final Result<Object> SUCCESS = builder().code(0).build();
    private int code;
    private T res;
    private String error;
    private String trace;

    public boolean ok() {
        return this.code == 0;
    }

    public boolean notOk() {
        return !this.ok();
    }

    public static <T> Result.ResultBuilder<T> builder() {
        return new Result.ResultBuilder();
    }

    public int getCode() {
        return this.code;
    }

    public T getRes() {
        return this.res;
    }

    public String getError() {
        return this.error;
    }

    public String getTrace() {
        return this.trace;
    }

    public void setCode(final int code) {
        this.code = code;
    }

    public void setRes(final T res) {
        this.res = res;
    }

    public void setError(final String error) {
        this.error = error;
    }

    public void setTrace(final String trace) {
        this.trace = trace;
    }

    public Result() {
    }

    public Result(final int code, final T res, final String error, final String trace) {
        this.code = code;
        this.res = res;
        this.error = error;
        this.trace = trace;
    }

    public static class ResultBuilder<T> {
        private int code;
        private T res;
        private String error;
        private String trace;

        ResultBuilder() {
        }

        public Result.ResultBuilder<T> code(final int code) {
            this.code = code;
            return this;
        }

        public Result.ResultBuilder<T> res(final T res) {
            this.res = res;
            return this;
        }

        public Result.ResultBuilder<T> error(final String error) {
            this.error = error;
            return this;
        }

        public Result.ResultBuilder<T> trace(final String trace) {
            this.trace = trace;
            return this;
        }

        public Result<T> build() {
            return new Result(this.code, this.res, this.error, this.trace);
        }

        public String toString() {
            return "Result.ResultBuilder(code=" + this.code + ", res=" + this.res + ", error=" + this.error + ", trace=" + this.trace + ")";
        }
    }
}
