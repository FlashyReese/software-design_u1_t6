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

import javax.swing.*;
import java.awt.*;

public class Register extends AbstractUserInterface {
    private JLabel userLabel;
    private JLabel passwordLabel;
    private JLabel confirmPasswordLabel;
    private JTextField user;
    private JPasswordField password;
    private JPasswordField confirmPassword;
    private JButton create;
    private JButton back;

    public Register(Controller controller) {
        super(controller, "User Registration", new Dimension(424, 165));
    }

    protected void initComponents() {
        this.userLabel = new JLabel("User:");
        this.passwordLabel = new JLabel("Password:");
        this.confirmPasswordLabel = new JLabel("Confirm Password:");
        this.user = new JTextField();
        this.password = new JPasswordField();
        this.confirmPassword = new JPasswordField();
        this.create = new JButton("Create");
        this.back = new JButton("Back");
    }

    protected void setupComponents() {
        this.userLabel.setLocation(2, 2);
        this.passwordLabel.setLocation(2, 32);
        this.confirmPasswordLabel.setLocation(2, 62);
        this.userLabel.setSize(100, 30);
        this.passwordLabel.setSize(100, 30);
        this.confirmPasswordLabel.setSize(100, 30);
        this.user.setLocation(102, 2);
        this.password.setLocation(102, 32);
        this.confirmPassword.setLocation(102, 62);
        this.user.setSize(300, 30);
        this.password.setSize(300, 30);
        this.confirmPassword.setSize(300, 30);

        this.create.setLocation(102, 92);
        this.back.setLocation(302, 92);
        this.create.setSize(100, 30);
        this.back.setSize(100, 30);

        this.create.addActionListener(e -> this.getController().registerUser());

        this.back.addActionListener(e -> {
            this.setVisible(false);
            this.getController().getLoginFrame().setVisible(true);
        });
    }

    protected void loadComponents() {
        this.add(this.userLabel);
        this.add(this.passwordLabel);
        this.add(this.confirmPasswordLabel);
        this.add(this.user);
        this.add(this.password);
        this.add(this.confirmPassword);
        this.add(this.create);
        this.add(this.back);
    }

    public JTextField getUser() {
        return user;
    }

    public JTextField getPassword() {
        return password;
    }

    public JTextField getConfirmPassword() {
        return confirmPassword;
    }
}
