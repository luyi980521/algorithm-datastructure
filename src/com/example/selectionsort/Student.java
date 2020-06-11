package com.example.selectionsort;

/**
 * Created by Lyman.
 * 描述：学生类
 * 设定以学生的分数进行比较
 */
public class Student implements Comparable<Student> {

    private String name;
    private Integer score;

    public Student(String name, Integer score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public int compareTo(Student that) {
        if (this.score < that.score) {
            return 1;
        } else if (this.score > that.score) {
            return -1;
        } else {
            return this.name.compareTo(that.name);
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
