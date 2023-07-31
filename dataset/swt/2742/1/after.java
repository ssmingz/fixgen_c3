class PlaceHold {
  public void setImage(Image image) {
    checkWidget();
    if ((style & SWT.ARROW) != 0) {
      return;
    }
    if ((image != null) && image.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    if (cIcon != 0) {
      destroyCIcon(cIcon);
      cIcon = 0;
    }
    this.image = image;
    isImage = true;
    if (image == null) {
      setText(text);
      return;
    }
    if (text.length() > 0) {
      char[] buffer = new char[] {' '};
      int ptr = OS.CFStringCreateWithCharacters(kCFAllocatorDefault, buffer, buffer.length);
      if (ptr == 0) {
        error(ERROR_CANNOT_SET_TEXT);
      }
      OS.SetControlTitleWithCFString(handle, ptr);
      OS.CFRelease(ptr);
    }
    cIcon = createCIcon(image);
    ControlButtonContentInfo inContent = new ControlButtonContentInfo();
    inContent.contentType = ((short) (OS.kControlContentCIconHandle));
    inContent.iconRef = cIcon;
    OS.SetBevelButtonContentInfo(handle, inContent);
    redraw();
  }
}
