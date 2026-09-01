package ermaster.editor.controller.editpart.element.node;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.ui.PlatformUI;
import ermaster.editor.controller.command.diagram_contents.element.node.table_view.ChangeTableViewPropertyCommand;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.diagram_contents.element.node.view.View;
import ermaster.editor.model.settings.Settings;
import ermaster.editor.view.dialog.element.view.ViewDialog;
import ermaster.editor.view.figure.view.ViewFigure;

public class ViewEditPart extends TableViewEditPart {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected IFigure createFigure() {
		ERDiagram diagram = this.getDiagram();
		Settings settings = diagram.getDiagramContents().getSettings();

		ViewFigure figure = new ViewFigure(settings.getTableStyle());

		this.changeFont(figure);

		return figure;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void performRequestOpen() {
		View view = (View) this.getModel();
		ERDiagram diagram = this.getDiagram();

		View copyView = view.copyData();

		ViewDialog dialog = new ViewDialog(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell(), this.getViewer(),
				copyView);

		if (dialog.open() == IDialogConstants.OK_ID) {
			CompoundCommand command = createChangeViewPropertyCommand(diagram,
					view, copyView);

			this.executeCommand(command.unwrap());
		}
	}

	public static CompoundCommand createChangeViewPropertyCommand(
			ERDiagram diagram, View view, View copyView) {
		CompoundCommand command = new CompoundCommand();

		ChangeTableViewPropertyCommand changeViewPropertyCommand = new ChangeTableViewPropertyCommand(
				view, copyView);
		command.add(changeViewPropertyCommand);

		return command;
	}

}
