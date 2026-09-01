package ermaster.db.impl.standard_sql;

import org.eclipse.swt.widgets.Composite;
import ermaster.db.EclipseDBManagerBase;
import ermaster.editor.view.dialog.element.table_view.tab.AdvancedComposite;
import ermaster.editor.view.dialog.outline.tablespace.TablespaceDialog;

public class StandardSQLEclipseDBManager extends EclipseDBManagerBase {

	public String getId() {
		return StandardSQLDBManager.ID;
	}

	public AdvancedComposite createAdvancedComposite(Composite composite) {
		return new StandardSQLAdvancedComposite(composite);
	}

	public TablespaceDialog createTablespaceDialog() {
		return null;
	}

}
