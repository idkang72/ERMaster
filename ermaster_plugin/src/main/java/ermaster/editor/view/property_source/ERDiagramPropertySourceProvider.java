package ermaster.editor.view.property_source;

import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import ermaster.editor.ERDiagramMultiPageEditor;
import ermaster.editor.controller.editpart.element.ERDiagramEditPart;
import ermaster.editor.controller.editpart.element.node.ERTableEditPart;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.diagram_contents.element.node.table.ERTable;

public class ERDiagramPropertySourceProvider implements IPropertySourceProvider {

	private ERDiagramMultiPageEditor editor;

	public ERDiagramPropertySourceProvider(ERDiagramMultiPageEditor editor) {
		this.editor = editor;
	}

	public IPropertySource getPropertySource(Object object) {
		if (object instanceof ERDiagramEditPart) {
			ERDiagram diagram = (ERDiagram) ((ERDiagramEditPart) object)
					.getModel();
			return new ERDiagramPropertySource(this.editor, diagram);

		} else if (object instanceof ERTableEditPart) {
			ERTable table = (ERTable) ((ERTableEditPart) object).getModel();
			return new ERTablePropertySource(this.editor, table);

		}

		return null;
	}
}
