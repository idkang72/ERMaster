package ermaster.editor.view.action.outline.orderby;

import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.widgets.Event;
import ermaster.ResourceString;
import ermaster.editor.controller.command.display.outline.ChangeOutlineViewOrderByCommand;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.settings.Settings;
import ermaster.editor.view.action.outline.AbstractOutlineBaseAction;

public class ChangeOutlineViewOrderByPhysicalNameAction extends
		AbstractOutlineBaseAction {

	public static final String ID = ChangeOutlineViewOrderByPhysicalNameAction.class
			.getName();

	public ChangeOutlineViewOrderByPhysicalNameAction(TreeViewer treeViewer) {
		super(ID, null, IAction.AS_RADIO_BUTTON, treeViewer);
		this.setText(ResourceString.getResourceString("label.physical.name"));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(Event event) {
		ERDiagram diagram = this.getDiagram();

		ChangeOutlineViewOrderByCommand command = new ChangeOutlineViewOrderByCommand(
				diagram, Settings.VIEW_MODE_PHYSICAL);

		this.execute(command);
	}

}
