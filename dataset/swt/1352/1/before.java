class PlaceHold {
  public ImageData getImageData() {
    int nIconIndex = 0;
    String fileName = iconName;
    int index = iconName.indexOf(',');
    if (index != (-1)) {
      fileName = iconName.substring(0, index);
      String iconIndex = iconName.substring(index + 1, iconName.length()).trim();
      try {
        nIconIndex = Integer.parseInt(iconIndex);
      } catch (NumberFormatException e) {
      }
    }
    byte[] lpszFile = Converter.wcsToMbcs(0, fileName, true);
    int[] phiconSmall = new int[1];
    int[] phiconLarge = null;
    OS.ExtractIconEx(lpszFile, nIconIndex, phiconLarge, phiconSmall, 1);
    if (phiconSmall[0] == 0) {
      return null;
    }
    Image image = Image.win32_new(null, ICON, phiconSmall[0]);
    ImageData imageData = image.getImageData();
    image.dispose();
    return imageData;
  }
}
