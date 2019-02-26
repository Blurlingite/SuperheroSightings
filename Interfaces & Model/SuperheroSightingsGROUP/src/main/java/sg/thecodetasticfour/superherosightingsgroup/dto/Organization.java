/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.thecodetasticfour.superherosightingsgroup.dto;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author Blake
 */
public class Organization {
    private int organizationId ;
    private String organizationName;
    private String organizationDescription;
    private String organizationStreet;
    private String organizationCity;
    private String organizationState;
    private String organizationZipcode;
    private String organizationCountry;
    private Boolean isItAHeroOrganization;
    private List<Person> listOfPersons;
    private List<User> organizationAdmins;

    private List<Integer> allOrganizationIdsForOrganizationListInOrganizationDTO;
    private List<String> allUserNamesForUserListInOrganizationDTO;



    
    
    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationDescription() {
        return organizationDescription;
    }

    public void setOrganizationDescription(String organizationDescription) {
        this.organizationDescription = organizationDescription;
    }

    public String getOrganizationStreet() {
        return organizationStreet;
    }

    public void setOrganizationStreet(String organizationStreet) {
        this.organizationStreet = organizationStreet;
    }

    public String getOrganizationCity() {
        return organizationCity;
    }

    public void setOrganizationCity(String organizationCity) {
        this.organizationCity = organizationCity;
    }

    public String getOrganizationState() {
        return organizationState;
    }

    public void setOrganizationState(String organizationState) {
        this.organizationState = organizationState;
    }

    public String getOrganizationZipcode() {
        return organizationZipcode;
    }

    public void setOrganizationZipcode(String organizationZipcode) {
        this.organizationZipcode = organizationZipcode;
    }

    public String getOrganizationCountry() {
        return organizationCountry;
    }

    public void setOrganizationCountry(String organizationCountry) {
        this.organizationCountry = organizationCountry;
    }

    public Boolean getIsItAHeroOrganization() {
        return isItAHeroOrganization;
    }

    public void setIsItAHeroOrganization(Boolean isItAHeroOrganization) {
        this.isItAHeroOrganization = isItAHeroOrganization;
    }

    public List<Person> getListOfPersons() {
        return listOfPersons;
    }

    public void setListOfPersons(List<Person> listOfPersons) {
        this.listOfPersons = listOfPersons;
    }

    public List<User> getOrganizationAdmins() {
        return organizationAdmins;
    }

    public void setOrganizationAdmins(List<User> organizationAdmins) {
        this.organizationAdmins = organizationAdmins;
    }
    
    
    public List<Integer> getAllOrganizationIdsForOrganizationListInOrganizationDTO() {
        return allOrganizationIdsForOrganizationListInOrganizationDTO;
    }

    public void setAllOrganizationIdsForOrganizationListInOrganizationDTO(List<Integer> allOrganizationIdsForOrganizationListInOrganizationDTO) {
        this.allOrganizationIdsForOrganizationListInOrganizationDTO = allOrganizationIdsForOrganizationListInOrganizationDTO;
    }

    public List<String> getAllUserNamesForUserListInOrganizationDTO() {
        return allUserNamesForUserListInOrganizationDTO;
    }

    public void setAllUserNamesForUserListInOrganizationDTO(List<String> allUserNamesForUserListInOrganizationDTO) {
        this.allUserNamesForUserListInOrganizationDTO = allUserNamesForUserListInOrganizationDTO;
    }




    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.organizationId;
        hash = 17 * hash + Objects.hashCode(this.organizationName);
        hash = 17 * hash + Objects.hashCode(this.organizationDescription);
        hash = 17 * hash + Objects.hashCode(this.organizationStreet);
        hash = 17 * hash + Objects.hashCode(this.organizationCity);
        hash = 17 * hash + Objects.hashCode(this.organizationState);
        hash = 17 * hash + Objects.hashCode(this.organizationZipcode);
        hash = 17 * hash + Objects.hashCode(this.organizationCountry);
        hash = 17 * hash + Objects.hashCode(this.isItAHeroOrganization);
        hash = 17 * hash + Objects.hashCode(this.listOfPersons);
        hash = 17 * hash + Objects.hashCode(this.organizationAdmins);
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
        final Organization other = (Organization) obj;
        if (this.organizationId != other.organizationId) {
            return false;
        }
        if (!Objects.equals(this.organizationName, other.organizationName)) {
            return false;
        }
        if (!Objects.equals(this.organizationDescription, other.organizationDescription)) {
            return false;
        }
        if (!Objects.equals(this.organizationStreet, other.organizationStreet)) {
            return false;
        }
        if (!Objects.equals(this.organizationCity, other.organizationCity)) {
            return false;
        }
        if (!Objects.equals(this.organizationState, other.organizationState)) {
            return false;
        }
        if (!Objects.equals(this.organizationZipcode, other.organizationZipcode)) {
            return false;
        }
        if (!Objects.equals(this.organizationCountry, other.organizationCountry)) {
            return false;
        }
        if (!Objects.equals(this.isItAHeroOrganization, other.isItAHeroOrganization)) {
            return false;
        }
        if (!Objects.equals(this.listOfPersons, other.listOfPersons)) {
            return false;
        }
        if (!Objects.equals(this.organizationAdmins, other.organizationAdmins)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
    

}