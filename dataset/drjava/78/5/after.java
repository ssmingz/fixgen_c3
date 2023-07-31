class PlaceHold {
  private void _storePositionInfo() {
    assert EventQueue.isDispatchThread();
    Configuration config = DrJava.getConfig();
    if (config.getSetting(WINDOW_STORE_POSITION).booleanValue()) {
      Rectangle bounds = getBounds();
      config.setSetting(WINDOW_HEIGHT, Integer.valueOf(bounds.height));
      config.setSetting(WINDOW_WIDTH, Integer.valueOf(bounds.width));
      config.setSetting(WINDOW_X, Integer.valueOf(bounds.x));
      config.setSetting(WINDOW_Y, Integer.valueOf(bounds.y));
      config.setSetting(WINDOW_STATE, Integer.valueOf(getExtendedState()));
    } else {
      config.setSetting(WINDOW_HEIGHT, WINDOW_HEIGHT.getDefault());
      config.setSetting(WINDOW_WIDTH, WINDOW_WIDTH.getDefault());
      config.setSetting(WINDOW_X, WINDOW_X.getDefault());
      config.setSetting(WINDOW_Y, WINDOW_Y.getDefault());
      config.setSetting(WINDOW_STATE, WINDOW_STATE.getDefault());
    }
    if ((DrJava.getConfig().getSetting(DIALOG_GOTOFILE_STORE_POSITION).booleanValue()
            && (_gotoFileDialog != null))
        && (_gotoFileDialog.getFrameState() != null)) {
      config.setSetting(DIALOG_GOTOFILE_STATE, _gotoFileDialog.getFrameState().toString());
    } else {
      config.setSetting(DIALOG_GOTOFILE_STATE, DIALOG_GOTOFILE_STATE.getDefault());
    }
    if ((DrJava.getConfig().getSetting(DIALOG_OPENJAVADOC_STORE_POSITION).booleanValue()
            && (_openJavadocDialog != null))
        && (_openJavadocDialog.getFrameState() != null)) {
      config.setSetting(DIALOG_OPENJAVADOC_STATE, _openJavadocDialog.getFrameState().toString());
    } else {
      config.setSetting(DIALOG_OPENJAVADOC_STATE, DIALOG_OPENJAVADOC_STATE.getDefault());
    }
    if ((DrJava.getConfig().getSetting(DIALOG_COMPLETE_WORD_STORE_POSITION).booleanValue()
            && (_completeWordDialog != null))
        && (_completeWordDialog.getFrameState() != null)) {
      config.setSetting(DIALOG_COMPLETE_WORD_STATE, _completeWordDialog.getFrameState().toString());
    } else {
      config.setSetting(DIALOG_COMPLETE_WORD_STATE, DIALOG_COMPLETE_WORD_STATE.getDefault());
    }
    if ((DrJava.getConfig().getSetting(DIALOG_JAROPTIONS_STORE_POSITION).booleanValue()
            && (_jarOptionsDialog != null))
        && (_jarOptionsDialog.getFrameState() != null)) {
      config.setSetting(DIALOG_JAROPTIONS_STATE, _jarOptionsDialog.getFrameState().toString());
    } else {
      config.setSetting(DIALOG_JAROPTIONS_STATE, DIALOG_JAROPTIONS_STATE.getDefault());
    }
    if ((DrJava.getConfig().getSetting(DIALOG_TABBEDPANES_STORE_POSITION).booleanValue()
            && (_tabbedPanesFrame != null))
        && (_tabbedPanesFrame.getFrameState() != null)) {
      config.setSetting(DIALOG_TABBEDPANES_STATE, _tabbedPanesFrame.getFrameState().toString());
    } else {
      config.setSetting(DIALOG_TABBEDPANES_STATE, DIALOG_TABBEDPANES_STATE.getDefault());
    }
    if ((DrJava.getConfig().getSetting(DIALOG_DEBUGFRAME_STORE_POSITION).booleanValue()
            && (_debugFrame != null))
        && (_debugFrame.getFrameState() != null)) {
      config.setSetting(DIALOG_DEBUGFRAME_STATE, _debugFrame.getFrameState().toString());
    } else {
      config.setSetting(DIALOG_DEBUGFRAME_STATE, DIALOG_DEBUGFRAME_STATE.getDefault());
    }
    if (_showDebugger) {
      config.setSetting(DEBUG_PANEL_HEIGHT, Integer.valueOf(_debugPanel.getHeight()));
    }
    config.setSetting(DOC_LIST_WIDTH, Integer.valueOf(_docSplitPane.getDividerLocation()));
  }
}
