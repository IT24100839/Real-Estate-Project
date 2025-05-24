package com.yourdomain.realestate.service; // Keep this line as it is

import com.yourdomain.realestate.model.Property; // Keep this import
import com.yourdomain.realestate.util.PropertyBST; // Import your PropertyBST class
import com.yourdomain.realestate.util.PropertyQuickSorter; // Import your QuickSorter
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList; // Still needed for converting BST to List for saving/sorting
import java.util.List;
import java.util.Optional;
import java.util.UUID; // Still needed for generating new IDs if necessary

/**
 * Service responsible for loading, saving, and managing Property objects using a JSON file and a Binary Search Tree.
 */
@Service
public class PropertyFileStorageService {

    @Value("${data.file.path:data/properties.json}")
    private String dataFilePath;

    // ** Modified: Use PropertyBST instead of List **
    private PropertyBST propertyTree = new PropertyBST();
    private ObjectMapper objectMapper = new ObjectMapper();
    private PropertyQuickSorter quickSorter = new PropertyQuickSorter(); // Inject or instantiate QuickSorter

    public PropertyFileStorageService() {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @PostConstruct
    private void loadData() {
        try {
            File file = new File(dataFilePath);
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            if (file.exists() && file.length() > 0) {
                // Read the data as a List from the JSON file first
                List<Property> loadedProperties = objectMapper.readValue(file, new TypeReference<List<Property>>(){});
                System.out.println("Read " + loadedProperties.size() + " properties from " + dataFilePath);

                // ** Modified: Insert properties into the BST **
                propertyTree = new PropertyBST(); // Ensure a clean BST before loading
                for (Property property : loadedProperties) {
                    propertyTree.insert(property);
                }
                System.out.println("Loaded " + propertyTree.size() + " properties into BST.");

            } else {
                // ** Modified: Initialize an empty BST **
                propertyTree = new PropertyBST();
                System.out.println("No data file found or file is empty. Starting with empty property tree at: " + dataFilePath);
            }
        } catch (IOException e) {
            System.err.println("Error loading properties from file: " + dataFilePath);
            e.printStackTrace();
            // ** Modified: Initialize an empty BST on error **
            propertyTree = new PropertyBST();
        }
    }

    private void saveData() {
        try {
            File file = new File(dataFilePath);
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            // ** Modified: Get all properties from the BST to save **
            List<Property> propertiesToSave = propertyTree.getAllProperties(); // This uses in-order traversal

            objectMapper.writeValue(file, propertiesToSave);
            System.out.println("Saved " + propertiesToSave.size() + " properties to " + dataFilePath);
        } catch (IOException e) {
            System.err.println("Error saving properties to file: " + dataFilePath);
            e.printStackTrace();
        }
    }

    // ** Modified: Use BST's getAllProperties method **
    public List<Property> getAllProperties() {
        return propertyTree.getAllProperties(); // Returns properties sorted by ID (due to in-order traversal)
    }

    // ** Modified: Use BST's search method **
    public Optional<Property> getPropertyById(String id) {
        Property foundProperty = propertyTree.search(id);
        return Optional.ofNullable(foundProperty);
    }

    // ** Modified: Use BST's insert method **
    public void addProperty(Property property) {
        // The Property constructor should generate the ID, but a safeguard remains
        if (property.getId() == null || property.getId().isEmpty()) {
            property.setId(UUID.randomUUID().toString());
        }
        propertyTree.insert(property);
        saveData(); // Save changes to file after adding
    }

    // ** Modified: Use BST's update method **
    public boolean updateProperty(Property updatedProperty) {
        boolean updated = propertyTree.update(updatedProperty);
        if (updated) {
            saveData(); // Save changes to file after updating
        }
        return updated;
    }

    // ** Modified: Use BST's delete method **
    public boolean deleteProperty(String id) {
        Property foundProperty = propertyTree.search(id); // Check if property exists before attempting delete (optional but good practice)
        if (foundProperty != null) {
            propertyTree.delete(id);
            saveData(); // Save changes to file after deleting
            return true;
        }
        return false; // Property not found
    }

    // ** Modified: Use BST's getAllProperties and the separate QuickSorter **
    public List<Property> getAllPropertiesSortedByPrice() {
        List<Property> sortedList = propertyTree.getAllProperties(); // Get all properties from BST

        // Use your custom Quick Sort implementation
        quickSorter.sortByPrice(sortedList); // Sort the list by price in place

        return sortedList;
    }

    // Optional: Add a method to get properties sorted by location, etc.
    // public List<Property> getAllPropertiesSortedByLocation() {
    //     List<Property> sortedList = propertyTree.getAllProperties();
    //     // Use your QuickSorter with a Comparator for location
    //     quickSorter.sortByLocation(sortedList); // Assuming you add sortByLocation to PropertyQuickSorter
    //     return sortedList;
    // }
}