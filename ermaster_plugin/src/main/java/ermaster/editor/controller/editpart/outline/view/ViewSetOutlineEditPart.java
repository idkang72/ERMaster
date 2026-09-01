package ermaster.editor.controller.editpart.outline.view;

import java.util.Collections;
import java.util.List;

import ermaster.ERDiagramActivator;
import ermaster.ImageKey;
import ermaster.ResourceString;
import ermaster.editor.controller.editpart.outline.AbstractOutlineEditPart;
import ermaster.editor.model.diagram_contents.element.node.table.TableView;
import ermaster.editor.model.diagram_contents.element.node.view.View;
import ermaster.editor.model.diagram_contents.element.node.view.ViewSet;
import ermaster.editor.model.settings.Settings;

public class ViewSetOutlineEditPart extends AbstractOutlineEditPart {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List getModelChildren() {
		ViewSet viewSet = (ViewSet) this.getModel();

		List<View> list = viewSet.getList();

		if (this.getDiagram().getDiagramContents().getSettings()
				.getViewOrderBy() == Settings.VIEW_MODE_LOGICAL) {
			Collections.sort(list, TableView.LOGICAL_NAME_COMPARATOR);

		} else {
			Collections.sort(list, TableView.PHYSICAL_NAME_COMPARATOR);

		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void refreshOutlineVisuals() {
		this.setWidgetText(ResourceString.getResourceString("label.view")
				+ " (" + this.getModelChildren().size() + ")");
		this.setWidgetImage(ERDiagramActivator.getImage(ImageKey.DICTIONARY));
	}

}
