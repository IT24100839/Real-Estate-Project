package com.yourdomain.realestate.util; // Adjust package if you put it elsewhere

import com.yourdomain.realestate.model.Property;

/**
 * Represents a node in the Binary Search Tree for Property objects.
 */
public class PropertyNode {
    private Property property;
    private PropertyNode left;
    private PropertyNode right;

    /**
     * Constructs a new PropertyNode with the given Property object.
     *
     * @param property The Property object to store in this node.
     */
    public PropertyNode(Property property) {
        this.property = property;
        this.left = null;
        this.right = null;
    }

    // --- Getters ---
    public Property getProperty() {
        return property;
    }

    public PropertyNode getLeft() {
        return left;
    }

    public PropertyNode getRight() {
        return right;
    }

    // --- Setters ---
    // Setters are needed to modify the node's children and potentially the property itself
    public void setProperty(Property property) {
        this.property = property;
    }

    public void setLeft(PropertyNode left) {
        this.left = left;
    }

    public void setRight(PropertyNode right) {
        this.right = right;
    }
}
