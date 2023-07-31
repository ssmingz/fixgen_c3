class PlaceHold {
  @Override
  public Reader chain(final Reader rdr) {
    ConcatFilter newFilter = new ConcatFilter(rdr);
    newFilter.setPrepend(getPrepend());
    newFilter.setAppend(getAppend());
    return newFilter;
  }
}
