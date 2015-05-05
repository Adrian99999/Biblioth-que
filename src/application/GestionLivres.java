package application;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JOptionPane;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class GestionLivres {
	BufferedWriter ecrire;
	BufferedReader lire;
public GestionLivres()
{
	
}
/**
 * Effectue le tri en ordre croissant d'un tableau, reçu en entrée, en fonction de la cote des livres. Utilise l'algorithme de tri à bulle amélioré.
 * Pour courant variant de 1 à (n-1)
		valTemp = a[courant] 
		position = courant
		Tant que (( position > 0 ) et ( a[position - 1] > valTemp ))
			a[position] = a[position - 1]
			position = position - 1
		Fin tant que
		a[position] = valTemp
	Fin pour
 * @param vecteurLivre
 */
public static void trierParCote(Livre [] vecteurLivre)
{
	//declaration des variables 
	Livre livreTemp = null;
	//la variable qui verifie si on a fait des permutation pour trie le 
	boolean permutationOK = false;
	//la taille est 10, courant est 10-2=8, le tableau contient 0-9 elements, comme caon arrive a comparetre 8 avec 8+1=9 est on n'a pas de debordement 
	int courant = vecteurLivre.length - 2;
	//pour chaque passe
	do
	{
		permutationOK = false;
		for (int i = 0; i<=courant; i++)
		{
			if(vecteurLivre[i].getCote().compareTo(vecteurLivre[i+1].getCote())>0)
			{
				livreTemp = vecteurLivre[i+1];
				vecteurLivre[i+1] = vecteurLivre[i];
				vecteurLivre[i] = livreTemp;
				permutationOK = true;
			}
		}
		courant --;
	} while (permutationOK);
}
/**
 * Effectue le tri en ordre croissant d'un tableau, reçu en entrée, en fonction du titre des livres. Utilise l'algorithme de tri par insertion.
 * Tri par insertion :
	Pour courant variant de 1 à (n-1)
		valTemp = a[courant] 
		position = courant
		Tant que (( position > 0 ) et ( a[position - 1] > valTemp ))
			a[position] = a[position - 1]
			position = position - 1
		Fin tant que
		a[position] = valTemp
	Fin pour

 * @param vecteurLivre
 */
public static void trierParTitre( Livre [] vecteurLivre)
{
	Livre livreTemp = null;
	int courant = vecteurLivre.length-1;
	int position = courant;
	for(int i =1; i<=courant; i++)
	{
		livreTemp= vecteurLivre[i];
		position = i;
		while ( (position > 0) && (vecteurLivre[position-1].getTitre().compareToIgnoreCase(livreTemp.getTitre())>0))
		{
			vecteurLivre[position] = vecteurLivre[position-1];
			position --;
		}
		vecteurLivre[position] = livreTemp;
	}
}
/**
 * Effectue le tri en ordre croissant d'un tableau, reçu en entrée, en fonction du nombre de pages des livres. Utilise l'algorithme de tri par  sélection.
 * Tri par sélection :
	Pour courant variant de 0 à (n-2)
		indiceTemp = courant
		Pour position variant de courant+1 à (n-1)
			Si (a[position] < a[indiceTemp])
				indiceTemp = position	//valeur de l’indice du vecteur A
			Fin si
		Fin pour
		Si indiceTemp <> courant
			valTemp = a[indiceTemp]
			a[indiceTemp] = a[courant]
			a[courant] = valTemp
		Fin si
	Fin pour

 * @param vecteurLivre
 */
public static void trierParPages (Livre [] vecteurLivre)
{
	Livre livreIntermediar=null;
	int position;
	for (int i = 0; i < vecteurLivre.length-2; i++)
	{
		position = i;
		for ( int j = position + 1; j<vecteurLivre.length-1; j++)
		{
			if (vecteurLivre[j].getPages()<vecteurLivre[position].getPages())
			{
				position = j;
			}
		}
		if (position != i)
		{
			livreIntermediar = vecteurLivre[position];
			vecteurLivre[position] = vecteurLivre[i];
			vecteurLivre[i] = livreIntermediar;
		}
	}
}
/**
 * Recherche séquentielle, dans le tableau reçu en entrée (pTab), d'un titre  qui commence par la chaîne reçue en entrée (pTitre). Les livres trouvés  seront tous ceux pour lesquels le titre commence par la chaîne pTitre. Il
 * peut y avoir plusieurs titres pour lesquels le titre (ou le début du  titre) correspond à la chaîne.
 * 
 * Cette méthode retourne un tableau des indices des livres trouvés ou null s’il y en a aucun. (Voir algorithme de recherche séquentielle). Attention aux minuscules et majuscules.
 * 
 * Attention: Vous n'avez pas besoin de vérifier si "pTab" est égale  à null, mais le tableau peut être vide (0 objet).
 * 
 *IMPORTANT : la méthode "startsWith()" de la classe "String" pourrait  peut-être vous aider.
 * 
 * @param pTitre le début du titre à rechercher
 * @param pTab le tableau de livres
 * 
 * @return int[], un tableau des indices des livres correspondant ou "null"
 */
/*Le premier algorithme applique la recherche séquentielle dans un tableau qui n'est pas nécessairement trié.

Recherche séquentielle :
	indiceRetour = -1
	indice = 0
	
	Tant que (( indice < n ) et ( elementRechercher <> a[indice] ))
		indice = indice + 1
	Fin tant que

	Si ( indice < n )
		indiceRetour = indice
	FinSi

	Retourner indiceRetour
*/
public static int [] rechercherTitre(String pTitre, Livre [] vecteurLivre)
{
	int nombreDesLivresTrouveAvecLeMemeNom = 0;
	int [] vecteurIndiceLivres = null;
	for ( int i = 0;i<vecteurLivre.length; i++)
	{
		if (vecteurLivre[i].getTitre().toUpperCase().startsWith(pTitre.toUpperCase()))
		{
			nombreDesLivresTrouveAvecLeMemeNom++;
		}
	}
	if (nombreDesLivresTrouveAvecLeMemeNom>0)
	{
		vecteurIndiceLivres = new int [nombreDesLivresTrouveAvecLeMemeNom];
		//indice du vecteurIndiceLivres
		int i=0;
		for ( int j = 0;j<vecteurLivre.length; j++)
		{
			if (vecteurLivre[j].getTitre().toUpperCase().startsWith(pTitre.toUpperCase()))
			{
				vecteurIndiceLivres[i]=j;
				i++;
			}
		}
	}
	return vecteurIndiceLivres;
}
/**
 * L’algorithme suivant fait la recherche d’un élément dans un tableau OBLIGATOIREMENT trié.
Recherche dichotomique :
	inf = 0			//Indice inférieur de la recherche
	sup = n - 1		//Indice supérieur de la recherche
	indice = -1		//Valeur retournée si pas trouvé

	Tant que (( inf <= sup ) et ( indice = -1 ))
		milieu = (inf + sup) / 2
		Si (a[milieu] > elementRechercher )
			sup = milieu - 1
		Sinon	Si (a[milieu] < elementRechercher )
			inf = milieu + 1
		Sinon
			indice = milieu
		Fin si
	Fin tant que

	Retourner indice

 * Recherche dichotomique, dans un tableau de livres trié par cote, reçu en entrée (pTab), d'un livre ayant une cote correspondant de façon identique
 * à celle reçue en entrée (pCote) (Voir algorithme de recherche  dichotomique).
 * 
 * La cote est unique et un seul livre pourra être trouvé. La méthode retourne l'indice du livre trouvé ou –1 si la cote n'existe pas.
 * Attention aux minuscules et majuscules.
 * 
 * Attention:Vous n'avez pas besoin de vérifier si "pTab" est égale  à null, mais le tableau peut être vide (0 objet).
 * 
 *IMPORTANT : la classe "String" est "Comparable".
 * 
 * @param pCote la cote du livre à rechercher
 * @param pTab un tableau de livres
 * 
 * @return int, l'indice du livre trouvé ou -1 si pas trouvé
 */
public static int rechercherCote(String pCote, Livre [] vecteurLivre)
{
	int indice=-1;
	int inf=0;
	int sup=vecteurLivre.length-1;
	
	while (inf<=sup && indice == -1)
	{
		int milieu = (inf + sup)/2;
		if(vecteurLivre[milieu].getCote().compareTo(pCote.toUpperCase())>0)
		{
			sup = milieu -1;
		}
		else if (vecteurLivre[milieu].getCote().compareTo(pCote.toUpperCase())<0)
			{
				inf = milieu + 1;
			}
		else 
		{
			indice=milieu;
		}
	}
	return indice;
}
/**
 * Identificateurs :
	v1:	Un tableau (vecteur) trié de nv1 éléments
	v2:	Un tableau (vecteur) trié de nv2 éléments
	v3:	Un tableau (vecteur) trié résultant de la fusion (nv1 + nv2 éléments)
	iv1:	Indice où on est rendu dans le tableau v1
	iv2:	Indice où on est rendu dans le tableau v2
	iv3:	Indice où on est rendu dans le tableau v3
	nv1:	Nombre d'éléments du tableau v1
	nv2:	Nombre d'éléments du tableau v2


Algorithme :
	iv1 = 0
	iv2 = 0
	iv3 = 0
	Faire
		Si (v1[iv1] < v2[iv2])
			v3[iv3] = v1[iv1]
			iv1 = iv1 + 1
		Sinon
			v3[iv3] = v2[iv2]
			iv2 = iv2 + 1
		Fin si
		iv3 = iv3 + 1
	Tant que (( iv1 < nv1 ) et ( iv2 < nv2 ))

	//Placer le reste des éléments dans vecteur v3
	Si (iv1 = nv1)
		Pour iv2 variant de iv2 à nv2-1
			v3[iv3] = v2[iv2]
			iv3 = iv3 + 1
		Fin pour
	Sinon
		Pour iv1 variant de iv1 à nv1-1
			v3[iv3] = v1[iv1]
			iv3 = iv3 + 1
		Fin pour
	Fin si

 * Effectue la fusion de 2 tableaux de livres triés par cote, reçus en entrée. Elle retourne le nouveau tableau fusionné. (Voir algorithme de  fusion).
 * 
 * Attention: Vous n'avez pas besoin de vérifier si "pTab1" ou "pTab2" sont égale à null, mais les tableaux peuvent être vides (0  objet).
 * 
 * IMPORTANT : la classe "String" est "Comparable".
 * 
 * @param pTab1 un premier tableau de livres
 * @param pTab2 un deuxième tableau de livres
 * 
 * @return Livre[], un tableau de livres, le résultat de la fusion
 */

public static Livre [] fusionnerTableau (Livre [] vecteurLivres1, Livre [] vecteurLivres2)
{
	Livre [] vecteurLivreFusionne = new Livre [vecteurLivres1.length+vecteurLivres2.length];
	int iv1=0, iv2=0, iv3=0;
	
	do 
	{
		if(vecteurLivres1[iv1].getCote().compareTo(vecteurLivres2[iv2].getCote())<0)
		{
			vecteurLivreFusionne[iv3] = vecteurLivres1[iv1];
			iv1 = iv1+1;
		}
		else 
		{
			vecteurLivreFusionne[iv3] = vecteurLivres2[iv2];
			iv2++;
		}
		iv3++;
	}
	while (iv1 <vecteurLivres1.length && iv2 < vecteurLivres2.length);
	if (iv1 == vecteurLivres1.length)
	{
		for (int i=iv2;i<=vecteurLivres2.length-1;i++)
		{
			vecteurLivreFusionne[iv3] = vecteurLivres2[i];
			iv3 = iv3 +1;
		}
	}
	else
	{
		for (int i= iv1; i<=vecteurLivres1.length-1;i++)
		{
			vecteurLivreFusionne[iv3] = vecteurLivres1[i];
			iv3 ++;
		}
	}
	return vecteurLivreFusionne;
}

public static Livre [] recupererFichier(File pFichier)
{
	DataInputStream dataInpStr = null;
	int compteur = 0;
	int i=0;
	Livre [] vecteurRetour =null;
	//Lire le fichier une fois pour compter combien il y a des livres 
	try
	{
		dataInpStr = new DataInputStream(new BufferedInputStream(new FileInputStream(pFichier)));
		try
		{
			do
			{
				dataInpStr.readUTF();
				dataInpStr.readUTF();
				dataInpStr.readInt();
				dataInpStr.readFloat();
				
				compteur++;
			}
			while (true);
		}
		// Fermer le fichier lorsque fin de fichier détecté
		catch (EOFException e)
		{
			dataInpStr.close();
		}
	}
	// Afficher message d'erreur lorsque le fichier n'est pas ouvert
	catch (java.io.IOException e)
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Erreur IO");
		alert.setHeaderText("");
		alert.setContentText("Erreur : "+e.getMessage());
		alert.showAndWait();
	}
	// Ne rien faire si on n'a pas de livre ...................................................
	if(compteur == 0)
	{
		
	}
	// Instancier le tableau selon le nombre de livres comptés à la lecture précédente.
	vecteurRetour= new Livre [compteur];
	// Lire le fichier une seconde fois pour remplir le tableau
	try
	{
		dataInpStr = new DataInputStream(new BufferedInputStream(new FileInputStream(pFichier)));
		//declaration des attribut de la clas Livre, ils sont private dans leur classe
		try
		{
			do
			{
				//il faut les declarer dans la boucle pour que le pointeur change en meme temps avec le i
				String cote = dataInpStr.readUTF(); 
				String titre = dataInpStr.readUTF();
				int pages = dataInpStr.readInt();
				float prix = dataInpStr.readFloat();
				
				vecteurRetour[i++] = new Livre(cote, titre, pages, prix);
				//vecteurRetour[i++] = new Livre(dataInpStr.readUTF(), dataInpStr.readUTF(), dataInpStr.readInt(),dataInpStr.readFloat());
			}
			while (true);
		}
		// Fermer le fichier lorsque fin de fichier détecté
		catch (EOFException e)
		{
			dataInpStr.close();
		}
	}
	// Afficher message d'erreur lorsque le fichier n'est pas ouvert
	catch (java.io.IOException e)
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Erreur IO");
		alert.setHeaderText("");
		alert.setContentText("Erreur : "+e.getMessage());
		alert.showAndWait();
	}
	return vecteurRetour;
}
public static String formaterListeLivres(Livre [] vecteurLivres)
{
	String text ="";
	for (int i = 0; i<vecteurLivres.length;i++)
	{
		text+= vecteurLivres[i].getCote() +"\t"
			 +"("+ vecteurLivres[i].getPages()+")" + "\t"
			 + vecteurLivres[i].getTitre() + "\n";
	}
	return text;
}
//Test
public static void main (String [] args)
{
	Livre l2 = new Livre("AA111", "Titre1", 12, 40);
	Livre l1 = new Livre("BB111", "Titre4", 24, 38);
	Livre l3 = new Livre("CC444", "Titre2", 18, 30);
	Livre l4 = new Livre("DD555", "Titre3", 32, 37);

	Livre [] vecteurTestLivre = {l1,l2,l3,l4};
	System.out.println("Variante initiale : \n"+formaterListeLivres(vecteurTestLivre));
	//trierParCote(vecteurTestLivre);
	//System.out.println(formaterListeLivres(vecteurTestLivre));
	//trierParTitre(vecteurTestLivre);
	//System.out.println("Trie par titre :\n"+formaterListeLivres(vecteurTestLivre));
	//trierParPages(vecteurTestLivre);
	//System.out.println("Trie par pages :\n"+formaterListeLivres(vecteurTestLivre));
	
	//String cote = JOptionPane.showInputDialog(null,"Entrez une cote : ");
	//System.out.println(vecteurTestLivre[rechercherCote(cote, vecteurTestLivre)].getTitre() + "\t" + vecteurTestLivre[rechercherCote(cote, vecteurTestLivre)].getPages() + "\t" + vecteurTestLivre[rechercherCote(cote, vecteurTestLivre)].getValeur());
	
	//pour afficher le livre recherche par le titre 
	String pt = JOptionPane.showInputDialog(null, "Entrez un titre");
	int taille = rechercherTitre(pt, vecteurTestLivre).length;
	for (int i=0; i<taille; i++)
	{
		String leftAlignFormat = "| %-8s  | %-15s | %-4d |%n";

		System.out.format("+-----------+-----------------+------+%n");
		System.out.printf("|Cote       | Titre           | pages|%n");
		System.out.format("+-----------+-----------------+------+%n");
		for (int j = 0; j < 5; j++) {
		    System.out.format(leftAlignFormat,vecteurTestLivre[rechercherTitre(pt, vecteurTestLivre)[i]].getCote(), vecteurTestLivre[rechercherTitre(pt, vecteurTestLivre)[i]].getTitre(), 
		    								   vecteurTestLivre[rechercherTitre(pt, vecteurTestLivre)[i]].getPages());
		}
		System.out.format("+-----------+-----------------+------+%n");
	}
	
}
}





