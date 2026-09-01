package ermaster.db.impl.access;

import ermaster.editor.model.dbimport.ImportFromDBManagerEclipseBase;

public class AccessTableImportManager extends ImportFromDBManagerEclipseBase {

	@Override
	protected String getViewDefinitionSQL(String schema) {
		return null;
	}
}
