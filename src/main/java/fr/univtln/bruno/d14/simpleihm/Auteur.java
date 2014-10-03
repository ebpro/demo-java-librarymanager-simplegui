package fr.univtln.bruno.d14.simpleihm;

/**
 * Created by bruno on 03/10/14.
 */
public class Auteur {
    public static int nbAuteur = 0;
    public final int ID;
    private String nom;
    private String prenom;
    private String biographie;

    public static class AuteurBuilder {
        private String nom;
        private String prenom;
        private String biographie = null;

        public AuteurBuilder setNom(String nom) {
            this.nom = nom;
            return this;
        }

        public AuteurBuilder setPrenom(String prenom) {
            this.prenom = prenom;
            return this;
        }

        public AuteurBuilder setBiographie(String biographie) {
            this.biographie = biographie;
            return this;
        }

        public Auteur createAuteur() {
            return new Auteur(nom, prenom, biographie);
        }
    }

    public String getBiographie() {
        return biographie;
    }

    private Auteur(String nom, String prenom, String biographie) {
        this.ID = nbAuteur++;
        this.nom = nom;
        this.prenom = prenom;
        this.biographie = biographie;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    @Override
    public String toString() {
        return nom + ", " + prenom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Auteur auteur = (Auteur) o;

        if (ID != auteur.ID) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return ID;
    }
}
