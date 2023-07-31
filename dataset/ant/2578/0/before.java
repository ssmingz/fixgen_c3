class PlaceHold {
  private void addSourceFiles(Vector sf) {
    Iterator e = nestedSourceFiles.iterator();
    while (e.hasNext()) {
      ResourceCollection rc = ((ResourceCollection) (e.next()));
      if (!rc.isFilesystemOnly()) {
        throw new BuildException("only file system based resources are" + " supported by javadoc");
      }
      if (rc instanceof FileSet) {
        FileSet fs = ((FileSet) (rc));
        if ((!fs.hasPatterns()) && (!fs.hasSelectors())) {
          FileSet fs2 = ((FileSet) (fs.clone()));
          fs2.createInclude().setName("**/*.java");
          if (includeNoSourcePackages) {
            fs2.createInclude().setName("**/package.html");
          }
          rc = fs2;
        }
      }
      Iterator iter = rc.iterator();
      while (iter.hasNext()) {
        sf.addElement(new SourceFile(((FileProvider) (iter.next())).getFile()));
      }
    }
  }
}
