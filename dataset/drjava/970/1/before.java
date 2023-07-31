class PlaceHold {
  private void _displayPage(URL url) {
    try {
      _mainDocPane.setPage(url);
      if (_baseURL != null) {
        ((HTMLDocument) (_contentsDocPane.getDocument())).setBase(_baseURL);
      }
      _lastURL = url;
    } catch (IOException ioe) {
      String path = url.getPath();
      try {
        URL newURL = new URL(_baseURL + path);
        _mainDocPane.setPage(newURL);
        if (_baseURL != null) {
          ((HTMLDocument) (_contentsDocPane.getDocument())).setBase(_baseURL);
        }
        _lastURL = newURL;
      } catch (IOException ioe2) {
        _displayMainError(url, ioe2);
      }
    }
  }
}
