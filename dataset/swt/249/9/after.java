class PlaceHold {
  void createButtonSelected(SelectionEvent event) {
    int style = SWT.NONE;
    if (okButton.getEnabled() && okButton.getSelection()) {
      style |= SWT.OK;
    }
    if (cancelButton.getEnabled() && cancelButton.getSelection()) {
      style |= SWT.CANCEL;
    }
    if (yesButton.getEnabled() && yesButton.getSelection()) {
      style |= SWT.YES;
    }
    if (noButton.getEnabled() && noButton.getSelection()) {
      style |= SWT.NO;
    }
    if (retryButton.getEnabled() && retryButton.getSelection()) {
      style |= SWT.RETRY;
    }
    if (abortButton.getEnabled() && abortButton.getSelection()) {
      style |= SWT.ABORT;
    }
    if (ignoreButton.getEnabled() && ignoreButton.getSelection()) {
      style |= SWT.IGNORE;
    }
    if (iconErrorButton.getEnabled() && iconErrorButton.getSelection()) {
      style |= SWT.ICON_ERROR;
    }
    if (iconInformationButton.getEnabled() && iconInformationButton.getSelection()) {
      style |= SWT.ICON_INFORMATION;
    }
    if (iconQuestionButton.getEnabled() && iconQuestionButton.getSelection()) {
      style |= SWT.ICON_QUESTION;
    }
    if (iconWarningButton.getEnabled() && iconWarningButton.getSelection()) {
      style |= SWT.ICON_WARNING;
    }
    if (iconWorkingButton.getEnabled() && iconWorkingButton.getSelection()) {
      style |= SWT.ICON_WORKING;
    }
    if (primaryModalButton.getEnabled() && primaryModalButton.getSelection()) {
      style |= SWT.PRIMARY_MODAL;
    }
    if (applicationModalButton.getEnabled() && applicationModalButton.getSelection()) {
      style |= SWT.APPLICATION_MODAL;
    }
    if (systemModalButton.getEnabled() && systemModalButton.getSelection()) {
      style |= SWT.SYSTEM_MODAL;
    }
    if (saveButton.getEnabled() && saveButton.getSelection()) {
      style |= SWT.SAVE;
    }
    if (openButton.getEnabled() && openButton.getSelection()) {
      style |= SWT.OPEN;
    }
    String name = dialogCombo.getText();
    Shell shell = tabFolderPage.getShell();
    if (name.equals(ControlExample.getResourceString("ColorDialog"))) {
      ColorDialog dialog = new ColorDialog(shell, style);
      dialog.setRGB(new RGB(100, 100, 100));
      dialog.setText(ControlExample.getResourceString("Title"));
      RGB result = dialog.open();
      textWidget.append(ControlExample.getResourceString("ColorDialog") + Text.DELIMITER);
      textWidget.append(
          (ControlExample.getResourceString("Result", new String[] {"" + result}) + Text.DELIMITER)
              + Text.DELIMITER);
      return;
    }
    if (name.equals(ControlExample.getResourceString("DirectoryDialog"))) {
      DirectoryDialog dialog = new DirectoryDialog(shell, style);
      dialog.setMessage(ControlExample.getResourceString("Example_string"));
      dialog.setText(ControlExample.getResourceString("Title"));
      String result = dialog.open();
      textWidget.append(ControlExample.getResourceString("DirectoryDialog") + Text.DELIMITER);
      textWidget.append(
          (ControlExample.getResourceString("Result", new String[] {"" + result}) + Text.DELIMITER)
              + Text.DELIMITER);
      return;
    }
    if (name.equals(ControlExample.getResourceString("FileDialog"))) {
      FileDialog dialog = new FileDialog(shell, style);
      dialog.setFileName(ControlExample.getResourceString("readme_txt"));
      dialog.setFilterNames(FilterNames);
      dialog.setFilterExtensions(FilterExtensions);
      dialog.setText(ControlExample.getResourceString("Title"));
      String result = dialog.open();
      textWidget.append(ControlExample.getResourceString("FileDialog") + Text.DELIMITER);
      textWidget.append(
          (ControlExample.getResourceString("Result", new String[] {"" + result}) + Text.DELIMITER)
              + Text.DELIMITER);
      return;
    }
    if (name.equals(ControlExample.getResourceString("FontDialog"))) {
      FontDialog dialog = new FontDialog(shell, style);
      dialog.setText(ControlExample.getResourceString("Title"));
      FontData result = dialog.open();
      textWidget.append(ControlExample.getResourceString("FontDialog") + Text.DELIMITER);
      textWidget.append(
          (ControlExample.getResourceString("Result", new String[] {"" + result}) + Text.DELIMITER)
              + Text.DELIMITER);
      return;
    }
    if (name.equals(ControlExample.getResourceString("PrintDialog"))) {
      PrintDialog dialog = new PrintDialog(shell, style);
      dialog.setText(ControlExample.getResourceString("Title"));
      PrinterData result = dialog.open();
      textWidget.append(ControlExample.getResourceString("PrintDialog") + Text.DELIMITER);
      textWidget.append(
          (ControlExample.getResourceString("Result", new String[] {"" + result}) + Text.DELIMITER)
              + Text.DELIMITER);
      return;
    }
    if (name.equals(ControlExample.getResourceString("MessageBox"))) {
      MessageBox dialog = new MessageBox(shell, style);
      dialog.setMessage(ControlExample.getResourceString("Example_string"));
      dialog.setText(ControlExample.getResourceString("Title"));
      int result = dialog.open();
      textWidget.append(ControlExample.getResourceString("MessageBox") + Text.DELIMITER);
      switch (result) {
        case SWT.OK:
          textWidget.append(ControlExample.getResourceString("Result", new String[] {"SWT.OK"}));
          break;
        case SWT.YES:
          textWidget.append(ControlExample.getResourceString("Result", new String[] {"SWT.YES"}));
          break;
        case SWT.NO:
          textWidget.append(ControlExample.getResourceString("Result", new String[] {"SWT.NO"}));
          break;
        case SWT.CANCEL:
          textWidget.append(
              ControlExample.getResourceString("Result", new String[] {"SWT.CANCEL"}));
          break;
        case SWT.ABORT:
          textWidget.append(ControlExample.getResourceString("Result", new String[] {"SWT.ABORT"}));
          break;
        case SWT.RETRY:
          textWidget.append(ControlExample.getResourceString("Result", new String[] {"SWT.RETRY"}));
          break;
        case SWT.IGNORE:
          textWidget.append(
              ControlExample.getResourceString("Result", new String[] {"SWT.IGNORE"}));
          break;
        default:
          textWidget.append(ControlExample.getResourceString("Result", new String[] {"" + result}));
          break;
      }
      textWidget.append(Text.DELIMITER + Text.DELIMITER);
    }
  }
}
