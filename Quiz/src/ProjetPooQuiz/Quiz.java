package ProjetPooQuiz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Quiz {
    private String theme;
    private String auteur;
    private int nbqcm;
    private ArrayList<Qcm> qcmTable;
    private HashMap<Etudiant, Integer[]> reponsesEtudiants;

    public Quiz() {
        this.qcmTable = new ArrayList<>();
        this.reponsesEtudiants = new HashMap<>();
    }

    public void creerquiz() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Donnez le thème : ");
        this.theme = sc.nextLine();
        System.out.println("Donnez l'auteur : ");
        this.auteur = sc.nextLine();
        System.out.println("Nombre de QCM dans le quiz : ");
        this.nbqcm = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < nbqcm; i++) {
            System.out.println("Création du QCM #" + (i + 1));
            Qcm qcm = new Qcm();
            qcm.creerqcm();
            qcmTable.add(qcm);
        }
    }

    public void visualiserquiz() {
        System.out.println("Thème : " + theme);
        System.out.println("Auteur : " + auteur);
        for (int i = 0; i < qcmTable.size(); i++) {
            System.out.println("QCM #" + (i + 1));
            System.out.println(qcmTable.get(i).toString(true));
        }
    }

    public void ajouterQcm(Qcm qcm) {
        qcmTable.add(qcm);
    }

    public void enregistrerReponses(Etudiant etudiant, Integer[] reponses) {
        if (reponses == null || reponses.length != qcmTable.size()) {
            System.out.println("Erreur : les réponses fournies ne correspondent pas au nombre de questions du quiz.");
            return;
        }
        reponsesEtudiants.put(etudiant, reponses);
    }

    public void enregistrerScoreEtudiant(Etudiant etudiant, int score) {
        if (!reponsesEtudiants.containsKey(etudiant)) {
            reponsesEtudiants.put(etudiant, new Integer[qcmTable.size()]);
        }
        etudiant.enregistrerScore(this.theme, score);
    }


    public void visualiserEtudiantsAvecScores() {
        System.out.println("Liste des étudiants ayant passé le quiz '" + theme + "' :");

        for (Etudiant etudiant : reponsesEtudiants.keySet()) {
            Integer[] reponses = reponsesEtudiants.get(etudiant);
            int score = 0;

            if (reponses != null) {
                for (int i = 0; i < qcmTable.size(); i++) {
                    if (reponses.length > i && reponses[i] != null) {
                        if (reponses[i] == qcmTable.get(i).getCorrectOption()) {
                            score++;
                        }
                    }
                }
            }

            System.out.println("- " + etudiant.getNom() + " " + etudiant.getPrenom() + " : " + score + " / " + qcmTable.size());
        }
    }
    public void visualiserStatistiquesQcm() {
        if (reponsesEtudiants.isEmpty()) {
            System.out.println("Aucun étudiant n'a encore répondu au quiz '" + theme + "'.");
            return;
        }

        System.out.println("\nStatistiques pour chaque QCM du quiz '" + theme + "':");

        for (int i = 0; i < qcmTable.size(); i++) {
            Qcm qcm = qcmTable.get(i);
            int correctCount = 0;
            int incorrectCount = 0;

            for (Etudiant etudiant : reponsesEtudiants.keySet()) {
                Integer[] reponses = reponsesEtudiants.get(etudiant);

                if (reponses != null && reponses.length > i && reponses[i] != null) {
                    if (reponses[i] == qcm.getCorrectOption()) {
                        correctCount++;
                    } else {
                        incorrectCount++;
                    }
                }
            }

            int totalResponses = correctCount + incorrectCount;
            double correctPercentage = 0.0;
            double incorrectPercentage = 0.0;

            if (totalResponses > 0) {
                correctPercentage = ((double) correctCount / totalResponses) * 100;
                incorrectPercentage = 100 - correctPercentage;
            }

            System.out.println("QCM #" + (i + 1) + " : " + qcm.getQuestion());
            System.out.println(" - Réponses justes : " + String.format("%.2f", correctPercentage) + "%");
            System.out.println(" - Réponses fausses : " + String.format("%.2f", incorrectPercentage) + "%");
        }
    }
    public void afficherClassement() {
        System.out.println("\n--- Classement des meilleurs scores ---");

        // Si aucun étudiant n'a répondu au quiz
        if (reponsesEtudiants.isEmpty()) {
            System.out.println("Aucun étudiant n'a passé ce quiz.");
            return;
        }

        // Liste pour stocker les étudiants et leurs scores
        ArrayList<Etudiant> studentList = new ArrayList<>();
        
        // Remplir la liste avec les étudiants et leur score
        for (Etudiant etudiant : reponsesEtudiants.keySet()) {
            Integer[] reponses = reponsesEtudiants.get(etudiant);
            int score = 0;

            // Compter les bonnes réponses
            if (reponses != null) {
                for (int i = 0; i < qcmTable.size(); i++) {
                    if (reponses[i] != null && reponses[i] == qcmTable.get(i).getCorrectOption()) {
                        score++;
                    }
                }
            }

            // Ajouter l'étudiant et son score à la liste
            studentList.add(etudiant);
            // Enregistrer le score dans l'étudiant (optionnel si déjà enregistré ailleurs)
            etudiant.enregistrerScore(this.theme, score);
        }

        // Trier les étudiants par score décroissant en utilisant une méthode de comparaison simple
        studentList.sort((etudiant1, etudiant2) -> {
            int score1 = etudiant1.getScores().get(this.theme); // Obtenir le score de chaque étudiant
            int score2 = etudiant2.getScores().get(this.theme);
            return Integer.compare(score2, score1); // Trier en ordre décroissant
        });

        // Afficher le classement avec le rang
        int rank = 1;
        for (Etudiant etudiant : studentList) {
            int score = etudiant.getScores().get(this.theme); // Obtenir le score de l'étudiant pour ce quiz
            System.out.println(rank + ". " + etudiant.getNom() + " " + etudiant.getPrenom() + " : " + score + " / " + qcmTable.size());
            rank++;
        }
    }

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}
	

	public int getNbqcm() {
		return nbqcm;
	}

	public void setNbqcm(int nbqcm) {
		this.nbqcm = nbqcm;
	}

	public ArrayList<Qcm> getQcmTable() {
		return qcmTable;
	}

	public void setQcmTable(ArrayList<Qcm> qcmTable) {
		this.qcmTable = qcmTable;
	}

	public HashMap<Etudiant, Integer[]> getReponsesEtudiants() {
		return reponsesEtudiants;
	}

	public void setReponsesEtudiants(HashMap<Etudiant, Integer[]> reponsesEtudiants) {
		this.reponsesEtudiants = reponsesEtudiants;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

    
}