/*Sans doute, l’une des méthodes les plus utilisées de cette classe. Elle permet de comparer deux chaînes et de retourner si la première est égale, plus
petite ou plus grande à la seconde chaîne. Elle existe en deux saveurs, la première vérifie les chaînes telles que transmises, la seconde ignore les
majuscules et minuscules lors de la comparaison. Les valeurs retournées sont 0 si les chaînes sont égales, une valeur < 0 si la chaîne en cours est
plus petite que celle donnée en paramètre et une valeur > 0 si la chaîne en cours est plus grande que celle donnée en paramètre.
public int compareTo(String chaine);
public int compareToIgnoreCase(String chaine);
Exemple :
String chaine1 = "Julie";
String chaine2 = "jolie";
String chaine3 = "Paul";
 retourne une valeur négative Julie est plus petit que jolie le code ASCII de J est 74 et j est 106
System.out.println(chaine1.compareTo(chaine2));
// retourne une valeur positive Julie est plus grand que jolie
// les majuscules sont ignorées le u est plus grand que le o
System.out.println(chaine1.compareToIgnoreCase(chaine2));
// retourne une valeur négative Julie est plus petit que Paul
System.out.println(chaine1.compareToIgnoreCase(chaine3));
// retourne 0 les deux chaînes sont égales
System.out.println(chaine1.compareToIgnoreCase(chaine1));
*/
/*public static void trierParCote(Livre [] vecteurLivres)
{
	for (int i=0; i<vecteurLivres.length; i++)
	{
		if(vecteurLivres[i].compareTo(vecteurLivres[i+1])>0)
		{
			
		}
	}
}*/