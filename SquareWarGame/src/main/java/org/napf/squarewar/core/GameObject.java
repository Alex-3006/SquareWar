package org.napf.squarewar.core;

public class GameObject {
   protected double xpos;
   protected double ypos;
   protected String name;
   protected Appearance appearance;
   
   public GameObject(double xpos, double ypos, String name, AppearanceComponent... appearanceComponents){
	   this.xpos = xpos;
	   this.ypos = ypos;
	   this.name = name;
	   this.appearance = new Appearance(this, appearanceComponents);
	   GameController.getInstance().registerGameObject(this);
   }
   
   public GameObject(double xpos, double ypos, String name){
	   this(xpos, ypos, name, new AppearanceComponent[0]);
   }
   
   public GameObject(double xpos, double ypos){
	   this(xpos, ypos, "new GameObject");
   }
   

   public void moveBy(double x, double y) {
	   xpos += x;
	   ypos += y;
   }
   
   public void moveTo(double x, double y) {
	   xpos = x;
	   ypos = y;
   }
   
   public void update(){
	   
   }

	public double getXpos() {
		return xpos;
	}
	
	
	public void setXpos(double xpos) {
		this.xpos = xpos;
	}
	
	
	public double getYpos() {
		return ypos;
	}
	
	
	public void setYpos(double ypos) {
		this.ypos = ypos;
	}
	
	
	public String getName() {
		return name;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public Appearance getAppearance() {
		return appearance;
	}
	
	
	public void setAppearance(Appearance appearance) {
		this.appearance = appearance;
	}
	   
   
}
