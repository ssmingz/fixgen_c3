class PlaceHold {
  protected URL getURL(final String libName) {
    if (null != libName) {
      final File lib = new File(getContext().resolveFilename(libName));
      try {
        return lib.toURL();
      } catch (final MalformedURLException mue) {
        throw new AntException("Malformed task-lib parameter " + m_lib, mue);
      }
    } else {
      return null;
    }
  }
}
