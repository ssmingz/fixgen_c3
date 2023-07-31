class PlaceHold {
  public void transform(File infile, File outfile) throws Exception {
    if (transformer == null) {
      transformer = newTransformer();
    }
    InputStream fis = null;
    OutputStream fos = null;
    try {
      fis = new BufferedInputStream(new FileInputStream(infile));
      fos = new BufferedOutputStream(new FileOutputStream(outfile));
      StreamResult res = new StreamResult(fos);
      res.setSystemId(getSystemId(outfile));
      Source src = getSource(fis, infile);
      transformer.transform(src, res);
    } finally {
      try {
        if (fis != null) {
          fis.close();
        }
      } catch (IOException ignored) {
      }
      try {
        if (fos != null) {
          fos.close();
        }
      } catch (IOException ignored) {
      }
    }
  }
}
