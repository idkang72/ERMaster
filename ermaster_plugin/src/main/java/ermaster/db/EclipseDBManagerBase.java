package ermaster.db;

public abstract class EclipseDBManagerBase implements EclipseDBManager {

	public EclipseDBManagerBase() {
		EclipseDBManagerFactory.addDB(this);
	}

}
