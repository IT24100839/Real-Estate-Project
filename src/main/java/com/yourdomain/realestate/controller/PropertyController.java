package com.yourdomain.realestate.controller; // Keep this package declaration

import com.yourdomain.realestate.model.Property; // Import your Property model
import com.yourdomain.realestate.service.PropertyFileStorageService; // Import your service
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping; // Import PostMapping for form submissions
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute; // Import ModelAttribute for form binding
import org.springframework.web.servlet.mvc.support.RedirectAttributes; // Import RedirectAttributes

import java.util.List;
import java.util.Optional;
import java.util.Collections; // Import Collections for reversing the list

/**
 * Controller to handle web requests related to Property listings.
 * Manages displaying properties, viewing details, and handling CRUD operations.
 */
@Controller // Marks this class as a Spring MVC Controller
public class PropertyController {

    private final PropertyFileStorageService propertyFileStorageService; // Use final for injected dependencies

    @Autowired // Spring automatically injects an instance of PropertyFileStorageService
    public PropertyController(PropertyFileStorageService propertyFileStorageService) {
        this.propertyFileStorageService = propertyFileStorageService;
    }

    /**
     * Handles requests to display the list of properties.
     * Supports sorting by price via a request parameter.
     *
     * @param model The Model object to pass data to the view.
     * @param sort  Optional request parameter for sorting ("price_asc", "price_desc").
     * @return The name of the Thymeleaf template to render.
     */
    @GetMapping("/properties") // Maps HTTP GET requests to /properties
    public String listProperties(Model model, @RequestParam(name = "sort", required = false) String sort) {
        List<Property> properties;

        if ("price_asc".equals(sort)) {
            properties = propertyFileStorageService.getAllPropertiesSortedByPrice();
        } else if ("price_desc".equals(sort)) {
            properties = propertyFileStorageService.getAllPropertiesSortedByPrice();
            // Since getAllPropertiesSortedByPrice returns ascending, reverse if desc is requested
            Collections.reverse(properties); // Use Collections.reverse
        } else {
            properties = propertyFileStorageService.getAllProperties(); // Default: from BST (sorted by ID)
        }

        model.addAttribute("properties", properties); // Add the list of properties to the model

        // Add a message attribute if present (e.g., from redirects after CRUD operations)
        // This allows displaying success/error messages on the listings page
        if (model.containsAttribute("message")) {
            model.addAttribute("message", model.getAttribute("message"));
        }
        if (model.containsAttribute("errorMessage")) {
            model.addAttribute("errorMessage", model.getAttribute("errorMessage"));
        }


        return "properties"; // Return the name of the Thymeleaf template (properties.html)
    }

    /**
     * Handles requests to view the details of a single property.
     *
     * @param id    The ID of the property from the URL path.
     * @param model The Model object to pass data to the view.
     * @return The name of the Thymeleaf template to render, or a redirect if property not found.
     */
    @GetMapping("/properties/{id}") // Maps HTTP GET requests to /properties/{id}
    public String viewPropertyDetails(@PathVariable("id") String id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Property> propertyOptional = propertyFileStorageService.getPropertyById(id);

        if (propertyOptional.isPresent()) {
            model.addAttribute("property", propertyOptional.get()); // Add the property to the model
            return "property-details"; // Return the name of the Thymeleaf template (property-details.html)
        } else {
            // Handle property not found
            redirectAttributes.addFlashAttribute("errorMessage", "Property not found with ID: " + id);
            return "redirect:/properties"; // Redirect back to the property listings page
        }
    }

    /**
     * Handles requests to display the form for adding a new property.
     *
     * @param model The Model object to pass data to the view.
     * @return The name of the Thymeleaf template for the add property form.
     */
    @GetMapping("/properties/new") // Maps HTTP GET requests to /properties/new
    public String showAddPropertyForm(Model model) {
        // Add an empty Property object to the model to bind form data
        model.addAttribute("property", new Property());
        return "add-property"; // Return the name of the Thymeleaf template (add-property.html)
    }

    /**
     * Handles the submission of the form for adding a new property.
     *
     * @param property The Property object populated with form data.
     * @param redirectAttributes Used to add flash attributes for the redirect.
     * @return A redirect URL.
     */
    @PostMapping("/properties") // Maps HTTP POST requests to /properties (form submission)
    public String addProperty(@ModelAttribute Property property, RedirectAttributes redirectAttributes) {
        // The Property object is automatically populated by Spring from form data
        propertyFileStorageService.addProperty(property); // Add the new property using the service
        redirectAttributes.addFlashAttribute("message", "Property added successfully!"); // Add a success message
        return "redirect:/properties"; // Redirect back to the property listings page
    }


    // TODO: Implement methods for Update and Delete operations

    /**
     * Handles requests to display the form for editing an existing property.
     *
     * @param id The ID of the property to edit.
     * @param model The Model object to pass data to the view.
     * @param redirectAttributes Used to add flash attributes for redirect if property not found.
     * @return The name of the Thymeleaf template for the edit property form, or a redirect.
     */
    @GetMapping("/properties/edit/{id}") // Maps HTTP GET requests to /properties/edit/{id}
    public String showEditPropertyForm(@PathVariable("id") String id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Property> propertyOptional = propertyFileStorageService.getPropertyById(id);

        if (propertyOptional.isPresent()) {
            model.addAttribute("property", propertyOptional.get()); // Add the existing property to the model
            return "edit-property"; // Return the name of the Thymeleaf template (edit-property.html)
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Property not found with ID: " + id + " for editing.");
            return "redirect:/properties"; // Redirect back to the listings page if property not found
        }
    }

    /**
     * Handles the submission of the form for updating an existing property.
     *
     * @param property The Property object populated with form data (including the ID).
     * @param redirectAttributes Used to add flash attributes for the redirect.
     * @return A redirect URL.
     */
    @PostMapping("/properties/update") // Maps HTTP POST requests to /properties/update (edit form submission)
    public String updateProperty(@ModelAttribute Property property, RedirectAttributes redirectAttributes) {
        // The Property object is automatically populated by Spring from form data, including the ID
        boolean updated = propertyFileStorageService.updateProperty(property); // Update the property using the service

        if (updated) {
            redirectAttributes.addFlashAttribute("message", "Property updated successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to update property with ID: " + property.getId());
        }

        return "redirect:/properties"; // Redirect back to the property listings page
    }

    /**
     * Handles requests to delete a property.
     *
     * @param id The ID of the property to delete.
     * @param redirectAttributes Used to add flash attributes for the redirect.
     * @return A redirect URL.
     */
    @PostMapping("/properties/delete/{id}") // Maps HTTP POST requests to /properties/delete/{id}
    public String deleteProperty(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
        boolean deleted = propertyFileStorageService.deleteProperty(id); // Delete the property using the service

        if (deleted) {
            redirectAttributes.addFlashAttribute("message", "Property deleted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete property with ID: " + id);
        }

        return "redirect:/properties"; // Redirect back to the property listings page
    }
}
