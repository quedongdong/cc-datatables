package cc.datatables.core.model;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class DatatablesCriterias {

	private final String search;
	private final Integer start;
	private final Integer length;
	private final List<ColumnDef> columnDefs;
	private final List<ColumnDef> sortedColumnDefs;
	private final Integer echo;

	public DatatablesCriterias(String search, Integer displayStart, Integer displaySize, List<ColumnDef> columnDefs,
			List<ColumnDef> sortedColumnDefs, Integer echo) {
		this.search = search;
		this.start = displayStart;
		this.length = displaySize;
		this.columnDefs = columnDefs;
		this.sortedColumnDefs = sortedColumnDefs;
		this.echo = echo;
	}

	public String getSearch() {
		return search;
	}

	public Integer getStart() {
		return start;
	}

	public Integer getLength() {
		return length;
	}

	public List<ColumnDef> getColumnDefs() {
		return columnDefs;
	}

	public List<ColumnDef> getSortedColumnDefs() {
		return sortedColumnDefs;
	}
 
	public Integer getEcho() {
		return echo;
	}

	public Boolean hasOneFilterableColumn() {
		return hasOneSearchableColumn();
	}

	public Boolean hasOneSearchableColumn() {
		for (ColumnDef columnDef : this.columnDefs) {
			if (columnDef.isSearchable()) {
				return true;
			}
		}
		return false;
	}

	public Boolean hasOneFilteredColumn() {
		for (ColumnDef columnDef : this.columnDefs) {
			if (StringUtils.isNotBlank(columnDef.getSearch()) || StringUtils.isNotBlank(columnDef.getSearchFrom())
					|| StringUtils.isNotBlank(columnDef.getSearchTo())) {
				return true;
			}
		}
		return false;
	}

	

	@Override
	public String toString() {
		return "DatatablesCriterias [search=" + search + ", start=" + start + ", length=" + length + ", columnDefs="
				+ columnDefs + ", sortingColumnDefs=" + sortedColumnDefs + ", echo=" + echo + "]";
	}
}
