package print.Lora.React.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import print.Lora.Auth.Model.AppUser;

import java.io.Serializable;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class LikeId implements Serializable {

       @ManyToOne
       @JoinColumn(name = "app_user_id")  // adjust the column name if necessary
       private AppUser sender;
       @ManyToOne
       @JoinColumns({
               @JoinColumn(name = "idr", referencedColumnName = "idr"),

       })
       private ReactEntity react;
}
