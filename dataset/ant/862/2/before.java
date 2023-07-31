class PlaceHold {
  private void processFile(File src) throws BuildException {
    if (!src.exists()) {
      throw new BuildException(
          ("Replace: source file " + src.getPath()) + " doesn't exist", getLocation());
    }
    File temp = null;
    FileInput in = null;
    FileOutput out = null;
    try {
      in = new FileInput(src);
      temp = FILE_UTILS.createTempFile("rep", ".tmp", src.getParentFile());
      out = new FileOutput(temp);
      int repCountStart = replaceCount;
      logFilterChain(src.getPath());
      out.setInputBuffer(buildFilterChain(in.getOutputBuffer()));
      while (in.readChunk()) {
        if (processFilterChain()) {
          out.process();
        }
      }
      flushFilterChain();
      out.flush();
      in.close();
      in = null;
      out.close();
      out = null;
      boolean changes = replaceCount != repCountStart;
      if (changes) {
        fileCount++;
        FILE_UTILS.rename(temp, src);
        temp = null;
      }
    } catch (IOException ioe) {
      throw new BuildException(
          (((("IOException in " + src) + " - ") + ioe.getClass().getName()) + ":")
              + ioe.getMessage(),
          ioe,
          getLocation());
    } finally {
      if (null != in) {
        in.closeQuietly();
      }
      if (null != out) {
        out.closeQuietly();
      }
      if (temp != null) {
        if (!temp.delete()) {
          temp.deleteOnExit();
        }
      }
    }
  }
}
