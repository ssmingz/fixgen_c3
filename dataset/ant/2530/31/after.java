class PlaceHold {
  private void flushFilterChain() {
    for (int i = 0; i < replacefilters.size(); i++) {
      Replacefilter filter = ((Replacefilter) (replacefilters.get(i)));
      filter.flush();
    }
  }
}
