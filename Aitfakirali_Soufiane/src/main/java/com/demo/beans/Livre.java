package com.demo.beans;

import java.sql.Date;

public class Livre {
    int livre_id;
    String livre_auteur;
    String livre_titre;
    String imageUrl;
    Date date_creation;
    Category category;
    String description;
    boolean emprunte;

	public boolean isEmprunte() {
		return emprunte;
	}

	public void setEmprunte(boolean emprunte) {
		this.emprunte = emprunte;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Livre [livre_id=" + livre_id + ", livre_auteur=" + livre_auteur + ", livre_titre=" + livre_titre
				+ ", imageUrl=" + imageUrl + ", date_creation=" + date_creation + ", category_id=" + category + "]";
	}

	public int getLivre_id() {
        return livre_id;
    }

    public void setLivre_id(int livre_id) {
        this.livre_id = livre_id;
    }

    public String getLivre_auteur() {
        return livre_auteur;
    }

    public void setLivre_auteur(String livre_auteur) {
        this.livre_auteur = livre_auteur;
    }

    public String getLivre_titre() {
        return livre_titre;
    }

    public void setLivre_titre(String livre_titre) {
        this.livre_titre = livre_titre;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

	public Category getCategory() {
		return category;
	}


}
