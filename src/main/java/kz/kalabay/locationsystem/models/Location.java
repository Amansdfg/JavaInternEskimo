package kz.kalabay.locationsystem.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @NotBlank
    private String name;
    @NotBlank
    @Pattern(regexp = "\\d+\\s[A-z]+\\s[A-z]+", message = "Address must be in US format (e.g., 123 Estemesova st)")
    private String address;
    @ManyToOne(fetch = FetchType.EAGER)
    private User owner;
}
