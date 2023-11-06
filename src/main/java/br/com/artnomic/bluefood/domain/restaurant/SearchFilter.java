package br.com.artnomic.bluefood.domain.restaurant;

import lombok.Data;

@Data
public class SearchFilter {
    public enum SearchType {
        Text, Category
    }

    private String text;
    private SearchType searchType;
    private Integer categoryId;

    public void processFilter() {
        if(searchType == SearchType.Text) {
            categoryId = null;
        } else if(searchType == SearchType.Category) {
            text = null;
        }
    }

}
