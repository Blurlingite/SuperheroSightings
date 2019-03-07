/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.thecodetasticfour.superherosightingsgroup.dto;

import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author vishnukdawah
 */
public class Person {
    
    private int personId;
   @NotEmpty(message = "You must supply a value for First Name.")
   @Length(max = 100, message = "First Name must be no more than 100 characters in length.")
    private String firstName;
   @NotEmpty(message = "You must supply a value for Last Name.")
   @Length(max = 100, message = "Last Name must be no more than 100 characters in length.")
    private String lastName;
   @NotNull(message = "You must choose either Superhero or Super-Villain.")
    private Boolean isHero;
   @NotEmpty(message = "You must supply a value for Person Description.")
   @Length(max = 100, message = "Person Description must be no more than 100 characters in length.")
    private String descriptionOfPerson;     // I created this variable to replace String Description because web page was not able to read this field -Vishnu
    private List<Superpower> listOfSuperpowers;
    private List<Organization> listOfOrganizations;
    
    private List<Integer> listOfSuperpowerIdsToPopulateSuperpowerListInPersonDTO;
    private List<Integer> listOfOrganizationIdsForOrganizationListInPersonDTO;



    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getIsHero() {
        return isHero;
    }

    public void setIsHero(Boolean isHero) {
        this.isHero = isHero;
    }

    public String getDescriptionOfPerson() {
        return descriptionOfPerson;
    }

    public void setDescriptionOfPerson(String descriptionOfPerson) {
        this.descriptionOfPerson = descriptionOfPerson;
    }

    public List<Superpower> getListOfSuperpowers() {
        return listOfSuperpowers;
    }

    public void setListOfSuperpowers(List<Superpower> listOfSuperpowers) {
        this.listOfSuperpowers = listOfSuperpowers;
    }

    public List<Organization> getListOfOrganizations() {
        return listOfOrganizations;
    }

    public void setListOfOrganizations(List<Organization> listOfOrganizations) {
        this.listOfOrganizations = listOfOrganizations;
    }
    
    
    public List<Integer> getListOfSuperpowerIdsToPopulateSuperpowerListInPersonDTO() {
        return listOfSuperpowerIdsToPopulateSuperpowerListInPersonDTO;
    }

    public void setListOfSuperpowerIdsToPopulateSuperpowerListInPersonDTO(List<Integer> listOfSuperpowerIdsToPopulateSuperpowerListInPersonDTO) {
        this.listOfSuperpowerIdsToPopulateSuperpowerListInPersonDTO = listOfSuperpowerIdsToPopulateSuperpowerListInPersonDTO;
    }

    public List<Integer> getListOfOrganizationIdsForOrganizationListInPersonDTO() {
        return listOfOrganizationIdsForOrganizationListInPersonDTO;
    }

    public void setListOfOrganizationIdsForOrganizationListInPersonDTO(List<Integer> listOfOrganizationIdsForOrganizationListInPersonDTO) {
        this.listOfOrganizationIdsForOrganizationListInPersonDTO = listOfOrganizationIdsForOrganizationListInPersonDTO;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + this.personId;
        hash = 19 * hash + Objects.hashCode(this.firstName);
        hash = 19 * hash + Objects.hashCode(this.lastName);
        hash = 19 * hash + Objects.hashCode(this.isHero);
        hash = 19 * hash + Objects.hashCode(this.descriptionOfPerson);
        hash = 19 * hash + Objects.hashCode(this.listOfSuperpowers);
        hash = 19 * hash + Objects.hashCode(this.listOfOrganizations);
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
        final Person other = (Person) obj;
        if (this.personId != other.personId) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.descriptionOfPerson, other.descriptionOfPerson)) {
            return false;
        }
        if (!Objects.equals(this.isHero, other.isHero)) {
            return false;
        }
        if (!Objects.equals(this.listOfSuperpowers, other.listOfSuperpowers)) {
            return false;
        }
        if (!Objects.equals(this.listOfOrganizations, other.listOfOrganizations)) {
            return false;
        }
        return true;
    }
    
    


    


    
    
    
    
    
    
    

}
