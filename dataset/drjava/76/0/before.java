class PlaceHold {
  public static File[] attemptListFiles(File f, Predicate<? super File> filter) {
    return attemptListFiles(f, predicateFileFilter(filter));
  }
}
