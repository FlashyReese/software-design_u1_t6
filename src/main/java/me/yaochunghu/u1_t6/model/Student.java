/*
 * Copyright © 2021 Yao Chung Hu
 *
 * This file is part of Software Design U1 T6.
 *
 * Licensed under the MIT license. For more information,
 * see the LICENSE file.
 */

package me.yaochunghu.u1_t6.model;

public class Student {
    private final int id;
    private final String firstLastName;
    private final String secondLastName;
    private final String names;

    public Student(int id, String firstLastName, String secondLastName, String names) {
        this.id = id;
        this.firstLastName = firstLastName;
        this.secondLastName = secondLastName;
        this.names = names;
    }

    public Student(String[] data) {
        this(Integer.parseInt(data[0]), data[1], data[2], data[3]);
    }

    public int getId() {
        return id;
    }

    public String getFirstLastName() {
        return firstLastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public String getNames() {
        return names;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s", this.id, this.firstLastName, this.secondLastName, this.names);
    }
}
