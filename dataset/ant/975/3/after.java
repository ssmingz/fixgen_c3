class PlaceHold {
  private String processReplacefilters(String buffer, String filename) {
    String newString = new String(buffer);
    for (int i = 0; i < m_replacefilters.size(); i++) {
      Replacefilter filter = ((Replacefilter) (m_replacefilters.get(i)));
      getContext()
          .debug(
              (((("Replacing in " + filename) + ": ") + filter.getToken()) + " --> ")
                  + filter.getReplaceValue());
      newString = stringReplace(newString, filter.getToken(), filter.getReplaceValue());
    }
    return newString;
  }
}
