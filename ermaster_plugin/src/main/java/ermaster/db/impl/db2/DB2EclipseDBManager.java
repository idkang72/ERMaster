package ermaster.db.impl.db2;

import org.eclipse.swt.widgets.Composite;
import ermaster.db.EclipseDBManagerBase;
import ermaster.db.impl.db2.tablespace.DB2TablespaceDialog;
import ermaster.editor.view.dialog.element.table_view.tab.AdvancedComposite;
import ermaster.editor.view.dialog.outline.tablespace.TablespaceDialog;

public class DB2EclipseDBManager extends EclipseDBManagerBase {

	public String getId() {
		return DB2DBManager.ID;
	}

	public AdvancedComposite createAdvancedComposite(Composite composite) {
		return new DB2AdvancedComposite(composite);
	}

	public TablespaceDialog createTablespaceDialog() {
		return new DB2TablespaceDialog();
	}

}
