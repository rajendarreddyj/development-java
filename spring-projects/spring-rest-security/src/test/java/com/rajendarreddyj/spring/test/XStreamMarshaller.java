package com.rajendarreddyj.spring.test;

import java.util.List;

import org.springframework.http.MediaType;

import com.google.common.base.Preconditions;
import com.rajendarreddyj.spring.persistence.model.Foo;
import com.thoughtworks.xstream.XStream;

public final class XStreamMarshaller implements IMarshaller {

    private XStream xstream;

    public XStreamMarshaller() {
        super();

        this.xstream = new XStream();
        this.xstream.autodetectAnnotations(true);
        this.xstream.processAnnotations(Foo.class);
    }

    // API

    @Override
    public final <T> String encode(final T resource) {
        Preconditions.checkNotNull(resource);
        return this.xstream.toXML(resource);
    }

    @SuppressWarnings("unchecked")
    @Override
    public final <T> T decode(final String resourceAsString, final Class<T> clazz) {
        Preconditions.checkNotNull(resourceAsString);
        return (T) this.xstream.fromXML(resourceAsString);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> decodeList(final String resourcesAsString, final Class<T> clazz) {
        return this.decode(resourcesAsString, List.class);
    }

    @Override
    public final String getMime() {
        return MediaType.APPLICATION_XML.toString();
    }

}
