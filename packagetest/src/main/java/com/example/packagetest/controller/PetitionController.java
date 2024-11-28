package com.example.packagetest.controller;

import com.example.packagetest.dao.PetitionDAO;
import com.example.packagetest.dao.SignDAO;
import com.example.packagetest.entity.Petition;
import com.example.packagetest.entity.Sign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PetitionController {

    private PetitionDAO petitionDAO;
    private SignDAO signDAO;

    @Autowired
    public PetitionController(PetitionDAO petitionDAO, SignDAO signDAO) {
        this.petitionDAO = petitionDAO;
        this.signDAO = signDAO;
    }

    @GetMapping("/")
    public String index(Model model) {

        List<Petition> petitions = petitionDAO.findAll();
        model.addAttribute("petitions", petitions);
        return "list-petitions";
    }

    @GetMapping("/petitions")
    public String listPetitions(Model model) {
        List<Petition> petitions = petitionDAO.findAll();
        model.addAttribute("petitions", petitions);
        return "list-petitions";
    }

    // Render the search page
    @GetMapping("/search-petitions")
    public String searchPage() {
        return "search-petitions";
    }

    // Handle search requests and display results
    @GetMapping("/search-petitions/results")
    public String searchPetitions(@RequestParam("searchTerm") String searchTerm, Model model) {
        List<Petition> searchResults = petitionDAO.findBySearchTerm(searchTerm);
        model.addAttribute("searchResults", searchResults);
        return "search-results";
    }
    @GetMapping("/view-petition")
    public String viewPetition(@RequestParam("id") Long petitionId, Model model) {
        // Fetch the petition details
        Petition petition = petitionDAO.findById(petitionId);
        model.addAttribute("petition", petition);

        // Fetch associated signs
        List<Sign> signs = signDAO.findAllByPetitionId(petitionId);
        model.addAttribute("signs", signs);

        int signCount = signDAO.getSignCountById(petitionId);
        model.addAttribute("signCount", signCount);

        return "view-petition";
    }

    // Render the create petition page
    @GetMapping("/create-petition")
    public String createPetitionPage() {
        return "create-petition";
    }

    // Handle the form submission
    @PostMapping("/create-petition")
    public String createPetition(@RequestParam("author") String author,
                                 @RequestParam("title") String title,
                                 @RequestParam("description") String description,
                                 @RequestParam("email") String email) {

        // Save the new petition
        Petition newPetition = new Petition(title, description, author, email);
        petitionDAO.save(newPetition);

        // Redirect to the petitions list
        return "redirect:/petitions";
    }

    @PostMapping("/sign-petition")
    public String signPetition(@RequestParam("petitionId") Long petitionId,
                               @RequestParam("signerName") String signerName,
                               @RequestParam("signerComment") String signerComment) {

        // Create and save the new sign
        Sign newSign = new Sign(signerName, signerComment, petitionId);
        signDAO.save(newSign);

        // Redirect back to the view petition page
        return "redirect:/view-petition?id=" + petitionId;
    }
}
