package ermaster.editor.view.dialog.element.table;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import ermaster.common.dialog.ValidatableTabWrapper;
import ermaster.editor.model.diagram_contents.element.node.table.ERTable;
import ermaster.editor.view.dialog.element.table.tab.AttributeTabWrapper;
import ermaster.editor.view.dialog.element.table.tab.ComplexUniqueKeyTabWrapper;
import ermaster.editor.view.dialog.element.table.tab.ConstraintTabWrapper;
import ermaster.editor.view.dialog.element.table.tab.IndexTabWrapper;
import ermaster.editor.view.dialog.element.table.tab.TableAdvancedTabWrapper;
import ermaster.editor.view.dialog.element.table_view.TableViewDialog;
import ermaster.editor.view.dialog.element.table_view.tab.DescriptionTabWrapper;

public class TableDialog extends TableViewDialog {

	private ERTable copyData;

	public TableDialog(Shell parentShell, EditPartViewer viewer,
			ERTable copyData) {
		super(parentShell, viewer);

		this.copyData = copyData;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getTitle() {
		return "dialog.title.table";
	}

	@Override
	protected List<ValidatableTabWrapper> createTabWrapperList(
			TabFolder tabFolder) {
		List<ValidatableTabWrapper> list = new ArrayList<ValidatableTabWrapper>();

		list.add(new AttributeTabWrapper(this, tabFolder, this.copyData));
		list.add(new DescriptionTabWrapper(this, tabFolder, this.copyData));
		list.add(new ComplexUniqueKeyTabWrapper(this, tabFolder, this.copyData));
		list.add(new ConstraintTabWrapper(this, tabFolder, this.copyData));
		list.add(new IndexTabWrapper(this, tabFolder, this.copyData));
		list.add(new TableAdvancedTabWrapper(this, tabFolder, this.copyData));

		return list;
	}
}
