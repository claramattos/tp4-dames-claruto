/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.jeu_dames;

/**
 *
 * @author Clara
 */
public class Peon {
    protected int x;
    protected int y;
    protected boolean couleur;
    protected TableauDeJeu partie;
    
    public Peon (int x, int y, boolean couleur, TableauDeJeu partie) {
        this.x = x;
        this.y = y;
        this.couleur = couleur;
        this.partie = partie;
    }
    
    public boolean verifieVoisin(int newX, int newY) {
        int middleX = (this.x + newX) / 2;
        int middleY = (this.y + newY) / 2;
        Peon peonVoisin = partie.getPeon(middleX, middleY);

        return peonVoisin != null && peonVoisin.couleur != this.couleur;
    } 
    
    public boolean verifieDeplacement(int newX, int newY) {
        int deltaX = Math.abs(newX - this.x);
        int deltaY = newY - this.y;

        // Peon peuvent se deplacer en diagonel et avant
        if (this.couleur) { // Noir = true
            return deltaX == 1 && deltaY == 1;
        } else { // Blanc = false
            return deltaX == 1 && deltaY == -1;
        } 
    }
    
    public boolean verifieDeplacementApresPrise(int newX, int newY) {
        int deltaX = Math.abs(newX - this.x);
        int deltaY = newY - this.y;

        // Peon peuvent se deplacer en diagonel et avant
        if (this.couleur) { // Noir = true
            return deltaX == 2 && deltaY == 2;
        } else { // Blanc = false
            return deltaX == 2 && deltaY == -2;
        }
        
    }
   
    public boolean deplacer(int newX, int newY) {
        // Verifie si la position est valide et se deplace
        if (verifieDeplacement(newX, newY)) {
            this.x = newX;
            this.y = newY;
            return true;
        }
        return false;
    }     
    
    public boolean deplacerApresPrise(int newX, int newY) {
        // Verifie si la position est valide et se deplace
        if (verifieDeplacementApresPrise(newX, newY)) {
            this.x = newX;
            this.y = newY;
            return true;
        }
        return false;
    }    
    
    public void deplacerTest(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }
    
    public boolean prise(int newX, int newY) {
        if (verifieVoisin(newX, newY)) {
            int middleX = (this.x + newX) / 2;
            int middleY = (this.y + newY) / 2;

            // Effacer le peon enemie
            partie.removePeon(middleX, middleY);

            // deplace le peon courrant
            return deplacerApresPrise(newX, newY);
        }
        return false;
    }
    
    public boolean estPromu() {
        // Est arrivee à la fin du tableau
        if (this.couleur && this.y == 9 || !this.couleur && this.y == 0) {
            return true;
        }
        return false;
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isCouleur() {
        return couleur;
    }

    public void setCouleur(boolean couleur) {
        this.couleur = couleur;
    }
    
}
