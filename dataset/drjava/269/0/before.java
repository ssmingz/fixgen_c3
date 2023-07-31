class PlaceHold {
  public static FileFilter regexpCanonicalCaseFileFilter(String regexp) {
    return predicateFileFilter(regexpCanonicalCaseFilePredicate(regexp));
  }
}
