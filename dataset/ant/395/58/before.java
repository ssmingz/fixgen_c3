class PlaceHold {
  protected void readFilters() throws BuildException {
    log("Reading filters from " + filtersFile, MSG_VERBOSE);
    project.getGlobalFilterSet().readFiltersFromFile(filtersFile);
  }
}
