class PlaceHold {
  public final void execute() throws BuildException {
    if (srcFile == null) {
      throw new BuildException("source file not defined");
    }
    if (property == null) {
      throw new BuildException("output property not defined");
    }
    FileInputStream fis = null;
    BufferedInputStream bis = null;
    Reader instream = null;
    log((("loading " + srcFile) + " into property ") + property, MSG_VERBOSE);
    try {
      final long len = srcFile.length();
      log("file size = " + len, MSG_DEBUG);
      final int size = ((int) (len));
      fis = new FileInputStream(srcFile);
      bis = new BufferedInputStream(fis);
      if (encoding == null) {
        instream = new InputStreamReader(bis);
      } else {
        instream = new InputStreamReader(bis, encoding);
      }
      String text = "";
      if (size != 0) {
        ChainReaderHelper crh = new ChainReaderHelper();
        crh.setBufferSize(size);
        crh.setPrimaryReader(instream);
        crh.setFilterChains(filterChains);
        crh.setProject(getProject());
        instream = crh.getAssembledReader();
        text = crh.readFully(instream);
      }
      if (text != null) {
        if (text.length() > 0) {
          getProject().setNewProperty(property, text);
          log(("loaded " + text.length()) + " characters", MSG_VERBOSE);
          log((property + " := ") + text, MSG_DEBUG);
        }
      }
    } catch (final IOException ioe) {
      final String message = "Unable to load file: " + ioe.toString();
      if (failOnError) {
        throw new BuildException(message, ioe, location);
      } else {
        log(message, MSG_ERR);
      }
    } catch (final BuildException be) {
      if (failOnError) {
        throw be;
      } else {
        log(be.getMessage(), MSG_ERR);
      }
    } finally {
      try {
        if (fis != null) {
          fis.close();
        }
      } catch (IOException ioex) {
      }
    }
  }
}
