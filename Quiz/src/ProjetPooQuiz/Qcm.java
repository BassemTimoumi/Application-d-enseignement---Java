package ProjetPooQuiz;
import java.util.Scanner;

public class Qcm {
    private String question;
    private String[] options;
    private int correctOption; 
    private int nbop;

    public Qcm() {
        this.correctOption = -1;
    }

    public void creerqcm() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Donnez la question : ");
        this.question = sc.nextLine();

        while (nbop <= 1) {
            System.out.println("Nombre d'options (doit être supérieur à 1) : ");
            this.nbop = sc.nextInt();
            sc.nextLine();
        }

        this.options = new String[nbop];
        System.out.println("Donnez les options : ");
        for (int i = 0; i < nbop; i++) {
            System.out.println("Option " + (i + 1) + ": ");
            options[i] = sc.nextLine();
        }

        while (correctOption < 1 || correctOption > nbop) {
            System.out.println("Entrez le numéro de l'option correcte (1-" + nbop + "): ");
            this.correctOption = sc.nextInt();
            sc.nextLine();
        }
        correctOption--; 
    }

 
    
    public String toString(boolean showCorrectOption) {
        StringBuilder sb = new StringBuilder();
        sb.append("Question: ").append(question).append("\n");
        for (int i = 0; i < options.length; i++) {
            sb.append((i + 1)).append(". ").append(options[i]).append("\n");
        }
        if (showCorrectOption) {
            sb.append("Correct Option: ").append(correctOption + 1).append("\n"); // Add 1 for human-readable index
        }
        return sb.toString();
    }
    public void ajouterOption(String nouvelleOption) {
        // Créer un nouveau tableau avec une taille augmentée de 1
        String[] nouvellesOptions = new String[this.options.length + 1];

        // Copier les anciennes options dans le nouveau tableau
        System.arraycopy(this.options, 0, nouvellesOptions, 0, this.options.length);

        // Ajouter la nouvelle option à la fin du tableau
        nouvellesOptions[this.options.length] = nouvelleOption;

        // Mettre à jour le tableau des options avec les nouvelles options
        this.options = nouvellesOptions;

        // Afficher un message de confirmation
        System.out.println("Nouvelle option ajoutée : " + nouvelleOption);
    }
    
    
//getters and setters :
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String[] getOptions() {
		return options;
	}

	public void setOptions(String[] options) {
		this.options = options;
	}

	public int getCorrectOption() {
		return correctOption;
	}

	public void setCorrectOption(int correctOption) {
		this.correctOption = correctOption;
	}

	public int getNbop() {
		return nbop;
	}

	public void setNbop(int nbop) {
		this.nbop = nbop;
	}
    

    
}
