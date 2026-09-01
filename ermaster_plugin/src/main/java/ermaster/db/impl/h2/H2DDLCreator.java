package ermaster.db.impl.h2;

import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.dbexport.ddl.DDLCreator;
import ermaster.editor.model.diagram_contents.element.node.category.Category;
import ermaster.editor.model.diagram_contents.not_element.sequence.Sequence;
import ermaster.editor.model.diagram_contents.not_element.tablespace.Tablespace;
import ermaster.util.Check;

public class H2DDLCreator extends DDLCreator {

	public H2DDLCreator(ERDiagram diagram, Category targetCategory, boolean semicolon) {
		super(diagram, targetCategory, semicolon);
	}

	@Override
	protected String getDDL(Tablespace tablespace) {
		return null;
	}

	@Override
	public String getDDL(Sequence sequence) {
		StringBuilder ddl = new StringBuilder();

		String description = sequence.getDescription();
		if (this.semicolon && !Check.isEmpty(description)
				&& this.ddlTarget.inlineTableComment) {
			ddl.append("-- ");
			ddl.append(replaceLF(description, LF() + "-- "));
			ddl.append(LF());
		}

		ddl.append("CREATE ");
		ddl.append("SEQUENCE IF NOT EXISTS ");
		ddl.append(filterName(this.getNameWithSchema(sequence.getSchema(), sequence
				.getName())));
		if (sequence.getStart() != null) {
			ddl.append(" START WITH ");
			ddl.append(sequence.getStart());
		}
		if (sequence.getIncrement() != null) {
			ddl.append(" INCREMENT BY ");
			ddl.append(sequence.getIncrement());
		}
		if (sequence.getCache() != null) {
			ddl.append(" CACHE ");
			ddl.append(sequence.getCache());
		}
		if (this.semicolon) {
			ddl.append(";");
		}

		return ddl.toString();

	}

}
