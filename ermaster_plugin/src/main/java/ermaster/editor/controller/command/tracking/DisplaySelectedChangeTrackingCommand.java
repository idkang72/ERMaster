package ermaster.editor.controller.command.tracking;

import ermaster.editor.controller.command.AbstractCommand;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.diagram_contents.DiagramContents;

/**
 * 変更履歴の置換コマンド
 */
public class DisplaySelectedChangeTrackingCommand extends AbstractCommand {

	private ERDiagram diagram;

	private DiagramContents oldDiagramContents;

	private DiagramContents newDiagramContents;

	/**
	 * 置換コマンドを作成します。
	 * 
	 * @param diagram
	 * @param nodeElements
	 * @param columnGroups
	 */
	public DisplaySelectedChangeTrackingCommand(ERDiagram diagram,
			DiagramContents newDiagramContents) {
		this.diagram = diagram;

		this.oldDiagramContents = this.diagram.getDiagramContents();
		this.newDiagramContents = newDiagramContents;
	}

	/**
	 * 置換処理を実行する
	 */
	@Override
	protected void doExecute() {
		this.diagram.replaceContents(this.newDiagramContents);
		this.diagram.refresh();
	}

	/**
	 * 置換処理を元に戻す
	 */
	@Override
	protected void doUndo() {
		this.diagram.replaceContents(this.oldDiagramContents);
		this.diagram.refresh();
	}

}
