package dallaglio.juan.globallogic.exception;

import androidx.annotation.Nullable;

public class HandledException extends Exception {
    String message;

    public HandledException(String message) {
        this.message = message;
    }

    @Nullable
    @Override
    public String getMessage() {
        return message;
    }
}
