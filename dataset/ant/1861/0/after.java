class PlaceHold {
  protected void removeDir(File d) {
    String[] list = d.list();
    if (list == null) {
      list = new String[0];
    }
    for (int i = 0; i < list.length; i++) {
      String s = list[i];
      File f = new File(d, s);
      if (f.isDirectory()) {
        removeDir(f);
      } else {
        log("Deleting " + f.getAbsolutePath(), verbosity);
        if (!delete(f)) {
          handle("Unable to delete file " + f.getAbsolutePath());
        }
      }
    }
    log("Deleting directory " + d.getAbsolutePath(), verbosity);
    if (!delete(d)) {
      handle("Unable to delete directory " + dir.getAbsolutePath());
    }
  }
}
