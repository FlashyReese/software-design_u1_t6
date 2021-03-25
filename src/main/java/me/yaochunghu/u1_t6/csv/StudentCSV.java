/*
 * Copyright © 2021 Yao Chung Hu
 *
 * This file is part of Software Design U1 T6.
 *
 * Licensed under the MIT license. For more information,
 * see the LICENSE file.
 */

package me.yaochunghu.u1_t6.csv;

import me.yaochunghu.u1_t6.model.Student;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentCSV extends AbstractCSV<Student> {
    public StudentCSV() {
        super(new File("students.csv"));
    }

    @Override
    public List<Student> read() {
        List<Student> students = new ArrayList<>();
        if (!this.getFile().exists()) {
            return students;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(this.getFile()))) {
            br.lines().forEach(line -> {
                String[] data = line.split(",");
                students.add(new Student(data));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }
}
