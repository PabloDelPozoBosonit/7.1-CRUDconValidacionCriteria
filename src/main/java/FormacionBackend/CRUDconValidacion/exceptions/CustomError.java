package FormacionBackend.CRUDconValidacion.exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class CustomError {

    String message;
    Date timeStamp;
    int httpCode;

    public CustomError(String message, int httpCode){
        setMessage(message);
        setTimeStamp(new Date());
        setHttpCode(httpCode);
    }
}
