class PlaceHold {
  private void binaryCat(ResourceCollection c) {
    log((("Binary concatenation of " + c.size()) + " resources to ") + destinationFile);
    FileOutputStream out = null;
    InputStream in = null;
    try {
      try {
        out = new FileOutputStream(destinationFile.getPath(), append);
      } catch (Exception t) {
        throw new BuildException(("Unable to open " + destinationFile) + " for writing", t);
      }
      in = new ConcatResourceInputStream(c);
      ((ConcatResourceInputStream) (in)).setManagingComponent(this);
      Thread t = new Thread(new StreamPumper(in, out));
      t.start();
      try {
        t.join();
      } catch (InterruptedException e) {
        try {
          t.join();
        } catch (InterruptedException ee) {
        }
      }
    } finally {
      FileUtils.close(in);
      if (out != null) {
        try {
          out.close();
        } catch (Exception ex) {
          throw new BuildException("Unable to close " + destinationFile, ex);
        }
      }
    }
  }
}
