package nl.gjosse.mysql;

/**
 * @author room-243-16
 * @version $Revision: 1.0 $
 */
public enum SCHEMA {

	MySQL,
	SQLite;

	/**
	 * Method toString.
	
	 * @return String */
	@Override
	public String toString(){
		return this.toString().toUpperCase();
	}

}