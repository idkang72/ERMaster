package ermaster.editor.persistent;

import java.io.IOException;
import java.io.InputStream;

import ermaster.editor.model.ERDiagram;
import ermaster.editor.persistent.impl.PersistentXmlImpl;

public abstract class Persistent {

	private static Persistent persistent = new PersistentXmlImpl();

	public static Persistent getInstance() {
		return persistent;
	}

	abstract public InputStream createInputStream(ERDiagram diagram)
			throws IOException;

	abstract public ERDiagram load(InputStream in) throws Exception;

}