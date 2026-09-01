package ermaster.editor.model.dbexport.ddl.validator.rule;

import java.util.List;

import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.dbexport.ddl.validator.ValidateResult;

public interface Rule {

	public List<ValidateResult> getErrorList();

	public void clear();

	abstract public boolean validate(ERDiagram diagram);
}
