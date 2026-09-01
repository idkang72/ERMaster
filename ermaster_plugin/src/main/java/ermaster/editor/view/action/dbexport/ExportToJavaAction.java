package ermaster.editor.view.action.dbexport;

import ermaster.ImageKey;
import ermaster.editor.ERDiagramEditor;
import ermaster.editor.view.dialog.dbexport.AbstractExportDialog;
import ermaster.editor.view.dialog.dbexport.ExportToJavaDialog;

public class ExportToJavaAction extends AbstractExportWithDialogAction {

	public static final String ID = ExportToJavaAction.class.getName();

	public ExportToJavaAction(ERDiagramEditor editor) {
		super(ID, "action.title.export.java", ImageKey.EXPORT_TO_JAVA, editor);
	}

	@Override
	protected AbstractExportDialog getExportDialog() {
		return new ExportToJavaDialog();
	}
}
