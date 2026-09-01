package ermaster.db.impl.h2;

import org.eclipse.swt.widgets.Composite;
import ermaster.db.EclipseDBManagerBase;
import ermaster.editor.view.dialog.element.table_view.tab.AdvancedComposite;
import ermaster.editor.view.dialog.outline.tablespace.TablespaceDialog;

public class H2EclipseDBManager extends EclipseDBManagerBase {

	public String getId() {
		return H2DBManager.ID;
	}

	public AdvancedComposite createAdvancedComposite(Composite composite) {
		return new H2AdvancedComposite(composite);
	}

	public TablespaceDialog createTablespaceDialog() {
		return null;
	}

}
