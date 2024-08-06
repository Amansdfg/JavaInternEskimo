package kz.kalabay.locationsystem.models;

import javax.persistence.*;

@Entity

public class ShareLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Location location;
    @ManyToOne
    private User user;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccessType accessType;
    public enum AccessType{
        READ_ONLY,ADMIN
    }
}
