package nl.gjosse.mysql;

public enum SCHEMA {

	MySQL,
	SQLite;

	@Override
	public String toString(){
		return this.toString().toUpperCase();
	}

}