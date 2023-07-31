class PlaceHold {
  protected void readFilters() throws TaskException {
    log("Reading filters from " + filtersFile, MSG_VERBOSE);
    project.getGlobalFilterSet().readFiltersFromFile(filtersFile);
  }
}
