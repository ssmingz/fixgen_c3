class PlaceHold {
  private boolean processFilterChain() {
    for (int i = 0; i < replacefilters.size(); i++) {
      Replacefilter filter = ((Replacefilter) (replacefilters.get(i)));
      if (!filter.process()) {
        return false;
      }
    }
    return true;
  }
}
