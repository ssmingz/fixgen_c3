class PlaceHold {
  public final void execute() throws BuildException {
    if (src == null) {
      throw new BuildException("source resource not defined");
    }
    if (property == null) {
      throw new BuildException("output property not defined");
    }
    if (!src.isExists()) {
      String message = src + " doesn't exist";
      if (failOnError) {
        throw new BuildException(message);
      } else {
        log(message, MSG_ERR);
        return;
      }
    }
    InputStream is = null;
    BufferedInputStream bis = null;
    Reader instream = null;
    log((("loading " + src) + " into property ") + property, MSG_VERBOSE);
    try {
      final long len = src.getSize();
      log(
          "resource size = " + (len != Resource.UNKNOWN_SIZE ? String.valueOf(len) : "unknown"),
          MSG_DEBUG);
      final int size = ((int) (len));
      is = src.getInputStream();
      bis = new BufferedInputStream(is);
      if (encoding == null) {
        instream = new InputStreamReader(bis);
      } else {
        instream = new InputStreamReader(bis, encoding);
      }
      String text = "";
      if (size != 0) {
        ChainReaderHelper crh = new ChainReaderHelper();
        if (len != Resource.UNKNOWN_SIZE) {
          crh.setBufferSize(size);
        }
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
      final String message = "Unable to load resource: " + ioe.toString();
      if (failOnError) {
        throw new BuildException(message, ioe, getLocation());
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
      FileUtils.close(is);
    }
  }
}
