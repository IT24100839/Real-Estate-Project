package lk.ac.sliit.se1020.pgno7.realestateportal.controller;

import lk.ac.sliit.se1020.pgno7.realestateportal.data.AdminFileHandler;
import lk.ac.sliit.se1020.pgno7.realestateportal.model.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

@Controller
public class AdminController {

    private AdminFileHandler adminFileHandler = new AdminFileHandler();

    // Handlers for Register (GET and POST), List (GET), and Edit (GET) remain here...

    @GetMapping("/admin/register")
    public String showRegistrationForm() {
        return "registerAdmin";
    }

    @PostMapping("/admin/register")
    public String registerAdmin(
            @RequestParam("userId") String userId,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("name") String name,
            @RequestParam("contactInfo") String contactInfo,
            RedirectAttributes redirectAttributes
    ) {
        // Basic Input Validation
        if (userId == null || userId.trim().isEmpty() || username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "User ID, Username, and Password are required.");
            return "redirect:/admin/register";
        }

        // --- Check if User ID or Username already exists ---
        List<Admin> existingAdmins = adminFileHandler.readAdminsFromFile();
        // Corrected typo here: existingAdmins -> existingAdmins
        boolean userExists = existingAdmins.stream()
                .anyMatch(admin -> admin.getUserId().equals(userId) || admin.getUsername().equals(username));

        if (userExists) {
            redirectAttributes.addFlashAttribute("message", "User ID or Username already exists.");
            return "redirect:/admin/register";
        }

        // Create New Admin Object
        Admin newAdmin = new Admin(userId, username, password, name, contactInfo);

        // Add New Admin to the List and Save
        existingAdmins.add(newAdmin);
        adminFileHandler.writeAdminsToFile(existingAdmins);

        redirectAttributes.addFlashAttribute("message", "Admin registered successfully!");
        return "redirect:/admin/register";
    }

    @GetMapping("/admin/list")
    public String listAdmins(Model model) {
        List<Admin> admins = adminFileHandler.readAdminsFromFile();
        model.addAttribute("admins", admins);
        return "adminList";
    }

    // Handler for Displaying Edit Form (GET request)
    @GetMapping("/admin/edit")
    public String showEditForm(@RequestParam("userId") String userId, Model model, RedirectAttributes redirectAttributes) {
        Optional<Admin> adminToEdit = adminFileHandler.findAdminById(userId);

        if (adminToEdit.isPresent()) {
            model.addAttribute("admin", adminToEdit.get());
            return "editAdmin";
        } else {
            redirectAttributes.addFlashAttribute("message", "Admin with ID " + userId + " not found.");
            return "redirect:/admin/list";
        }
    }

    // Handler for Processing Update Form Submission (POST request)
    @PostMapping("/admin/update")
    public String updateAdmin(
            @RequestParam("userId") String userId,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("name") String name,
            @RequestParam("contactInfo") String contactInfo,
            RedirectAttributes redirectAttributes
    ) {
        if (userId == null || userId.trim().isEmpty() || username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "User ID, Username, and Password are required for update.");
            return "redirect:/admin/edit?userId=" + userId;
        }

        List<Admin> admins = adminFileHandler.readAdminsFromFile();
        Optional<Admin> adminOptional = admins.stream()
                .filter(admin -> admin.getUserId().equals(userId))
                .findFirst();

        if (adminOptional.isPresent()) {
            Admin adminToUpdate = adminOptional.get();

            adminToUpdate.setUsername(username);
            adminToUpdate.setPassword(password);
            adminToUpdate.setName(name);
            adminToUpdate.setContactInfo(contactInfo);

            adminFileHandler.writeAdminsToFile(admins);

            redirectAttributes.addFlashAttribute("message", "Admin updated successfully!");
            return "redirect:/admin/list";
        } else {
            redirectAttributes.addFlashAttribute("message", "Error: Admin with ID " + userId + " not found for update.");
            return "redirect:/admin/list";
        }
    }

    // --- New Handler for Deleting an Admin (GET request to /admin/delete) ---

    @GetMapping("/admin/delete")
    public String deleteAdmin(@RequestParam("userId") String userId, RedirectAttributes redirectAttributes) {
        // Use the file handler method to perform the deletion
        boolean deleted = adminFileHandler.deleteAdminById(userId);

        if (deleted) {
            redirectAttributes.addFlashAttribute("message", "Admin with ID " + userId + " deleted successfully.");
        } else {
            redirectAttributes.addFlashAttribute("message", "Error: Admin with ID " + userId + " not found for deletion.");
        }

        // Redirect back to the admin list page
        return "redirect:/admin/list";
    }
}