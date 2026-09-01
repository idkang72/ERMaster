package ermaster.editor.controller.command;

import org.eclipse.gef.commands.Command;
import ermaster.ERDiagramActivator;

public abstract class AbstractCommand extends Command {

	/**
	 * {@inheritDoc}
	 */
	@Override
	final public void execute() {
		try {
			doExecute();

		} catch (Exception e) {
			ERDiagramActivator.showExceptionDialog(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	final public void undo() {
		try {
			doUndo();
		} catch (Exception e) {
			ERDiagramActivator.showExceptionDialog(e);
		}
	}

	abstract protected void doExecute();

	abstract protected void doUndo();
}
