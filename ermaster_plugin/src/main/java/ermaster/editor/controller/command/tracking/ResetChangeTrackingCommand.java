package ermaster.editor.controller.command.tracking;

import ermaster.editor.controller.command.AbstractCommand;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.tracking.ChangeTrackingList;

/**
 * 変更履歴計算コマンド
 */
public class ResetChangeTrackingCommand extends AbstractCommand {

	private ERDiagram diagram;
	private ChangeTrackingList changeTrackingList;

	private boolean oldCalculated;

	public ResetChangeTrackingCommand(ERDiagram diagram) {
		this.diagram = diagram;
		this.changeTrackingList = this.diagram.getChangeTrackingList();
		this.oldCalculated = this.changeTrackingList.isCalculated();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doExecute() {
		this.changeTrackingList.setCalculated(false);
		this.diagram.refresh();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doUndo() {
		this.changeTrackingList.setCalculated(oldCalculated);
		this.diagram.refresh();
	}
}
