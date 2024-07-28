package cat.itacademy.barcelonactiva.PozoGuerrero.Hector.s05.t01.n01.S05T01N01.model.repository;

import cat.itacademy.barcelonactiva.PozoGuerrero.Hector.s05.t01.n01.S05T01N01.model.domain.domain.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepo extends JpaRepository<Branch,Integer> {
}
