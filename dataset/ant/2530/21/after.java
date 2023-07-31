class PlaceHold {
  private StringBuffer buildFilterChain(StringBuffer inputBuffer) {
    StringBuffer buf = inputBuffer;
    for (int i = 0; i < replacefilters.size(); i++) {
      Replacefilter filter = ((Replacefilter) (replacefilters.get(i)));
      filter.setInputBuffer(buf);
      buf = filter.getOutputBuffer();
    }
    return buf;
  }
}
