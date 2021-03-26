package me.yaochunghu.u1_t6.ui.component;

import me.yaochunghu.u1_t6.model.Student;
import me.yaochunghu.u1_t6.model.Subject;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class SubjectGradeTableModel extends AbstractTableModel {
    private final String[] columnNames = {"ID", "Student Name", "Subject Name", "Grade"};

    private final List<Student> students;
    private final List<Subject> subjects = new ArrayList<>();

    public SubjectGradeTableModel(List<Student> students) {
        this.students = students;
        this.students.forEach(student -> this.subjects.add(new Subject(student.getId(), "Software Design")));
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
                    int value = Integer.parseInt((String) aValue);
                    if (value >= 0 && value <= 100) {
                        student.setGrade(value);
                    } else {
                        JOptionPane.showMessageDialog(null, "The grade must be a between 0 - 100");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "The grade must be a number.");
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