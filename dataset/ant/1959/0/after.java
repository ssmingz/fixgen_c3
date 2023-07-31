class PlaceHold {
  public int size() {
    if (isReference()) {
      return ((Archives) (getCheckedRef())).size();
    }
    dieOnCircularReference();
    int total = 0;
    for (final Iterator<ArchiveFileSet> i = grabArchives(); i.hasNext(); ) {
      total += i.next().size();
    }
    return total;
  }
}
