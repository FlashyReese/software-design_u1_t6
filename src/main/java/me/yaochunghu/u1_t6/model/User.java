/*
 * Copyright © 2021 Yao Chung Hu
 *
 * This file is part of Software Design U1 T6.
 *
 * Licensed under the MIT license. For more information,
 * see the LICENSE file.
 */

package me.yaochunghu.u1_t6.model;

public class User {
    private final String username;
    private String password;

    public User(String username) {
        this.username = username;
    }

    public User(String username, String password) {
        this(username);
        this.password = password;
    }

    public User(String[] data) {
        this(data[0], data[1]);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("%s,%s", this.username, this.password);
    }
}
