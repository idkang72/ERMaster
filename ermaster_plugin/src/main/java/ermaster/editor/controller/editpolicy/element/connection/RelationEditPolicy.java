package ermaster.editor.controller.editpolicy.element.connection;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.requests.GroupRequest;
import ermaster.editor.controller.command.diagram_contents.element.connection.relation.DeleteRelationCommand;
import ermaster.editor.model.diagram_contents.element.connection.Relation;

public class RelationEditPolicy extends ConnectionEditPolicy {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Command getDeleteCommand(GroupRequest grouprequest) {
		Relation relation = (Relation) this.getHost().getModel();
		return new DeleteRelationCommand(relation, null);
	}

	
}
