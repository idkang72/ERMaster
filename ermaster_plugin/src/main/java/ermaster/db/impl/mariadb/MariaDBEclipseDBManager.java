package ermaster.db.impl.mariadb;

import org.eclipse.swt.widgets.Composite;

import ermaster.db.EclipseDBManagerBase;
import ermaster.db.impl.mysql.MySQLAdvancedComposite;
import ermaster.db.impl.mysql.tablespace.MySQLTablespaceDialog;
import ermaster.editor.view.dialog.element.table_view.tab.AdvancedComposite;
import ermaster.editor.view.dialog.outline.tablespace.TablespaceDialog;

public class MariaDBEclipseDBManager extends EclipseDBManagerBase {

	@Override
	public String getId() {
		return MariaDBDBManager.ID;
	}


	@Override
	public AdvancedComposite createAdvancedComposite(Composite composite) {
		return new MySQLAdvancedComposite(composite);
	}


	@Override
	public TablespaceDialog createTablespaceDialog() {
		return new MySQLTablespaceDialog();
	}
}
