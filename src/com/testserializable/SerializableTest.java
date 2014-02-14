package com.testserializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URISyntaxException;

public class SerializableTest {

    public static void main(String[] args) throws URISyntaxException, IOException, ClassNotFoundException {
        String path = SerializableTest.class.getClassLoader().getResource("").toURI().getPath();
        System.out.println(path);
        File file = new File(path + "BlueBird.txt");

        // 序列化
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
        Bird bird1 = new Bird();
        bird1.setName("BlueBird");
        outputStream.writeObject(bird1);
        outputStream.close();

        // 反列化
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
        Bird bird2 = (Bird) inputStream.readObject();
        System.out.println(bird2.getName());

    }
}

class Bird implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
