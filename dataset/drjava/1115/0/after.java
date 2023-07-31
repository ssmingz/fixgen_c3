class PlaceHold {
  public static Iterable<File> attemptListFilesAsIterable(File f, Predicate<? super File> filter) {
    return attemptListFilesAsIterable(f, ((FileFilter) (asFilePredicate(filter))));
  }
}
