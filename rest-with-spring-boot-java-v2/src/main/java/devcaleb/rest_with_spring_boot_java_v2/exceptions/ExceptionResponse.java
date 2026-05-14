package devcaleb.rest_with_spring_boot_java_v2.exceptions;

import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String details) {
}
