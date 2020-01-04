package com.xieyy.basic;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author xieyyt
 * @date 2019/12/24 19:59
 */
@Data
public class Student implements Serializable {
    private String name;
    private String address;
    private Integer sex;
    private Integer age;

    public static String getA(String name, Integer age) {
        return name + "--" + age;
    }


    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        Student s1 = new Student();
        s1.setName("zhangsan");
        s1.setAddress("china beijing changping beiqinglu");
        s1.setSex(1);
        s1.setAge(10);

        Student s2 = new Student();
        s2.setName("lisi");
        s2.setAddress("china beijing changping beiqinglu");
        s2.setSex(1);
        s2.setAge(20);

        Student s3 = new Student();
        s3.setName("wangwu");
        s3.setAddress("china beijing changping beiqinglu");
        s3.setSex(2);
        s3.setAge(30);

        Student s4 = new Student();
        s4.setName("zhaoliu");
        s4.setAddress("china beijing changping beiqinglu");
        s4.setSex(2);
        s4.setAge(40);

        Student s5 = new Student();
        s5.setName("tianqi");
        s5.setAddress("china beijing changping beiqinglu");
        s5.setSex(2);
        s5.setAge(50);

        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        list.add(s5);

//        list.forEach(student -> {
//            if (student.getSex().equals(1)) {
//                System.out.println(student);
//            }
//        });

        List<Boolean> collect = list.stream().filter(student -> student.getSex().equals(1)).map(student -> student.getAge() < 20).collect(toList());
        System.out.println(collect);
//
//        List<Student> list2 = new ArrayList<>();
//        list2.add(s3);
//        list2.add(s4);
//        list2.add(s5);

//        String str = "1.2.3.4";
//
//        System.out.println(Stream.of(str).collect(toList()));

    }
}
