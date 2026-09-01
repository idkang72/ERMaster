package ermaster.editor.controller.command.diagram_contents.not_element.sequence;

import ermaster.editor.controller.command.AbstractCommand;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.diagram_contents.not_element.sequence.Sequence;
import ermaster.editor.model.diagram_contents.not_element.sequence.SequenceSet;

public class EditSequenceCommand extends AbstractCommand {

	private SequenceSet sequenceSet;

	private Sequence oldSequence;

	private Sequence newSequence;

	public EditSequenceCommand(ERDiagram diagram, Sequence oldSequence,
			Sequence newSequence) {
		this.sequenceSet = diagram.getDiagramContents().getSequenceSet();
		this.oldSequence = oldSequence;
		this.newSequence = newSequence;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doExecute() {
		this.sequenceSet.remove(this.oldSequence);
		this.sequenceSet.addObject(this.newSequence);
		this.sequenceSet.refresh();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doUndo() {
		this.sequenceSet.remove(this.newSequence);
		this.sequenceSet.addObject(this.oldSequence);
		this.sequenceSet.refresh();
	}
}
