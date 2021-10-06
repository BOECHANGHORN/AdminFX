package Utils;

import Role.Role;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class AutoCompleteRoleBox implements EventHandler{
    private ComboBox<Role> comboBox;
    private ObservableList<Role> data;

    public AutoCompleteRoleBox(final ComboBox comboBox) {
        this.comboBox = comboBox;
        this.data = comboBox.getItems();

        this.doAutoCompleteBox();
    }

    private void doAutoCompleteBox() {
        this.comboBox.setEditable(true);

        RoleStringConverter rsc = new RoleStringConverter();
        rsc.setRoleList(data);
        this.comboBox.setConverter(rsc);

        this.comboBox.getEditor().focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){//mean onfocus
                this.comboBox.show();
            }
        });



        this.comboBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            moveCaret(this.comboBox.getEditor().getText().length());
        });


        this.comboBox.setOnKeyReleased(this);

}

    @Override
    public void handle(Event e) {
        if (!(e instanceof KeyEvent))
            return;

        KeyEvent event = (KeyEvent) e;
        if ( event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN
                || event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT
                || event.getCode() == KeyCode.HOME
                || event.getCode() == KeyCode.END || event.getCode() == KeyCode.TAB
        ) {
            return;
        }

        if(event.getCode() == KeyCode.BACK_SPACE){
            String str = this.comboBox.getEditor().getText();
            if (str != null && str.length() > 0) {
                str = str.substring(0, str.length() - 1);
            }
            if(str != null){
                this.comboBox.getEditor().setText(str);
                moveCaret(str.length());
            }
            this.comboBox.getSelectionModel().clearSelection();
        }

        if(event.getCode() == KeyCode.ENTER && comboBox.getSelectionModel().getSelectedIndex()>-1)
            return;

        setItems();
    }

    private void setItems() {
        ObservableList<Role> list = FXCollections.observableArrayList();

        for (Role role : this.data) {
            String s = this.comboBox.getEditor().getText().toLowerCase();
            if (role.getUserName().toLowerCase().contains(s.toLowerCase())) {
                list.add(role);
            }
        }

        if(list.isEmpty()) this.comboBox.hide();

        this.comboBox.setItems(list);
        this.comboBox.show();
    }

    private void moveCaret(int textLength) {
        this.comboBox.getEditor().positionCaret(textLength);
    }

}