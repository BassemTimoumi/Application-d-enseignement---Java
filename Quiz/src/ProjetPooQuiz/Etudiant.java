package ProjetPooQuiz;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Etudiant {
    private String nom;
    private String prenom;
    private int nbgrp;
    private String cin;
    private ArrayList<String> modulesRepondus; 
    private HashMap<String, Integer> scores;

    public Etudiant() {
        modulesRepondus = new ArrayList<>();
        scores = new HashMap<>();
    }

    public void creeretudiant() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Donnez le nom : ");
        this.nom = sc.nextLine();
        System.out.println("Donnez le prenom : ");
        this.prenom = sc.nextLine();
        System.out.println("Donnez le numéro de groupe : ");
        this.nbgrp = sc.nextInt();
        sc.nextLine();	
    }

    
   
   
    public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getNbgrp() {
		return nbgrp;
	}

	public void setNbgrp(int nbgrp) {
		this.nbgrp = nbgrp;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public void setModulesRepondus(ArrayList<String> modulesRepondus) {
		this.modulesRepondus = modulesRepondus;
	}

	public void setScores(HashMap<String, Integer> scores) {
		this.scores = scores;
	}

	public ArrayList<String> getModulesRepondus() {
        return modulesRepondus;
    }

    public HashMap<String, Integer> getScores() {
        return scores;
    }

    public void ajouterModuleRepondu(String module) {
        if (!modulesRepondus.contains(module)) {
            modulesRepondus.add(module);
        }
    }
    public boolean aDejaRepondu(String module) {
        return modulesRepondus.contains(module);
    }
    public void enregistrerScore(String module, int score) {
        scores.put(module, score);
    }

    public void afficherScores(ArrayList<Quiz> quizTable) {
        if (scores.isEmpty()) {
            System.out.println("Aucun score disponible.");
            return;
        }
        System.out.println("Scores obtenus :");
        for (String module : scores.keySet()) {
            int score = scores.get(module);
            int totalPoints = 0;
            for (Quiz quiz : quizTable) {
                if (quiz.getTheme().equals(module)) {
                    totalPoints = quiz.getNbqcm();
                    break;
                }
            }
            System.out.println(module + " : " + score + " / " + totalPoints);
        }
    }


}
