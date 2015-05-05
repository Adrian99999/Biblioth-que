package application;

public class Livre /*implements Comparable<Livre>*/
{
	//DECALARATION DES CONSTANTS 
	public static final String COTE_PAR_DEFAUT="AA000";
	public static final int NBR_CAR_COTE=5;
	public static final String TITRE_PAR_DEFAUT="TITRE PAR DEFAUT";
	public static final int LONGUEUR_TITRE_MIN=2;
	public static final int LONGUEUR_TITRE_MAX=20;
	public static final int PAGES_PAR_DEFAUT=2;
	public static final int PAGES_MIN=2;
	public static final float VALEUR_PAR_DEFAUT=0;
	public static final float VALEUR_MIN=0;
	//declaration des attributs pour chaque objecte de type Livre
	private String cote=null;
	private String titre=null;
	private int pages=0;
	private float valeur=0;
	
	//le CONSTRUCTEUR PAR DEFAUT
	public Livre(String pCote, String pTitre, int pPages, float pValeur)
	{
		boolean ok=setCote(pCote) && setTitre(pTitre) && setPages(pPages) && setValeur(pValeur);
		if (!ok)
		{
			setCote(COTE_PAR_DEFAUT);
			setTitre(TITRE_PAR_DEFAUT);
			setPages(PAGES_PAR_DEFAUT);
			setValeur(VALEUR_PAR_DEFAUT);
		}
	}
	public String getCote() {
		return cote;
	}

	public boolean setCote(String cote) {
		boolean ok=validerCote(cote);
		if(ok)
		{
			this.cote = cote;
		}
		return ok;
	}

	public static boolean validerCote(String pCote)
	{
		return (pCote != null) &&(pCote.matches("[A-Z][A-Z][0-9][0-9][0-9]")) ;
	}
	
	public static boolean validerCoteComparaisonParCaracter(String pCote)
	{
		return (pCote != null) && (pCote.length() == NBR_CAR_COTE) 
				//Character est une class par defaut
				&& (Character.isUpperCase(pCote.charAt(0)))
				&& (Character.isUpperCase(pCote.charAt(1)))
				&& (Character.isDigit(pCote.charAt(2)))
				&& (Character.isDigit(pCote.charAt(3)))
				&& (Character.isDefined(pCote.charAt(4)))
				;
	}
	public String getTitre() {
		return titre;
	}

	public boolean setTitre(String titre) {
		if(validerTitre(titre))
		{
		this.titre = titre;
		}
		return validerTitre(titre);
	}
	
	public static boolean validerTitre( String pTitre)
	{
		return (pTitre.length()>LONGUEUR_TITRE_MIN && pTitre.length() < LONGUEUR_TITRE_MAX);
	}

	public int getPages() {
		return pages;
	}

	public boolean setPages(int pages) {
		boolean ok= validerPages(pages);
		if(ok)
		{
		this.pages = pages;
		}
		return ok;
	}
	
	public static boolean validerPages(int pPages)
	{
		return (pPages>=2);
	}

	public float getValeur() {
		return valeur;
	}

	public boolean setValeur(float valeur) {
		boolean ok=validerValeur(valeur);
		if(ok)
		{
			this.valeur = valeur;
		}
		return ok;
	}
	
	public static boolean  validerValeur(float pValeur)
	{
		return (pValeur >=0);
	}
	
	public static String filtrerTitre(String pTitre)
	{
		if(pTitre != null)
		{
			pTitre=pTitre.trim().toUpperCase();
		}
		return pTitre;
	}
	
	public static String filtrerCote(String pCote)
	{
		if(pCote != null)
		{
			pCote = pCote.trim().toUpperCase();
			
			if(pCote.length() > NBR_CAR_COTE)
			{
				pCote = pCote.substring(0, Livre.NBR_CAR_COTE);
			}
		}
		return pCote;
	}
	
	public int compareTo(Livre pLivre)
	{
		int valRetour = 1;
		if(pLivre != null)
		{
			valRetour=this.getPages()-pLivre.getPages();
		}
		return valRetour;
	}
	
	public String enChaineComplete ()
	{
		String text="";
		text=this.getCote() +" "+this.getPages()+" "+this.getTitre();
		return text;
	}
	public String enChaineAbregee()
	{
		String infoLivre="";
		infoLivre = "Cote : " +this.getCote()+"    "+"Titre : "+this.getTitre()+"\n"+
					"Pages : "+ this.getPages() + "     "+ "Valeur : "+this.getValeur()+"$";
		return infoLivre;
	}
	
	public static void main(String [] args)
	{
		Livre livre1 = new Livre ("AA111", "Titre",12,30);
		System.out.println(livre1.getCote()+ "\n"+
						   livre1.getTitre()+"\n"+
						   livre1.getPages()+"\n"+
						   livre1.getValeur());
		Livre livre2 = new Livre("BB111", "Titre", 14, 35);
		System.out.println(livre1.compareTo(livre2));
		
		String testCote="  AA555";
		System.out.println(testCote);
		System.out.println(filtrerCote(testCote));
	}
	
}
