package com.rajendarreddyj.spring.test;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import com.rajendarreddyj.spring.persistence.model.Foo;

public final class JacksonMarshaller implements IMarshaller {
    private final Logger logger = LoggerFactory.getLogger(JacksonMarshaller.class);

    private final ObjectMapper objectMapper;

    public JacksonMarshaller() {
        super();

        this.objectMapper = new ObjectMapper();
    }

    // API

    @Override
    public final <T> String encode(final T resource) {
        Preconditions.checkNotNull(resource);
        String entityAsJSON = null;
        try {
            entityAsJSON = this.objectMapper.writeValueAsString(resource);
        } catch (final JsonParseException parseEx) {
            this.logger.error("", parseEx);
        } catch (final JsonMappingException mappingEx) {
            this.logger.error("", mappingEx);
        } catch (final IOException ioEx) {
            this.logger.error("", ioEx);
        }

        return entityAsJSON;
    }

    @Override
    public final <T> T decode(final String resourceAsString, final Class<T> clazz) {
        Preconditions.checkNotNull(resourceAsString);

        T entity = null;
        try {
            entity = this.objectMapper.readValue(resourceAsString, clazz);
        } catch (final JsonParseException parseEx) {
            this.logger.error("", parseEx);
        } catch (final JsonMappingException mappingEx) {
            this.logger.error("", mappingEx);
        } catch (final IOException ioEx) {
            this.logger.error("", ioEx);
        }

        return entity;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final <T> List<T> decodeList(final String resourcesAsString, final Class<T> clazz) {
        Preconditions.checkNotNull(resourcesAsString);

        List<T> entities = null;
        try {
            if (clazz.equals(Foo.class)) {
                entities = this.objectMapper.readValue(resourcesAsString, new TypeReference<List<Foo>>() {
                    // ...
                });
            } else {
                entities = this.objectMapper.readValue(resourcesAsString, List.class);
            }
        } catch (final JsonParseException parseEx) {
            this.logger.error("", parseEx);
        } catch (final JsonMappingException mappingEx) {
            this.logger.error("", mappingEx);
        } catch (final IOException ioEx) {
            this.logger.error("", ioEx);
        }

        return entities;
    }

    @Override
    public final String getMime() {
        return MediaType.APPLICATION_JSON.toString();
    }

}
