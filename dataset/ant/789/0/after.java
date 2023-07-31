class PlaceHold {
  private void binaryCat() {
    log((("Binary concatenation of " + sourceFiles.size()) + " files to ") + destinationFile);
    FileOutputStream out = null;
    FileInputStream in = null;
    byte[] buffer = new byte[BUFFER_SIZE];
    try {
      try {
        out = new FileOutputStream(destinationFile);
      } catch (Exception t) {
        throw new BuildException(("Unable to open " + destinationFile) + " for writing", t);
      }
      for (Iterator i = sourceFiles.iterator(); i.hasNext(); ) {
        File sourceFile = ((File) (i.next()));
        try {
          in = new FileInputStream(sourceFile);
        } catch (Exception t) {
          throw new BuildException("Unable to open input file " + sourceFile, t);
        }
        int count = 0;
        do {
          try {
            count = in.read(buffer, 0, buffer.length);
          } catch (Exception t) {
            throw new BuildException("Unable to read from " + sourceFile, t);
          }
          try {
            if (count > 0) {
              out.write(buffer, 0, count);
            }
          } catch (Exception t) {
            throw new BuildException("Unable to write to " + destinationFile, t);
          }
        } while (count > 0);
        try {
          in.close();
        } catch (Exception t) {
          throw new BuildException("Unable to close " + sourceFile, t);
        }
        in = null;
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
