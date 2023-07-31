class PlaceHold {
  protected void readFilters() throws TaskException {
    getLogger().debug("Reading filters from " + filtersFile);
    getGlobalFilterSet().readFiltersFromFile(filtersFile);
  }
}
