package me.yaochunghu.u1_t6.ui.component;

import me.yaochunghu.u1_t6.model.Student;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class StudentTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Matr\u00EDcula", "Nombres", "Apellido Paterno", "Apellido Materno"};

    private final List<Student> students;

    public StudentTableModel(List<Student> students) {
        this.students = students;
    }

    @Override
    public String getColumnName(int column) {
        return this.columnNames[column];
    }

    @Override
    public int getRowCount() {
        return this.students.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = null;
        Student user = this.students.get(rowIndex);
        switch (columnIndex) {
            case 0:
                value = user.getId();
                break;
            case 1:
                value = user.getNames();
                break;
            case 2:
                value = user.getFirstLastName();
                break;
            case 3:
                value = user.getSecondLastName();
                break;
        }
        return value;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}