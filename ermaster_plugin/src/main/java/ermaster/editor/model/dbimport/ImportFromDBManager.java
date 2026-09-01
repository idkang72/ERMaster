package ermaster.editor.model.dbimport;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.diagram_contents.element.node.table.ERTable;
import ermaster.editor.model.settings.DBSetting;

public interface ImportFromDBManager {

	public void init(Connection con, DBSetting dbSetting, ERDiagram diagram,
			List<DBObject> dbObjectList, boolean useCommentAsLogicalNameButton,
			boolean mergeWord) throws SQLException;

	public void run() throws InvocationTargetException, InterruptedException;

	public List<ERTable> getImportedTables();
}
