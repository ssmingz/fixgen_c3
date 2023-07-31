class BrowserFunction {
  public BrowserFunction(Browser browser, String name) {
    super();
    if (browser == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (name == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (browser.isDisposed()) {
      SWT.error(ERROR_WIDGET_DISPOSED);
    }
    this.browser = browser;
    this.name = name;
    browser.webBrowser.addFunction(this);
  }
}
