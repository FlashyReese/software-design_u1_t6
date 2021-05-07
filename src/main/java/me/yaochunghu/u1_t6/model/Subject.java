/*
 * Copyright © 2021 Yao Chung Hu
 *
 * This file is part of Software Design U1 T6.
 *
 * Licensed under the MIT license. For more information,
 * see the LICENSE file.
 */

package me.yaochunghu.u1_t6.model;

public class Subject {
    private final int id;
    private final String name;
    private Float grade;

    public Subject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Subject(int id, String name, float grade) {
        this(id, name);
        this.grade = grade;
    }

    public Subject(String[] data) {
        this(Integer.parseInt(data[0]), data[1], Float.parseFloat(data[2]));
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s", this.id, this.name, this.grade);
    }
}
