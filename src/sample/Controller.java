package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import sample.dataModal.ToDoData;
import sample.dataModal.ToDoItem;
import java.time.format.DateTimeFormatter;

public class Controller {

    @FXML
    private TextArea itemDetailsTextArea;

    @FXML
    private ListView<ToDoItem> todoListView;

    @FXML
    private Label deadLineLabel;

    public void initialize() {
//        ToDoItem item1 = new ToDoItem("Test", "Extended Test",
//                LocalDate.of(2020, Month.SEPTEMBER, 20));
//        ToDoItem item2 = new ToDoItem("Test2", "Extended Test 2",
//                LocalDate.of(2020, Month.SEPTEMBER, 21));
//        ToDoItem item3 = new ToDoItem("Test3", "Extended Test 3",
//                LocalDate.of(2020, Month.SEPTEMBER, 22));
//        ToDoItem item4 = new ToDoItem("Test 4", "Extended Test 4",
//                LocalDate.of(2020, Month.SEPTEMBER, 23));
//        ToDoItem item5 = new ToDoItem("Test 5", "Extended Test 5",
//                LocalDate.of(2020, Month.SEPTEMBER, 24));
//        toDoItems = new ArrayList<>();
//        toDoItems.add(item1);
//        toDoItems.add(item2);
//        toDoItems.add(item3);
//        toDoItems.add(item4);
//        toDoItems.add(item5);
//
//        ToDoData.getInstance().setToDoItems(toDoItems);

        todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ToDoItem>() {
            @Override
            public void changed(ObservableValue<? extends ToDoItem> observableValue, ToDoItem oldItem, ToDoItem newItem) {
                if (newItem != null) {
                    itemDetailsTextArea.setText(newItem.getDetails());
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM d, yyyy");
                    deadLineLabel.setText(df.format(newItem.getDeadline()));
                }
            }
        });

        todoListView.getItems().setAll(ToDoData.getInstance().getToDoItems());
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListView.getSelectionModel().selectFirst();
    }

    @FXML
    public void handleClickListView() {
        ToDoItem item = todoListView.getSelectionModel().getSelectedItem();
        itemDetailsTextArea.setText(item.getDetails());
        deadLineLabel.setText(item.getDeadline().toString());
    }
}
