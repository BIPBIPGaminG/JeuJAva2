package fr.lejeu.main;

public enum screenTypes {

	  //Objets directement construits
	  TITLE (1, "title"),
	  GAME (2, "game");
	   
	  private int id;
	  private String type = "";
	   
	  //Constructeur
	  screenTypes(int id, String type){
	    this.type = type;
	    this.id = id;
	  }
	   
	  public String getType(){
	    return type;
	  }
	  
	  public int getTypeId(){
		  return id;
	  }
}
