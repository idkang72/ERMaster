package ermaster.editor.persistent.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import ermaster.editor.model.ERDiagram;
import ermaster.editor.persistent.Persistent;

public class PersistentSerializeImpl extends Persistent {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InputStream createInputStream(ERDiagram diagram) throws IOException {
		InputStream inputStream = null;
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		ObjectOutputStream oos = new ObjectOutputStream(out);
		oos.writeObject(diagram);
		oos.close();

		inputStream = new ByteArrayInputStream(out.toByteArray());

		return inputStream;
	}

	@Override
	public ERDiagram load(InputStream in) throws Exception {
		return (ERDiagram) ((ObjectInputStream) in).readObject();
	}

}