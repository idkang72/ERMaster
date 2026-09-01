package ermaster.editor.view.action.dbexport;

import ermaster.ImageKey;
import ermaster.editor.ERDiagramEditor;
import ermaster.editor.view.dialog.dbexport.AbstractExportDialog;
import ermaster.editor.view.dialog.dbexport.ExportToDDLDialog;

public class ExportToDDLAction extends AbstractExportWithDialogAction {

	public static final String ID = ExportToDDLAction.class.getName();

	public ExportToDDLAction(ERDiagramEditor editor) {
		super(ID, "action.title.export.ddl", ImageKey.EXPORT_DDL, editor);
	}

	@Override
	protected AbstractExportDialog getExportDialog() {
		return new ExportToDDLDialog();
	}

}
