package com.demo.employee_management.controller;

import com.demo.employee_management.entity.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.assertThat;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void handleResourceNotFound_shouldReturnErrorViewWithNotFoundStatus() {
        String errorMsg = "Resource not found!";
        ResourceNotFoundException ex = new ResourceNotFoundException(errorMsg);

        ModelAndView mav = handler.handleResourceNotFound(ex);

        assertThat(mav.getViewName()).isEqualTo("error");
        assertThat(mav.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(mav.getModel().get("message")).isEqualTo(errorMsg);
    }

    @Test
    void handleGlobal_shouldReturnErrorViewWithInternalServerErrorStatus() {
        Exception ex = new RuntimeException("Something went wrong");

        ModelAndView mav = handler.handleGlobal(ex);

        assertThat(mav.getViewName()).isEqualTo("error");
        assertThat(mav.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(mav.getModel().get("message")).isEqualTo("An internal error occurred. Please try again later.");
    }
}
