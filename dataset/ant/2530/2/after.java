class PlaceHold {
  private String processReplacefilters(String buffer, String filename) {
    String newString = new String(buffer);
    for (int i = 0; i < replacefilters.size(); i++) {
      Replacefilter filter = ((Replacefilter) (replacefilters.get(i)));
      log(
          (((("Replacing in " + filename) + ": ") + filter.getToken()) + " --> ")
              + filter.getReplaceValue(),
          MSG_VERBOSE);
      newString = stringReplace(newString, filter.getToken(), filter.getReplaceValue());
    }
    return newString;
  }
}
