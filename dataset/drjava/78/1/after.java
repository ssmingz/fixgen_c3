class PlaceHold {
  private void _storePositionInfo() {
    Configuration config = DrJava.getConfig();
    if (config.getSetting(WINDOW_STORE_POSITION).booleanValue()) {
      Rectangle bounds = getBounds();
      config.setSetting(WINDOW_HEIGHT, new Integer(bounds.height));
      config.setSetting(WINDOW_WIDTH, new Integer(bounds.width));
      config.setSetting(WINDOW_X, new Integer(bounds.x));
      config.setSetting(WINDOW_Y, new Integer(bounds.y));
    } else {
      config.setSetting(WINDOW_HEIGHT, WINDOW_HEIGHT.getDefault());
      config.setSetting(WINDOW_WIDTH, WINDOW_WIDTH.getDefault());
      config.setSetting(WINDOW_X, WINDOW_X.getDefault());
      config.setSetting(WINDOW_Y, WINDOW_Y.getDefault());
    }
    if ((DrJava.getConfig().getSetting(DIALOG_GOTOFILE_STORE_POSITION).booleanValue()
            && (_gotoFileDialog != null))
        && (_gotoFileDialog.getFrameState() != null)) {
      config.setSetting(DIALOG_GOTOFILE_STATE, _gotoFileDialog.getFrameState().toString());
    } else {
      config.setSetting(DIALOG_GOTOFILE_STATE, DIALOG_GOTOFILE_STATE.getDefault());
    }
    if ((DrJava.getConfig().getSetting(DIALOG_COMPLETE_FILE_STORE_POSITION).booleanValue()
            && (_completeFileDialog != null))
        && (_completeFileDialog.getFrameState() != null)) {
      config.setSetting(DIALOG_COMPLETE_FILE_STATE, _completeFileDialog.getFrameState().toString());
    } else {
      config.setSetting(DIALOG_COMPLETE_FILE_STATE, DIALOG_COMPLETE_FILE_STATE.getDefault());
    }
    if ((DrJava.getConfig().getSetting(DIALOG_JAROPTIONS_STORE_POSITION).booleanValue()
            && (_jarOptionsDialog != null))
        && (_jarOptionsDialog.getFrameState() != null)) {
      config.setSetting(DIALOG_JAROPTIONS_STATE, _jarOptionsDialog.getFrameState().toString());
    } else {
      config.setSetting(DIALOG_JAROPTIONS_STATE, DIALOG_JAROPTIONS_STATE.getDefault());
    }
    if (_debugPanel != null) {
      config.setSetting(DEBUG_PANEL_HEIGHT, new Integer(_debugPanel.getHeight()));
    }
    config.setSetting(DOC_LIST_WIDTH, new Integer(_docSplitPane.getDividerLocation()));
  }
}
