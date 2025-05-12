package com.yourdomain.realestate.model; // This must match your package: com.yourdomain.realestate.model

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a real estate property listing.
 */
public class Property implements Serializable {

    // Unique identifier for the property
    private String id;

    private String title; // e.g., "Luxury Apartment", "Family Home"
    private BigDecimal price; // Use BigDecimal for accurate currency representation
    private String location; // e.g., "New York, NY", "Suburban Area"
    private String description; // A longer description for the details page
    private String shortDescription; // A brief description for the listing view
    private String imagePath; // Path or URL to the main image file (e.g., "images/apt1.jpg")

    /**
     * Default constructor - generates a unique ID.
     * Used for creating new properties or deserialization.
     */
    public Property() {
        this.id = UUID.randomUUID().toString(); // Generate a unique ID using UUID
    }

    /**
     * Parameterized constructor to create a Property object with initial values.
     */
    public Property(String title, BigDecimal price, String location, String description, String shortDescription, String imagePath) {
        this(); // Call the default constructor to automatically generate the ID
        this.title = title;
        this.price = price;
        this.location = location;
        this.description = description;
        this.shortDescription = shortDescription;
        this.imagePath = imagePath;
    }

    // --- Getters ---
    // Provide access to the private fields

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getImagePath() {
        return imagePath;
    }

    // --- Setters ---
    // Allow modification of the fields. Setters are also needed for JSON deserialization.

    // We allow setting the ID, primarily for loading from file,
    // but generation is automatic on creation.
    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    // --- equals() and hashCode() ---
    // Important for comparing properties, especially by their unique ID.
    // Used in collections like Lists or when searching.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property property = (Property) o;
        return Objects.equals(id, property.id); // Properties are considered equal if their IDs match
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // --- toString() ---
    // Useful for debugging and logging, provides a string representation of the object.

    @Override
    public String toString() {
        return "Property{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", location='" + location + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}