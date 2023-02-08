package com.rukevwe.league.service;

import com.rukevwe.league.error.InvalidFileException;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MatrixServiceTest {

    @Autowired
    private MatrixService matrixService;

    private MultipartFile validMultipartFile;

    private MultipartFile emptyMultipartFile;


    @BeforeEach
    public void init() throws IOException {

        InputStream validInputStream = MatrixServiceTest.class.getResourceAsStream("/matrix.csv");
        validMultipartFile = new MockMultipartFile("matrix.csv", validInputStream);

        InputStream emptyInputStream = MatrixServiceTest.class.getResourceAsStream("/empty_matrix.csv");
        emptyMultipartFile = new MockMultipartFile("empty_matrix.csv", emptyInputStream);
    }

    @Test
    void echo_whenFileIsValid_returnsExpectedOutput() {
        // Given
        final String expected = "1,2,3\n" + "4,5,6\n" + "7,8,9";

        // When
        String actual = matrixService.echo(validMultipartFile);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void echo_whenFileIsEmpty_throwsInvalidInputException() {
        // Given
        final String expected = "1,2,3\n" + "4,5,6\n" + "7,8,9";

        // When
        ThrowableAssert.ThrowingCallable actual = () -> matrixService.echo(emptyMultipartFile);

        // Then
        assertThatThrownBy(actual)
                .isExactlyInstanceOf(InvalidFileException.class)
                .hasMessageContaining("Csv file can not be empty.");
    }

    @Test
    void invert_whenFileIsValid_returnsExpectedOutput() {
        // Given
        final String expected = "1,4,7\n" + "2,5,8\n" + "3,6,9";

        // When
        String actual = matrixService.invert(validMultipartFile);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void flatten_whenFileIsValid_returnsExpectedOutput() {
        // Given
        final String expected = "1,2,3,4,5,6,7,8,9";

        // When
        String actual = matrixService.flatten(validMultipartFile);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void sum_whenFileIsValid_returnsExpectedOutput() {
        // Given
        final String expected = "45";

        // When
        String actual = matrixService.sum(validMultipartFile);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void multiply_whenFileIsValid_returnsExpectedOutput() {
        // Given
        final String expected = "362880";

        // When
        String actual = matrixService.multiply(validMultipartFile);

        // Then
        assertEquals(expected, actual);
    }
}
