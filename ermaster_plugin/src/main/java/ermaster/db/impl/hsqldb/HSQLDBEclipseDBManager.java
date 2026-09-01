package ermaster.db.impl.hsqldb;

import org.eclipse.swt.widgets.Composite;
import ermaster.db.EclipseDBManagerBase;
import ermaster.editor.view.dialog.element.table_view.tab.AdvancedComposite;
import ermaster.editor.view.dialog.outline.tablespace.TablespaceDialog;

public class HSQLDBEclipseDBManager extends EclipseDBManagerBase {

	public String getId() {
		return HSQLDBDBManager.ID;
	}

	public AdvancedComposite createAdvancedComposite(Composite composite) {
		return new HSQLDBAdvancedComposite(composite);
	}

	public TablespaceDialog createTablespaceDialog() {
		return null;
	}

}
