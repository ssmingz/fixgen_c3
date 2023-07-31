class PlaceHold {
  public ImageIcon getImageIcon(String fileName) {
    if (fileName == null) {
      return null;
    }
    ImageIcon icon = null;
    URL location = getClass().getResource("resources/" + fileName);
    if (location != null) {
      icon = new ImageIcon(location);
    }
    return icon;
  }
}
