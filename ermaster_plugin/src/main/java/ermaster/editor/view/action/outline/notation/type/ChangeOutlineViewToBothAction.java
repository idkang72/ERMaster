package ermaster.editor.view.action.outline.notation.type;

import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.widgets.Event;
import ermaster.ResourceString;
import ermaster.editor.controller.command.display.outline.ChangeOutlineViewModeCommand;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.settings.Settings;
import ermaster.editor.view.action.outline.AbstractOutlineBaseAction;

public class ChangeOutlineViewToBothAction extends AbstractOutlineBaseAction {

	public static final String ID = ChangeOutlineViewToBothAction.class
			.getName();

	public ChangeOutlineViewToBothAction(TreeViewer treeViewer) {
		super(ID, null, IAction.AS_RADIO_BUTTON, treeViewer);
		this.setText(ResourceString
				.getResourceString("action.title.change.mode.to.both"));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(Event event) {
		ERDiagram diagram = this.getDiagram();

		ChangeOutlineViewModeCommand command = new ChangeOutlineViewModeCommand(
				diagram, Settings.VIEW_MODE_BOTH);

		this.execute(command);
	}

}
