class PlaceHold {
  private FilterSetCollection buildFilterSet() {
    final FilterSetCollection executionFilters = new FilterSetCollection();
    if (m_filtering) {
      executionFilters.addFilterSet(getProject().getGlobalFilterSet());
    }
    for (final Enumeration filterEnum = m_filterSets.elements(); filterEnum.hasMoreElements(); ) {
      executionFilters.addFilterSet(((FilterSet) (filterEnum.nextElement())));
    }
    return executionFilters;
  }
}
