package com.rajendarreddyj.spring;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.StreamSerializer;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class ObjectStreamSerializer implements StreamSerializer<Object> {

    @Override
    public int getTypeId() {
        return 2;
    }

    @Override
    public void write(final ObjectDataOutput objectDataOutput, final Object object) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream((OutputStream) objectDataOutput);
        out.writeObject(object);
        out.flush();
    }

    @Override
    public Object read(final ObjectDataInput objectDataInput) throws IOException {
        ObjectInputStream in = new ObjectInputStream((InputStream) objectDataInput);
        try {
            return in.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void destroy() {
    }

}
