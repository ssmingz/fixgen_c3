class PlaceHold {
  public final void execute() throws BuildException {
    if (srcFile == null) {
      throw new BuildException("Source file not defined.");
    }
    if (!srcFile.exists()) {
      throw new BuildException("Source file does not exist.");
    }
    if (!srcFile.isFile()) {
      throw new BuildException("Source file is not a file.");
    }
    FileInputStream fis = null;
    BufferedInputStream bis = null;
    Reader instream = null;
    try {
      final long len = srcFile.length();
      final int size = ((int) (len));
      fis = new FileInputStream(srcFile);
      bis = new BufferedInputStream(fis);
      instream = new InputStreamReader(bis);
      ChainReaderHelper crh = new ChainReaderHelper();
      crh.setBufferSize(size);
      crh.setPrimaryReader(instream);
      crh.setFilterChains(filterChains);
      instream = crh.getAssembledReader();
      String text = crh.readFully(instream);
      if (text != null) {
        if (!text.endsWith("\n")) {
          text = text + "\n";
        }
        final StringInputStream sis = new StringInputStream(text);
        final Properties props = new Properties();
        props.load(sis);
        final Enumeration e = props.keys();
        while (e.hasMoreElements()) {
          final String key = ((String) (e.nextElement()));
          final String value = props.getProperty(key);
          if (((key != null) && (value != null)) && (value.trim().length() > 0)) {
            project.setNewProperty(key, value);
          }
        }
        sis.close();
      }
    } catch (final IOException ioe) {
      final String message = "Unable to load file: " + ioe.toString();
      throw new BuildException(message, ioe, location);
    } catch (final BuildException be) {
      throw be;
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
