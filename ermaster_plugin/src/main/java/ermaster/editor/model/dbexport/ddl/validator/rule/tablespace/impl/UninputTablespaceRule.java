package ermaster.editor.model.dbexport.ddl.validator.rule.tablespace.impl;

import org.eclipse.core.resources.IMarker;
import ermaster.ResourceString;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.dbexport.ddl.validator.ValidateResult;
import ermaster.editor.model.dbexport.ddl.validator.rule.tablespace.TablespaceRule;
import ermaster.editor.model.diagram_contents.not_element.tablespace.Tablespace;
import ermaster.editor.model.diagram_contents.not_element.tablespace.TablespaceProperties;
import ermaster.editor.model.settings.Environment;

public class UninputTablespaceRule extends TablespaceRule {

	@Override
	public boolean validate(ERDiagram diagram, Tablespace tablespace,
			Environment environment) {
		TablespaceProperties tablespaceProperties = tablespace.getProperties(
				environment, diagram);

		for (String errorMessage : tablespaceProperties.validate()) {
			ValidateResult validateResult = new ValidateResult();
			validateResult.setMessage(ResourceString
					.getResourceString(errorMessage)
					+ this.getMessageSuffix(tablespace, environment));
			validateResult.setLocation(tablespace.getName());
			validateResult.setSeverity(IMarker.SEVERITY_WARNING);
			validateResult.setObject(tablespace);

			this.addError(validateResult);
		}

		return true;
	}

	protected String getMessageSuffix(Tablespace tablespace,
			Environment environment) {
		StringBuilder suffix = new StringBuilder();
		suffix.append(" ");
		suffix.append(ResourceString
				.getResourceString("error.tablespace.suffix.1"));
		suffix.append(tablespace.getName());
		suffix.append(ResourceString.getResourceString("error.tablespace.suffix.2"));
		suffix.append(environment.getName());

		return suffix.toString();
	}
}
