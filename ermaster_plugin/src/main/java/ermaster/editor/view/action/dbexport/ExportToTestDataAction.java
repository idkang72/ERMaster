package ermaster.editor.view.action.dbexport;

import ermaster.ImageKey;
import ermaster.editor.ERDiagramEditor;
import ermaster.editor.view.dialog.dbexport.AbstractExportDialog;
import ermaster.editor.view.dialog.dbexport.ExportToTestDataDialog;

public class ExportToTestDataAction extends AbstractExportWithDialogAction {

	public static final String ID = ExportToTestDataAction.class.getName();

	public ExportToTestDataAction(ERDiagramEditor editor) {
		super(ID, "action.title.export.test.data",
				ImageKey.EXPORT_TO_TEST_DATA, editor);
	}

	@Override
	protected AbstractExportDialog getExportDialog() {
		return new ExportToTestDataDialog(this.getDiagram()
				.getDiagramContents().getTestDataList());
	}

}
