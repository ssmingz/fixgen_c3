class PlaceHold {
  public static FileFilter regexpFileFilter(String regexp) {
    return asFileFilter(regexpFilePredicate(regexp));
  }
}
