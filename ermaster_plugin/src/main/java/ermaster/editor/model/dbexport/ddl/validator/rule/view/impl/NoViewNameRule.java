package ermaster.editor.model.dbexport.ddl.validator.rule.view.impl;

import org.eclipse.core.resources.IMarker;
import ermaster.ResourceString;
import ermaster.editor.model.dbexport.ddl.validator.ValidateResult;
import ermaster.editor.model.dbexport.ddl.validator.rule.view.ViewRule;
import ermaster.editor.model.diagram_contents.element.node.view.View;

public class NoViewNameRule extends ViewRule {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean validate(View view) {
		if (view.getPhysicalName() == null
				|| view.getPhysicalName().trim().equals("")) {
			ValidateResult validateResult = new ValidateResult();
			validateResult.setMessage(ResourceString
					.getResourceString("error.validate.no.view.name"));
			validateResult.setLocation(view.getLogicalName());
			validateResult.setSeverity(IMarker.SEVERITY_WARNING);
			validateResult.setObject(view);

			this.addError(validateResult);
		}

		return true;
	}
}
