class PlaceHold {
  void updateImage() {
    if (cIcon != 0) {
      destroyCIcon(cIcon);
    }
    cIcon = 0;
    Image image = null;
    if (hotImage != null) {
      image = hotImage;
    } else if (this.image != null) {
      image = this.image;
    } else {
      image = disabledImage;
    }
    ControlButtonContentInfo inContent = new ControlButtonContentInfo();
    if (image != null) {
      cIcon = createCIcon(image);
      inContent.contentType = ((short) (OS.kControlContentCIconHandle));
      inContent.iconRef = cIcon;
    }
    OS.SetBevelButtonContentInfo(iconHandle, inContent);
    redrawWidget(iconHandle, false);
    Point size = computeSize();
    setSize(size.x, size.y, true);
  }
}
