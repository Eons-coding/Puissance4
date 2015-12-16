package be.ephec.menu;

public class ProfilInfo {
	private String nom, sexe, age, couleur, force;

	  public ProfilInfo(){}
	  public ProfilInfo(String nom, String sexe, String age, String couleur, String force){
	    this.nom = nom;
	    this.sexe = sexe;
	    this.age = age;
	    this.couleur = couleur;
	    this.force = force;
	  }

	  public String toString(){
	    String str;
	    if(this.nom != null && this.sexe != null && this.force != null && this.age != null && this.couleur != null){
	      str = "";
	      str += "Pseudo : " + this.nom + "\n";
	      str += "Sexe : " + this.sexe + "\n";
	      str += "Age : " + this.age + "\n";
	      str += "Jetons : " + this.couleur + "\n";
	      str += "Force : " + this.force + "\n";
	    }
	    else{
	      str = "Aucune information !";
	    }
	    return str;
	  }
	}

