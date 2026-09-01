package ermaster.editor.controller.command.diagram_contents.not_element.trigger;

import ermaster.editor.controller.command.AbstractCommand;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.diagram_contents.not_element.trigger.Trigger;
import ermaster.editor.model.diagram_contents.not_element.trigger.TriggerSet;

public class DeleteTriggerCommand extends AbstractCommand {

	private TriggerSet triggerSet;

	private Trigger trigger;

	public DeleteTriggerCommand(ERDiagram diagram, Trigger trigger) {
		this.triggerSet = diagram.getDiagramContents().getTriggerSet();
		this.trigger = trigger;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doExecute() {
		this.triggerSet.remove(this.trigger);
		this.triggerSet.refresh();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doUndo() {
		this.triggerSet.addObject(this.trigger);
		this.triggerSet.refresh();
	}
	
}
