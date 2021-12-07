package gui.swing.model;

import java.awt.Color;

public class ModelLegend1 {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ModelLegend1(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public ModelLegend1() {
    }

    private String name;
    private Color color;
}
