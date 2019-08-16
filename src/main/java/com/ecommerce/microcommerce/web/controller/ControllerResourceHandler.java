package com.ecommerce.microcommerce.web.controller;

import com.ecommerce.microcommerce.web.exceptions.ControllerTestException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;
import java.nio.file.Files;

public class ControllerResourceHandler {

    private MockMvc mockMvc;

    public ControllerResourceHandler(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    public ResultActions performAndExpectStatusAndContentAndThrowException(RequestBuilder builder, ResultMatcher expectedStatus, ResultMatcher expectedContent) throws ControllerTestException
    {
        try
        {
            return performExpectStatusAndThrowException(builder, expectedStatus).andExpect(expectedContent);
        }
        catch (Exception e)
        {
            throw new ControllerTestException(e);
        }
    }

    public ResultActions performExpectStatusAndThrowException(RequestBuilder builder, ResultMatcher expectedStatus) throws ControllerTestException
    {
        try
        {
            return mockMvc.perform(builder).andExpect(expectedStatus);
        }
        catch(Exception e)
        {
            throw new ControllerTestException(e);
        }
    }

//    public String retrieveRessourceContent(final String filename) throws IOException
//    {
//        final String path = Objects.requireNonNull(this.getClass().getClassLoader().getResource(filename)).getFile();
//        return Files.readString(Paths.get(path));
//    }
}
