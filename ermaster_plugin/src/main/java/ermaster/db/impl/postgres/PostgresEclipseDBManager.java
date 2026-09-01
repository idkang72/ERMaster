package ermaster.db.impl.postgres;

import org.eclipse.swt.widgets.Composite;
import ermaster.db.EclipseDBManagerBase;
import ermaster.db.impl.postgres.tablespace.PostgresTablespaceDialog;
import ermaster.editor.view.dialog.element.table_view.tab.AdvancedComposite;
import ermaster.editor.view.dialog.outline.tablespace.TablespaceDialog;

public class PostgresEclipseDBManager extends EclipseDBManagerBase {

	public String getId() {
		return PostgresDBManager.ID;
	}

	public AdvancedComposite createAdvancedComposite(Composite composite) {
		return new PostgresAdvancedComposite(composite);
	}

	public TablespaceDialog createTablespaceDialog() {
		return new PostgresTablespaceDialog();
	}

}
