package com.company.coursemanagement.application.dto.response;

import java.time.LocalDateTime;

public record ErrorResponse(

        String code,
        String message,
        LocalDateTime timestamp

) {}
