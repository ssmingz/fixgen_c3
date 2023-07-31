class PlaceHold {
  private void logFilterChain(String filename) {
    for (int i = 0; i < replacefilters.size(); i++) {
      Replacefilter filter = ((Replacefilter) (replacefilters.elementAt(i)));
      log(
          (((("Replacing in " + filename) + ": ") + filter.getToken()) + " --> ")
              + filter.getReplaceValue(),
          MSG_VERBOSE);
    }
  }
}
