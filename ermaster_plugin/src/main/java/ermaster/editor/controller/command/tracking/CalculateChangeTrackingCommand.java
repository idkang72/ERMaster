package ermaster.editor.controller.command.tracking;

import java.util.List;

import ermaster.editor.controller.command.AbstractCommand;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.diagram_contents.element.node.NodeElement;
import ermaster.editor.model.diagram_contents.element.node.NodeSet;
import ermaster.editor.model.tracking.ChangeTrackingList;
import ermaster.editor.model.tracking.RemovedNodeElement;
import ermaster.editor.model.tracking.UpdatedNodeElement;

/**
 * 変更履歴計算コマンド
 */
public class CalculateChangeTrackingCommand extends AbstractCommand {

	private ERDiagram diagram;

	private NodeSet comparison;

	private ChangeTrackingList changeTrackingList;

	private List<NodeElement> oldAddedNodeElements;

	private List<UpdatedNodeElement> oldUpdatedNodeElements;

	private List<RemovedNodeElement> oldRemovedNodeElements;

	/**
	 * 変更履歴計算コマンドを作成します。
	 * 
	 * @param diagram
	 * @param comparison
	 */
	public CalculateChangeTrackingCommand(ERDiagram diagram, NodeSet comparison) {
		this.diagram = diagram;
		this.comparison = comparison;

		this.changeTrackingList = this.diagram.getChangeTrackingList();

		this.oldAddedNodeElements = this.changeTrackingList
				.getAddedNodeElementSet();
		this.oldUpdatedNodeElements = this.changeTrackingList
				.getUpdatedNodeElementSet();
		this.oldRemovedNodeElements = this.changeTrackingList
				.getRemovedNodeElementSet();
	}

	/**
	 * 変更履歴計算処理を実行する
	 */
	@Override
	protected void doExecute() {
		this.changeTrackingList.calculateUpdatedNodeElementSet(this.comparison,
				this.diagram.getDiagramContents().getContents());
		this.diagram.refresh();
	}

	/**
	 * 変更履歴計算処理を元に戻す
	 */
	@Override
	protected void doUndo() {
		this.changeTrackingList.restore(this.oldAddedNodeElements,
				this.oldUpdatedNodeElements, this.oldRemovedNodeElements);
		this.diagram.refresh();
	}
}
