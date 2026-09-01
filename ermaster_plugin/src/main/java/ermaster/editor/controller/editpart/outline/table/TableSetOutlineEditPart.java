package ermaster.editor.controller.editpart.outline.table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ermaster.ERDiagramActivator;
import ermaster.ImageKey;
import ermaster.ResourceString;
import ermaster.editor.controller.editpart.outline.AbstractOutlineEditPart;
import ermaster.editor.model.diagram_contents.element.node.category.Category;
import ermaster.editor.model.diagram_contents.element.node.table.ERTable;
import ermaster.editor.model.diagram_contents.element.node.table.TableSet;
import ermaster.editor.model.diagram_contents.element.node.table.TableView;
import ermaster.editor.model.settings.Settings;

public class TableSetOutlineEditPart extends AbstractOutlineEditPart {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List getModelChildren() {
		TableSet tableSet = (TableSet) this.getModel();

		List<ERTable> list = new ArrayList<ERTable>();

		Category category = this.getCurrentCategory();
		for (ERTable table : tableSet) {
			if (category == null || category.contains(table)) {
				list.add(table);
			}
		}

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
		this.setWidgetText(ResourceString.getResourceString("label.table")
				+ " (" + this.getModelChildren().size() + ")");
		this.setWidgetImage(ERDiagramActivator.getImage(ImageKey.DICTIONARY));
	}

}
