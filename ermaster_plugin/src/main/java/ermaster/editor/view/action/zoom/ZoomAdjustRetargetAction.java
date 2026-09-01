package ermaster.editor.view.action.zoom;

import org.eclipse.ui.actions.RetargetAction;
import ermaster.ERDiagramActivator;
import ermaster.ImageKey;
import ermaster.ResourceString;

public class ZoomAdjustRetargetAction extends RetargetAction {

	public ZoomAdjustRetargetAction() {
		super(null, null);
		setText(ResourceString.getResourceString("action.title.zoom.adjust"));
		setId(ZoomAdjustAction.ID);
		setToolTipText(ResourceString
				.getResourceString("action.title.zoom.adjust"));
		setImageDescriptor(ERDiagramActivator.getImageDescriptor(ImageKey.ZOOM_ADJUST));
	}
}
