package application;
	
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class Interface {
	private MenuBar mainMenuAdrian = null;
	private Menu menuTrier = null;
	protected MenuItem menuItemTrierCote = null;
	protected MenuItem menuItemTrieTitre = null;
	protected MenuItem menuItemTrieNbPage = null;
	private Menu menuRecherche = null;
	protected MenuItem menuItemRechCote = null;
	protected MenuItem menuItemRechTitre = null;
	private Menu menuFusion = null;
	protected MenuItem menuItemFusion = null;
	private Menu menuQuitter = null;
	protected MenuItem menuItemQuitter = null;
	protected Scene scene = null;
	private BorderPane root = null;
	private VBox zoneHaut = null;
	private VBox zoneCentre = null;
	protected TextArea zoneText = null;
	
	public Interface()
	{
		creerZoneMenus();
		creerZoneCentre();
		
		root = new BorderPane();
		scene = new Scene(root,500,600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		root.setTop(zoneHaut);
		root.setCenter(zoneCentre);
	}
	
	/*@Override
	public void start(Stage primaryStage) {
		try {
			
			creerZoneMenus();
			creerZoneCentre();
			
			root = new BorderPane();
			scene = new Scene(root,500,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			root.setTop(zoneHaut);
			root.setCenter(zoneCentre);
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Gestion de Livres");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}*/
	
	public void creerZoneMenus()
	{
		zoneHaut = new VBox();
		
		mainMenu =new MenuBar();
		
		menuTrier = new Menu("Trier Livres");
		menuItemTrierCote = new MenuItem("Trier Livres par cote");
		menuItemTrierCote.getStyleClass().add("background");
		menuItemTrieTitre = new MenuItem("Trier Livres par titre");
		menuItemTrieNbPage = new MenuItem("Trier Livres par le nombre de pages");
		menuTrier.getItems().addAll(menuItemTrierCote, menuItemTrieTitre, menuItemTrieNbPage);
		
		menuRecherche = new Menu("Recherche de Livres");
		menuItemRechCote = new MenuItem("Recherche de Livres par la cote");
		menuItemRechTitre = new MenuItem("Recherche de Livres par le titre");
		menuRecherche.getItems().addAll(menuItemRechCote, menuItemRechTitre);
		
		menuFusion = new Menu("Fusion");
		menuItemFusion = new MenuItem("Fusion selon la cote");
		menuFusion.getItems().add(menuItemFusion);
		
		menuQuitter = new Menu("Quitter");
		menuItemQuitter = new MenuItem("Exit");
		menuItemQuitter.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				System.exit(0);
			}
		}
				
				);
		menuQuitter.getItems().add(menuItemQuitter);
		
		mainMenu.getMenus().addAll(menuTrier, menuRecherche, menuFusion, menuQuitter);
		mainMenu.setStyle("-fx-background-color: #00CCFF");
		zoneHaut.getChildren().add(mainMenu);
	}
	
	public void creerZoneCentre()
	{
		zoneCentre = new VBox();
		
		zoneText= new TextArea();
		zoneText.setPrefRowCount(30);
		zoneText.setPrefColumnCount(100);
		zoneText.setPrefWidth(300);
		//?????
		zoneText.setWrapText(true);
		
		zoneText.setText("111");
		//pour ne pouvoar ecrire
		zoneText.setEditable(false);
		zoneText.getStyleClass().add("text");
		zoneCentre.getChildren().add(zoneText);
	}
	
	
	/*public static void main(String[] args) {
		launch(args);
	}*/
}
