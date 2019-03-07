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
    private Boolean isAdmin;
    private String userName;
    private String userPassword;
    private String firstName;
    private String lastName;
    private String email;
    private List<Organization> userOrganizations;
    private List<String> authorities = new ArrayList<>();

    private List<Integer> allOrganizationIdsToPopulateOrganizationListInUserDTO;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
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
    
//    public void removeAuthority(String authority) {
//        authorities.remove(authority);
//    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.userId;
        hash = 59 * hash + Objects.hashCode(this.isAdmin);
        hash = 59 * hash + Objects.hashCode(this.userName);
        hash = 59 * hash + Objects.hashCode(this.userPassword);
        hash = 59 * hash + Objects.hashCode(this.firstName);
        hash = 59 * hash + Objects.hashCode(this.lastName);
        hash = 59 * hash + Objects.hashCode(this.email);
        hash = 59 * hash + Objects.hashCode(this.userOrganizations);
        hash = 59 * hash + Objects.hashCode(this.authorities);
        hash = 59 * hash + Objects.hashCode(this.allOrganizationIdsToPopulateOrganizationListInUserDTO);
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
        if (!Objects.equals(this.isAdmin, other.isAdmin)) {
            return false;
        }
        if (!Objects.equals(this.userOrganizations, other.userOrganizations)) {
            return false;
        }
        if (!Objects.equals(this.authorities, other.authorities)) {
            return false;
        }
        if (!Objects.equals(this.allOrganizationIdsToPopulateOrganizationListInUserDTO, other.allOrganizationIdsToPopulateOrganizationListInUserDTO)) {
            return false;
        }
        return true;
    }
    

 



    
    
}
