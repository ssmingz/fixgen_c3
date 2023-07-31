class PlaceHold {
  public Image getSystemImage(int id) {
    checkDevice();
    int image = 0;
    switch (id) {
      case SWT.ICON_ERROR:
        {
          if (errorImage == 0) {
            errorImage = OS.LoadImage(0, OS.MAKEINTRESOURCE(OIC_HAND), IMAGE_ICON, 0, 0, LR_SHARED);
          }
          image = errorImage;
          break;
        }
      case SWT.ICON_INFORMATION:
      case SWT.ICON_WORKING:
        {
          if (infoImage == 0) {
            infoImage =
                OS.LoadImage(0, OS.MAKEINTRESOURCE(OIC_INFORMATION), IMAGE_ICON, 0, 0, LR_SHARED);
          }
          image = infoImage;
          break;
        }
      case SWT.ICON_QUESTION:
        {
          if (questionImage == 0) {
            questionImage =
                OS.LoadImage(0, OS.MAKEINTRESOURCE(OIC_QUES), IMAGE_ICON, 0, 0, LR_SHARED);
          }
          image = questionImage;
          break;
        }
      case SWT.ICON_WARNING:
        {
          if (warningImage == 0) {
            warningImage =
                OS.LoadImage(0, OS.MAKEINTRESOURCE(OIC_BANG), IMAGE_ICON, 0, 0, LR_SHARED);
          }
          image = warningImage;
          break;
        }
      default:
        return null;
    }
    if (image == 0) {
      error(ERROR_NO_HANDLES);
    }
    return Image.win32_new(this, ICON, image);
  }
}
