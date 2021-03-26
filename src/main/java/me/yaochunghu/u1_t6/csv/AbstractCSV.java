/*
 * Copyright © 2021 Yao Chung Hu
 *
 * This file is part of Software Design U1 T6.
 *
 * Licensed under the MIT license. For more information,
 * see the LICENSE file.
 */

package me.yaochunghu.u1_t6.csv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public abstract class AbstractCSV<T> {
    private final File file;

    public AbstractCSV(File file) {
        this.file = file;
    }

    public abstract List<T> read();

    public void save(T object, boolean append) {
        try (FileWriter writer = new FileWriter(this.file, append)) {
            String line = object.toString() + "\r\n";
            writer.write(line);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getFile() {
        return file;
    }
}
