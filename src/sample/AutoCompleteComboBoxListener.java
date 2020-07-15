package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Collections;
import java.util.Comparator;

public class AutoCompleteComboBoxListener<T> implements EventHandler<KeyEvent> {

    private ComboBox<T> comboBox;
    private ObservableList<T> data;
    private boolean moveCaretToPos = false;
    private int caretPos;


    public AutoCompleteComboBoxListener(final ComboBox<T> comboBox) {
        this.comboBox = comboBox;
        data = comboBox.getItems();

        this.comboBox.setEditable(true);
        this.comboBox.setOnKeyReleased(AutoCompleteComboBoxListener.this);
        this.showOnFocus();

    }

    @Override
    public void handle(KeyEvent event) {


        if(event.getCode() == KeyCode.UP) {
            caretPos = -1;
            moveCaret(comboBox.getEditor().getText().length());
            return;
        } else if(event.getCode() == KeyCode.DOWN) {
            if(!comboBox.isShowing())
                comboBox.show();

            caretPos = -1;
            moveCaret(comboBox.getEditor().getText().length());
            return;
        }

        if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT
                || event.isControlDown() || event.getCode() == KeyCode.HOME
                || event.getCode() == KeyCode.END || event.getCode() == KeyCode.TAB) {
            return;
        }


        comboBox.hide();



        if(event.getCode() == KeyCode.BACK_SPACE) {
            moveCaretToPos = true;
            caretPos = comboBox.getEditor().getCaretPosition();
        } else if(event.getCode() == KeyCode.DELETE) {
            moveCaretToPos = true;
            caretPos = comboBox.getEditor().getCaretPosition();
        }



        ObservableList<T> list = FXCollections.observableArrayList();
        for (int i=0; i<data.size(); i++) {
            if(data.get(i).toString().toLowerCase().startsWith(
                    AutoCompleteComboBoxListener.this.comboBox
                            .getEditor().getText().toLowerCase())) {
                list.add(data.get(i));
            }
        }
        String t = comboBox.getEditor().getText();

        comboBox.setItems(list);
        comboBox.getEditor().setText(t);
        if(!moveCaretToPos) {
            caretPos = -1;
        }
        moveCaret(t.length());
        if(!list.isEmpty()) {
            comboBox.show();
            String editorText;
            editorText=comboBox.getEditor().getText();
            list.sort((a, b) -> Integer.compare(a.toString().length(), b.toString().length()));
            comboBox.getSelectionModel().selectFirst();
            comboBox.getEditor().setText(editorText);
            moveCaret(comboBox.getSelectionModel().getSelectedItem().toString().length());
        }
        if (event.getCode()==KeyCode.ENTER){
            comboBox.hide();
            comboBox.getEditor().setText(comboBox.getSelectionModel().getSelectedItem().toString());

        }
    }

    private void moveCaret(int textLength) {

        if(caretPos == -1)
            comboBox.getEditor().positionCaret(textLength);
        else
            comboBox.getEditor().positionCaret(caretPos);

        moveCaretToPos = false;
    }

    public void showOnFocus(){
        this.comboBox.focusedProperty().addListener((obs, oldVal, newVal) -> this.comboBox.show());
    }

}
