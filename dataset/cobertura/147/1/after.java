class PlaceHold {
  private void dumpSourceFiles(PackageData packageData) {
    println("<classes>");
    increaseIndentation();
    Iterator it = packageData.getSourceFiles().iterator();
    while (it.hasNext()) {
      dumpClasses(((SourceFileData) (it.next())));
    }
    decreaseIndentation();
    println("</classes>");
  }
}
