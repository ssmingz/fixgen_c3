class PlaceHold {
  public static void deleteSymlink(File linkfil) throws IOException {
    if (!linkfil.exists()) {
      throw new FileNotFoundException("No such symlink: " + linkfil);
    }
    File canfil = linkfil.getCanonicalFile();
    File temp = FILE_UTILS.createTempFile("symlink", ".tmp", canfil.getParentFile(), false, false);
    try {
      try {
        FILE_UTILS.rename(canfil, temp);
      } catch (IOException e) {
        throw new IOException("Couldn't rename resource when attempting to delete " + linkfil);
      }
      if (!linkfil.delete()) {
        throw new IOException(
            ("Couldn't delete symlink: " + linkfil)
                + " (was it a real file? is this not a UNIX system?)");
      }
    } finally {
      try {
        FILE_UTILS.rename(temp, canfil);
      } catch (IOException e) {
        throw new IOException(
            (((("Couldn't return resource " + temp) + " to its original name: ")
                        + canfil.getAbsolutePath())
                    + "\n THE RESOURCE\'S NAME ON DISK HAS ")
                + "BEEN CHANGED BY THIS ERROR!\n");
      }
    }
  }
}
