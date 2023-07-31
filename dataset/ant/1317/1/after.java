class PlaceHold {
  public String encodedata(final String value) {
    StringBuffer sb = new StringBuffer();
    int len = value.length();
    for (int i = 0; i < len; ++i) {
      char c = value.charAt(i);
      if (isLegalCharacter(c)) {
        sb.append(c);
      }
    }
    String result = sb.substring(0);
    int cdEnd = result.indexOf("]]>");
    while (cdEnd != (-1)) {
      sb.setLength(cdEnd);
      sb.append("&#x5d;&#x5d;&gt;").append(result.substring(cdEnd + 3));
      result = sb.substring(0);
      cdEnd = result.indexOf("]]>");
    }
    return result;
  }
}
