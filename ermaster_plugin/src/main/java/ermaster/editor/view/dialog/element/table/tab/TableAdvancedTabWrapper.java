package ermaster.editor.view.dialog.element.table.tab;

import org.eclipse.swt.widgets.TabFolder;
import ermaster.common.dialog.AbstractTabbedDialog;
import ermaster.db.EclipseDBManagerFactory;
import ermaster.editor.model.diagram_contents.element.node.table.ERTable;
import ermaster.editor.view.dialog.element.table_view.tab.AdvancedComposite;
import ermaster.editor.view.dialog.element.table_view.tab.AdvancedTabWrapper;

public class TableAdvancedTabWrapper extends AdvancedTabWrapper {

	public TableAdvancedTabWrapper(AbstractTabbedDialog dialog,
			TabFolder parent, ERTable table) {
		super(dialog, parent, table);
	}

	@Override
	protected AdvancedComposite createAdvancedComposite() {
		return EclipseDBManagerFactory.getEclipseDBManager(
				this.tableView.getDiagram()).createAdvancedComposite(this);
	}

}
