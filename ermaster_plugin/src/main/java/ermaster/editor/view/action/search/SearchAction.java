package ermaster.editor.view.action.search;

import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.PlatformUI;
import ermaster.ERDiagramActivator;
import ermaster.ImageKey;
import ermaster.ResourceString;
import ermaster.editor.ERDiagramEditor;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.view.action.AbstractBaseAction;
import ermaster.editor.view.dialog.search.SearchDialog;

public class SearchAction extends AbstractBaseAction {

	public static final String ID = SearchAction.class.getName();

	public SearchAction(ERDiagramEditor editor) {
		super(ID, ResourceString.getResourceString("action.title.find"), editor);
		this.setActionDefinitionId("org.eclipse.ui.edit.findReplace");
		this.setImageDescriptor(ERDiagramActivator.getImageDescriptor(ImageKey.FIND));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(Event event) {
		ERDiagram diagram = this.getDiagram();

		SearchDialog dialog = new SearchDialog(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell(), this
				.getGraphicalViewer(), diagram);

		dialog.open();
	}

}
