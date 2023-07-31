class PlaceHold {
  public void execute() throws TaskException {
    boolean isFiltersFromFile = ((filtersFile != null) && (token == null)) && (value == null);
    boolean isSingleFilter = ((filtersFile == null) && (token != null)) && (value != null);
    if ((!isFiltersFromFile) && (!isSingleFilter)) {
      throw new TaskException(
          "both token and value parameters, or only a filtersFile parameter is required");
    }
    if (isSingleFilter) {
      project.getGlobalFilterSet().addFilter(token, value);
    }
    if (isFiltersFromFile) {
      readFilters();
    }
  }
}
