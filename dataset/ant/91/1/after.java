class PlaceHold{
private void sanitizeText() {
    if (textBuffer != null) {
        if (textBuffer.substring(0).trim().length() == 0) {
            textBuffer = null;
        }
    }
}
}