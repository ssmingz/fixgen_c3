class PlaceHold {
  public final void execute() throws BuildException {
    if (src == null) {
      throw new BuildException("A source resource is required.");
    }
    if (!src.isExists()) {
      if (src instanceof JavaResource) {
        log("Unable to find resource " + src, MSG_WARN);
        return;
      }
      throw new BuildException("Source resource does not exist: " + src);
    }
    BufferedInputStream bis = null;
    Reader instream = null;
    ByteArrayInputStream tis = null;
    try {
      bis = new BufferedInputStream(src.getInputStream());
      if (encoding == null) {
        instream = new InputStreamReader(bis);
      } else {
        instream = new InputStreamReader(bis, encoding);
      }
      ChainReaderHelper crh = new ChainReaderHelper();
      crh.setPrimaryReader(instream);
      crh.setFilterChains(filterChains);
      crh.setProject(getProject());
      instream = crh.getAssembledReader();
      String text = crh.readFully(instream);
      if ((text != null) && (text.length() != 0)) {
        if (!text.endsWith("\n")) {
          text = text + "\n";
        }
        if (encoding == null) {
          tis = new ByteArrayInputStream(text.getBytes());
        } else {
          tis = new ByteArrayInputStream(text.getBytes(encoding));
        }
        final Properties props = new Properties();
        props.load(tis);
        Property propertyTask = new Property();
        propertyTask.bindToOwner(this);
        propertyTask.addProperties(props);
      }
    } catch (final IOException ioe) {
      throw new BuildException("Unable to load file: " + ioe, ioe, getLocation());
    } finally {
      FileUtils.close(bis);
      FileUtils.close(tis);
    }
  }
}
