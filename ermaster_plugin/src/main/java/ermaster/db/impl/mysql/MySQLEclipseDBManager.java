package ermaster.db.impl.mysql;

import org.eclipse.swt.widgets.Composite;
import ermaster.db.EclipseDBManagerBase;
import ermaster.db.impl.mysql.tablespace.MySQLTablespaceDialog;
import ermaster.editor.view.dialog.element.table_view.tab.AdvancedComposite;
import ermaster.editor.view.dialog.outline.tablespace.TablespaceDialog;

public class MySQLEclipseDBManager extends EclipseDBManagerBase {

	public String getId() {
		return MySQLDBManager.ID;
	}

	public AdvancedComposite createAdvancedComposite(Composite composite) {
		return new MySQLAdvancedComposite(composite);
	}

	public TablespaceDialog createTablespaceDialog() {
		return new MySQLTablespaceDialog();
	}

}
