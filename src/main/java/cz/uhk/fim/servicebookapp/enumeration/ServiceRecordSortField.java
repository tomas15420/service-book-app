package cz.uhk.fim.servicebookapp.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ServiceRecordSortField {
    ID("id"),
    DATE("date"),
    COST("cost");

    private final String databaseFieldName;
}
