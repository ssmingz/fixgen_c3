class PlaceHold {
  public Runnable print(Printer printer) {
    checkWidget();
    if (printer == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    return new StyledTextPrinter(this, printer);
  }
}
