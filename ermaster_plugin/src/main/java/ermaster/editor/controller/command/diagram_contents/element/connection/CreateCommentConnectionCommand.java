package ermaster.editor.controller.command.diagram_contents.element.connection;

import ermaster.editor.model.diagram_contents.element.connection.ConnectionElement;
import ermaster.editor.model.diagram_contents.element.node.note.Note;

public class CreateCommentConnectionCommand extends CreateConnectionCommand {

	public CreateCommentConnectionCommand(ConnectionElement connection) {
		super(connection);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canExecute() {
		if (!super.canExecute()) {
			return false;
		}

		if (!(this.source.getModel() instanceof Note)
				&& !(this.target.getModel() instanceof Note)) {
			return false;
		}

		return true;
	}

}
