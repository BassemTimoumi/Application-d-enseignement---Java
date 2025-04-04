package ProjetPooQuiz;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        ArrayList<Quiz> quizTable = new ArrayList<>();
        ArrayList<Etudiant> etudiantTable = new ArrayList<>();
        HashSet<String> cinSet = new HashSet<>();
        Scanner sc = new Scanner(System.in);
        int type;
        boolean exit = false;

        while (!exit) {
            System.out.println("\n---- MENU PRINCIPAL ----");
            System.out.println("Tapez 1 pour accéder comme enseignant.");
            System.out.println("Tapez 2 pour accéder comme étudiant.");
            System.out.println("Tapez 0 pour quitter.");
            System.out.print("Votre choix: ");
         
            type = sc.nextInt();
            sc.nextLine();

            switch (type) {
                case 1: 
                    boolean teacherExit = false;
                    while (!teacherExit) {
                        System.out.println("\n--- Mode Enseignant ---");
                        System.out.println("1. Créer un quiz.");
                        System.out.println("2. Visualiser un quiz.");
                        System.out.println("3. Modifier un quiz.");
                        System.out.println("4. Supprimer un quiz.");
                        System.out.println("5. Visualiser la liste des étudiants ayant passé un quiz.");
                        System.out.println("6. Visualiser Statistiques Qcm.");
                        System.out.println("7. Afficher le classement pour un quiz.");
                        System.out.println("0. Quitter.");
                        System.out.print("Votre choix: ");
                        int teacherChoice = sc.nextInt();
                        sc.nextLine();

                        switch (teacherChoice) {
                            case 1: // Créer un quiz
                                System.out.println("\n--- Création du Quiz ---");
                                Quiz newQuiz = new Quiz();
                                newQuiz.creerquiz();
                                quizTable.add(newQuiz);
                                System.out.println("Quiz créé avec succès !");
                                break;

                            case 2: // Visualiser un quiz
                                System.out.println("\n--- Visualisation du Quiz ---");
                                System.out.print("Entrez le module du quiz : ");
                                String module = sc.nextLine();
                                boolean quizFound = false;

                                for (Quiz quiz : quizTable) {
                                    if (quiz.getTheme().equals(module)) { 
                                        quiz.visualiserquiz();
                                        quizFound = true;
                                        break;
                                    }
                                }

                                if (!quizFound) {
                                    System.out.println("Quiz non trouvé.");
                                }
                                break;

                            case 3: // Modifier un quiz
                                System.out.println("\n--- Mode Modification du Quiz ---");
                                System.out.print("Entrez le module du quiz à modifier : ");
                                String moduleToModify = sc.nextLine();
                                quizFound = false;

                                for (Quiz quiz : quizTable) {
                                    if (quiz.getTheme().equals(moduleToModify)) {
                                        quizFound = true;

                                        boolean quizModificationExit = false;
                                        while (!quizModificationExit) {
                                            System.out.println("\nQue souhaitez-vous modifier ?");
                                            System.out.println("1. Modifier une question (QCM)");
                                            System.out.println("2. Ajouter une option à une question");
                                            System.out.println("3. Modifier la réponse correcte d'une question");
                                            System.out.println("4. Ajouter un nouveau QCM au quiz");
                                            System.out.println("0. Quitter la modification.");
                                            System.out.print("Votre choix : ");
                                            int modifyChoice = sc.nextInt();
                                            sc.nextLine();

                                            switch (modifyChoice) {
                                                case 1: // Modifier une question existante
                                                    System.out.print("Entrez le numéro du QCM à modifier : ");
                                                    int qcmIndex = sc.nextInt() - 1;
                                                    sc.nextLine();

                                                    if (qcmIndex >= 0 && qcmIndex < quiz.getQcmTable().size()) {
                                                        Qcm qcmToModify = quiz.getQcmTable().get(qcmIndex); // Getter pour accéder à la liste des QCM
                                                        System.out.println("Question actuelle : " + qcmToModify.getQuestion());
                                                        System.out.print("Entrez la nouvelle question : ");
                                                        String newQuestion = sc.nextLine();
                                                        qcmToModify.setQuestion(newQuestion); // Setter pour modifier la question
                                                        System.out.println("Question modifiée avec succès !");
                                                    } else {
                                                        System.out.println("Numéro de QCM invalide.");
                                                    }
                                                    break;

                                                case 2: // Ajouter une option à une question
                                                    System.out.print("Entrez le numéro du QCM auquel ajouter une option : ");
                                                    qcmIndex = sc.nextInt() - 1;
                                                    sc.nextLine();

                                                    if (qcmIndex >= 0 && qcmIndex < quiz.getQcmTable().size()) {
                                                        Qcm qcmToModify = quiz.getQcmTable().get(qcmIndex);
                                                        System.out.print("Entrez la nouvelle option : ");
                                                        String newOption = sc.nextLine();
                                                        qcmToModify.ajouterOption(newOption); // Appel à la méthode pour ajouter une option
                                                        System.out.println("Option ajoutée avec succès !");
                                                    } else {
                                                        System.out.println("Numéro de QCM invalide.");
                                                    }
                                                    break;

                                                case 3: // Modifier la réponse correcte
                                                    System.out.print("Entrez le numéro du QCM pour modifier la réponse correcte : ");
                                                    qcmIndex = sc.nextInt() - 1;
                                                    sc.nextLine();

                                                    if (qcmIndex >= 0 && qcmIndex < quiz.getQcmTable().size()) {
                                                        Qcm qcmToModify = quiz.getQcmTable().get(qcmIndex);
                                                        System.out.println("Options disponibles :");
                                                        for (int i = 0; i < qcmToModify.getOptions().length; i++) {
                                                            System.out.println((i + 1) + ". " + qcmToModify.getOptions()[i]);
                                                        }
                                                        System.out.print("Entrez le numéro de la nouvelle réponse correcte : ");
                                                        int newCorrectOption = sc.nextInt() - 1;
                                                        sc.nextLine();

                                                        if (newCorrectOption >= 0 && newCorrectOption < qcmToModify.getOptions().length) {
                                                            qcmToModify.setCorrectOption(newCorrectOption); // Setter pour modifier la réponse correcte
                                                            System.out.println("Réponse correcte modifiée avec succès !");
                                                        } else {
                                                            System.out.println("Numéro de réponse incorrect.");
                                                        }
                                                    } else {
                                                        System.out.println("Numéro de QCM invalide.");
                                                    }
                                                    break;

                                                case 4: // Ajouter un nouveau QCM
                                                    System.out.println("\n--- Ajouter un nouveau QCM ---");
                                                    Qcm newQcm = new Qcm();
                                                    newQcm.creerqcm();
                                                    quiz.ajouterQcm(newQcm); // Appel à la méthode pour ajouter un QCM
                                                    System.out.println("QCM ajouté avec succès !");
                                                    break;

                                                case 0: // Quitter la modification
                                                    quizModificationExit = true;
                                                    break;

                                                default:
                                                    System.out.println("Choix invalide. Veuillez réessayer.");
                                            }
                                        }
                                        break;
                                    }
                                }

                                if (!quizFound) {
                                    System.out.println("Quiz non trouvé.");
                                }
                                break;


                            case 4: 
                                System.out.println("\n--- Suppression du Quiz ---");
                                System.out.println("Donnez le nom de l'auteur : ");
                                String nom = sc.nextLine();
                                System.out.println("Donnez le module : ");
                                module = sc.nextLine();

                                quizFound = false;
                                for (int i = 0; i < quizTable.size(); i++) {
                                    Quiz quiz = quizTable.get(i);
                                    // Utilisation du getter getTheme() pour récupérer le thème du quiz
                                    if (quiz.getAuteur().equals(nom) && quiz.getTheme().equals(module)) {
                                        quizTable.remove(i);
                                        quizFound = true;
                                        System.out.println("Quiz supprimé avec succès.");
                                        break;
                                    }
                                }

                                if (!quizFound) {
                                    System.out.println("Échec de la suppression. Aucun quiz correspondant trouvé.");
                                }
                                break;

                            case 5: // Visualiser la liste des étudiants ayant passé un quiz
                                System.out.println("Donnez le nom du module : ");
                                module = sc.nextLine();

                                quizFound = false;
                                for (Quiz q : quizTable) {
                                    // Utilisation du getter getTheme() pour accéder au module
                                    if (q.getTheme().equals(module)) {
                                        q.visualiserEtudiantsAvecScores(); // Utilisation de la méthode pour afficher les étudiants avec scores
                                        quizFound = true;
                                        break;
                                    }
                                }

                                if (!quizFound) {
                                    System.out.println("Aucun quiz correspondant trouvé pour ce module.");
                                }
                                break;
                            case 6: // Visualiser les statistiques pour chaque QCM
                                System.out.print("Entrez le module du quiz pour lequel vous voulez visualiser les statistiques : ");
                                String moduleStat = sc.nextLine();

                                quizFound = false;
                                for (Quiz quiz : quizTable) {
                                    if (quiz.getTheme().equals(moduleStat)) {
                                        quiz.visualiserStatistiquesQcm(); // Appel de la méthode dans Quiz
                                        quizFound = true;
                                        break;
                                    }
                                }

                                if (!quizFound) {
                                    System.out.println("Aucun quiz correspondant trouvé pour ce module.");
                                }
                                break;
                            case 7: // Visualiser le classement d'un module donné
                                System.out.println("\n--- Visualiser le classement d'un module ---");
                                System.out.print("Entrez le nom du module : ");
                                String moduleChoisi = sc.nextLine();

                                boolean moduleFound = false;
                                
                                // Chercher le quiz correspondant au module donné
                                for (Quiz quiz : quizTable) {
                                    if (quiz.getTheme().equals(moduleChoisi)) { // Utiliser getTheme() pour comparer le module
                                        quiz.afficherClassement(); // Appel de la méthode afficherClassement() pour afficher le classement du module choisi
                                        moduleFound = true;
                                        break;
                                    }
                                }

                                if (!moduleFound) {
                                    System.out.println("Aucun quiz correspondant trouvé pour ce module.");
                                }
                                break;

        
						case 0:
                                teacherExit = true;
                                break;

                            default:
                                System.out.println("Choix invalide. Veuillez réessayer.");
                            }}
                    break;

                case 2:
                    boolean etudiantExit = false;
                    Etudiant etudiantConnecte = null;

                    while (etudiantConnecte == null) {
                        System.out.println("\n--- Mode Etudiant ---");
                        System.out.println("Tapez 1 pour vous identifier.");
                        System.out.println("Tapez 2 pour vous inscrire (si vous êtes un nouvel étudiant).");
                        System.out.print("Votre choix: ");
                        int type3 = sc.nextInt();
                        sc.nextLine();

                        if (type3 == 1) {
                            System.out.println("\n--- Identification ---");
                            System.out.print("Entrez votre CIN : ");
                            String cin = sc.nextLine();
                            boolean identifie = false;

                            for (Etudiant etudiant : etudiantTable) {
                                if (etudiant.getCin().equals(cin)) {
                                    etudiantConnecte = etudiant;
                                    identifie = true;
                                    System.out.println("Identification réussie ! Bienvenue, " + etudiantConnecte.getNom());
                                    break;
                                }
                            }

                            if (!identifie) {
                                System.out.println("Échec de l'identification. CIN non trouvé.");
                            }

                        } else if (type3 == 2) {
                        	 System.out.println("\n--- Inscription ---");
                        	    Etudiant nouvelEtudiant = new Etudiant();

                        	    System.out.print("Entrez votre CIN : ");
                        	    String cin = sc.nextLine();
                        	    
                        	    // Vérifier si le CIN existe déjà dans le HashSet
                        	    if (cinSet.contains(cin)) {
                        	        System.out.println("Le CIN est déjà utilisé, veuillez en saisir un autre.");
                        	    } else {
                        	        // Ajouter le CIN au HashSet pour garantir l'unicité
                        	        cinSet.add(cin);
                        	        nouvelEtudiant.setCin(cin);
                        	        nouvelEtudiant.creeretudiant(); // Demander les autres informations

                        	        // Ajouter l'étudiant dans la liste des étudiants
                        	        etudiantTable.add(nouvelEtudiant);
                        	        System.out.println("Inscription réussie ! Bienvenue, " + nouvelEtudiant.getNom());
                        	    }
                        } else {
                            System.out.println("Choix invalide. Veuillez entrer 1 pour vous identifier ou 2 pour vous inscrire.");
                        }
                    }

                    while (!etudiantExit) {
                        System.out.println("\n--- Menu Étudiant ---");
                        System.out.println("Tapez 2 pour Visualiser la liste des Quiz.");
                        System.out.println("Tapez 3 pour Répondre à un quiz.");
                        System.out.println("Tapez 4 pour Consulter les scores obtenus.");
                        System.out.println("Tapez 5 pour Consulter la correction d’un Quiz.");
                        System.out.println("Tapez 0 pour quitter.");
                        System.out.print("Votre choix: ");
                        type = sc.nextInt();
                        sc.nextLine();

                        switch (type) {
                            case 2:
                                if (etudiantConnecte == null) {
                                    System.out.println("Vous devez d'abord vous identifier.");
                                    break;
                                }

                                System.out.println("\n--- Liste des Quiz disponibles ---");

                                boolean quizDisponible = false;

                                for (Quiz quiz : quizTable) {
                                    if (etudiantConnecte.aDejaRepondu(quiz.getTheme())) {
                                        System.out.println("- " + quiz.getTheme() + " (Auteur: " + quiz.getAuteur() + ") - Répondu");
                                    } else {
                                        System.out.println("- " + quiz.getTheme() + " (Auteur: " + quiz.getAuteur() + ") - Pas encore répondu");
                                        quizDisponible = true;
                                    }
                                }

                                if (!quizDisponible) {
                                    System.out.println("Tous les quiz ont déjà été passés.");
                                }

                                break;

                            case 3:
                     

                                System.out.println("\n--- Choisir un module ---");
                                quizDisponible = false;

                                for (Quiz quiz : quizTable) {
                                    if (!etudiantConnecte.aDejaRepondu(quiz.getTheme())) {
                                        System.out.println("- " + quiz.getTheme());
                                        quizDisponible = true;
                                    }
                                }

                                if (!quizDisponible) {
                                    System.out.println("Tous les quiz ont déjà été passés.");
                                    break;
                                }

                                System.out.print("Choisissez le module auquel vous souhaitez répondre : ");
                                String moduleChoisi = sc.nextLine();

                                Quiz quizChoisi = null;
                                for (Quiz quiz : quizTable) {
                                    if (quiz.getTheme().equals(moduleChoisi) && !etudiantConnecte.aDejaRepondu(quiz.getTheme())) {
                                        quizChoisi = quiz;
                                        break;
                                    }
                                }

                                if (quizChoisi == null) {
                                    System.out.println("Quiz non disponible ou déjà répondu.");
                                    break;
                                }

                                int score = 0;
                                Integer[] reponses = new Integer[quizChoisi.getQcmTable().size()]; // Tableau pour enregistrer les réponses

                                for (int i = 0; i < quizChoisi.getQcmTable().size(); i++) {
                                    Qcm qcm = quizChoisi.getQcmTable().get(i); // Récupération du QCM
                                    System.out.println("\nQuestion " + (i + 1) + " : " + qcm.getQuestion());

                                    // Affichage des options
                                    for (int j = 0; j < qcm.getOptions().length; j++) {
                                        System.out.println((j + 1) + ". " + qcm.getOptions()[j]);
                                    }

                                    // Lecture et validation de la réponse
                                    int reponse = -1;
                                    while (reponse < 1 || reponse > qcm.getOptions().length) {
                                        System.out.print("Votre réponse (numéro de l'option) : ");
                                        reponse = sc.nextInt();
                                        sc.nextLine();

                                        if (reponse < 1 || reponse > qcm.getOptions().length) {
                                            System.out.println("Réponse invalide. Veuillez choisir un numéro entre 1 et " + qcm.getOptions().length + ".");
                                        }
                                    }

                                    reponses[i] = reponse - 1; // Enregistrer l'index de l'option choisie (0-based)

                                    // Vérification si la réponse est correcte
                                    if (reponses[i] == qcm.getCorrectOption()) {
                                        score++;
                                    }
                                }

                                // Enregistrement des réponses de l'étudiant
                                quizChoisi.enregistrerReponses(etudiantConnecte, reponses);

                                // Enregistrement du score final de l'étudiant
                                quizChoisi.enregistrerScoreEtudiant(etudiantConnecte, score);
                                System.out.println("\nQuiz terminé. Votre score est : " + score + " / " + quizChoisi.getQcmTable().size());

                                // Marquer le module comme répondu par l'étudiant
                                etudiantConnecte.ajouterModuleRepondu(quizChoisi.getTheme());
                                etudiantConnecte.enregistrerScore(quizChoisi.getTheme(), score);

                            case 4:
  

                                etudiantConnecte.afficherScores(quizTable);
                                break;

                            case 5:


                                System.out.println("\n--- Consulter la correction d’un quiz ---");

                                quizDisponible = false;
                                for (Quiz quiz : quizTable) {
                                    if (etudiantConnecte.aDejaRepondu(quiz.getTheme())) {
                                        System.out.println("- " + quiz.getTheme());
                                        quizDisponible = true;
                                    }
                                }

                                if (!quizDisponible) {
                                    System.out.println("Vous n'avez pas encore répondu aux quiz.");
                                    break;
                                }

                                System.out.print("Choisissez le module dont vous souhaitez voir la correction : ");
                                String moduleChoisiCorrection = sc.nextLine();

                                Quiz quizChoisiCorrection = null;
                                for (Quiz quiz : quizTable) {
                                    if (quiz.getTheme().equals(moduleChoisiCorrection) && etudiantConnecte.aDejaRepondu(quiz.getTheme())) {
                                        quizChoisiCorrection = quiz;
                                        break;
                                    }
                                }

                                if (quizChoisiCorrection == null) {
                                    System.out.println("Quiz non trouvé ou vous n'avez pas encore répondu à ce quiz.");
                                    break;
                                }

                                System.out.println("\n--- Correction du quiz " + quizChoisiCorrection.getTheme() + " ---");

                                for (Qcm qcm : quizChoisiCorrection.getQcmTable()) {
                                    System.out.println("Question : " + qcm.getQuestion());

                                    for (int j = 0; j < qcm.getOptions().length; j++) {
                                        System.out.println((j + 1) + ". " + qcm.getOptions()[j]);
                                    }

                                    if (qcm.getCorrectOption() >= 0 && qcm.getCorrectOption() < qcm.getOptions().length) {
                                        System.out.println("Réponse correcte : " + qcm.getOptions()[qcm.getCorrectOption()]);
                                    } else {
                                        System.out.println("Réponse correcte : Index invalide");
                                    }

                                    System.out.println();
                                }

                                break;

                            case 0:
                                System.out.println("Merci de votre visite.");
                                etudiantExit = true;
                                break;

                            default:
                                System.out.println("Choix invalide. Veuillez réessayer.");
                                break;
                        }
                    }
                    break;

                case 0:
                    System.out.println("Merci d'avoir utilisé le programme. Au revoir !");
                    exit = true;
                    break;

                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
                    break;
            }
        }
    }
}
