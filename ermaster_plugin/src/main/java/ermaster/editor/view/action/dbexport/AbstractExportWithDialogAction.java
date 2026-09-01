package ermaster.editor.view.action.dbexport;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Event;
import ermaster.ERDiagramActivator;
import ermaster.ResourceString;
import ermaster.editor.ERDiagramEditor;
import ermaster.editor.controller.command.settings.ChangeSettingsCommand;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.view.action.AbstractBaseAction;
import ermaster.editor.view.dialog.dbexport.AbstractExportDialog;

public abstract class AbstractExportWithDialogAction extends AbstractBaseAction {

	public AbstractExportWithDialogAction(String id, String titleResource,
			String imageKey, ERDiagramEditor editor) {
		super(id, ResourceString.getResourceString(titleResource), editor);
		this.setImageDescriptor(ERDiagramActivator.getImageDescriptor(imageKey));
	}

	@Override
	public void execute(Event event) {
		ERDiagram diagram = this.getDiagram();

		AbstractExportDialog dialog = this.getExportDialog();
		dialog.init(diagram);

		if (dialog.open() == IDialogConstants.OK_ID) {
			ChangeSettingsCommand command = new ChangeSettingsCommand(diagram,
					dialog.getSettings(), false);
			this.execute(command);
		}
	}

	protected abstract AbstractExportDialog getExportDialog();

}