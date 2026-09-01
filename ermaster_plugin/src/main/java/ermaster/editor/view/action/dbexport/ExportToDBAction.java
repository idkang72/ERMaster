package ermaster.editor.view.action.dbexport;

import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.RetargetAction;
import ermaster.ERDiagramActivator;
import ermaster.ImageKey;
import ermaster.ResourceString;
import ermaster.editor.ERDiagramEditor;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.dbexport.ddl.validator.ValidateResult;
import ermaster.editor.model.dbexport.ddl.validator.Validator;
import ermaster.editor.model.settings.DBSetting;
import ermaster.editor.view.action.AbstractBaseAction;
import ermaster.editor.view.dialog.dbexport.ExportDBSettingDialog;
import ermaster.editor.view.dialog.dbexport.ExportErrorDialog;
import ermaster.editor.view.dialog.dbexport.ExportToDBDialog;

public class ExportToDBAction extends AbstractBaseAction {

	public static final String ID = ExportToDBAction.class.getName();

	private Validator validator;

	public ExportToDBAction(ERDiagramEditor editor) {
		super(ID, ResourceString.getResourceString("action.title.export.db"),
				editor);

		this.validator = new Validator();
	}

	@Override
	public void execute(Event event) {
		ERDiagram diagram = this.getDiagram();

		List<ValidateResult> errorList = validator.validate(diagram);

		if (!errorList.isEmpty()) {
			ExportErrorDialog dialog = new ExportErrorDialog(PlatformUI
					.getWorkbench().getActiveWorkbenchWindow().getShell(),
					errorList);
			dialog.open();
			return;
		}

		ExportDBSettingDialog dialog = new ExportDBSettingDialog(PlatformUI
				.getWorkbench().getActiveWorkbenchWindow().getShell(), diagram);

		if (dialog.open() == IDialogConstants.OK_ID) {
			String ddl = dialog.getDdl();

			DBSetting dbSetting = dialog.getDbSetting();

			ExportToDBDialog exportToDBDialog = new ExportToDBDialog(PlatformUI
					.getWorkbench().getActiveWorkbenchWindow().getShell(),
					diagram, dbSetting, ddl);

			exportToDBDialog.open();
		}
	}

	public static class ExportToDBRetargetAction extends RetargetAction {

		public ExportToDBRetargetAction() {
			super(ID, ResourceString
					.getResourceString("action.title.export.db"));

			this.setImageDescriptor(ERDiagramActivator
					.getImageDescriptor(ImageKey.EXPORT_TO_DB));
			this.setToolTipText(this.getText());
		}

	}
}
