package com.nisum.evaluacionJava.config.modelMapper;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ModelMapperConfigTest {
    @Test
    public void testModelMapperCreation() {
        ModelMapperConfig config = new ModelMapperConfig();

        ModelMapper modelMapper = config.modelMapper();

        assertNotNull(modelMapper);
    }
}