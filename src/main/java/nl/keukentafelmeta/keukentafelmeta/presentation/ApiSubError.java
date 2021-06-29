package nl.keukentafelmeta.keukentafelmeta.presentation;

import lombok.Data;
import lombok.EqualsAndHashCode;

abstract class ApiSubError {

}

@Data
@EqualsAndHashCode(callSuper = false)
class ApiValidationError extends ApiSubError {
    private String object;
    private String field;
    private String rejectedValue;
    private String message;

    ApiValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }

    public ApiValidationError(String object, String field, String rejectedValue, String message) {
        this.object = object;
        this.field = field;
        this.rejectedValue = rejectedValue;
        this.message = message;
    }
}
