class PlaceHold {
  String getLabel(int buttonFlag, int index, int buttonTitle) {
    String label = null;
    int flag = (buttonFlag & (0xff * index)) / index;
    switch (flag) {
      case nsIPromptService.BUTTON_TITLE_CANCEL:
        label = SWT.getMessage("SWT_Cancel");
        break;
      case nsIPromptService.BUTTON_TITLE_NO:
        label = SWT.getMessage("SWT_No");
        break;
      case nsIPromptService.BUTTON_TITLE_OK:
        label = SWT.getMessage("SWT_OK");
        break;
      case nsIPromptService.BUTTON_TITLE_SAVE:
        label = SWT.getMessage("SWT_Save");
        break;
      case nsIPromptService.BUTTON_TITLE_YES:
        label = SWT.getMessage("SWT_Yes");
        break;
      case nsIPromptService.BUTTON_TITLE_IS_STRING:
        {
          int length = XPCOM.nsCRT_strlen_PRUnichar(buttonTitle);
          char[] dest = new char[length];
          XPCOM.memmove(dest, buttonTitle, length * 2);
          label = new String(dest);
        }
    }
    return label;
  }
}
