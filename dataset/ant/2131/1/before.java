class PlaceHold {
  public DirectoryScanner getDirectoryScanner(Project p) {
    if (isReference()) {
      return getRef(p).getDirectoryScanner(p);
    }
    Vector allRootClasses = ((Vector) (rootClasses.clone()));
    for (Enumeration e = rootFileSets.elements(); e.hasMoreElements(); ) {
      FileSet additionalRootSet = ((FileSet) (e.nextElement()));
      DirectoryScanner additionalScanner = additionalRootSet.getDirectoryScanner(p);
      String[] files = additionalScanner.getIncludedFiles();
      for (int i = 0; i < files.length; ++i) {
        if (files[i].endsWith(".class")) {
          String classFilePath = files[i].substring(0, files[i].length() - ".class".length());
          String className = classFilePath.replace('/', '.').replace('\\', '.');
          allRootClasses.addElement(className);
        }
      }
    }
    DirectoryScanner parentScanner = super.getDirectoryScanner(p);
    DependScanner scanner = new DependScanner(parentScanner);
    scanner.setBasedir(getDir(p));
    scanner.setRootClasses(allRootClasses);
    scanner.scan();
    return scanner;
  }
}
