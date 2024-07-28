package cat.itacademy.barcelonactiva.PozoGuerrero.Hector.s05.t01.n01.S05T01N01.controllers;

import cat.itacademy.barcelonactiva.PozoGuerrero.Hector.s05.t01.n01.S05T01N01.model.dto.BranchDTO;
import cat.itacademy.barcelonactiva.PozoGuerrero.Hector.s05.t01.n01.S05T01N01.model.services.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/branches")
public class BranchController {
    @Autowired
    private BranchService branchService;

    @GetMapping("/home")
    public String index (Model model){
        model.addAttribute("branches", branchService.getAllBranches());
        return "home";
    }

    @GetMapping("/addBranchForm")
    public String addBranchForm (Model model){
        BranchDTO branchDTO = new BranchDTO();
        model.addAttribute("newBranch", branchDTO);
        return "addBranchForm";
    }

    @PostMapping("/add")
    public String addBranch(@ModelAttribute("newBranch") BranchDTO branchDTO) {
        branchService.addBranch(branchDTO);
        return "redirect:/branches/home";
    }
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable int id, Model model) {
        BranchDTO branchDTO = branchService.getOneBranch(id);
        model.addAttribute("branchToUpdate", branchDTO);
        return "editBranchForm";
    }

    @PostMapping("/update")
    public String updateBranch(@ModelAttribute("branchToUpdate") BranchDTO branchDTO) {
        branchService.updateBranch(branchDTO);
        return "redirect:/branches/home";
    }


    @GetMapping("/delete/{id}")
    public String deleteBranch(@PathVariable int id){
        branchService.deleteBranch(id);
        return "redirect:/branches/home";
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<BranchDTO> getOneBranch(@PathVariable int id){
        BranchDTO branchDTO = branchService.getOneBranch(id);
        return ResponseEntity.ok().body(branchDTO);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<BranchDTO>> getAll(){
        List <BranchDTO> list = branchService.getAllBranches();
        return ResponseEntity.ok(list);
    }
}
