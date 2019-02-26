/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.thecodetasticfour.superherosightingsgroup.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author plainjane
 */
public class User {
    private int userId;
    private Boolean isEnabled;
    private String userName;
    private String userPassword;
    private String firstName;
    private String lastName;
    private String email;
    private List<Organization> userOrganizations;
    private List<String> authorities = new ArrayList<>();

    private List<Integer> allOrganizationIdsToPopulateOrganizationListInUserDTO;
    
    private Boolean isAdmin;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Organization> getUserOrganizations() {
        return userOrganizations;
    }

    public void setUserOrganizations(List<Organization> userOrganizations) {
        this.userOrganizations = userOrganizations;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public List<Integer> getAllOrganizationIdsToPopulateOrganizationListInUserDTO() {
        return allOrganizationIdsToPopulateOrganizationListInUserDTO;
    }

    public void setAllOrganizationIdsToPopulateOrganizationListInUserDTO(List<Integer> allOrganizationIdsToPopulateOrganizationListInUserDTO) {
        this.allOrganizationIdsToPopulateOrganizationListInUserDTO = allOrganizationIdsToPopulateOrganizationListInUserDTO;
    }
    
    
    public void addAuthority(String authority) {
        authorities.add(authority);
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }


    
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.userId;
        hash = 89 * hash + Objects.hashCode(this.isEnabled);
        hash = 89 * hash + Objects.hashCode(this.userName);
        hash = 89 * hash + Objects.hashCode(this.userPassword);
        hash = 89 * hash + Objects.hashCode(this.firstName);
        hash = 89 * hash + Objects.hashCode(this.lastName);
        hash = 89 * hash + Objects.hashCode(this.email);
        hash = 89 * hash + Objects.hashCode(this.userOrganizations);
        hash = 89 * hash + Objects.hashCode(this.authorities);
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
        final User other = (User) obj;
        if (this.userId != other.userId) {
            return false;
        }
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        if (!Objects.equals(this.userPassword, other.userPassword)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.isEnabled, other.isEnabled)) {
            return false;
        }
        if (!Objects.equals(this.userOrganizations, other.userOrganizations)) {
            return false;
        }
        if (!Objects.equals(this.authorities, other.authorities)) {
            return false;
        }
        return true;
    }




    
    
}
