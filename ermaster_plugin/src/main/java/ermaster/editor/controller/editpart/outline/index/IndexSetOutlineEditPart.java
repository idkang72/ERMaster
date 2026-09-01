package ermaster.editor.controller.editpart.outline.index;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ermaster.ERDiagramActivator;
import ermaster.ImageKey;
import ermaster.ResourceString;
import ermaster.editor.controller.editpart.outline.AbstractOutlineEditPart;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.diagram_contents.element.node.category.Category;
import ermaster.editor.model.diagram_contents.element.node.table.ERTable;
import ermaster.editor.model.diagram_contents.element.node.table.index.Index;

public class IndexSetOutlineEditPart extends AbstractOutlineEditPart {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List getModelChildren() {
		List<Index> children = new ArrayList<Index>();

		ERDiagram diagram = this.getDiagram();
		Category category = this.getCurrentCategory();

		for (ERTable table : diagram.getDiagramContents().getContents()
				.getTableSet()) {
			if (category == null || category.contains(table)) {
				children.addAll(table.getIndexes());
			}
		}

		Collections.sort(children);

		return children;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void refreshOutlineVisuals() {
		this.setWidgetText(ResourceString.getResourceString("label.index")
				+ " (" + this.getModelChildren().size() + ")");
		this.setWidgetImage(ERDiagramActivator.getImage(ImageKey.DICTIONARY));
	}

}
