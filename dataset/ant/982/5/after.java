class PlaceHold {
  protected void extract() {
    if (source.lastModified() > dest.lastModified()) {
      log((("Expanding " + source.getAbsolutePath()) + " to ") + dest.getAbsolutePath());
      FileOutputStream out = null;
      CBZip2InputStream zIn = null;
      InputStream fis = null;
      BufferedInputStream bis = null;
      try {
        out = new FileOutputStream(dest);
        fis = srcResource.getInputStream();
        bis = new BufferedInputStream(fis);
        int b = bis.read();
        if (b != 'B') {
          throw new BuildException("Invalid bz2 file.", getLocation());
        }
        b = bis.read();
        if (b != 'Z') {
          throw new BuildException("Invalid bz2 file.", getLocation());
        }
        zIn = new CBZip2InputStream(bis);
        byte[] buffer = new byte[BUFFER_SIZE];
        int count = 0;
        do {
          out.write(buffer, 0, count);
          count = zIn.read(buffer, 0, buffer.length);
        } while (count != (-1));
      } catch (IOException ioe) {
        String msg = "Problem expanding bzip2 " + ioe.getMessage();
        throw new BuildException(msg, ioe, getLocation());
      } finally {
        FileUtils.close(bis);
        FileUtils.close(fis);
        FileUtils.close(out);
        FileUtils.close(zIn);
      }
    }
  }
}
