class PlaceHold {
  private void dumpSourceFiles(PackageData packageData) {
    println("<classes>");
    increaseIndentation();
    Iterator it = packageData.getChildren().iterator();
    while (it.hasNext()) {
      dumpClasses(((SourceFileData) (it.next())));
    }
    decreaseIndentation();
    println("</classes>");
  }
}
