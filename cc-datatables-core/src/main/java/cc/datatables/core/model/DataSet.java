package cc.datatables.core.model;

import java.io.Serializable;
import java.util.List;

public class DataSet<T> implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2938317951551648945L;
	private final List<T> rows;
	private final Long totalDisplayRecords;
	private final Long totalRecords;

	public DataSet(List<T> rows, Long totalRecords, Long totalDisplayRecords) {
		this.rows = rows;
		this.totalRecords = totalRecords;
		this.totalDisplayRecords = totalDisplayRecords;
	}

	public List<T> getRows() {
		return rows;
	}

	public Long getTotalDisplayRecords() {
		return totalDisplayRecords;
	}

	public Long getTotalRecords() {
		return totalRecords;
	}
}
