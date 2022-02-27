package com.tanvoid0.tanspring.core.diff;

public enum DiffState {
  ADDED("The value has been added to the working object"),
  CHANGED("The value exists but differs between the base and working object"),
  REMOVED("The value has been removed from the working object"),
  UNTOUCHED("The value is identical in the working and base object"),
  CIRCULAR("Special state to mark circular references"),
  IGNORED("The value has not been looked at and has been ignored"),
  INACCESSIBLE("WHen a comparison was not possible because the underlying value was not accessible");

  private final String reason;

  DiffState(final String reason) {
    this.reason = reason;
  }

  public String getReason() {
    return reason;
  }
}
