class PlaceHold {
  public TokenizedPath rtrimWildcardTokens() {
    StringBuffer sb = new StringBuffer();
    int newLen = 0;
    for (; newLen < tokenizedPattern.length; newLen++) {
      if (SelectorUtils.hasWildcards(tokenizedPattern[newLen])) {
        break;
      }
      if ((newLen > 0) && (sb.charAt(sb.length() - 1) != File.separatorChar)) {
        sb.append(File.separator);
      }
      sb.append(tokenizedPattern[newLen]);
    }
    if (newLen == 0) {
      return TokenizedPath.EMPTY_PATH;
    }
    String[] newPats = new String[newLen];
    System.arraycopy(tokenizedPattern, 0, newPats, 0, newLen);
    return new TokenizedPath(sb.toString(), newPats);
  }
}
