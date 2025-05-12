package com.yourdomain.realestate.util; // Adjust package if needed

import com.yourdomain.realestate.model.Property;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID; // Although not directly used for comparisons here, good to have if needed

/**
 * A Binary Search Tree implementation for managing Property objects,
 * ordered by the Property's unique String ID.
 */
public class PropertyBST {
    private PropertyNode root;

    public PropertyBST() {
        this.root = null;
    }

    /**
     * Inserts a new Property into the BST.
     *
     * @param property The Property to insert.
     */
    public void insert(Property property) {
        // Ensure the property has an ID before inserting (though Property constructor handles this)
        if (property.getId() == null || property.getId().isEmpty()) {
            // This case should ideally not happen if the Property constructor is used
            // but as a safeguard:
            property.setId(UUID.randomUUID().toString());
        }
        root = insertRecursive(root, property);
    }

    // Recursive helper method for insertion
    private PropertyNode insertRecursive(PropertyNode current, Property property) {
        // Base case: If the current node is null, we've found the insertion point
        if (current == null) {
            return new PropertyNode(property);
        }

        // Compare the ID of the property to be inserted with the ID of the property in the current node
        int comparison = property.getId().compareTo(current.getProperty().getId());

        if (comparison < 0) {
            // If the new property's ID is smaller, go to the left subtree
            current.setLeft(insertRecursive(current.getLeft(), property));
        } else if (comparison > 0) {
            // If the new property's ID is greater, go to the right subtree
            current.setRight(insertRecursive(current.getRight(), property));
        } else {
            // If the IDs are equal, the property is considered a duplicate
            // For this application, we assume unique IDs, so we might ignore insertion
            // or handle as an update if needed. For simplicity, we'll just return the current node.
            System.out.println("Warning: Attempted to insert duplicate property ID: " + property.getId());
            return current;
        }

        return current; // Return the (potentially updated) current node
    }

    /**
     * Searches for a Property in the BST by its ID.
     *
     * @param id The ID of the Property to search for.
     * @return The found Property object, or null if not found.
     */
    public Property search(String id) {
        return searchRecursive(root, id);
    }

    // Recursive helper method for searching
    private Property searchRecursive(PropertyNode current, String id) {
        // Base cases: If the current node is null, the property is not found.
        // If the current node's property ID matches the search ID, return the property.
        if (current == null || current.getProperty().getId().equals(id)) {
            return (current == null) ? null : current.getProperty();
        }

        // Compare the search ID with the ID of the property in the current node
        int comparison = id.compareTo(current.getProperty().getId());

        if (comparison < 0) {
            // If the search ID is smaller, search in the left subtree
            return searchRecursive(current.getLeft(), id);
        } else {
            // If the search ID is greater, search in the right subtree
            return searchRecursive(current.getRight(), id);
        }
    }

    /**
     * Updates an existing Property in the BST.
     * Assumes the ID of the updatedProperty matches an existing property's ID.
     *
     * @param updatedProperty The Property object with updated details (ID must match existing).
     * @return true if the property was found and updated, false otherwise.
     */
    public boolean update(Property updatedProperty) {
        // For BST update, the key (ID) should ideally not change.
        // We find the node by ID and update the Property object within that node.
        PropertyNode nodeToUpdate = findNodeById(root, updatedProperty.getId());
        if (nodeToUpdate != null) {
            nodeToUpdate.setProperty(updatedProperty); // Update the property object in the node
            return true; // Property found and updated
        }
        return false; // Property with the given ID not found
    }

    // Helper method to find a node by ID (useful for update and delete)
    private PropertyNode findNodeById(PropertyNode current, String id) {
        if (current == null) {
            return null;
        }
        int comparison = id.compareTo(current.getProperty().getId());
        if (comparison == 0) {
            return current;
        } else if (comparison < 0) {
            return findNodeById(current.getLeft(), id);
        } else {
            return findNodeById(current.getRight(), id);
        }
    }


    /**
     * Deletes a Property from the BST by its ID.
     *
     * @param id The ID of the Property to delete.
     */
    public void delete(String id) {
        root = deleteRecursive(root, id);
    }

    // Recursive helper method for deletion
    private PropertyNode deleteRecursive(PropertyNode current, String id) {
        // Base case: If the current node is null, the property is not found
        if (current == null) {
            return null;
        }

        // Compare the ID to be deleted with the ID of the property in the current node
        int comparison = id.compareTo(current.getProperty().getId());

        if (comparison < 0) {
            // If the ID is smaller, go to the left subtree
            current.setLeft(deleteRecursive(current.getLeft(), id));
        } else if (comparison > 0) {
            // If the ID is greater, go to the right subtree
            current.setRight(deleteRecursive(current.getRight(), id));
        } else {
            // If the ID is equal, this is the node to be deleted

            // Case 1: Node with only one child or no child
            if (current.getLeft() == null) {
                return current.getRight(); // Return the right child (could be null)
            } else if (current.getRight() == null) {
                return current.getLeft(); // Return the left child
            }

            // Case 2: Node with two children
            // Find the in-order successor (smallest in the right subtree)
            Property smallestValue = findSmallestValue(current.getRight());

            // Replace the current node's property with the in-order successor's property
            current.setProperty(smallestValue);

            // Delete the in-order successor from the right subtree
            current.setRight(deleteRecursive(current.getRight(), smallestValue.getId()));
        }
        return current; // Return the (potentially updated) current node
    }

    // Helper method to find the smallest value (in-order successor) in a subtree
    private Property findSmallestValue(PropertyNode root) {
        // The smallest value in a BST is the leftmost node
        return (root.getLeft() == null) ? root.getProperty() : findSmallestValue(root.getLeft());
    }

    /**
     * Retrieves all properties from the BST using in-order traversal.
     * This will return properties sorted by their ID.
     *
     * @return A List of all Property objects in the BST.
     */
    public List<Property> getAllProperties() {
        List<Property> properties = new ArrayList<>();
        inOrderTraversal(root, properties);
        return properties;
    }

    // Recursive helper method for in-order traversal
    private void inOrderTraversal(PropertyNode current, List<Property> properties) {
        if (current != null) {
            inOrderTraversal(current.getLeft(), properties); // Traverse left subtree
            properties.add(current.getProperty());         // Visit current node
            inOrderTraversal(current.getRight(), properties); // Traverse right subtree
        }
    }

    // You can add other traversal methods (pre-order, post-order) if needed for other purposes.

    /**
     * Checks if the BST is empty.
     * @return true if the BST is empty, false otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Gets the number of nodes in the BST.
     * @return The size of the BST.
     */
    public int size() {
        return sizeRecursive(root);
    }

    private int sizeRecursive(PropertyNode current) {
        if (current == null) {
            return 0;
        }
        return 1 + sizeRecursive(current.getLeft()) + sizeRecursive(current.getRight());
    }
}