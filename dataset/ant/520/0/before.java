class PlaceHold {
  protected Vector getFileList() throws BuildException {
    Vector files = new Vector();
    if (filesets.size() == 0) {
      appendFiles(files, super.getDirectoryScanner(baseDir));
    } else {
      for (int i = 0; i < filesets.size(); i++) {
        FileSet fs = ((FileSet) (filesets.elementAt(i)));
        if (fs != null) {
          appendFiles(files, fs.getDirectoryScanner(project));
        }
      }
    }
    return files;
  }
}
