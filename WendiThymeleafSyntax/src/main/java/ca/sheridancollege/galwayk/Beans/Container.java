package ca.sheridancollege.galwayk.Beans;

import java.io.Serializable;
 
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
 
/**
 * This class models a simple container with a specific
 * volume.  The container must have a name and the volume
 * of the container must be greater than 0.
 * 
 * @author Wendi Jollymore
 */
@Data
@NoArgsConstructor
public class Container implements Comparable<Container>, Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private int id = 0;
    @NonNull
    private String name = "container";
    private double volume = 1.0;
    
    /**
     * Construct a container with a specific name and
     * volume.  Name can't be empty 
     * and volume must be greater than 0.
     * 
     * @param name the name of this container
     * @param size the programmer-specified volume
     */
    public Container( String name, double size) {
        
        // make sure name and volume are valid
        setName(name);
        setVolume(size);
    }
    
    /**
     * Construct a container with a specific id, name, and
     * volume.  ID can't be 0 or less, name can't be empty 
     * and volume must be greater than 0.
     * 
     * @param id the unique container ID
     * @param name the name of this container
     * @param size the programmer-specified volume
     */
    public Container(int id, String name, double size) {
        
        // make sure id, name, and volume are valid
        setId(id);
        setName(name);
        setVolume(size);
    }
    
    /**
     * Attempts to place a valid id into this container's
     * id member.  The id can't be 0 or less, otherwise an 
     * exception is thrown.
     * 
     * @param name the programmer-specified container ID
     * @throws IllegalArgumentException if the IDis invalid
     */
    public void setId(int id) {
        
        // make sure id isn't invalid
        if (id > 0) {
            this.id = id;
        } else { // id is not valid
            throw new IllegalArgumentException("Error: must be greater than 0.");
        }
    }
        
    
    /**
     * Attempts to place a valid volume into this container's
     * volume member.  If the volume is not greater than 0, an
     * exception is thrown.
     * 
     * @param volume the programmer-specified volume
     * @throws IllegalArgumentException if the volume is invalid
     */
    public void setVolume(double volume) {
        
        // make sure volume is valid
        if (volume > 0) {
            this.volume = volume;
        } else { // volume is not valid
            throw new IllegalArgumentException("Error: size must be greater"
                + " than 0.");
        }
    }
    
    /**
     * Gets this container as a String.
     * 
     * @return this container as a formatted string
     */
    public String toString() {
        
        // formatted container name and volume
        return String.format("%s: %.2f", name, volume);
    }
    /**
     * Compares this container to another container.  Containers are compared by
     * volume.  If this container is larger than the other container, a 1 is 
     * returned.  If this container is smaller than the other container, a -1 is
     * returned.  If both containers have the same volume, a 0 is returned.
     * 
     * @param c the container to compare this container to
     * @return 1, -1, or 0 depending on how the containers compare
     */
    @Override
    public int compareTo(Container c) {
        
        if (this.volume > c.getVolume())
            return 1;
        else if (this.volume < c.getVolume())
            return -1;
        else 
            return 0;
    }
}