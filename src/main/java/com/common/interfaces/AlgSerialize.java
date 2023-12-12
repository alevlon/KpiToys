package com.common.interfaces;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public interface AlgSerialize {
    default Object deserialize(Object object) {
        try {
            byte [] data = Base64.getDecoder().decode((String) object);
            ObjectInputStream ois = null;
            ois = new ObjectInputStream(
                    new ByteArrayInputStream(data));
            Object o  = ois.readObject();
            ois.close();
            return o;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    default String serialize(Object o) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream( baos );
            oos.writeObject(o);
            oos.close();
            String res = Base64.getEncoder().encodeToString(baos.toByteArray());
            return res;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
