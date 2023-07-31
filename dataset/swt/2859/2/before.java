class PlaceHold {
  void menuOpenFile() {
    animate = false;
    resetScaleCombos();
    FileDialog fileChooser = new FileDialog(shell, SWT.OPEN);
    if (lastPath != null) {
      fileChooser.setFilterPath(lastPath);
    }
    fileChooser.setFilterExtensions(
        new String[] {
          "*.bmp; *.gif; *.ico; *.jpg; *.pcx; *.png; *.tif",
          "*.bmp",
          "*.gif",
          "*.ico",
          "*.jpg",
          "*.pcx",
          "*.png",
          "*.tif"
        });
    fileChooser.setFilterNames(
        new String[] {
          bundle.getString("All_images") + " (bmp, gif, ico, jpg, pcx, png, tif)",
          "BMP (*.bmp)",
          "GIF (*.gif)",
          "ICO (*.ico)",
          "JPEG (*.jpg)",
          "PCX (*.pcx)",
          "PNG (*.png)",
          "TIFF (*.tif)"
        });
    String filename = fileChooser.open();
    lastPath = fileChooser.getFilterPath();
    if (filename == null) {
      return;
    }
    Cursor waitCursor = new Cursor(display, SWT.CURSOR_WAIT);
    shell.setCursor(waitCursor);
    imageCanvas.setCursor(waitCursor);
    try {
      loader = new ImageLoader();
      if (incremental) {
        loader.addImageLoaderListener(
            new ImageLoaderListener() {
              public void imageDataLoaded(ImageLoaderEvent event) {
                incrementalDataLoaded(event);
              }
            });
        incrementalThreadStart();
      }
      long startTime = System.currentTimeMillis();
      imageDataArray = loader.load(filename);
      loadTime = System.currentTimeMillis() - startTime;
      if (imageDataArray.length > 0) {
        currentName = filename;
        fileName = filename;
        previousButton.setEnabled(imageDataArray.length > 1);
        nextButton.setEnabled(imageDataArray.length > 1);
        animateButton.setEnabled(
            ((imageDataArray.length > 1) && (loader.logicalScreenWidth > 0))
                && (loader.logicalScreenHeight > 0));
        imageDataIndex = 0;
        displayImage(imageDataArray[imageDataIndex]);
        resetScrollBars();
      }
    } catch (SWTException e) {
      showErrorDialog(bundle.getString("Loading_lc"), filename, e);
    } finally {
      shell.setCursor(null);
      imageCanvas.setCursor(crossCursor);
      waitCursor.dispose();
    }
  }
}
