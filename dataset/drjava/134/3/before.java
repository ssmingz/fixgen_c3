class PlaceHold {
  protected static String _escapeQuotesAndBackslashes(String s) {
    StringBuffer buf = new StringBuffer(s);
    for (int i = s.length() - 1; i >= 0; i--) {
      char c = s.charAt(i);
      if ((c == '\\') || (c == '"')) {
        buf.insert(i, '\\');
      } else if (c == '\n') {
        buf.deleteCharAt(i);
        buf.insert(i, "\\n");
      } else if (c == '\t') {
        buf.deleteCharAt(i);
        buf.insert(i, "\\t");
      } else if (c == '\r') {
        buf.deleteCharAt(i);
        buf.insert(i, "\\r");
      } else if (c == '\b') {
        buf.deleteCharAt(i);
        buf.insert(i, "\\b");
      } else if (c == '\f') {
        buf.deleteCharAt(i);
        buf.insert(i, "\\f");
      }
    }
    return buf.toString();
  }
}
