package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Judgment {

    @SerializedName("items")
    @Expose
    private List<Item> items;

    public Judgment() {
        items = new ArrayList<>();
    }

    public Judgment(List<Item> items) {
        super();
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void add(Judgment addedJson) {
        items.addAll(addedJson.getItems());
    }
}
