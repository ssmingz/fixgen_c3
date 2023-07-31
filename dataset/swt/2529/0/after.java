class PlaceHold {
  void menuPrint() {
    if (image == null) {
      return;
    }
    try {
      PrintDialog dialog = new PrintDialog(shell, SWT.NONE);
      PrinterData printerData = dialog.open();
      if (printerData == null) {
        return;
      }
      Printer printer = new Printer(printerData);
      Point screenDPI = display.getDPI();
      Point printerDPI = printer.getDPI();
      int scaleFactor = printerDPI.x / screenDPI.x;
      Rectangle trim = printer.computeTrim(0, 0, 0, 0);
      if (printer.startJob(currentName)) {
        if (printer.startPage()) {
          GC gc = new GC(printer);
          int transparentPixel = imageData.transparentPixel;
          if ((transparentPixel != (-1)) && (!transparent)) {
            imageData.transparentPixel = -1;
          }
          Image printerImage = new Image(printer, imageData);
          gc.drawImage(
              printerImage,
              0,
              0,
              imageData.width,
              imageData.height,
              -trim.x,
              -trim.y,
              scaleFactor * imageData.width,
              scaleFactor * imageData.height);
          if ((transparentPixel != (-1)) && (!transparent)) {
            imageData.transparentPixel = transparentPixel;
          }
          printerImage.dispose();
          gc.dispose();
          printer.endPage();
        }
        printer.endJob();
      }
      printer.dispose();
    } catch (SWTError e) {
      MessageBox box = new MessageBox(shell, SWT.ICON_ERROR);
      box.setMessage(bundle.getString("Printing_error") + e.getMessage());
      box.open();
    }
  }
}
