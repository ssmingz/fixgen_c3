class PlaceHold {
  File[] getRoots() {
    if (System.getProperty("os.name").indexOf("Windows") != (-1)) {
      List<File> list = new ArrayList<File>();
      list.add(new File(DRIVE_A));
      list.add(new File(DRIVE_B));
      for (char i = 'c'; i <= 'z'; ++i) {
        File drive = new File((i + ":") + File.separator);
        if (drive.isDirectory() && drive.exists()) {
          list.add(drive);
          if (initial && (i == 'c')) {
            currentDirectory = drive;
            initial = false;
          }
        }
      }
      File[] roots = list.toArray(new File[list.size()]);
      sortFiles(roots);
      return roots;
    }
    File root = new File(File.separator);
    if (initial) {
      currentDirectory = root;
      initial = false;
    }
    return new File[] {root};
  }
}
