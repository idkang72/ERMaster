package ermaster.db.impl.sqlserver;

import org.eclipse.swt.widgets.Composite;
import ermaster.db.EclipseDBManagerBase;
import ermaster.db.impl.sqlserver.tablespace.SqlServerTablespaceDialog;
import ermaster.editor.view.dialog.element.table_view.tab.AdvancedComposite;
import ermaster.editor.view.dialog.outline.tablespace.TablespaceDialog;

public class SqlServerEclipseDBManager extends EclipseDBManagerBase {

	public String getId() {
		return SqlServerDBManager.ID;
	}

	public AdvancedComposite createAdvancedComposite(Composite composite) {
		return new SqlServerAdvancedComposite(composite);
	}

	public TablespaceDialog createTablespaceDialog() {
		return new SqlServerTablespaceDialog();
	}

}
