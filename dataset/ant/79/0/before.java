class PlaceHold{
public boolean isValid() {
    return "CRC".equalsIgnoreCase(algorithm) || "ADLER".equalsIgnoreCase(algorithm);
}
}