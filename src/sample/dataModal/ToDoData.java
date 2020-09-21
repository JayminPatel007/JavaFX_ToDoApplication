package sample.dataModal;

import javafx.collections.FXCollections;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ToDoData {
    private static ToDoData instance = new ToDoData();
    private static String fileName = "ToDOListItems.txt";
    private List<ToDoItem> toDoItems;
    private DateTimeFormatter formatter;

    public static ToDoData getInstance() {
        return instance;
    }

    private ToDoData() {
        this.formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public List<ToDoItem> getToDoItems() {
        return toDoItems;
    }

    public void loadToDoItems() throws IOException {

        toDoItems = FXCollections.observableArrayList();
        Path path = Paths.get(fileName);
        BufferedReader br = Files.newBufferedReader(path);

        String input;

        try {
            while ((input = br.readLine()) != null) {
                String[] itemPices = input.split("\t");
                String shortDescription = itemPices[0];
                String details = itemPices[1];
                String dateString = itemPices[2];

                LocalDate date = LocalDate.parse(dateString, formatter);
                ToDoItem toDoItem = new ToDoItem(shortDescription, details, date);
                toDoItems.add(toDoItem);
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    public void storeToDoItems() throws IOException {
        Path path = Paths.get(fileName);
        BufferedWriter bw = Files.newBufferedWriter(path);
        try {
            for (ToDoItem item : toDoItems) {
                bw.write(String.format("%s\t%s\t%s", item.getShortDescription(),
                        item.getDetails(),
                        item.getDeadline().format(formatter)));
                bw.newLine();
            }
        } finally {
            if (bw != null) {
                bw.close();
            }
        }
    }
}
