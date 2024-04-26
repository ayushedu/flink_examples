package org.apache.flink.datastreamapi;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.streaming.api.*;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class Example {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<Person> flintStones = env.fromElements(
                new Person("Fred", 35),
                new Person("Wilma", 35),
                new Person("Pebbles", 2)
        );

        SingleOutputStreamOperator<Person> adults = flintStones.filter((FilterFunction<Person>) person -> person.age > 18);

        adults.print();
        env.execute();
    }

    public static class Person {
        public String name;
        public Integer age;

        public Person() {}

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String toString() {
            return this.name + ", age " + this.age;
        }
    }
}
