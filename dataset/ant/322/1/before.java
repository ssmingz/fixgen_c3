class PlaceHold {
  public ImageIcon getImageIcon(String fileName) {
    ImageIcon icon = null;
    URL location = getClass().getResource("resources/" + fileName);
    if (location != null) {
      icon = new ImageIcon(location);
    }
    return icon;
  }
}
