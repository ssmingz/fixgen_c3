class PlaceHold {
  @Override
  public int size() {
    if (isReference()) {
      return ((Archives) (getCheckedRef())).size();
    }
    dieOnCircularReference();
    int total = 0;
    for (Iterator<ArchiveFileSet> i = grabArchives(); i.hasNext(); ) {
      total += i.next().size();
    }
    return total;
  }
}
