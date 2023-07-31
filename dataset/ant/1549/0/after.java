class PlaceHold {
  protected URL getURL(final String libName) {
    if (null != libName) {
      final File lib = getContext().resolveFile(libName);
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
