package cat.itacademy.barcelonactiva.PozoGuerrero.Hector.s05.t01.n01.S05T01N01.model.services;

import cat.itacademy.barcelonactiva.PozoGuerrero.Hector.s05.t01.n01.S05T01N01.model.dto.BranchDTO;

import java.util.List;

public interface BranchService {

    void addBranch(BranchDTO branchDTO);
    void updateBranch(BranchDTO branchDTO);
    void deleteBranch(int id);
    BranchDTO getOneBranch(int id);
    List<BranchDTO> getAllBranches();

}
