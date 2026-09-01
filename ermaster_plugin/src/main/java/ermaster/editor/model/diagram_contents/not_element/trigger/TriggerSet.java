package ermaster.editor.model.diagram_contents.not_element.trigger;

import ermaster.ResourceString;
import ermaster.editor.model.diagram_contents.not_element.ObjectSet;

public class TriggerSet extends ObjectSet<Trigger> {

	private static final long serialVersionUID = 1L;

	@Override
	public TriggerSet clone() {
		return (TriggerSet) super.clone();
	}

	public String getName() {
		return ResourceString.getResourceString("label.object.type.trigger_list");
	}

}
