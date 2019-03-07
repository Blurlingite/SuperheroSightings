/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.thecodetasticfour.superherosightingsgroup.dto;

import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author vishnukdawah
 */
public class Superpower {
   private int superpowerId;
   
   @NotEmpty(message = "You must supply a value for Superpower Name.")
   @Length(max = 45, message = "Superpower Name must be no more than 45 characters in length.")
   private String superpowerName;
   @NotEmpty(message = "You must supply a value for Superpower Description.")
   @Length(max = 200, message = "Superpower Description must be no more than 200 characters in length.")
   private String superpowerDescription;

    public int getSuperpowerId() {
        return superpowerId;
    }

    public void setSuperpowerId(int superpowerId) {
        this.superpowerId = superpowerId;
    }

    public String getSuperpowerName() {
        return superpowerName;
    }

    public void setSuperpowerName(String superpowerName) {
        this.superpowerName = superpowerName;
    }

    public String getSuperpowerDescription() {
        return superpowerDescription;
    }

    public void setSuperpowerDescription(String superpowerDescription) {
        this.superpowerDescription = superpowerDescription;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.superpowerId;
        hash = 67 * hash + Objects.hashCode(this.superpowerName);
        hash = 67 * hash + Objects.hashCode(this.superpowerDescription);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Superpower other = (Superpower) obj;
        if (this.superpowerId != other.superpowerId) {
            return false;
        }
        if (!Objects.equals(this.superpowerName, other.superpowerName)) {
            return false;
        }
        if (!Objects.equals(this.superpowerDescription, other.superpowerDescription)) {
            return false;
        }
        return true;
    }
   
   
}
