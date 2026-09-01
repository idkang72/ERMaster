package ermaster.db.impl.standard_sql;

import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.dbexport.ddl.DDLCreator;
import ermaster.editor.model.diagram_contents.element.node.category.Category;
import ermaster.editor.model.diagram_contents.not_element.tablespace.Tablespace;

public class StandardSQLDDLCreator extends DDLCreator {

	public StandardSQLDDLCreator(ERDiagram diagram, Category targetCategory,
			boolean semicolon) {
		super(diagram, targetCategory, semicolon);
	}

	@Override
	protected String getDDL(Tablespace tablespace) {
		return null;
	}
}
