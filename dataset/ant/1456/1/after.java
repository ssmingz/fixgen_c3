class PlaceHold {
  @Override
  public long getSize() {
    if (isExists()) {
      InputStream in = null;
      try {
        in = getInputStream();
        final byte[] buf = new byte[BUFFER_SIZE];
        int size = 0;
        int readNow;
        while ((readNow = in.read(buf, 0, buf.length)) > 0) {
          size += readNow;
        }
        return size;
      } catch (final IOException ex) {
        throw new BuildException("caught exception while reading " + getName(), ex);
      } finally {
        FileUtils.close(in);
      }
    } else {
      return 0;
    }
  }
}
