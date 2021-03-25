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

public abstract class AbstractUserInterface extends JFrame{
    private final Controller controller;

    public AbstractUserInterface(Controller controller, String title, Dimension dimension) {
        this.controller = controller;
        this.setTitle("Control Panel");
        this.initComponents();
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setPreferredSize(dimension);
        this.setupComponents();
        this.loadComponents();
        this.pack();
        this.setLocationRelativeTo(null);
    }

    protected abstract void initComponents();

    protected abstract void setupComponents();

    protected abstract void loadComponents();

    public Controller getController() {
        return controller;
    }
}
