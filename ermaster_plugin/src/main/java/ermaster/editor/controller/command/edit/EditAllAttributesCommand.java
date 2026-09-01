package ermaster.editor.controller.command.edit;

import ermaster.editor.controller.command.AbstractCommand;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.diagram_contents.DiagramContents;

/**
 * DiagramContents の置換コマンド
 */
public class EditAllAttributesCommand extends AbstractCommand {

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
	public EditAllAttributesCommand(ERDiagram diagram,
			DiagramContents newDiagramContents) {
		this.diagram = diagram;

		this.oldDiagramContents = this.diagram.getDiagramContents();
		this.newDiagramContents = newDiagramContents;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doExecute() {
		this.diagram.replaceContents(this.newDiagramContents);
		this.diagram.refresh();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doUndo() {
		this.diagram.replaceContents(this.oldDiagramContents);
		this.diagram.refresh();
	}

}
