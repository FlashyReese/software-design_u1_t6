/*
 * Copyright © 2021 Yao Chung Hu
 *
 * This file is part of Software Design U1 T6.
 *
 * Licensed under the MIT license. For more information,
 * see the LICENSE file.
 */

package me.yaochunghu.u1_t6.csv;

import me.yaochunghu.u1_t6.model.Subject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SubjectCSV extends AbstractCSV<Subject> {
    public SubjectCSV() {
        super(new File("subjects-grades.csv"));
    }

    @Override
    public List<Subject> read() {
        List<Subject> subjects = new ArrayList<>();
        if (!this.getFile().exists()) {
            return subjects;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(this.getFile()))) {
            br.lines().forEach(line -> {
                String[] data = line.split(",");
                subjects.add(new Subject(data));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return subjects;
    }
}
