package ermaster.editor.view.action.option.notation;

import org.eclipse.jface.action.IAction;
import org.eclipse.swt.widgets.Event;
import ermaster.ResourceString;
import ermaster.editor.ERDiagramEditor;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.view.action.AbstractBaseAction;

public class TooltipAction extends AbstractBaseAction {

	public static final String ID = TooltipAction.class.getName();

	public TooltipAction(ERDiagramEditor editor) {
		super(ID, null, IAction.AS_CHECK_BOX, editor);
		this.setText(ResourceString
				.getResourceString("action.title.display.tooltip"));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(Event event) {
		ERDiagram diagram = this.getDiagram();

		diagram.setTooltip(this.isChecked());
	}
	
}
