class PlaceHold {
  public void execute() throws BuildException {
    if (srcDir == null) {
      srcDir = project.resolveFile(".");
    }
    DirectoryScanner scanner = super.getDirectoryScanner(srcDir);
    String[] dependencies = scanner.getIncludedFiles();
    log((("assembling " + dependencies.length) + " file") + (dependencies.length == 1 ? "" : "s"));
    String baseDir = scanner.getBasedir().toString();
    for (int i = 0; i < dependencies.length; i++) {
      String targetFile = dependencies[i];
      targetFile = (baseDir + File.separator) + targetFile;
      executeOneFile(targetFile);
    }
  }
}
