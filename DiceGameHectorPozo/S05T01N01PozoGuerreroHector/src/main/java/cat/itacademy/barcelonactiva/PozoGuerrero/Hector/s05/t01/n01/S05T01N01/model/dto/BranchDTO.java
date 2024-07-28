package cat.itacademy.barcelonactiva.PozoGuerrero.Hector.s05.t01.n01.S05T01N01.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Data
@NoArgsConstructor
public class BranchDTO implements Serializable {
    private int id;
    private String name;
    private String country;
    private String type;
    private static final List<String> euCountries = new ArrayList<>(Arrays.asList("portugal","spain","france",
            "austria","germany","italy","greece", "belgium", "bulgaria", "croatia", "republic of cyprus",
            "czech republic", "denmark", "estonia", "finland", "hungary", "ireland", "latvia", "lithuania",
            "luxembourg", "malta", "netherlands", "poland", "romania", "slovakia", "slovenia", "sweden"));

    public BranchDTO(String name, String country) {
        this.name = name;
        this.country = country;
        this.type = country != null && euCountries.contains(country.toLowerCase()) ? "EU" : "NOT EU";
    }
}
