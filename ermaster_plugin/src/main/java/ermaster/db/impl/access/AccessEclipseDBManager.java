package ermaster.db.impl.access;

import org.eclipse.swt.widgets.Composite;
import ermaster.db.EclipseDBManagerBase;
import ermaster.editor.view.dialog.element.table_view.tab.AdvancedComposite;
import ermaster.editor.view.dialog.outline.tablespace.TablespaceDialog;

public class AccessEclipseDBManager extends EclipseDBManagerBase {

	public String getId() {
		return AccessDBManager.ID;
	}

	public AdvancedComposite createAdvancedComposite(Composite composite) {
		return new AccessAdvancedComposite(composite);
	}

	public TablespaceDialog createTablespaceDialog() {
		return null;
	}

}
