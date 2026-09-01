package ermaster.editor.view.action.edit;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.DeleteAction;
import org.eclipse.ui.IWorkbenchPart;

import ermaster.ResourceString;
import ermaster.editor.ERDiagramEditor;
import ermaster.editor.controller.command.WithoutUpdateCommandWrapper;
import ermaster.editor.model.ERDiagram;

public class DeleteWithoutUpdateAction extends DeleteAction {

	private ERDiagramEditor editor;

	public DeleteWithoutUpdateAction(ERDiagramEditor editor) {
		super((IWorkbenchPart) editor);
		this.editor = editor;
		this.setText(ResourceString.getResourceString("action.title.delete"));
		this.setToolTipText(ResourceString
				.getResourceString("action.title.delete"));

		this.setActionDefinitionId("org.eclipse.ui.edit.delete");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Command createDeleteCommand(List objects) {
		List<EditPart> editPartList = new ArrayList<EditPart>();
		for (Object object : objects) {
			if (object instanceof EditPart) {
				editPartList.add((EditPart)object);
			}
		}
		Command command = super.createDeleteCommand(editPartList);

		if (command == null) {
			return null;
		}

		if (command instanceof CompoundCommand) {
			CompoundCommand compoundCommand = (CompoundCommand) command;
			if (compoundCommand.getCommands().isEmpty()) {
				return null;
			}

			if (compoundCommand.getCommands().size() == 1) {
				return compoundCommand;
			}

			EditPart editPart = this.editor.getGraphicalViewer().getContents();
			ERDiagram diagram = (ERDiagram) editPart.getModel();

			return new WithoutUpdateCommandWrapper(compoundCommand, diagram);
		}

		return command;
	}

	@Override
	protected boolean calculateEnabled() {
		Command cmd = createDeleteCommand(getSelectedObjects());
		if (cmd == null) {
			return false;
		}

		return true;
	}

}
