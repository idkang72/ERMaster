package ermaster.editor.controller.command.diagram_contents.not_element.sequence;

import ermaster.editor.controller.command.AbstractCommand;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.diagram_contents.not_element.sequence.Sequence;
import ermaster.editor.model.diagram_contents.not_element.sequence.SequenceSet;

public class DeleteSequenceCommand extends AbstractCommand {

	private SequenceSet sequenceSet;

	private Sequence sequence;

	public DeleteSequenceCommand(ERDiagram diagram, Sequence sequence) {
		this.sequenceSet = diagram.getDiagramContents().getSequenceSet();
		this.sequence = sequence;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doExecute() {
		this.sequenceSet.remove(this.sequence);
		this.sequenceSet.refresh();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doUndo() {
		this.sequenceSet.addObject(this.sequence);
		this.sequenceSet.refresh();
	}
}
