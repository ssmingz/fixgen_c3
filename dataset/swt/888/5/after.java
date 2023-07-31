class PlaceHold {
  public void setImage(Image image) {
    checkWidget();
    if ((style & SWT.SEPARATOR) != 0) {
      return;
    }
    super.setImage(image);
    if (imageHandle == 0) {
      return;
    }
    if (image != null) {
      ImageList imageList = parent.imageList;
      if (imageList == null) {
        imageList = parent.imageList = new ImageList();
      }
      int imageIndex = imageList.indexOf(image);
      if (imageIndex == (-1)) {
        imageIndex = imageList.add(image);
      }
      int pixbuf = imageList.getPixbuf(imageIndex);
      OS.gtk_image_set_from_pixbuf(imageHandle, pixbuf);
      OS.gtk_widget_show(imageHandle);
    } else {
      OS.gtk_image_set_from_pixbuf(imageHandle, 0);
      OS.gtk_widget_hide(imageHandle);
    }
    parent.relayout();
  }
}
