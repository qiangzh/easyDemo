package com.mock.mockito;

import org.mockito.Mockito;

public class MockitoTest {
    public static void main(String[] args) {

        // interface
        Hello hello = Mockito.mock(Hello.class);
        Mockito.when(hello.sayHello("zhqiang")).thenReturn("Hello zhqiang~~");

        System.out.println(hello.sayHello("zhqiang"));
        //Mockito.verify(hello).sayHello("zhqiang1");

        //¿‡
        Bird bird = Mockito.mock(Bird.class);
        Mockito.when(bird.sing()).thenReturn("COO~COO~");
        System.out.println(bird.sing());

    }

    interface Hello {
        public String sayHello(String name);
    }

}

class Bird {

    public String sing() {
        return "Coo~ Coo~";
    }

}
