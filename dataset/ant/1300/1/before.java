class PlaceHold {
  public synchronized Hashtable getFilterHash() {
    if (filterHash == null) {
      filterHash = new Hashtable(getFilters().size());
      for (Enumeration e = getFilters().elements(); e.hasMoreElements(); ) {
        Filter filter = ((Filter) (e.nextElement()));
        filterHash.put(filter.getToken(), filter.getValue());
      }
    }
    return filterHash;
  }
}
