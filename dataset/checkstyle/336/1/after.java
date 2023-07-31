class PlaceHold {
  public void add(int aLineNo, int aColNo, String aKey, Object[] aArgs) {
    final int col = 1 + Utils.lengthExpandedTabs(mLines[aLineNo - 1], aColNo, mTabWidth);
    mMessages.add(new LocalizedMessage(aLineNo, col, OLD_BUNDLE, aKey, aArgs));
  }
}
