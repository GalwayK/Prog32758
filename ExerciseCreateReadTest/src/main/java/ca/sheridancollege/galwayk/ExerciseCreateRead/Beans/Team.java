package ca.sheridancollege.galwayk.ExerciseCreateRead.Beans;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Team implements Serializable
{
 
    private static final long serialVersionUID = 1L;
   
    private String id;
    private String city;
    private String name;
 
    public String toString() 
    {
        return city + " " + name;
    }
 
}
