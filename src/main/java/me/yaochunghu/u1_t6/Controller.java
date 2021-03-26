/*
 * Copyright © 2021 Yao Chung Hu
 *
 * This file is part of Software Design U1 T6.
 *
 * Licensed under the MIT license. For more information,
 * see the LICENSE file.
 */

package me.yaochunghu.u1_t6;

import me.yaochunghu.u1_t6.csv.StudentCSV;
import me.yaochunghu.u1_t6.csv.SubjectCSV;
import me.yaochunghu.u1_t6.csv.UserCSV;
import me.yaochunghu.u1_t6.model.Subject;
import me.yaochunghu.u1_t6.model.User;
import me.yaochunghu.u1_t6.ui.ControlPanel;
import me.yaochunghu.u1_t6.ui.Login;
import me.yaochunghu.u1_t6.ui.Register;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

public class Controller {
    private final StudentCSV studentCSV = new StudentCSV();
    private final UserCSV userCSV = new UserCSV();
    private final SubjectCSV subjectCSV = new SubjectCSV();

    private final Login loginFrame = new Login(this);
    private final Register registerFrame = new Register(this);
    private final ControlPanel controlPanelFrame = new ControlPanel(this);

    private User currentUser = null;

    public Controller() {
        this.loginFrame.setVisible(true);
    }

    public void verifyLogin() {
        Optional<User> userOptional = this.userCSV.read().stream().filter(user -> user.getUsername().equals(this.loginFrame.getUser().getText())).findFirst();
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPassword().equals(this.loginFrame.getPassword().getText())) {
                this.currentUser = user;
                this.loginFrame.getUser().setText("");
                this.loginFrame.getPassword().setText("");
                this.loginFrame.setVisible(false);
                this.controlPanelFrame.setVisible(true);
                this.controlPanelFrame.updateInterface();
            } else {
                JOptionPane.showMessageDialog(this.loginFrame, "¡Contraseña invalida!");
            }
        } else {
            JOptionPane.showMessageDialog(this.loginFrame, "¡Nombre de usuario no válido!");
        }
    }

    public void registerUser() {
        Optional<User> userOptional = this.userCSV.read().stream().filter(user -> user.getUsername().equals(this.registerFrame.getUser().getText())).findFirst();
        if (userOptional.isPresent()) {
            JOptionPane.showMessageDialog(this.registerFrame, "¡Nombre de usuario ya tomado!");
        } else {
            if (this.registerFrame.getPassword().getText().equals(this.registerFrame.getConfirmPassword().getText())) {
                User user = new User(this.registerFrame.getUser().getText(), this.registerFrame.getPassword().getText());
                this.userCSV.save(user, true);
                this.currentUser = user;
                JOptionPane.showMessageDialog(this.registerFrame, "¡Registro completado!");
                this.registerFrame.getUser().setText("");
                this.registerFrame.getPassword().setText("");
                this.registerFrame.getConfirmPassword().setText("");
                this.registerFrame.setVisible(false);
                this.controlPanelFrame.setVisible(true);
                this.controlPanelFrame.updateInterface();
            } else {
                JOptionPane.showMessageDialog(this.registerFrame, "¡Las contraseñas no coinciden!");
            }
        }
    }

    public void exportGradesCSV() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Especifique un archivo para guardar");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV", "csv");
        fileChooser.setFileFilter(filter);
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setAcceptAllFileFilterUsed(false);

        int userSelection = fileChooser.showSaveDialog(this.controlPanelFrame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            if (!fileToSave.getAbsolutePath().endsWith(".csv")) fileToSave = new File(fileToSave.getAbsolutePath() + ".csv");
            try (FileWriter writer = new FileWriter(fileToSave)) {
                for (Subject subject : this.controlPanelFrame.getGradeTableModel().getSubjects()) {
                    if (subject.getGrade() != null) {
                        String line = subject.toString() + "\r\n";
                        writer.write(line);
                    }
                }
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Login getLoginFrame() {
        return loginFrame;
    }

    public Register getRegisterFrame() {
        return registerFrame;
    }

    public ControlPanel getControlPanelFrame() {
        return controlPanelFrame;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public StudentCSV getStudentCSV() {
        return studentCSV;
    }

    public UserCSV getUserCSV() {
        return userCSV;
    }

    public SubjectCSV getSubjectCSV() {
        return subjectCSV;
    }
}
