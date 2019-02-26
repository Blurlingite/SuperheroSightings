/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.thecodetasticfour.superherosightingsgroup.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author zissah
 */
public class Sighting {
 private int SightingId;
 private Boolean isHeroSighting;
 
 // correct format that gets to the edit page
// @DateTimeFormat(pattern="yyyy-MM-ddTHH:mm")  
 private LocalDateTime sightingDate;
 
  @DateTimeFormat(pattern="yyyy-MM-dd")  
 private LocalDate justTheSightingDate;
 private Location Location;
 private Person Person;

    public int getSightingId() {
        return SightingId;
    }

    public void setSightingId(int SightingId) {
        this.SightingId = SightingId;
    }

    public Boolean getIsHeroSighting() {
        return isHeroSighting;
    }

    public void setIsHeroSighting(Boolean isHeroSighting) {
        this.isHeroSighting = isHeroSighting;
    }

    public LocalDateTime getSightingDate() {
        return sightingDate;
    }

    public void setSightingDate(LocalDateTime sightingDate) {
        this.sightingDate = sightingDate;
    }

    public Location getLocation() {
        return Location;
    }

    public void setLocation(Location Location) {
        this.Location = Location;
    }

    public Person getPerson() {
        return Person;
    }

    public void setPerson(Person Person) {
        this.Person = Person;
    }

    public LocalDate getJustTheSightingDate() {
        return justTheSightingDate;
    }

    public void setJustTheSightingDate(LocalDate justTheSightingDate) {
        this.justTheSightingDate = justTheSightingDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.SightingId;
        hash = 79 * hash + Objects.hashCode(this.isHeroSighting);
        hash = 79 * hash + Objects.hashCode(this.sightingDate);
        hash = 79 * hash + Objects.hashCode(this.justTheSightingDate);
        hash = 79 * hash + Objects.hashCode(this.Location);
        hash = 79 * hash + Objects.hashCode(this.Person);
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
        final Sighting other = (Sighting) obj;
        if (this.SightingId != other.SightingId) {
            return false;
        }
        if (!Objects.equals(this.isHeroSighting, other.isHeroSighting)) {
            return false;
        }
        if (!Objects.equals(this.sightingDate, other.sightingDate)) {
            return false;
        }
        if (!Objects.equals(this.justTheSightingDate, other.justTheSightingDate)) {
            return false;
        }
        if (!Objects.equals(this.Location, other.Location)) {
            return false;
        }
        if (!Objects.equals(this.Person, other.Person)) {
            return false;
        }
        return true;
    }


 
 
 
}
