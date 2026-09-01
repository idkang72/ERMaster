package ermaster.db.impl.sqlite;

import org.eclipse.swt.widgets.Composite;
import ermaster.db.EclipseDBManagerBase;
import ermaster.editor.view.dialog.element.table_view.tab.AdvancedComposite;
import ermaster.editor.view.dialog.outline.tablespace.TablespaceDialog;

public class SQLiteEclipseDBManager extends EclipseDBManagerBase {

	public String getId() {
		return SQLiteDBManager.ID;
	}

	public AdvancedComposite createAdvancedComposite(Composite composite) {
		return new SQLiteAdvancedComposite(composite);
	}

	public TablespaceDialog createTablespaceDialog() {
		return null;
	}

}
