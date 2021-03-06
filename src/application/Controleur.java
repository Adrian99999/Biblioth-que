package application;

import java.io.File;
import java.util.Optional;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

public class Controleur extends Application{
public static final String FICHIER_LIVRES = "src//livres.dat";
public static final String FICHIER_FUSION = "src//fusion.dat";
/**
 * Un pointeur pour l'interface
 */
private Interface objectInterface;

//private GestionLivres objectGestionLivre;
/**
 * Un pointeur Stage pour le stage
 */
private Stage stage;
/**
 * Un tableau (vecteur) de livres. Il sera rempli au d�but � partir du contenu du fichier livres.dat depuis data
 */
private Livre [] vecteurLivres=null;


/**
 * M�thode qui instancie l'interface, ajoute les �couteurs sur les sous menu, r�cup�re le fichier de livres et affiche l'interface de d�part
 */

public void start(Stage primaryStage) {
	try {
		stage=primaryStage;
		objectInterface = new Interface();
		ajouterEcouteurs();
		
		primaryStage.setScene(objectInterface.scene);
		primaryStage.setTitle("Gestion de Livres");
		
		restaurerFichier();
		
		primaryStage.show();
	} catch(Exception e) {
		e.printStackTrace();
	}
}

/**
 * M�thode qui permet de r�cup�rer le fichier, le trier par cote et le placer dans la zone de texte
 */
private void restaurerFichier()
{
	vecteurLivres = GestionLivres.recupererFichier(new File(FICHIER_LIVRES));
	//vecteurLivres = objectGestionLivre.recupererFichier(new File(FICHIER_LIVRES));
	//System.out.println(GestionLivres.formaterListeLivres(vecteurLivres));
	GestionLivres.trierParCote(vecteurLivres);
	objectInterface.zoneText.setText(GestionLivres.formaterListeLivres(vecteurLivres));
}

/**
 * M�thode qui permet d'ajouter un �couteur d'�venements � chacun des sous menu de la zone de menus
 */
private void ajouterEcouteurs() {
	objectInterface.menuItemTrierCote.setOnAction(new ActionBoutons());
	objectInterface.menuItemFusion.setOnAction(new ActionBoutons());
	objectInterface.menuItemQuitter.setOnAction(new ActionBoutons());
	objectInterface.menuItemRechCote.setOnAction(new ActionBoutons());
	objectInterface.menuItemRechTitre.setOnAction(new ActionBoutons());
	objectInterface.menuItemTrieNbPage.setOnAction(new ActionBoutons());
	objectInterface.menuItemTrieTitre.setOnAction(new ActionBoutons());
}
/**
 * Classe permettant d'impl�menter la m�thode pour l'�coute des clics sur les boutons du bas
 */
private class ActionBoutons implements EventHandler<ActionEvent>
{

	public void handle(ActionEvent evenement) {
		if(evenement.getSource() == objectInterface.menuItemFusion)
		{
			gestionFusionCote();
		}
		if(evenement.getSource() == objectInterface.menuItemQuitter)
		{
			System.exit(0);
		}
		if(evenement.getSource() == objectInterface.menuItemRechCote)
		{
			gestionRechercheCote();
		}
		if(evenement.getSource() == objectInterface.menuItemRechTitre)
		{
			gestionRechercheTitre();
		}
		if(evenement.getSource() == objectInterface.menuItemTrieNbPage)
		{
			gestionTrierPages();
		}
		if(evenement.getSource() == objectInterface.menuItemTrierCote)
		{
			gestionTrierCote();
		}
		if(evenement.getSource() == objectInterface.menuItemTrieTitre)
		{
			gestionTrierTitre();
		}
		
	}
	/**
	 * Effectue la gestion de la fusion. 
	 * 
	 * Elle r�cup�re le contenu du fichier FICHIER_FUSION (Appel de la m�thode recupererFichier) et trie par cote le tableau r�sultant de cette r�cup�ration de fichier s'il n'est pas � "null". 
	 * 
	 * Elle trie aussi par cote le tableau courant "tabDeLivres" s'il n'est pas  � "null" et fusionne ces 2 tableaux tri�s.
	 * 
	 * Le r�sultat de la fusion est remis dans le tableau courant "tabDeLivres".
	 * 
	 * Attention, il faut pr�voir les cas o� un des 2 tableaux (ou les 2) soit null.
	 * 
	 * Apr�s la fusion, elle r�affiche correctement les informations du tableau  fusionn� dans le TextArea et d�sactive le sous menu "Fusion".
	 */

