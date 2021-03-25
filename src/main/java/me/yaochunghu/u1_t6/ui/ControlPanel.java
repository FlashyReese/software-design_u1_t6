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

public class ControlPanel extends AbstractUserInterface {
    private JLabel user;
    private JButton logout;

    private JTabbedPane tabbedPane;

    public ControlPanel(Controller controller) {
        super(controller, "Control Panel", new Dimension(480, 240));
    }

    protected void initComponents() {
        this.user = new JLabel("None");
        this.logout = new JButton("Logout");

        this.tabbedPane = new JTabbedPane();
    }

    protected void setupComponents() {
        this.user.setLocation(2, 2);
        this.logout.setLocation(360, 2);
        this.user.setSize(100, 30);
        this.logout.setSize(100, 30);

        this.tabbedPane.setLocation(2, 32);
        this.tabbedPane.setSize(476, 208);


        this.logout.addActionListener(e -> {
            this.setVisible(false);
            this.getController().getLoginFrame().setVisible(true);
        });
    }

    protected void loadComponents() {
        this.add(this.user);
        this.add(this.logout);
        this.add(this.tabbedPane);
    }

    public void updateInterface() {
        this.user.setText(this.getController().getCurrentUser().getUsername());
    }
}
