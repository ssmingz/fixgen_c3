class PlaceHold {
  public static FileFilter regexpFileFilter(String regexp) {
    return predicateFileFilter(regexpFilePredicate(regexp));
  }
}
