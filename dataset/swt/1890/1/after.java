class PlaceHold {
  void createExampleWidgets() {
    int style = getDefaultStyle();
    if (borderButton.getSelection()) {
      style |= SWT.BORDER;
    }
    if (mozillaButton.getSelection()) {
      style |= SWT.MOZILLA;
    }
    try {
      browser = new Browser(browserGroup, style);
    } catch (SWTError e) {
      try {
        browser = new Browser(browserGroup, style & (~SWT.MOZILLA));
      } catch (SWTError e2) {
        errorMessage = e.getMessage();
        return;
      }
      MessageBox dialog = new MessageBox(shell, SWT.ICON_WARNING | SWT.OK);
      dialog.setMessage(
          ControlExample.getResourceString("MozillaNotFound", new String[] {e.getMessage()}));
      dialog.open();
    }
    InputStream htmlStream = ControlExample.class.getResourceAsStream("browser-content.html");
    BufferedReader br = new BufferedReader(new InputStreamReader(htmlStream));
    StringBuffer sb = new StringBuffer(300);
    try {
      int read = 0;
      while ((read = br.read()) != (-1)) {
        sb.append(((char) (read)));
      }
    } catch (IOException e) {
      log(e.getMessage());
    } finally {
      try {
        br.close();
      } catch (IOException e) {
        log(e.getMessage());
      }
    }
    String text = sb.toString();
    browser.setText(text);
  }
}
