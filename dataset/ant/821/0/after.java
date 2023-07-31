class PlaceHold {
  protected void removeFiles(File d, String[] files) {
    if (files.length > 0) {
      log((("Deleting " + files.length) + " files from ") + d.getAbsolutePath());
      for (int j = 0; j < files.length; j++) {
        File f = new File(d, files[j]);
        log("Deleting " + f.getAbsolutePath(), verbosity);
        if ((!quiet) && (!f.delete())) {
          throw new BuildException("Unable to delete file " + f.getAbsolutePath());
        }
      }
    }
  }
}
