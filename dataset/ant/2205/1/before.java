class PlaceHold {
  protected void deleteDir(File d) {
    String[] list = d.list();
    for (int i = 0; i < list.length; i++) {
      String s = list[i];
      File f = new File(d, s);
      if (f.isDirectory()) {
        deleteDir(f);
      } else {
        throw new BuildException(
            ("UNEXPECTED ERROR - The file " + f.getAbsolutePath()) + " should not exist!");
      }
    }
    log("Deleting directory " + d.getAbsolutePath(), verbosity);
    if (!d.delete()) {
      throw new BuildException("Unable to delete directory " + d.getAbsolutePath());
    }
  }
}
