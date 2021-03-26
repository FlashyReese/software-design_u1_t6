/*
 * Copyright © 2021 Yao Chung Hu
 *
 * This file is part of Software Design U1 T6.
 *
 * Licensed under the MIT license. For more information,
 * see the LICENSE file.
 */

package me.yaochunghu.u1_t6.ui;

import me.yaochunghu.u1_t6.Controller;
import me.yaochunghu.u1_t6.ui.component.StudentTableModel;
import me.yaochunghu.u1_t6.ui.component.SubjectGradeTableModel;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends AbstractUserInterface {
    private JLabel user;
    private JButton logout;

    private JTabbedPane tabbedPane;

    private JPanel studentsTab;
    private JScrollPane studentsScrollPane;
    private JTable students;

    private JPanel gradesTab;
    private JScrollPane gradesScrollPane;
    private JTable grades;
    private JButton exportCSV;
    private SubjectGradeTableModel gradeTableModel;

    public ControlPanel(Controller controller) {
        super(controller, "Control Panel", new Dimension(800, 600));
    }

    protected void initComponents() {
        this.user = new JLabel("None");
        this.logout = new JButton("Logout");

        this.studentsTab = new JPanel();
        this.studentsScrollPane = new JScrollPane();
        this.students = new JTable();

        this.gradesTab = new JPanel();
        this.gradesScrollPane = new JScrollPane();
        this.grades = new JTable();
        this.exportCSV = new JButton("Export");
        this.gradeTableModel = new SubjectGradeTableModel(this.getController().getStudentCSV().read());

        this.tabbedPane = new JTabbedPane();
    }

    protected void setupComponents() {
        this.user.setLocation(6, 2);
        this.logout.setLocation(670, 2);
        this.user.setSize(100, 30);
        this.logout.setSize(100, 30);

        this.tabbedPane.setLocation(2, 32);
        this.tabbedPane.setSize(780, 525);

        this.students.setModel(new StudentTableModel(this.getController().getStudentCSV().read()));

        this.grades.setModel(this.gradeTableModel);

        this.studentsScrollPane.setViewportView(this.students);
        this.studentsScrollPane.setLocation(2, 2);
        this.studentsScrollPane.setPreferredSize(new Dimension(760, 490));

        this.gradesScrollPane.setViewportView(this.grades);
        this.gradesScrollPane.setLocation(2, 2);
        this.gradesScrollPane.setPreferredSize(new Dimension(760, 456));

        this.exportCSV.setLocation(2,458);
        this.exportCSV.setPreferredSize(new Dimension(750, 30));

        this.studentsTab.setLocation(0, 0);
        this.studentsTab.setSize(770, 500);

        this.gradesTab.setLocation(0, 0);
        this.gradesTab.setSize(770, 500);

        this.exportCSV.addActionListener(e -> this.getController().exportGradesCSV());

        this.logout.addActionListener(e -> {
            this.setVisible(false);
            this.getController().getLoginFrame().setVisible(true);
        });
    }

    protected void loadComponents() {
        this.studentsTab.add(this.studentsScrollPane);

        this.gradesTab.add(this.gradesScrollPane);
        this.gradesTab.add(this.exportCSV);

        this.tabbedPane.addTab("Students", this.studentsTab);

        this.tabbedPane.addTab("Grades", this.gradesTab);

        this.add(this.user);
        this.add(this.logout);
        this.add(this.tabbedPane);
    }

    public void updateInterface() {
        this.user.setText(this.getController().getCurrentUser().getUsername());
    }

    public SubjectGradeTableModel getGradeTableModel() {
        return gradeTableModel;
    }
}
