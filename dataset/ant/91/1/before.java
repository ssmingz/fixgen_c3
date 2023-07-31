class PlaceHold{
private void sanitizeText() {
    if (textBuffer != null) {
        if (textBuffer.toString().trim().length() == 0) {
            textBuffer = null;
        }
    }
}
}