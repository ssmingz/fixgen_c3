class PlaceHold {
  private void _saveAllBeforeProceeding(String message, BooleanOption option, String checkMsg) {
    if (_model.hasModifiedDocuments()) {
      if (!DrJava.getConfig().getSetting(option).booleanValue()) {
        ConfirmCheckBoxDialog dialog =
            new ConfirmCheckBoxDialog(this, "Must Save All Files to Continue", message, checkMsg);
        int rc = dialog.show();
        switch (rc) {
          case JOptionPane.YES_OPTION:
            _saveAll();
            if (dialog.getCheckBoxValue()) {
              DrJava.getConfig().setSetting(option, Boolean.TRUE);
            }
            break;
          case JOptionPane.NO_OPTION:
          case JOptionPane.CANCEL_OPTION:
          case JOptionPane.CLOSED_OPTION:
            break;
          default:
            throw new RuntimeException("Invalid rc from showConfirmDialog: " + rc);
        }
      } else {
        _saveAll();
      }
    }
  }
}
