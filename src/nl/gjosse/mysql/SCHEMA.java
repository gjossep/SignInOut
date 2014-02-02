package nl.gjosse.mysql;

/**
 */
public enum SCHEMA {

	MySQL,
	SQLite;

	/**
	 * Method toString.
	 * @return String
	 */
	@Override
	public String toString(){
		return this.toString().toUpperCase();
	}

}