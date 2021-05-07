package me.yaochunghu.u1_t6.ui.component;

import me.yaochunghu.u1_t6.csv.SubjectCSV;
import me.yaochunghu.u1_t6.model.Student;
import me.yaochunghu.u1_t6.model.Subject;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubjectGradeTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Matr\u00EDcula", "Nombre del estudiante", "Nombre del curso", "Calificaci\u00F3n"};

    private final List<Student> students;
    private final List<Subject> subjects = new ArrayList<>();

    private final SubjectCSV subjectCSV;

    public SubjectGradeTableModel(List<Student> students, SubjectCSV subjectCSV) {
        this.students = students;
        this.subjectCSV = subjectCSV;

        final List<Subject> temporalList = subjectCSV.read();
        this.students.forEach(student -> {
            Optional<Subject> subjectOptional = temporalList.stream().filter(subject -> subject.getId() == student.getId()).findFirst();
            if (!subjectOptional.isPresent()) {
                this.subjects.add(new Subject(student.getId(), "Dise\u00F1o de software"));
            } else {
                this.subjects.add(subjectOptional.get());
            }
        });
    }

    @Override
    public String getColumnName(int column) {
        return this.columnNames[column];
    }

    @Override
    public int getRowCount() {
        return this.subjects.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = null;
        Student student = this.students.get(rowIndex);
        Subject subject = this.subjects.get(rowIndex);
        switch (columnIndex) {
            case 0:
                value = subject.getId();
                break;
            case 1:
                value = String.format("%s %s %s", student.getFirstLastName(), student.getSecondLastName(), student.getNames()).trim().replaceAll(" +", " ");
                break;
            case 2:
                value = subject.getName();
                break;
            case 3:
                value = subject.getGrade();
                break;
        }
        return value;
    }


    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Subject student = this.subjects.get(rowIndex);
        if (columnIndex == 3) {
            if (aValue instanceof String) {
                try {
                    float value = Float.parseFloat((String) aValue);
                    if (value >= 0 && value <= 100) {
                        student.setGrade(value);
                        this.subjectCSV.save(this.subjects);
                    } else {
                        JOptionPane.showMessageDialog(null, "La calificaci\u00F3n debe estar entre 0 y 100");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "La calificaci\u00F3n debe ser un n\u00FAmero.");
                }
            }
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column == 3;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }
}