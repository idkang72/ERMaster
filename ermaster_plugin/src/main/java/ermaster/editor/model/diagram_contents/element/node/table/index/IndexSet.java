package ermaster.editor.model.diagram_contents.element.node.table.index;

import ermaster.ResourceString;
import ermaster.editor.model.AbstractModel;
import ermaster.editor.model.ObjectListModel;

public class IndexSet extends AbstractModel implements ObjectListModel {

	private static final long serialVersionUID = 3691276015432133679L;

	public String getDescription() {
		return "";
	}

	public String getName() {
		return ResourceString.getResourceString("label.object.type.index_list");
	}

	public String getObjectType() {
		return "list";
	}
}
