package ermaster.db.impl.oracle;

import org.eclipse.swt.widgets.Composite;
import ermaster.db.EclipseDBManagerBase;
import ermaster.db.impl.oracle.tablespace.OracleTablespaceDialog;
import ermaster.editor.view.dialog.element.table_view.tab.AdvancedComposite;
import ermaster.editor.view.dialog.outline.tablespace.TablespaceDialog;

public class OracleEclipseDBManager extends EclipseDBManagerBase {

	public String getId() {
		return OracleDBManager.ID;
	}

	public AdvancedComposite createAdvancedComposite(Composite composite) {
		return new OracleAdvancedComposite(composite);
	}

	public TablespaceDialog createTablespaceDialog() {
		return new OracleTablespaceDialog();
	}

}
