package ermaster.db;

import org.eclipse.swt.widgets.Composite;
import ermaster.editor.view.dialog.element.table_view.tab.AdvancedComposite;
import ermaster.editor.view.dialog.outline.tablespace.TablespaceDialog;

public interface EclipseDBManager {

	public String getId();

	public AdvancedComposite createAdvancedComposite(Composite composite);

	public TablespaceDialog createTablespaceDialog();

}
