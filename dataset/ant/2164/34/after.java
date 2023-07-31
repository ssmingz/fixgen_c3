class PlaceHold {
  public void execute() throws TaskException {
    if ((source == null) && (filesets.size() == 0)) {
      throw new TaskException("src attribute and/or filesets must be specified");
    }
    if (dest == null) {
      throw new TaskException("Dest attribute must be specified");
    }
    if (dest.exists() && (!dest.isDirectory())) {
      throw new TaskException("Dest must be a directory.");
    }
    if (source != null) {
      if (source.isDirectory()) {
        throw new TaskException("Src must not be a directory." + " Use nested filesets instead.");
      } else {
        expandFile(source, dest);
      }
    }
    if (filesets.size() > 0) {
      for (int j = 0; j < filesets.size(); j++) {
        FileSet fs = ((FileSet) (filesets.get(j)));
        DirectoryScanner ds = ScannerUtil.getDirectoryScanner(fs);
        File fromDir = fs.getDir();
        String[] files = ds.getIncludedFiles();
        for (int i = 0; i < files.length; ++i) {
          File file = new File(fromDir, files[i]);
          expandFile(file, dest);
        }
      }
    }
  }
}
