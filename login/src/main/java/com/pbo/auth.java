package com.pbo;

import com.pbo.model.key;

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

    private void register(Event e) {}

    private void validate(Event e) {}

    private void focus() {

        if (logTab.isSelected()) {
            key.focus(tUserLog);;
        } else {
            key.focus(tUserReg);;
        }
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