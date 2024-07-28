package cat.itacademy.barcelonactiva.PozoGuerrero.Hector.s05.t01.n01.S05T01N01.model.domain.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "branch")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idBranch;
    String name;
    String country;

    public Branch(String name, String country) {
        this.name = name;
        this.country = country;
    }
}
