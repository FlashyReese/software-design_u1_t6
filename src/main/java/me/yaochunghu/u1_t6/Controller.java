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
import me.yaochunghu.u1_t6.model.User;
import me.yaochunghu.u1_t6.ui.ControlPanel;
import me.yaochunghu.u1_t6.ui.Login;
import me.yaochunghu.u1_t6.ui.Register;

import javax.swing.*;
import java.util.Optional;

public class Controller {
    private final StudentCSV studentCSV = new StudentCSV();
    private final SubjectCSV subjectCSV = new SubjectCSV();
    private final UserCSV userCSV = new UserCSV();

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
                JOptionPane.showMessageDialog(this.loginFrame, "Invalid password!");
            }
        } else {
            JOptionPane.showMessageDialog(this.loginFrame, "Invalid username!");
        }
    }

    public void registerUser() {
        Optional<User> userOptional = this.userCSV.read().stream().filter(user -> user.getUsername().equals(this.registerFrame.getUser().getText())).findFirst();
        if (userOptional.isPresent()) {
            JOptionPane.showMessageDialog(this.registerFrame, "Username already taken!");
        } else {
            if (this.registerFrame.getPassword().getText().equals(this.registerFrame.getConfirmPassword().getText())) {
                User user = new User(this.registerFrame.getUser().getText(), this.registerFrame.getPassword().getText());
                this.userCSV.save(user);
                this.currentUser = user;
                JOptionPane.showMessageDialog(this.registerFrame, "Registration Completed!");
                this.registerFrame.getUser().setText("");
                this.registerFrame.getPassword().setText("");
                this.registerFrame.getConfirmPassword().setText("");
                this.registerFrame.setVisible(false);
                this.controlPanelFrame.setVisible(true);
                this.controlPanelFrame.updateInterface();
            } else {
                JOptionPane.showMessageDialog(this.registerFrame, "Password does not match!");
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

    public SubjectCSV getSubjectCSV() {
        return subjectCSV;
    }

    public UserCSV getUserCSV() {
        return userCSV;
    }
}
