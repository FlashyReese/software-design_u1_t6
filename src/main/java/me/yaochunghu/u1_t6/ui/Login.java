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

public class Login extends AbstractUserInterface {
    private JLabel userLabel;
    private JLabel passwordLabel;
    private JTextField user;
    private JPasswordField password;
    private JButton login;
    private JButton register;

    public Login(Controller controller) {
        super(controller, "Inicio de sesi\u00F3n de usuario", new Dimension(424, 135));
    }

    protected void initComponents() {
        this.userLabel = new JLabel("Usuario:");
        this.passwordLabel = new JLabel("Contrase\u00F1a:");
        this.user = new JTextField();
        this.password = new JPasswordField();
        this.login = new JButton("Accesarse");
        this.register = new JButton("Registrarse");
    }

    protected void setupComponents() {
        this.userLabel.setLocation(2, 2);
        this.passwordLabel.setLocation(2, 32);
        this.userLabel.setSize(100, 30);
        this.passwordLabel.setSize(100, 30);
        this.user.setLocation(102, 2);
        this.password.setLocation(102, 32);
        this.user.setSize(300, 30);
        this.password.setSize(300, 30);

        this.login.setLocation(102, 62);
        this.register.setLocation(302, 62);
        this.login.setSize(100, 30);
        this.register.setSize(100, 30);

        this.login.addActionListener(e -> this.getController().verifyLogin());

        this.register.addActionListener(e -> {
            this.setVisible(false);
            this.getController().getRegisterFrame().setVisible(true);
        });
    }

    protected void loadComponents() {
        this.add(this.userLabel);
        this.add(this.passwordLabel);
        this.add(this.user);
        this.add(this.password);
        this.add(this.login);
        this.add(this.register);
    }

    public JTextField getUser() {
        return user;
    }

    public JTextField getPassword() {
        return password;
    }
}
