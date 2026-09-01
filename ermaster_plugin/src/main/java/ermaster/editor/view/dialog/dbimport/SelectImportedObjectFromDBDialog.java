package ermaster.editor.view.dialog.dbimport;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import ermaster.ERDiagramActivator;
import ermaster.common.exception.InputException;
import ermaster.common.widgets.CompositeFactory;
import ermaster.editor.ERDiagramEditor;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.dbimport.DBObjectSet;

public class SelectImportedObjectFromDBDialog extends
		AbstractSelectImportedObjectDialog {

	private Button clearDiagramButton;

	private ERDiagramEditor editor;

	private ERDiagram diagram;

	public SelectImportedObjectFromDBDialog(Shell parentShell,
			ERDiagram diagram, DBObjectSet allObjectSet, ERDiagramEditor editor) {
		super(parentShell, diagram, allObjectSet);

		this.editor = editor;
		this.diagram = diagram;
	}

	@Override
	protected void initializeOptionGroup(Group group) {
		this.clearDiagramButton = CompositeFactory.createCheckbox(this, group,
				"label.clear.diagram", false);
		this.clearDiagramButton.setSelection(true);

		this.useCommentAsLogicalNameButton = CompositeFactory.createCheckbox(
				this, group, "label.use.comment.as.logical.name", false);
		super.initializeOptionGroup(group);
	}

	@Override
	protected void perfomeOK() throws InputException {
		super.perfomeOK();

		this.resultUseCommentAsLogicalName = this.useCommentAsLogicalNameButton
				.getSelection();

		if (this.clearDiagramButton.getSelection()) {
			if (!ERDiagramActivator.showConfirmDialog("label.clear.diagram.confirm")) {
				throw new InputException();
			} else {
				this.diagram.clear();
				this.editor.resetCommandStack();
			}
		}
	}
}
