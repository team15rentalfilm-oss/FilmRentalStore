package com.iem.FilmRentalStore.unit.util;

import com.iem.FilmRentalStore.exception.ErrorResponse;
import com.iem.FilmRentalStore.exception.GlobalExceptionHandler;
import com.iem.FilmRentalStore.exception.ResourceNotFoundException;
import com.iem.FilmRentalStore.util.DateUtil;
import com.iem.FilmRentalStore.util.FeeCalculator;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class UtilityAndExceptionUnitTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void dateUtilFormatsAndParsesDates() {
        LocalDate date = LocalDate.of(2026, 3, 30);

        assertThat(DateUtil.formatDate(date)).isEqualTo("2026-03-30");
        assertThat(DateUtil.formatDate(date, "dd/MM/yyyy")).isEqualTo("30/03/2026");
        assertThat(DateUtil.parseDate("2026-03-30")).isEqualTo(date);
        assertThat(DateUtil.parseDate("30/03/2026", "dd/MM/yyyy")).isEqualTo(date);
        assertThat(DateUtil.formatDate(null)).isNull();
        assertThat(DateUtil.parseDate("")).isNull();
    }

    @Test
    void feeCalculatorHandlesNullAndNegativeRanges() {
        assertThat(FeeCalculator.calculateRentalFee(LocalDate.of(2026, 3, 1), LocalDate.of(2026, 3, 4)))
                .isEqualByComparingTo(BigDecimal.valueOf(7.5));
        assertThat(FeeCalculator.calculateRentalFee(null, LocalDate.now()))
                .isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(FeeCalculator.calculateRentalFee(LocalDate.of(2026, 3, 4), LocalDate.of(2026, 3, 1)))
                .isEqualByComparingTo(BigDecimal.ZERO);
    }

    @Test
    void globalExceptionHandlerMapsKnownExceptions() {
        ErrorResponse resourceError = handler.handleResourceNotFound(new ResourceNotFoundException("Film", "id", 1))
                .getBody();
        ErrorResponse entityError = handler.handleEntityNotFound(new EntityNotFoundException("Missing entity"))
                .getBody();
        ErrorResponse argumentError = handler.handleIllegalArgument(new IllegalArgumentException("Bad input"))
                .getBody();
        ErrorResponse stateError = handler.handleIllegalState(new IllegalStateException("Conflict"))
                .getBody();
        ErrorResponse globalError = handler.handleGlobalException(new RuntimeException("Boom"))
                .getBody();

        assertThat(resourceError.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(resourceError.getMessage()).contains("Film");
        assertThat(entityError.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(argumentError.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(stateError.getStatus()).isEqualTo(HttpStatus.CONFLICT.value());
        assertThat(globalError.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @Test
    void globalExceptionHandlerReturnsFieldValidationErrors() throws Exception {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(new Object(), "request");
        bindingResult.addError(new FieldError("request", "name", "must not be blank"));

        Method method = UtilityAndExceptionUnitTest.class.getDeclaredMethod("validationProbe", String.class);
        MethodArgumentNotValidException exception =
                new MethodArgumentNotValidException(new MethodParameter(method, 0), bindingResult);

        ErrorResponse response = handler.handleValidationErrors(exception).getBody();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getMessage()).isEqualTo("Validation failed");
        assertThat(response.getErrors()).containsEntry("name", "must not be blank");
    }

    @SuppressWarnings("unused")
    private void validationProbe(String value) {
    }
}