	private void gestionFusionCote() {
		Livre [] vectTemp = GestionLivres.recupererFichier(new File(FICHIER_FUSION));
		//verification du tableau temporere
		if(vectTemp != null)
		{
			//le trier selon la cote 
			GestionLivres.trierParCote(vectTemp);
			//le tableau interne est-il null
			if( vecteurLivres != null)
			{
				GestionLivres.trierParCote(vecteurLivres);
				
				vecteurLivres = GestionLivres.fusionnerTableau(vecteurLivres, vectTemp);
			}
			else 
			{
				vecteurLivres = vectTemp;
			}
		}
		else if(vecteurLivres != null)
		{
			GestionLivres.trierParCote(vecteurLivres);
		}
		
		objectInterface.zoneText.setText(GestionLivres.formaterListeLivres(vecteurLivres));
		objectInterface.menuItemFusion.setDisable(true);
		
	}
	
}

/**
 * Effectue la gestion de la recherche de titres de livres.  
 * Elle demande un titre. Assurez-vous que la saisie du titre n�a pas �t�
 * annul�e et que le titre filtr� ait au moins un caract�re. (Saisie avec
 * feedback).
 * 
 * Effectue ensuite la recherche et affiche dans des bo�tes de message le ou les
 * livres dont le titre <b>commence</b> par la cha�ne saisie ou un message
 * appropri� si aucun livre n'existe avec ce titre.
 */

public void gestionRechercheTitre() {
	String titreRecherche=null;
	int [] vecteurOccuranceLivre= null;
	String msgAlertVecteur = "";
	Optional <String> titreSaisie = null;
	
	TextInputDialog dialog = new TextInputDialog();
	dialog.setTitle("Recherche par titre");
	dialog.setHeaderText(null);
	dialog.setContentText("Quelle est le titre recherch� ?");
	
	titreSaisie = dialog.showAndWait();
	//Assurez-vous que la saisie du titre n�a pas �t�  annul�e et que le titre filtr� ait au moins un caract�re. (Saisie avec  feedback).
	
	while(titreSaisie.isPresent() && (!Livre.validerTitre(Livre.filtrerTitre(titreSaisie.get()))))
	{
		dialog.setHeaderText("Attention le titre n'est pas valide.");
		dialog.setContentText("Le titre doit avoir min 2 et max 20 lettres. \n Quelle est le titre recherch� ?");
		titreSaisie= dialog.showAndWait();
	}
	
	if(titreSaisie.isPresent())
	{
		//cette m�thode fait toUpperCase le titre saise
		titreRecherche = Livre.filtrerTitre(titreSaisie.get());
		vecteurOccuranceLivre = GestionLivres.rechercherTitre(titreRecherche, vecteurLivres);
		
		//verification du vecteur return�
		if(vecteurOccuranceLivre == null)
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Titre inexistant.");
			alert.setContentText("Le titre recherch� n'existe pas.");
		}
		else
		{
			Alert alert = new Alert (AlertType.INFORMATION);
			alert.setTitle("Livre trouv�");
			alert.setHeaderText(null);
			for (int i = 0; i<vecteurOccuranceLivre.length; i ++)
			{
				msgAlertVecteur += vecteurLivres[vecteurOccuranceLivre[i]].enChaineAbregee() +"\n";
			}
			alert.setContentText(msgAlertVecteur);
			alert.show();
		}
	}
}

public void gestionRechercheCote() {
	String coteCherchee=null;
	int coteTrouvee=0;
	
	Optional<String> coteSaisie = null;
	
	//demander la cote
	TextInputDialog dialog = new TextInputDialog();
	dialog.setTitle("Recherche par cote");
	dialog.setHeaderText(null);
	dialog.setContentText("Quelle est la cote recherch�e?");
	
	coteSaisie = dialog.showAndWait();
	//tretemont de saisie
	while (coteSaisie.isPresent() && (!Livre.validerCote(Livre.filtrerCote(coteSaisie.get())))) 
	{
		dialog.setHeaderText("Attention la cote n'est pas valide.");
		dialog.setContentText("Elle doit avoir 2 lettres et 3 chiffres \n Quelle est la cote recherch�e?");
		coteSaisie = dialog.showAndWait();
	}
	
	if(coteSaisie.isPresent())
	{
		coteCherchee = Livre.filtrerCote(coteSaisie.get());
		GestionLivres.trierParCote(vecteurLivres);
		coteTrouvee = GestionLivres.rechercherCote(coteCherchee, vecteurLivres);
		
		if(coteTrouvee == -1)
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Cote inexistant");
			//alert.setHeaderText();
			alert.setContentText("La cote entr�e n'existe pas.");
			alert.getDialogPane().setStyle("-fx-text-background-color: red;");
		}
		//si la cote a �t� trouv�e, les renseignements du livre sont affich�s
		else 
		{
			Alert alert = new Alert (AlertType.INFORMATION);
			alert.setTitle("Livre trouv�!");
			alert.setHeaderText(null);
			alert.setContentText(vecteurLivres[coteTrouvee].enChaineAbregee());
			alert.show();
		}
	}
}

public void gestionTrierPages() {
	GestionLivres.trierParPages(vecteurLivres);
	objectInterface.zoneText.setText(GestionLivres.formaterListeLivres(vecteurLivres));
}

public void gestionTrierTitre() {
	GestionLivres.trierParTitre(vecteurLivres);
	objectInterface.zoneText.setText(GestionLivres.formaterListeLivres(vecteurLivres));
}

/**
 * Effectue les op�rations n�cessaires pour que le tableau de livres soit tri� par cote (trierParCote) et r�affiche correctement la liste de livres
 * dans le TextArea.
 */
public void gestionTrierCote() {
	GestionLivres.trierParCote(vecteurLivres);
	objectInterface.zoneText.setText(GestionLivres.formaterListeLivres(vecteurLivres));
}

public static void main(String[] args) {
	launch(args);
}
}
