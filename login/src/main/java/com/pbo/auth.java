package com.pbo;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import com.pbo.model.conn;
import com.pbo.model.key;

public class auth {

    Alert info = new Alert(AlertType.INFORMATION);

    @FXML
    private Tab logTab, regTab;
    @FXML
    private TextField tUserLog, tUserReg, tNamaReg, tEmailReg;
    @FXML
    private PasswordField tPassLog, tPassReg;
    @FXML
    private Button bLog, bReg;

    public void initialize() {
        focus();
    }

    @FXML
    void authTab(Event e) {
        focus();
    }

    @FXML
    void bLogClick(Event e) {
        validate(e);
    }

    @FXML
    void bRegClick(Event e) {
        register(e);
    }

    @FXML
    void tUserRegEnter(KeyEvent ke) {
        key.enterPress(ke, tNamaReg);
    }
    
    @FXML
    void tNamaRegEnter(KeyEvent ke) {
        key.enterPress(ke, tEmailReg);
    }
    
    @FXML
    void tEmailRegEnter(KeyEvent ke) {
        key.enterPress(ke, tPassReg);
    }

    @FXML
    void tPassRegEnter(KeyEvent ke) {

        if (ke.getCode().equals(KeyCode.ENTER)) {
            register(ke);
        }
    }

    @FXML
    void tUserLogEnter(KeyEvent ke) {
        key.enterPress(ke, tPassLog);
    }

    @FXML
    void tPassLogEnter(KeyEvent ke) {

        if (ke.getCode().equals(KeyCode.ENTER)) {
            validate(ke);
        }
    }

    private void register(Event e) {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Informasi");
        
        if (!check()) {
            try {
                conn.open();
                ResultSet rs = conn.select("select * from akun where userid = '" + tUserReg.getText() + "' and pass = '"
                        + tPassReg.getText() + "'");
    
                if (!rs.next()) {
                    conn.update("INSERT INTO akun(userid, pass, nama, email, status) value('" + tUserReg.getText() + "', '"
                            + tPassReg.getText() + "', '" + tNamaReg.getText() + "', '" + tEmailReg.getText()
                            + "', 'pembeli')");
                    alert.setContentText("Register berhasil!");
                    alert.showAndWait();
                    clearAfterReg();
                    focus();
                    conn.close();
                } else {
                    conn.close();
                    alert.setContentText("Username sudah ada! gunakan yang lain!");
                    alert.showAndWait();
                    focus();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void validate(Event e) {

        try {
            conn.open();
            ResultSet rs = conn.select("SELECT * FROM akun WHERE userid = '" + tUserLog.getText() + "' and pass = '"
                    + tPassLog.getText() + "'");

            if (rs.next()) {
                String status = rs.getString("status");
                conn.close();

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informasi");

                if (status.equals("admin")) {
                    tPassLog.clear();
                    alert.setContentText("Berhasil Login Sebagai Admin!");
                    alert.showAndWait();
                } else if (status.equals("manager")) {
                    tPassLog.clear();
                    alert.setContentText("Berhasil Login Sebagai Manager!");
                    alert.showAndWait();
                } else if (status.equals("staff")) {
                    tPassLog.clear();
                    alert.setContentText("Berhasil Login Sebagai Staff!");
                    alert.showAndWait();
                } else {
                    tPassLog.clear();
                    alert.setContentText("Berhasil Login Sebagai Pembeli!");
                    alert.showAndWait();
                }
            } else {
                conn.close();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informasi");
                alert.setContentText("Username atau password salah!");
                alert.showAndWait();
                focus();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void focus() {

        if (logTab.isSelected()) {
            key.focus(tUserLog);;
        } else {
            key.focus(tUserReg);;
        }
    }

    private void clearAfterReg() {

        tUserReg.clear();
        tPassReg.clear();
        tNamaReg.clear();
        tEmailReg.clear();
    }

    protected Boolean check() {

        if (key.empty(tUserReg)) {
            info.setContentText("Mohon isi username!");
            key.focus(tUserReg);
            info.showAndWait();
            return true;

        } else if (key.empty(tNamaReg)) {
            info.setContentText("Mohon isi nama!");
            key.focus(tNamaReg);
            info.showAndWait();
            return true;

        } else if (key.empty(tEmailReg)) {
            info.setContentText("Mohon isi email!");
            key.focus(tEmailReg);
            info.showAndWait();
            return true;

        } else if (key.empty(tPassReg)) {
            info.setContentText("Mohon isi !");
            key.focus(tPassReg);
            info.showAndWait();
            return true;

        } else {
            return false;
        }
    }
}