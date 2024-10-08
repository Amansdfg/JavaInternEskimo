package kz.kalabay.locationsystem.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShareLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Location location;
    @ManyToOne(fetch = FetchType.EAGER)
    private User shareFriend;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccessType accessType;
}
