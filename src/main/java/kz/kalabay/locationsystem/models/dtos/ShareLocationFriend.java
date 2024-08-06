package kz.kalabay.locationsystem.models.dtos;

import kz.kalabay.locationsystem.models.AccessType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShareLocationFriend {
    private Long ownerId;
    private Long friendId;
    private Long toId;
}
