/*
 * Copyright © 2021 Yao Chung Hu
 *
 * This file is part of Software Design U1 T6.
 *
 * Licensed under the MIT license. For more information,
 * see the LICENSE file.
 */

package me.yaochunghu.u1_t6.csv;

import com.qoppa.pdfWriter.PDFDocument;
import com.qoppa.pdfWriter.PDFGraphics;
import com.qoppa.pdfWriter.PDFPage;
import me.yaochunghu.u1_t6.model.Subject;

import java.awt.*;
import java.awt.print.PageFormat;
import java.io.*;
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

    public void save(List<Subject> subjects) {
        try (FileWriter writer = new FileWriter(this.getFile())) {
            for (Subject subject : subjects) {
                String line = subject.toString() + "\r\n";
                writer.write(line);
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generatePDF() {
        int dist = 20;
        PDFDocument pdfDoc = new PDFDocument();
        PDFPage newPage = pdfDoc.createPage(new PageFormat());

        // Draw to the page
        Graphics2D g2d = newPage.createGraphics();
        g2d.setFont(PDFGraphics.HELVETICA.deriveFont(13f));

        try {
            g2d.drawString("Matricula", 100, 100);
            g2d.drawString("Materia", 200, 100);
            g2d.drawString("Calificacion", 380, 100);

            for (Subject subject : this.read()) {
                if (subject.getGrade() != -1) {
                    g2d.drawString(String.valueOf(subject.getId()), 100, 100 + dist);
                    g2d.drawString(subject.getName(), 200, 100 + dist);
                    g2d.drawString(String.valueOf(subject.getGrade()), 380, 100 + dist);
                    dist += 20;
                }
            }

            // Add the page to the document and save it
            pdfDoc.addPage(newPage);
            pdfDoc.saveDocument("Student-Grades.pdf");
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}