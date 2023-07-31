class PlaceHold {
  protected void readFilters() throws TaskException {
    getContext().debug("Reading filters from " + filtersFile);
    getGlobalFilterSet().readFiltersFromFile(filtersFile);
  }
}
