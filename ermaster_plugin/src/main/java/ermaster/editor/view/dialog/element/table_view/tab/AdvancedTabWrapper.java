package ermaster.editor.view.dialog.element.table_view.tab;

import org.eclipse.swt.widgets.TabFolder;
import ermaster.common.dialog.AbstractTabbedDialog;
import ermaster.common.dialog.ValidatableTabWrapper;
import ermaster.common.exception.InputException;
import ermaster.editor.model.diagram_contents.element.node.table.ERTable;
import ermaster.editor.model.diagram_contents.element.node.table.TableView;

public class AdvancedTabWrapper extends ValidatableTabWrapper {

	protected TableView tableView;

	private AdvancedComposite composite;

	public AdvancedTabWrapper(AbstractTabbedDialog dialog, TabFolder parent,
			TableView tableView) {
		super(dialog, parent, "label.advanced.settings");

		this.tableView = tableView;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validatePage() throws InputException {
		this.composite.validate();
	}

	protected AdvancedComposite createAdvancedComposite() {
		return new AdvancedComposite(this);
	}

	@Override
	public void initComposite() {
		this.composite = this.createAdvancedComposite();

		ERTable table = null;

		if (this.tableView instanceof ERTable) {
			table = (ERTable) this.tableView;
		}

		this.composite.initialize(this.dialog,
				this.tableView.getTableViewProperties(),
				this.tableView.getDiagram(), table);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setInitFocus() {
		this.composite.setInitFocus();
	}

	@Override
	public void perfomeOK() {
	}
}
