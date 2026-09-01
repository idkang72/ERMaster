package ermaster.editor.model.dbexport.ddl.validator.rule.table.impl;

import org.eclipse.core.resources.IMarker;
import ermaster.ResourceString;
import ermaster.editor.model.dbexport.ddl.validator.ValidateResult;
import ermaster.editor.model.dbexport.ddl.validator.rule.table.TableRule;
import ermaster.editor.model.diagram_contents.element.node.table.ERTable;
import ermaster.editor.model.diagram_contents.element.node.table.column.NormalColumn;
import ermaster.editor.model.diagram_contents.element.node.table.index.Index;

public class FullTextIndexRule extends TableRule {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean validate(ERTable table) {
		for (Index index : table.getIndexes()) {
			if (index.isFullText()) {
				for (NormalColumn indexColumn : index.getColumns()) {
					if (!indexColumn.isFullTextIndexable()) {
						ValidateResult validateResult = new ValidateResult();
						validateResult
								.setMessage(ResourceString
										.getResourceString("error.validate.fulltext.index1")
										+ table.getPhysicalName()
										+ ResourceString
												.getResourceString("error.validate.fulltext.index2")
										+ index.getName()
										+ ResourceString
												.getResourceString("error.validate.fulltext.index3")
										+ indexColumn.getPhysicalName());
						validateResult.setLocation(table.getLogicalName());
						validateResult.setSeverity(IMarker.SEVERITY_WARNING);
						validateResult.setObject(index);

						this.addError(validateResult);
					}
				}
			}
		}

		return true;
	}
}
