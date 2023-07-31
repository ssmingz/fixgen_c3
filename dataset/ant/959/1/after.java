class PlaceHold {
  private void addResource(
      Resource r,
      String name,
      String prefix,
      ZipOutputStream zOut,
      int mode,
      ZipFile zf,
      File fromArchive)
      throws IOException {
    if (zf != null) {
      ZipEntry ze = zf.getEntry(r.getName());
      if (ze != null) {
        boolean oldCompress = doCompress;
        if (keepCompression) {
          doCompress = ze.getMethod() == ZipEntry.DEFLATED;
        }
        InputStream is = null;
        try {
          is = zf.getInputStream(ze);
          zipFile(
              is, zOut, prefix + name, ze.getTime(), fromArchive, mode, ze.getExtraFields(true));
        } finally {
          doCompress = oldCompress;
          FileUtils.close(is);
        }
      }
    } else {
      InputStream is = null;
      try {
        is = r.getInputStream();
        zipFile(
            is,
            zOut,
            prefix + name,
            r.getLastModified(),
            fromArchive,
            mode,
            r instanceof ZipResource ? ((ZipResource) (r)).getExtraFields() : null);
      } finally {
        FileUtils.close(is);
      }
    }
  }
}
