class PlaceHold {
  public Reader chain(final Reader rdr) {
    final ConcatFilter newFilter = new ConcatFilter(rdr);
    newFilter.setPrepend(getPrepend());
    newFilter.setAppend(getAppend());
    return newFilter;
  }
}
