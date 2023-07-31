class PlaceHold {
  public void setImage(Image image) {
    checkWidget();
    if ((style & SWT.SEPARATOR) != 0) {
      return;
    }
    super.setImage(image);
    if (((parent.style & SWT.FLAT) != 0) && (hotImage != null)) {
      return;
    }
    int imageHandle = 0;
    int type = OS.Pt_Z_STRING;
    if (image != null) {
      imageHandle = copyPhImage(image.handle);
      int[] args = new int[] {OS.Pt_ARG_TEXT_STRING, 0, 0};
      OS.PtGetResources(button, args.length / 3, args);
      if ((args[1] != 0) && (OS.strlen(args[1]) > 0)) {
        type = OS.Pt_TEXT_IMAGE;
      } else {
        type = OS.Pt_IMAGE;
      }
    }
    int[] args = new int[] {OS.Pt_ARG_LABEL_IMAGE, imageHandle, 0, OS.Pt_ARG_LABEL_TYPE, type, 0};
    OS.PtSetResources(button, args.length / 3, args);
    if (imageHandle != 0) {
      OS.free(imageHandle);
    }
    if ((style & SWT.DROP_DOWN) != 0) {
      if (OS.PtWidgetIsRealized(handle)) {
        OS.PtExtentWidget(handle);
      }
    }
  }
}
