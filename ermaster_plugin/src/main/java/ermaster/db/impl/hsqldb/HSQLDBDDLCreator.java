package ermaster.db.impl.hsqldb;

import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.dbexport.ddl.DDLCreator;
import ermaster.editor.model.diagram_contents.element.node.category.Category;
import ermaster.editor.model.diagram_contents.not_element.sequence.Sequence;
import ermaster.editor.model.diagram_contents.not_element.tablespace.Tablespace;
import ermaster.util.Check;

public class HSQLDBDDLCreator extends DDLCreator {

	public HSQLDBDDLCreator(ERDiagram diagram, Category targetCategory,
			boolean semicolon) {
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
		ddl.append("SEQUENCE ");
		ddl.append(filterName(this.getNameWithSchema(sequence.getSchema(),
				sequence.getName())));
		if (!Check.isEmpty(sequence.getDataType())) {
			ddl.append(" AS ");
			String dataType = sequence.getDataType();
			ddl.append(dataType);
		}
		if (sequence.getIncrement() != null) {
			ddl.append(" INCREMENT BY ");
			ddl.append(sequence.getIncrement());
		}
		if (sequence.getMinValue() != null) {
			ddl.append(" MINVALUE ");
			ddl.append(sequence.getMinValue());
		}
		if (sequence.getMaxValue() != null) {
			ddl.append(" MAXVALUE ");
			ddl.append(sequence.getMaxValue());
		}
		if (sequence.getStart() != null) {
			ddl.append(" START WITH ");
			ddl.append(sequence.getStart());
		}
		if (sequence.isCycle()) {
			ddl.append(" CYCLE");
		}
		if (this.semicolon) {
			ddl.append(";");
		}

		return ddl.toString();

	}

}
