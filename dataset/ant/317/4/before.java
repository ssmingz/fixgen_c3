class PlaceHold {
  private void logRemovedCount(
      int count, String prefix, String singularSuffix, String pluralSuffix) {
    File toDir = _copy.getToDir();
    String what = (prefix == null) ? "" : prefix;
    what += (count < 2) ? singularSuffix : pluralSuffix;
    if (count > 0) {
      log((((("Removed " + count) + " ") + what) + " from ") + toDir, MSG_INFO);
    } else {
      log((("NO " + what) + " to remove from ") + toDir, MSG_VERBOSE);
    }
  }
}
