package com.kosta.fac.dto;

public class Facility {

	
	  private String id;
	  private String type;
	  private String name;
	  private String typeName;
	  private Integer price;
	  private String time;
	  private String etc;
	  
	  
	  public Facility() {}


	  public Facility(String id, String type, String name, String typeName, Integer price, String time, String etc) {
		  super();
		  this.id = id;
		  this.type = type;
		  this.name = name;
		  this.typeName = typeName;
		  this.price = price;
		  this.time = time;
		  this.etc = etc;
	  }


		public String getId() {
			return id;
		}
		
		
		public void setId(String id) {
			this.id = id;
		}
		
		
		public String getType() {
			return type;
		}
		
		
		public void setType(String type) {
			this.type = type;
		}
		
		
		public String getName() {
			return name;
		}
		
		
		public void setName(String name) {
			this.name = name;
		}
		
		
		public String getTypeName() {
			return typeName;
		}
		
		
		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}
		
		
		public Integer getPrice() {
			return price;
		}
		
		
		public void setPrice(Integer price) {
			this.price = price;
		}
		
		
		public String getTime() {
			return time;
		}
		
		
		public void setTime(String time) {
			this.time = time;
		}
		
		
		public String getEtc() {
			return etc;
		}
		
		
		public void setEtc(String etc) {
			this.etc = etc;
		}


		@Override
		public String toString() {
			return "Facility [id=" + id + ", type=" + type + ", name=" + name + ", typeName=" + typeName + ", price="
					+ price + ", time=" + time + ", etc=" + etc + "]";
		}
}
