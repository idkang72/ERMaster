package ermaster.editor.controller.command.diagram_contents.element.node.table_view;

import ermaster.editor.controller.command.AbstractCommand;
import ermaster.editor.model.diagram_contents.element.node.table.TableView;
import ermaster.editor.model.diagram_contents.not_element.group.ColumnGroup;

public class AddColumnGroupCommand extends AbstractCommand {

	private TableView tableView;

	private ColumnGroup columnGroup;

	private int index;

	public AddColumnGroupCommand(TableView tableView, ColumnGroup columnGroup,
			int index) {
		this.tableView = tableView;
		this.columnGroup = columnGroup;
		this.index = index;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doExecute() {
		if (this.index != -1) {
			this.tableView.addColumn(index, columnGroup);
		}

		this.tableView.refresh();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doUndo() {
		this.tableView.removeColumn(columnGroup);

		this.tableView.refresh();
	}

}
