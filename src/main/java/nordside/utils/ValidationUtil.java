package nordside.utils;


import nordside.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.slf4j.Logger;

import java.time.LocalTime;
import java.util.stream.Collectors;

public class ValidationUtil {
    private ValidationUtil() {
    }

    public static <T> T checkNotFoundWithId(T object, int id) {
        checkNotFoundWithId(object != null, id);
        return object;
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    public static ResponseEntity<String> getStringResponseEntity(BindingResult result, Logger logger) {
        StringBuilder sb = new StringBuilder();
        result.getFieldErrors()
                .stream()
                .map(e -> sb.append(e.getField())
                        .append(" ")
                        .append(e.getDefaultMessage())
                        .append("<br>"))
                .collect(Collectors.toList());
        logger.info("binding error: {} ", sb.toString());
        return new ResponseEntity<>(sb.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public static boolean timeIsOver(LocalTime localTime) {
        return localTime.isAfter(LocalTime.of(11, 00, 00));
    }

}
