package com.demo.beans;

public class Etudiant {
    int etudiant_id;
    String etudiant_nom;
    String etudiant_prenom;
    String etudiant_email;
    String etudiant_adresse;
    String etudiant_telephone;
    String etudiant_password;
    String etudiant_filiere;
    String etudiant_role;
    int etudiant_age;

    @Override
    public String toString() {
        return "Etudiant{" +
                "etudiant_id=" + etudiant_id +
                ", etudiant_nom='" + etudiant_nom + '\'' +
                ", etudiant_prenom='" + etudiant_prenom + '\'' +
                ", etudiant_email='" + etudiant_email + '\'' +
                ", etudiant_adresse='" + etudiant_adresse + '\'' +
                ", etudiant_telephone='" + etudiant_telephone + '\'' +
                ", etudiant_password='" + etudiant_password + '\'' +
                ", etudiant_filiere='" + etudiant_filiere + '\'' +
                ", etudiant_role=" + etudiant_role +
                ", etudiant_age=" + etudiant_age +
                '}';
    }

    public int getEtudiant_id() {
        return etudiant_id;
    }

    public void setEtudiant_id(int etudiant_id) {
        this.etudiant_id = etudiant_id;
    }

    public String getEtudiant_nom() {
        return etudiant_nom;
    }

    public void setEtudiant_nom(String etudiant_nom) {
        this.etudiant_nom = etudiant_nom;
    }

    public String getEtudiant_prenom() {
        return etudiant_prenom;
    }

    public void setEtudiant_prenom(String etudiant_prenom) {
        this.etudiant_prenom = etudiant_prenom;
    }

    public String getEtudiant_email() {
        return etudiant_email;
    }

    public void setEtudiant_email(String etudiant_email) {
        this.etudiant_email = etudiant_email;
    }

    public String getEtudiant_adresse() {
        return etudiant_adresse;
    }

    public void setEtudiant_adresse(String etudiant_adresse) {
        this.etudiant_adresse = etudiant_adresse;
    }

    public String getEtudiant_telephone() {
        return etudiant_telephone;
    }

    public void setEtudiant_telephone(String etudiant_telephone) {
        this.etudiant_telephone = etudiant_telephone;
    }

    public String getEtudiant_password() {
        return etudiant_password;
    }

    public void setEtudiant_password(String etudiant_password) {
        this.etudiant_password = etudiant_password;
    }

    public String getEtudiant_filiere() {
        return etudiant_filiere;
    }

    public void setEtudiant_filiere(String etudiant_filiere) {
        this.etudiant_filiere = etudiant_filiere;
    }

    public String getEtudiant_role() {
        return etudiant_role;
    }

    public void setEtudiant_role(String etudiant_role) {
        this.etudiant_role = etudiant_role;
    }

    public int getEtudiant_age() {
        return etudiant_age;
    }

    public void setEtudiant_age(int etudiant_age) {
        this.etudiant_age = etudiant_age;
    }
}
