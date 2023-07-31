class PlaceHold {
  void menuSave() {
    if (image == null) {
      return;
    }
    animate = false;
    if ((imageData.type == SWT.IMAGE_UNDEFINED) || (fileName == null)) {
      menuSaveAs();
      return;
    }
    Cursor waitCursor = new Cursor(display, SWT.CURSOR_WAIT);
    shell.setCursor(waitCursor);
    imageCanvas.setCursor(waitCursor);
    try {
      loader.data = new ImageData[] {imageData};
      loader.save(fileName, imageData.type);
    } catch (SWTException e) {
      showErrorDialog(bundle.getString("Saving_lc"), fileName, e);
    } catch (SWTError e) {
      showErrorDialog(bundle.getString("Saving_lc"), fileName, e);
    } finally {
      shell.setCursor(null);
      imageCanvas.setCursor(crossCursor);
      waitCursor.dispose();
    }
  }
}
