package me.schf.personal.controller.error;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@ExtendWith(MockitoExtension.class)
class GlobalErrorControllerTests {

    GlobalErrorController controller;

    @Mock
    Model model;

    @Mock
    HttpServletRequest request;

    @BeforeEach
    void setUp() {
        controller = new GlobalErrorController(LocalDate.of(2025, 1, 1));
    }

    static Stream<ErrorTestCase> errorCases() {
        return Stream.of(
            new ErrorTestCase(404, "page not found."),
            new ErrorTestCase(500, "internal server error."),
            new ErrorTestCase(418, "unexpected error."),
            new ErrorTestCase(null, "unknown error.")
        );
    }

	@ParameterizedTest
    @MethodSource("errorCases")
    void test_handleError(ErrorTestCase testCase) {
        when(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)).thenReturn(testCase.statusCode());

        String viewName = controller.handleError(request, model);

        assertEquals("error", viewName);
        verify(model).addAttribute("year", 2025);
        verify(model).addAttribute("errorCode", testCase.statusCode() == null 
        		? "???" 
        		: testCase.statusCode());
        verify(model).addAttribute("errorMessage", testCase.expectedMessage());
    }

	private record ErrorTestCase(Integer statusCode, String expectedMessage) {
	}
}
