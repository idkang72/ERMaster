package ermaster.editor.controller.editpart.outline.tablespace;

import java.util.List;

import ermaster.ERDiagramActivator;
import ermaster.ImageKey;
import ermaster.ResourceString;
import ermaster.editor.controller.editpart.outline.AbstractOutlineEditPart;
import ermaster.editor.model.diagram_contents.not_element.tablespace.TablespaceSet;

public class TablespaceSetOutlineEditPart extends AbstractOutlineEditPart {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List getModelChildren() {
		TablespaceSet tablespaceSet = (TablespaceSet) this.getModel();

		return tablespaceSet.getObjectList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void refreshOutlineVisuals() {
		this.setWidgetText(ResourceString.getResourceString("label.tablespace")
				+ " (" + this.getModelChildren().size() + ")");
		this.setWidgetImage(ERDiagramActivator.getImage(ImageKey.DICTIONARY));
	}

}
