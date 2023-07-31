class PlaceHold {
  public Image getSystemImage(int style) {
    checkDevice();
    int image = 0;
    int mask = 0;
    switch (style) {
      case SWT.ICON_ERROR:
        {
          if (errorImage == 0) {
            errorImage = createImage("xm_error");
            errorMask = createMask(errorImage);
          }
          image = errorImage;
          mask = errorMask;
          break;
        }
      case SWT.ICON_INFORMATION:
        {
          if (infoImage == 0) {
            infoImage = createImage("xm_information");
            infoMask = createMask(infoImage);
          }
          image = infoImage;
          mask = infoMask;
          break;
        }
      case SWT.ICON_QUESTION:
        {
          if (questionImage == 0) {
            questionImage = createImage("xm_question");
            questionMask = createMask(questionImage);
          }
          image = questionImage;
          mask = questionMask;
          break;
        }
      case SWT.ICON_WARNING:
        {
          if (warningImage == 0) {
            warningImage = createImage("xm_warning");
            warningMask = createMask(warningImage);
          }
          image = warningImage;
          mask = warningMask;
          break;
        }
      case SWT.ICON_WORKING:
        {
          if (workingImage == 0) {
            workingImage = createImage("xm_working");
            workingMask = createMask(workingImage);
          }
          image = workingImage;
          mask = workingMask;
          break;
        }
      default:
        return null;
    }
    return Image.motif_new(this, ICON, image, mask);
  }
}
