class PlaceHold {
  protected void readFilters() throws TaskException {
    log("Reading filters from " + filtersFile, MSG_VERBOSE);
    getProject().getGlobalFilterSet().readFiltersFromFile(filtersFile);
  }
}
